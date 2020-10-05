package railIl;

public class MainUI {
	public static void main(String[] args) {
		String source = "tal-aviv", destination = "beer-sheva";
		Integer hour = new Integer(12);
		Integer minuts = new Integer(00);
		boolean isHTML = args.length > 0 && args[0].equalsIgnoreCase("html");

		if (isHTML) {
			System.out.println("<br>");
		}
		boolean isSource = args.length > 0 && !(args[1].equalsIgnoreCase("tal-aviv"));
		if (isSource) {
			source = args[1];
			System.out.println("<br>");

		}
		boolean isDestination = args.length > 0 && !(args[2].equalsIgnoreCase("Beer-sheva"));
		if (isDestination) {
			destination = args[2];
			System.out.println("<br>");
		}

		boolean isHour = args.length > 0 && !(args[3].equalsIgnoreCase("12"));
		if (isHour) {
			hour.parseInt(args[3]);
			System.out.println("<br>");
		}
		boolean isMinuts = args.length > 0 && !(args[4].equalsIgnoreCase("00"));
		if (isMinuts) {
			minuts.parseInt(args[4]);
			System.out.println("<br>");
		}
		System.out.println(source + "--->" + destination + " " + hour + ":" + minuts);
	}

}
