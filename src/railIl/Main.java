package railIl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Line> allLines = new ArrayList<>();
		ArrayList<Route> allRoutes = new ArrayList<>();
		int choice;

		do {
			System.out.println("Enter your choice: ");
			for (int i = 1; i <= 3; i++) {
				System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
			}
			System.out.println("[" + 9 + "]-To exit");
			choice = s.nextInt();

			switch (choice) {
			case 1: {
				allRoutes.add(new Route());
				System.out.println("new route added succefully");
				ArrayList<Route> allStops = new ArrayList<Route>();
				int stationCounter = 1;
				boolean bExceptionFree = true;
				int hourDelay = 0, minutesDelay = 0;
				do {
					try {
						System.out.println("what the frequency of the line you want?(hours and minutes)\n"
								+ "type hour frequency(0 for none): ");
						hourDelay = s.nextInt();
						System.out.println("type minutes frequency(0 for none): ");
						minutesDelay = s.nextInt();
						s.nextLine();
						bExceptionFree = false;
					} catch (InputMismatchException e) {
						System.out.println("entered invalid input, try again...");
					}
				} while (bExceptionFree);
				System.out.println(
						"input details (if frequent, the time is for the first line of the day) of station number "
								+ stationCounter + " :");
				allStops.add(new Route());
				System.out.println("stop number " + stationCounter + " added successfully");
				stationCounter++;
				boolean bAnotherStation = true, bError = true;
				do {
					System.out.println("input details of station number " + stationCounter + " :");
					allStops.add(new Route(allStops.get(stationCounter - 2).getArrivalPlace(),
							allStops.get(stationCounter - 2).getArrivalTime()));
					System.out.println("stop number " + stationCounter + " added successfully");
					stationCounter++;
					System.out.println("do you want to add another station to the line? (Y/N)");
					char answer;
					do {
						try {
							answer = s.next().charAt(0);
							if (answer == 'n' || answer == 'N')
								bAnotherStation = false;
							else if (answer == 'y' || answer == 'Y') {
							} else
								throw new Exception("input can only be 'y' || 'Y' || 'n' || 'N'");
							bError = false;
						} catch (Exception e) {
							System.out.println("please type only 'Y' (yes) / 'N' (no)... please try again");
						}
					} while (bError);
				} while (bAnotherStation);
				allLines.add(new Line(allStops));
				if (hourDelay != 0 || hourDelay != 0) {
					while ((allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour() + hourDelay)
							+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getHour()
									- allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour()) < 23
							&& (allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute()
									+ hourDelay)
									+ (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getMinute()
											- allLines.get(allLines.size() - 1).getLastStop().getDepartureTime()
													.getMinute()) < 59) {
						allLines.add(
								new Line(allLines.get(allLines.size() - 1).getAllStops(), hourDelay, minutesDelay));
					}
				}

				System.out.println();
				System.out.println("added new lines to the system from " + allStops.get(0).getDeparturePlace() + " to "
						+ allStops.get(allStops.size() - 1).getArrivalPlace() + " with frequency: " + hourDelay + ":"
						+ minutesDelay);

				break;
			}
			case 2: {
				BubblesortByTime(allRoutes);
				PrintAllRoutesDetails(allRoutes);
				PrintAllLinesDetails(allLines);
				break;
			}
			case 3: {
				if (allRoutes.isEmpty()) {
					System.out.println("No routes in the system");
					break;
				} else
					findRoutes(s, allLines);
			}

			}
		} while (choice != 9);
		System.out.println("Good bye:)");
		s.close();
	}

	private static void findRoutes(Scanner s, ArrayList<Line> allLines) {
		String departureStation, destinationStation;
		int hour, minuts, remember = 0, frequencyHour, frequencyMinuts;
		boolean fcontinue = true;
		do {

			try {
				System.out.println("Enter hour the frequency of line");
				frequencyHour = s.nextInt();
				System.out.println("Enter  minuts the frequency of line");
				frequencyMinuts = s.nextInt();
				System.out.println("Enter a departure station ");
				departureStation = s.next();
				System.out.println("Enter a destination station");
				destinationStation = s.next();
				System.out.println("Enter a departure hour");
				hour = s.nextInt();
				System.out.println("Enter a departure minuts");
				minuts = s.nextInt();

				for (int i = 0; i < allLines.size(); i++) {
					for (int j = 0; j < allLines.get(i).getAllStops().size(); j++) {
						if (allLines.get(i).getAllStops().get(i).getDeparturePlace().equals(departureStation)) {
							if (allLines.get(i).getAllStops().equals(destinationStation)) {
								if (allLines.get(i).getAllStops().get(i).getDepartureTime().getHour() >= hour
										&& allLines.get(i).getAllStops().get(i).getDepartureTime()
												.getMinute() >= minuts) {
									System.out.println(allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
											+ ":"
											+ allLines.get(i).getAllStops().get(i).getDepartureTime().getMinute());
									if ((allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
											+ frequencyHour) <= 12) {

										if (allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
												+ frequencyMinuts < 60) {
											System.out.println(
													(allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
															+ hour) + ":"
															+ (allLines.get(i).getAllStops().get(i).getDepartureTime()
																	.getMinute() + minuts));
										} else if (allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
												+ frequencyMinuts >= 60) {
											System.out
													.println(
															(allLines.get(i).getAllStops().get(i).getDepartureTime()
																	.getHour() + hour)
																	+ ":"
																	+ (allLines.get(i).getAllStops().get(i)
																			.getDepartureTime().getMinute() + minuts
																			- 60));
										}

									} else if ((allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
											+ frequencyHour) > 12) {
										if (allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
												+ frequencyMinuts < 60) {
											System.out.println(
													(allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
															+ hour - 12) + ":"
															+ (allLines.get(i).getAllStops().get(i).getDepartureTime()
																	.getMinute() + minuts));
										} else if (allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
												+ frequencyMinuts >= 60) {
											System.out.println(
													(allLines.get(i).getAllStops().get(i).getDepartureTime().getHour()
															+ hour - 12) + ":"
															+ (allLines.get(i).getAllStops().get(i).getDepartureTime()
																	.getMinute() + minuts - 60));

										}

									}

									System.out.println();

								}
							}
						}
					}
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage() + " try again");

			}
		} while (fcontinue);

	}

	public static String MenuHelper(int i) {
		final String str[] = new String[9];
		str[0] = "To Add new route and stop stations to system";
		str[1] = "Show details of all lines";
		str[2] = "To find details Routs ";

		return str[i];
	}

	public static void PrintAllRoutesDetails(ArrayList<Route> allRoutes) {
		for (int i = 0; i < allRoutes.size(); i++) {
			System.out.println(allRoutes.get(i).toString() + "\n");
		}
	}

	public static void PrintAllLinesDetails(ArrayList<Line> allLines) {
		for (int i = 0; i < allLines.size(); i++) {
			System.out.println(allLines.get(i).toString() + "\n");
		}
	}

	public static void BubblesortByTime(ArrayList<Route> allRoutes) {
		int i, j;
		Route temp;
		boolean swapped;
		for (i = 0; i < allRoutes.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < allRoutes.size() - i - 1; j++) {
				if (allRoutes.get(j).getDepartureTime().isAfter(allRoutes.get(j + 1).getDepartureTime())) {
					temp = new Route(allRoutes.get(j));
					allRoutes.set(j, allRoutes.get(j + 1));
					allRoutes.set(j + 1, temp);
					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
	}

}
