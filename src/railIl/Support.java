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
		File myObj = new File("/home/moshe/newRail/Rail_il_project/AllLines.txt");
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
		for (int i = 0; i < allLines.size(); i++) {
			for (int j = 0; j < allLines.get(i).getAllStops().size(); j++) {
				outputStream.append(allLines.get(i).getAllStops().get(j).getDeparturePlace() + "\n");
				outputStream.append(allLines.get(i).getAllStops().get(j).getDepartureTime().getHour() + "\n");
				outputStream.append(allLines.get(i).getAllStops().get(j).getDepartureTime().getMinute() + "\n");
				outputStream.append(allLines.get(i).getAllStops().get(j).getArrivalPlace() + "\n");
				outputStream.append(allLines.get(i).getAllStops().get(j).getArrivalTime().getHour() + "\n");
				outputStream.append(allLines.get(i).getAllStops().get(j).getArrivalTime().getMinute() + "\n");
			}
			if (i == (allLines.size() - 1))
				outputStream.append("new line\n");
			else
				outputStream.append("new line\n");
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
			String arrivalPlace, boolean isHtml) {
		ArrayList<Line> wantedLines = new ArrayList<>(
				findFastestRoute(allLines, departureTime, departurePlace, arrivalPlace));
		if (!wantedLines.isEmpty()) {
			StringBuffer str = new StringBuffer();
			if (isHtml)
				str.append("<style>\n" + "table, td {\n" + "  border: 1.5px solid black;\n"
						+ "  border-collapse: collapse;\n" + "}\n" + "</style> <table>\n" + "<tbody>\n");
			for (int i = 0; i < wantedLines.size(); i++) {
				if (isHtml)
					str.append("<tr>\n<td>\n" + (i + 1) + ") \n</td>\n<td>\n" + wantedLines.get(i).toString()
							+ "\n</td>\n</tr>\n");
				else
					str.append((i + 1) + ") " + wantedLines.get(i).toString() + "\n");
			}
			if (isHtml)
				str.append("</table>\n</tbody>");
			return str.toString();
		} else
			return null;
	}
}
