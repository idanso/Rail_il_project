package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import railIl.Line;
import railIl.Route;
import railIl.Support;

class testings {

	@Test
	void creatingNewLineTest() {
		ArrayList<Line> tAllLines = new ArrayList<>();
		tAllLines.add(createLineHaifaToAshdod());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 1, 30);
		assertEquals(
				"08:00 : haifa --> 09:00 : atlit --> 09:30 : hadera --> 10:20 : natanya --> 11:00 : herzelia --> 11:15 : tel aviv --> 11:40 : ashdod\n"
						+ "\n"
						+ "09:30 : haifa --> 10:30 : atlit --> 11:00 : hadera --> 11:50 : natanya --> 12:30 : herzelia --> 12:45 : tel aviv --> 13:10 : ashdod\n"
						+ "\n"
						+ "11:00 : haifa --> 12:00 : atlit --> 12:30 : hadera --> 13:20 : natanya --> 14:00 : herzelia --> 14:15 : tel aviv --> 14:40 : ashdod\n"
						+ "\n"
						+ "12:30 : haifa --> 13:30 : atlit --> 14:00 : hadera --> 14:50 : natanya --> 15:30 : herzelia --> 15:45 : tel aviv --> 16:10 : ashdod\n"
						+ "\n"
						+ "14:00 : haifa --> 15:00 : atlit --> 15:30 : hadera --> 16:20 : natanya --> 17:00 : herzelia --> 17:15 : tel aviv --> 17:40 : ashdod\n"
						+ "\n"
						+ "15:30 : haifa --> 16:30 : atlit --> 17:00 : hadera --> 17:50 : natanya --> 18:30 : herzelia --> 18:45 : tel aviv --> 19:10 : ashdod\n"
						+ "\n"
						+ "17:00 : haifa --> 18:00 : atlit --> 18:30 : hadera --> 19:20 : natanya --> 20:00 : herzelia --> 20:15 : tel aviv --> 20:40 : ashdod\n"
						+ "\n"
						+ "18:30 : haifa --> 19:30 : atlit --> 20:00 : hadera --> 20:50 : natanya --> 21:30 : herzelia --> 21:45 : tel aviv --> 22:10 : ashdod\n\n",
				Support.getAllLinesDetails(tAllLines));
	}

	@Test
	void sortigTest() {
		ArrayList<Line> tAllLines = new ArrayList<>();
		tAllLines.add(createLineHaifaToAshdod());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 2, 30);
		tAllLines.add(createLineEilatToTelAviv());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 1, 10);
		Support.bubblesortByTime(tAllLines);
		assertEquals(
				"07:30 : eilat --> 08:30 : beer sheva --> 09:30 : ashkelon --> 10:20 : ashdod --> 11:00 : yavne --> 11:15 : holon --> 11:40 : tel aviv\n"
						+ "\n"
						+ "08:00 : haifa --> 09:00 : atlit --> 09:30 : hadera --> 10:20 : natanya --> 11:00 : herzelia --> 11:15 : tel aviv --> 11:40 : ashdod\n"
						+ "\n"
						+ "08:40 : eilat --> 09:40 : beer sheva --> 10:40 : ashkelon --> 11:30 : ashdod --> 12:10 : yavne --> 12:25 : holon --> 12:50 : tel aviv\n"
						+ "\n"
						+ "09:50 : eilat --> 10:50 : beer sheva --> 11:50 : ashkelon --> 12:40 : ashdod --> 13:20 : yavne --> 13:35 : holon --> 14:00 : tel aviv\n"
						+ "\n"
						+ "10:30 : haifa --> 11:30 : atlit --> 12:00 : hadera --> 12:50 : natanya --> 13:30 : herzelia --> 13:45 : tel aviv --> 14:10 : ashdod\n"
						+ "\n"
						+ "11:00 : eilat --> 12:00 : beer sheva --> 13:00 : ashkelon --> 13:50 : ashdod --> 14:30 : yavne --> 14:45 : holon --> 15:10 : tel aviv\n"
						+ "\n"
						+ "12:10 : eilat --> 13:10 : beer sheva --> 14:10 : ashkelon --> 15:00 : ashdod --> 15:40 : yavne --> 15:55 : holon --> 16:20 : tel aviv\n"
						+ "\n"
						+ "13:00 : haifa --> 14:00 : atlit --> 14:30 : hadera --> 15:20 : natanya --> 16:00 : herzelia --> 16:15 : tel aviv --> 16:40 : ashdod\n"
						+ "\n"
						+ "13:20 : eilat --> 14:20 : beer sheva --> 15:20 : ashkelon --> 16:10 : ashdod --> 16:50 : yavne --> 17:05 : holon --> 17:30 : tel aviv\n"
						+ "\n"
						+ "14:30 : eilat --> 15:30 : beer sheva --> 16:30 : ashkelon --> 17:20 : ashdod --> 18:00 : yavne --> 18:15 : holon --> 18:40 : tel aviv\n"
						+ "\n"
						+ "15:30 : haifa --> 16:30 : atlit --> 17:00 : hadera --> 17:50 : natanya --> 18:30 : herzelia --> 18:45 : tel aviv --> 19:10 : ashdod\n"
						+ "\n"
						+ "15:40 : eilat --> 16:40 : beer sheva --> 17:40 : ashkelon --> 18:30 : ashdod --> 19:10 : yavne --> 19:25 : holon --> 19:50 : tel aviv\n"
						+ "\n"
						+ "16:50 : eilat --> 17:50 : beer sheva --> 18:50 : ashkelon --> 19:40 : ashdod --> 20:20 : yavne --> 20:35 : holon --> 21:00 : tel aviv\n"
						+ "\n"
						+ "18:00 : haifa --> 19:00 : atlit --> 19:30 : hadera --> 20:20 : natanya --> 21:00 : herzelia --> 21:15 : tel aviv --> 21:40 : ashdod\n"
						+ "\n"
						+ "18:00 : eilat --> 19:00 : beer sheva --> 20:00 : ashkelon --> 20:50 : ashdod --> 21:30 : yavne --> 21:45 : holon --> 22:10 : tel aviv\n\n",
				Support.getAllLinesDetails(tAllLines));
	}

	@Test
	void searchingRoute() {
		ArrayList<Line> tAllLines = new ArrayList<>();
		tAllLines.add(createLineHaifaToAshdod());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 2, 30);
		tAllLines.add(createLineEilatToTelAviv());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 1, 10);
		tAllLines.add(createLineTveriaToAshkelon());
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>) tAllLines, 2, 0);
		Support.bubblesortByTime(tAllLines);
		assertEquals("1) 14:30 : hadera --> 15:20 : natanya --> 16:00 : herzelia --> 16:15 : tel aviv\n" + 
				"2) 15:30 : hadera --> 16:20 : natanya --> 17:00 : tel aviv\n" + 
				"3) 17:00 : hadera --> 17:50 : natanya --> 18:30 : herzelia --> 18:45 : tel aviv\n", Support.routeSearch(tAllLines, LocalTime.of(14, 20), "hadera", "tel aviv"));
	}

	Line createLineHaifaToAshdod() {
		ArrayList<Route> tAllStops = new ArrayList<Route>();
		tAllStops.add(new Route(LocalTime.of(8, 0), LocalTime.of(9, 0), "haifa", "atlit"));
		tAllStops.add(new Route(LocalTime.of(9, 0), LocalTime.of(9, 30), "atlit", "hadera"));
		tAllStops.add(new Route(LocalTime.of(9, 30), LocalTime.of(10, 20), "hadera", "natanya"));
		tAllStops.add(new Route(LocalTime.of(10, 20), LocalTime.of(11, 0), "natanya", "herzelia"));
		tAllStops.add(new Route(LocalTime.of(11, 0), LocalTime.of(11, 15), "herzelia", "tel aviv"));
		tAllStops.add(new Route(LocalTime.of(11, 15), LocalTime.of(11, 40), "tel aviv", "ashdod"));
		return new Line(tAllStops);
	}

	Line createLineEilatToTelAviv() {
		ArrayList<Route> tAllStops = new ArrayList<Route>();
		tAllStops.add(new Route(LocalTime.of(7, 30), LocalTime.of(8, 30), "eilat", "beer sheva"));
		tAllStops.add(new Route(LocalTime.of(9, 0), LocalTime.of(9, 30), "beer sheva", "ashkelon"));
		tAllStops.add(new Route(LocalTime.of(9, 30), LocalTime.of(10, 20), "ashkelon", "ashdod"));
		tAllStops.add(new Route(LocalTime.of(10, 20), LocalTime.of(11, 0), "ashdod", "yavne"));
		tAllStops.add(new Route(LocalTime.of(11, 0), LocalTime.of(11, 15), "yavne", "holon"));
		tAllStops.add(new Route(LocalTime.of(11, 15), LocalTime.of(11, 40), "holon", "tel aviv"));
		return new Line(tAllStops);
	}

	Line createLineTveriaToAshkelon() {
		ArrayList<Route> tAllStops = new ArrayList<Route>();
		tAllStops.add(new Route(LocalTime.of(7, 30), LocalTime.of(8, 30), "tveria", "afula"));
		tAllStops.add(new Route(LocalTime.of(9, 0), LocalTime.of(9, 30), "afula", "hadera"));
		tAllStops.add(new Route(LocalTime.of(9, 30), LocalTime.of(10, 20), "hadera", "natanya"));
		tAllStops.add(new Route(LocalTime.of(10, 20), LocalTime.of(11, 0), "natanya", "tel aviv"));
		tAllStops.add(new Route(LocalTime.of(11, 0), LocalTime.of(11, 15), "tel aviv", "holon"));
		tAllStops.add(new Route(LocalTime.of(11, 15), LocalTime.of(11, 40), "holon", "ashdod"));
		tAllStops.add(new Route(LocalTime.of(11, 15), LocalTime.of(11, 40), "ashdod", "ashkelon"));
		return new Line(tAllStops);
	}
}
