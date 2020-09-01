package railIl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Route> allRoutes = new ArrayList<>();//check if still needed
		ArrayList<Line> allLines = new ArrayList<>();
		int choice;
		
		do {
			System.out.println("Enter your choice: ");
			for (int i = 1; i <= 4; i++) {
				System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
			}
			System.out.println("[" + 9 + "]-To exit");
			choice = s.nextInt();

			switch (choice) {
			case 1: {// check if still needed
				allRoutes.add(new Route());
				System.out.println("new route added succefully");

				break;
			}
			case 2: {
				BubblesortByTime(allRoutes);
				PrintAllRoutesDetails(allRoutes);
				break;
			}
			case 3: {
				ArrayList<Route> allStops  = new ArrayList<Route>(); 
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
				}
				while(bExceptionFree);
				System.out.println("input details (if frequent, the time is for the first line of the day) of station number " + stationCounter + " :" );
				allStops.add(new Route());
				System.out.println("stop number " + stationCounter + " added successfully");
				stationCounter++;
				boolean bAnotherStation = true, bError = true;
				do {
					System.out.println("input details of station number " + stationCounter + " :" );
					allStops.add(new Route(allStops.get(stationCounter-2).getArrivalPlace(), allStops.get(stationCounter-2).getArrivalTime()));
					System.out.println("stop number " + stationCounter + " added successfully");
					stationCounter++;
					System.out.println("do you want to add another station to the line? (Y/N)");
					char answer;
						do {
							try {
								answer = s.next().charAt(0);
								if(answer == 'n' || answer == 'N')
									bAnotherStation = false;
								else if(answer == 'y' || answer == 'Y') {}
								else
									throw new Exception("input can only be 'y' || 'Y' || 'n' || 'N'");
								bError = false;
							}
							catch(Exception e) {
								System.out.println("please type only 'Y' (yes) / 'N' (no)... please try again");
							}
						}
						while(bError);	
					}
				while(bAnotherStation);
				allLines.add(new Line(allStops));
				if(hourDelay != 0 || hourDelay !=0) {
					while((allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour() + hourDelay) + (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getHour() - allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getHour()) < 23 && (allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute() + hourDelay) + (allLines.get(allLines.size() - 1).getLastStop().getArrivalTime().getMinute() - allLines.get(allLines.size() - 1).getLastStop().getDepartureTime().getMinute()) < 59){
						allLines.add(new Line(allLines.get(allLines.size() - 1).getAllStops(),hourDelay,minutesDelay));
					}
				}
					
				System.out.println();
				System.out.println("added new lines to the system from " + allStops.get(0).getDeparturePlace() + " to " + allStops.get(allStops.size() - 1).getArrivalPlace() + " with frequency: " + hourDelay + ":" + minutesDelay);
				break;
			}
			case 4: {
				PrintAllLinesDetails(allLines);

				break;
			}
			/*
			 * case 5: {
			 * 
			 * break; } case 6: {
			 * 
			 * break; } case 7: {
			 * 
			 * break; } case 8: {
			 * 
			 * break; }
			 */

			}
			} while (choice != 9);
		System.out.println("Good bye:)");
		s.close();
	}

		

	public static String MenuHelper(int i) {
		final String str[] = new String[9];
		str[0] = "Enter new route to the system";
		str[1] = "show details of all routes";
		str[2] = "Enter new line to the system";
		str[3] = "show details of all lines";
		str[4] = "";
		str[5] = "";
		str[6] = "";
		str[7] = "";
		str[8] = "";
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

	private static void BubblesortByTime(ArrayList<Route> allRoutes) {
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
