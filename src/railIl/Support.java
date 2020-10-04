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
		int departureHour = 0, departureMinutes = 0, arrivalHour = 0, arrivalMinutes, counter = 0;
		File myObj = new File("AllLines.txt");
		Scanner myReader = new Scanner(myObj);
		while (!myReader.hasNext("finish")) {
			routs.clear();
			while (!myReader.hasNext("new line")) {
				departurePlace = myReader.nextLine();
				departureHour = Integer.parseInt(myReader.nextLine());
				departureMinutes = Integer.parseInt(myReader.nextLine());
				arrivalPlace = myReader.nextLine();
				arrivalHour = Integer.parseInt(myReader.nextLine());
				arrivalMinutes = Integer.parseInt(myReader.nextLine());
				routs.add(new Route(LocalTime.of(departureHour, departureMinutes),
						LocalTime.of(arrivalHour, arrivalMinutes), departurePlace, arrivalPlace));
			}
			allLines.add(new Line(routs));
		}
		myReader.close();
		return allLines;
	}

	public static void findRoutes(Scanner s, ArrayList<Line> allLines) {
		String departureStation, destinationStation;
		int hour, minutes;
		int counter1 = 0;
		boolean fcontinu = true;
		ArrayList<Route> routes = new ArrayList<Route>();

		try {

			System.out.println("Enter a departure station ");
			departureStation = s.nextLine();
			System.out.println("Enter a destination station");
			destinationStation = s.nextLine();
			System.out.println("Enter a departure hour");
			hour = s.nextInt();
			System.out.println("Enter a departure minuts");
			minutes = s.nextInt();
			s.nextLine();
			for (int i = 0; i < allLines.size(); i++) {
				if (allLines.get(i).findRoute(departureStation, destinationStation,
						LocalTime.of(hour, minutes)) != null) {
					routes.add(allLines.get(i).findRoute(departureStation, destinationStation,
							LocalTime.of(hour, minutes)));
					counter1++;

				}

				/*
				 * if (counter1 == 3) { fcontinu = false; }
				 */
			}
		}

		catch (InputMismatchException e) {
			System.out.println(e.getMessage() + " try again");
			fcontinu = true;
			s.nextLine();

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + " try again");
		}

		// Sort the Route
		int i, j, counter = 0;
		Route temp;
		boolean swapped;
		for (i = 0; i < routes.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < routes.size() - i - 1; j++) {
				if (routes.get(j).getDepartureTime().isAfter(routes.get(j + 1).getDepartureTime())) {
					temp = new Route(routes.get(j));
					routes.set(j, routes.get(j + 1));
					routes.set(j + 1, temp);
					counter++;
					swapped = true;

				}

			}

			if (swapped == false)
				break;
		}

		System.out.println(routes);

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
			for (Route j : i.getAllStops()) {
				outputStream.append(j.getDeparturePlace() + "\n");
				outputStream.append(j.getDepartureTime().getHour() + "\n");
				outputStream.append(j.getDepartureTime().getMinute() + "\n");
				outputStream.append(j.getArrivalPlace() + "\n");
				outputStream.append(j.getArrivalTime().getHour() + "\n");
				outputStream.append(j.getArrivalTime().getMinute() + "\n");
			}
			outputStream.append("new line\n");
		}
		outputStream.append("finish");
		outputStream.close();
	}

}
