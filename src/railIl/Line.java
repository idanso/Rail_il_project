package railIl;

import java.util.ArrayList;
import java.util.Scanner;

public class Line {

	private ArrayList<Route> allStops;

	public Line(ArrayList<Route> allStops) {
		super();
		this.allStops = allStops;
	}

	public Line() {
		Scanner s = new Scanner(System.in);
		allStops = new ArrayList<Route>();
		int stationCounter = 1;
		System.out.println("input details of station number " + stationCounter + " :" );
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
		System.out.println("added new line to the system from " + allStops.get(0).getDeparturePlace() + "to " + allStops.get(allStops.size() - 1).getArrivalPlace());
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(allStops.get(0).getDepartureTime() + " : " + allStops.get(0).getDeparturePlace().toString() + " --> ");
		for (int i = 0; i <allStops.size(); i++) {
			str.append(allStops.get(i).getArrivalTime() + " : " + allStops.get(i).getArrivalPlace().toString());	
			if(i < allStops.size()- 1) str.append(" --> ");
		}
		return str.toString();
	}
	

}
