package railIl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Support {

	public static ArrayList<Line> readAllLines() throws FileNotFoundException {

		ArrayList<Line> allLines = new ArrayList<>();
		ArrayList<Route> routs = new ArrayList<Route>();
		String departurePlace = null, arrivalPlace = null;
		int departureHour = 0, departureMinutes = 0, arrivalHour = 0, arrivalMinutes;
		File myObj = new File("AllLines.txt");
		Scanner myReader = new Scanner(myObj);
		String line;
		while (myReader.hasNextLine()) {
			routs.clear();
			while (!(line = myReader.nextLine()).equals("new line")) {
				departurePlace = line;
				departureHour = Integer.parseInt(myReader.nextLine());
				departureMinutes = Integer.parseInt(myReader.nextLine());
				arrivalPlace = myReader.nextLine();
				arrivalHour = Integer.parseInt(myReader.nextLine());
				arrivalMinutes = Integer.parseInt(myReader.nextLine());
				routs.add(new Route(LocalTime.of(departureHour, departureMinutes),
						LocalTime.of(arrivalHour, arrivalMinutes), departurePlace, arrivalPlace));
			}
			allLines.add(new Line(new ArrayList<Route>(routs)));
		}
		myReader.close();
		return allLines;
	}

	public static ArrayList<Line> DuplicateLineByFrequency(ArrayList<Line> allLines, int hourDelay, int minutesDelay) {
		while ((allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour() + hourDelay)
				+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getHour()
						- allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour()) < 23
				&& (allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute() + hourDelay)
						+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getMinute() - allLines
								.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute()) < 59) {
			allLines.add(new Line(allLines.get(allLines.size() - 1).getAllStops(), hourDelay, minutesDelay));
		}
		return allLines;
	}

	public static ArrayList<Line> bubblesortByTime(ArrayList<Line> allLines) {
		int i, j;
		Line temp;
		boolean swapped;
		for (i = 0; i < allLines.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < allLines.size() - i - 1; j++) {
				if (allLines.get(j).getAllStops().get(0).getDepartureTime()
						.isAfter(allLines.get(j + 1).getAllStops().get(0).getDepartureTime())) {
					temp = new Line(allLines.get(j));
					allLines.set(j, allLines.get(j + 1));
					allLines.set(j + 1, temp);
					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
		return allLines;
	}

	public static String getAllLinesDetails(ArrayList<Line> allLines) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < allLines.size(); i++) {
			str.append(allLines.get(i).toString() + "\n\n");
		}
		return str.toString();
	}

	public static void writeToFile(ArrayList<Line> allLines) throws FileNotFoundException {
		File fr = new File("AllLines.txt");
		PrintWriter outputStream = new PrintWriter(fr);
		for (Line i : allLines) {
			System.out.println("\n");
			for (Route j : i.getAllStops()) {
				outputStream.append(j.getDeparturePlace() + "\n");
				outputStream.append(j.getDepartureTime().getHour() + "\n");
				outputStream.append(j.getDepartureTime().getMinute() + "\n");
				outputStream.append(j.getArrivalPlace() + "\n");
				outputStream.append(j.getArrivalTime().getHour() + "\n");
				outputStream.append(j.getArrivalTime().getMinute() + "\n");
			}
			outputStream.append("new line");
		}
		outputStream.close();
	}

	public static ArrayList<Line> findFastestRoute(ArrayList<Line> allLines, LocalTime departureTime,
			String departurePlace, String arrivalPlace) {
		ArrayList<Line> wantedLines = new ArrayList<>();
		int counter = 0, counter2 = 0;
		while (counter < allLines.size() && wantedLines.size() < 3) {
			Line lineCheck = allLines.get(counter);
			wantedLines.add(new Line(lineCheck.findSubLine(departurePlace, arrivalPlace, departureTime)));
			if (wantedLines.get(counter2).getAllStops() == null) {
				wantedLines.remove(wantedLines.size() - 1);
				counter2--;
			}
			counter++;
			counter2++;
		}
		return wantedLines;
	}

	public static String routeSearch(ArrayList<Line> allLines, LocalTime departureTime, String departurePlace,
			String arrivalPlace) {
		ArrayList<Line> wantedLines = new ArrayList<>(
				findFastestRoute(allLines, departureTime, departurePlace, arrivalPlace));
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < wantedLines.size(); i++) {
			str.append((i + 1) + ") " + wantedLines.get(i).toString() + "\n");
		}
		return str.toString();
	}
}
