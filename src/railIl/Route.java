package railIl;

import java.time.LocalTime;
import java.util.Scanner;

public class Route {
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String departurePlace;
	private String arrivalPlace;

	public Route(LocalTime departureTime, LocalTime arrivalTime, String departurePlace, String arrivalPlace) {
		setDepartureTime(departureTime);
		setArrivalTime(arrivalTime);
		setDeparturePlace(departurePlace);
		setArrivalPlace(arrivalPlace);
	}

	public Route(Route another) {
		this.departurePlace = another.departurePlace;
		this.arrivalTime = another.arrivalTime;
		this.arrivalPlace = another.arrivalPlace;
		this.departureTime = another.departureTime;

	}

	public Route() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter departure location:");
		setDeparturePlace(s.nextLine());
		System.out.println("Enter departure hour:");
		int hour = s.nextInt();
		System.out.println("Enter departure minutes:");
		int minutes = s.nextInt();
		s.nextLine();
		setDepartureTime(LocalTime.of(hour, minutes));
		System.out.println("Enter destination location:");
		setArrivalPlace(s.nextLine());
		System.out.println("Enter destenation hour:");
		hour = s.nextInt();
		System.out.println("Enter destenation minutes:");
		minutes = s.nextInt();
		s.nextLine();
		setArrivalTime(LocalTime.of(hour, minutes));
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	@Override
	public String toString() {

		return departurePlace + ", " + departureTime.toString() + ", " + arrivalPlace + ", " + arrivalTime.toString();
	}

}