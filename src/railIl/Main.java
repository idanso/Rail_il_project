package railIl;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Route> allRoutes = new ArrayList<>();//check if still needed
		ArrayList<Line> allLines = new ArrayList<>();
		int choice;
		System.out.println("Enter your choice: ");
		for (int i = 1; i <= 4; i++) {
			System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
		}
		System.out.println("[" + 9 + "]-To exit");
		
		do {

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
				allLines.add(new Line());
				System.out.println("new Line added succefully");
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
