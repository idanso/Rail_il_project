package railIl;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Support {

	public static ArrayList<Line> DuplicateLineByFrequency (ArrayList<Line> allLines, int hourDelay, int minutesDelay) {
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
		return allLines;
	}
	
	public static ArrayList<Line> bubblesortByTime(ArrayList<Line> allLines) {
		int i, j;
		Line temp;
		boolean swapped;
		for (i = 0; i < allLines.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < allLines.size() - i - 1; j++) {
				if (allLines.get(j).getAllStops().get(0).getDepartureTime().isAfter(allLines.get(j +1).getAllStops().get(0).getDepartureTime())) {
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
	
	public static void writeToFile(ArrayList<Line> allLines) throws FileNotFoundException{
		PrintWriter outputStream = new PrintWriter("AllLines");
		for(Line i : allLines) {
			for(Route j : i.getAllStops() ) {
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
