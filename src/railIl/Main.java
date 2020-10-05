package railIl;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Line> allLines = new ArrayList<>();
		try {
			allLines = Support.readAllLines();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		int choice;

		do {
			System.out.println("Enter your choice: ");
			for (int i = 1; i <= 4; i++) {
				System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
			}
			System.out.println("[" + 9 + "]-To exit and save to file");
			choice = s.nextInt();
			s.nextLine();

			switch (choice) {
			case 1: {
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
						s.nextLine();
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
				if (hourDelay != 0 || minutesDelay != 0) {			
					allLines = Support.DuplicateLineByFrequency(allLines,hourDelay,minutesDelay);				
				}
				System.out.println();
				System.out.println("added new lines to the system from " + allStops.get(0).getDeparturePlace() + " to "
						+ allStops.get(allStops.size() - 1).getArrivalPlace() + " with frequency: " + hourDelay + ":"
						+ minutesDelay);

				break;
			}
			case 2: {
				allLines = Support.bubblesortByTime(allLines);
				System.out.println(Support.getAllLinesDetails(allLines));			
				break;
			}
			case 3: {
				if (!allLines.isEmpty()) {
					String departureStation, arrivalPlace;
					int hour, minutes;
					System.out.println("Enter a departure station ");
					departureStation = s.nextLine();
					System.out.println("Enter a destination station");
					arrivalPlace = s.nextLine();
					System.out.println("Enter a departure hour");
					hour = s.nextInt();
					System.out.println("Enter a departure minuts");
					minutes = s.nextInt();
					s.nextLine();
					LocalTime departureTime = LocalTime.of(hour, minutes);		
					System.out.println(Support.routeSearch(allLines, departureTime, departureStation, arrivalPlace));
				} else
					System.out.println("No routes in the system");
					
				
			}
			case 4: {
				try {
					Support.writeToFile(allLines);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("file saves succefully");
			}
		} 
		}while (choice != 9);	
		System.out.println("Good bye:)");
		s.close();
	}

		public static String MenuHelper(int i) {
		final String str[] = new String[9];
		str[0] = "To Add new route and stop stations to system";
		str[1] = "Show details of all lines";
		str[2] = "To find details Routs ";
		str[3] = "save to file";
		return str[i];
	}	
}
