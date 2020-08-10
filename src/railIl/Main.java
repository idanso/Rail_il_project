package railIl;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Route> allRoutes = new ArrayList<>();
		// SortRouteByTime(allRoutes);
		int choice;

		do {

			System.out.println("Enter your choice: ");
			for (int i = 1; i <= 9; i++) {
				System.out.println("[" + i + "]" + "-" + MenuHelper(i - 1));
			}
			choice = s.nextInt();

			switch (choice) {
			case 1: {
				allRoutes.add(new Route());
				System.out.println("new route added succefully");

				break;
			}
			case 2: {
				PrintDetails(allRoutes);// to delete only for testing
				BubblesortByTime(allRoutes);
				PrintDetails(allRoutes);
				break;
			}
			case 3: {

				break;
			}
			case 4: {

				break;
			}
			case 5: {

				break;
			}
			case 6: {

				break;
			}
			case 7: {

				break;
			}
			case 8: {

				break;
			}
			case 9: {
				System.out.println("exiting system... Good bay:) ");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} while (choice != 9);
		
		s.close();
	}

	public static String MenuHelper(int i) {
		final String str[] = new String[9];
		str[0] = "Enter new route to the system";
		str[1] = "show details";
		str[2] = "";
		str[3] = "";
		str[4] = "";
		str[5] = "";
		str[6] = "";
		str[7] = "";
		str[8] = "";
		return str[i];
	}

	public static void PrintDetails(ArrayList<Route> allRoutes) {
		for (int i = 0; i < allRoutes.size(); i++) {
			System.out.println(allRoutes.get(i).toString() + "\n");
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
