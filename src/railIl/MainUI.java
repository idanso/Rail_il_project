package railIl;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainUI {
	public static void main(String[] args) {
		ArrayList<Line> allLines = new ArrayList<>();
		String strWantedLines = null;
		try {
			allLines = Support.readAllLines();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String source = "tel-aviv", destination = "Beer-sheva";
		int hour = 12;
		int minuts = 00;
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isSource = args.length > 0;
		if (isSource) {
			source = args[1];
			if (source.contains("-"))
				source = source.replace("-", " ");
		}
		boolean isDestination = args.length > 0;
		if (isDestination) {
			destination = args[2];
			if (destination.contains("-"))
				destination = destination.replace("-", " ");
		}

		boolean isHour = args.length > 0 && !(args[3].equalsIgnoreCase("12"));
		if (isHour) {
			hour = Integer.parseInt(args[3]);
		}
		boolean isMinuts = args.length > 0 && !(args[4].equalsIgnoreCase("00"));
		if (isMinuts) {
			minuts = Integer.parseInt(args[4]);
<<<<<<< HEAD

=======
>>>>>>> a44283067d97893fbb84f229c095b2c670192d83
		}
		if (isHtml) {
			System.out.println("<h2>\n<span style=\"text-decoration: underline;\">search resoult for : " + source
					+ " ---> " + destination + " " + hour + ":" + minuts + " | html format: " + isHtml
					+ "</span>\n</h2>\n<br>\n<br>");
		} else
			System.out.println("search resoult for : " + source + " ---> " + destination + " " + hour + ":" + minuts
					+ " | html format: " + isHtml);
<<<<<<< HEAD
=======

>>>>>>> a44283067d97893fbb84f229c095b2c670192d83
		LocalTime departureTime = LocalTime.of(hour, minuts);
		strWantedLines = Support.routeSearch(allLines, departureTime, source, destination, isHtml);
		if (strWantedLines != null) {
			System.out.println(strWantedLines);
		} else
			System.out.println("couldn't find routs, try enter another parameters...");
	}

}
