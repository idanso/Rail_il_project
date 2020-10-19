package railIl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Line {

	public static void main(String[] args) {
		String neme = "12:22";
		System.out.println(neme.substring(0, 2));
		System.out.println(neme.subSequence(3, neme.length() - 1));
	}

	private ArrayList<Route> allStops;

	public Line(ArrayList<Route> allStops) {
		this.allStops = allStops;
	}

	public Line(ArrayList<Route> allStops, int hourDelay, int minutesDelay) {// constructor with time delay
		this.allStops = new ArrayList<>();
		for (Route r : allStops) {
			this.allStops.add(new Route(r.getDepartureTime().plusHours(hourDelay).plusMinutes(minutesDelay),
					r.getArrivalTime().plusHours(hourDelay).plusMinutes(minutesDelay), r.getDeparturePlace(),
					r.getArrivalPlace()));
		}
	}

	public Line(Line line) {
		this.allStops = line.allStops;
	}

	public Route getLastStop() {
		return allStops.get(allStops.size() - 1);
	}

	public ArrayList<Route> getAllStops() {
		return allStops;
	}

	public boolean findRoute(String departurePlace, String arrivalPlace, LocalTime departureTime) {
		// Route findRoute;
		for (int i = 0; i < allStops.size(); i++) {
			if (departurePlace.equals(allStops.get(i).getDeparturePlace())
					&& (arrivalPlace.equals(allStops.get(i).getArrivalPlace())
							&& departureTime.isBefore(allStops.get(i).getDepartureTime()))) {
				return true;
			}

		}
		return false;

	}

	public ArrayList<Route> findSubLine(String departurePlace, String arrivalPlace, LocalTime departureTime) {
		ArrayList<Route> subLine = new ArrayList<>();
		boolean placeNotFound = true;
		for (int i = 0; i < allStops.size(); i++) {
			Route checkRoute = new Route(allStops.get(i));
			if (placeNotFound) {
				if (checkRoute.getDeparturePlace().equals(departurePlace)
						&& checkRoute.getDepartureTime().isAfter(departureTime)) {
					placeNotFound = false;
				}
			}
			if (!placeNotFound) {
				subLine.add(checkRoute);
				if (checkRoute.getArrivalPlace().equals(arrivalPlace)) {
					return subLine;
				}
			}

		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(
				allStops.get(0).getDepartureTime() + " : " + allStops.get(0).getDeparturePlace().toString() + " --> ");
		for (int i = 0; i < allStops.size(); i++) {
			str.append(allStops.get(i).getArrivalTime() + " : " + allStops.get(i).getArrivalPlace().toString());
			if (i < allStops.size() - 1)
				str.append(" --> ");
		}
		return str.toString();
	}

}
