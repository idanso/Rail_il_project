package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import railIl.Line;
import railIl.Route;
import railIl.Support;

class testings {

	@Test
	void test() {
		ArrayList<Route> tAllStops = new ArrayList<Route>();
		ArrayList<Line> tAllLines = new ArrayList<>();
		tAllStops.add(new Route(LocalTime.of(8,0),LocalTime.of(9,0), "haifa", "atlit"));
		tAllStops.add(new Route(LocalTime.of(9,0),LocalTime.of(9,30), "atlit", "hadera"));
		tAllStops.add(new Route(LocalTime.of(9,30),LocalTime.of(10,20), "hadera", "natanya"));
		tAllStops.add(new Route(LocalTime.of(10,20),LocalTime.of(11,0), "natanya", "herzelia"));
		tAllStops.add(new Route(LocalTime.of(11,0),LocalTime.of(11,15), "herzelia", "tel aviv"));
		tAllStops.add(new Route(LocalTime.of(11,15),LocalTime.of(11,40), "tel aviv", "ashdod"));
		tAllLines.add(new Line(tAllStops));
		tAllLines.add(new Line(tAllStops,1,30));
		tAllStops.clear();
		//to add another line to test the sorting
		tAllLines = Support.DuplicateLineByFrequency((ArrayList<Line>)tAllLines,1,30);
		
		
		
		
		
		
		assertEquals("08:00 : haifa --> 09:00 : atlit --> 09:30 : hadera --> 10:20 : natanya --> 11:00 : herzelia --> 11:15 : tel aviv --> 11:40 : ashdod\n" + 
				"\n" + 
				"09:30 : haifa --> 10:30 : atlit --> 11:00 : hadera --> 11:50 : natanya --> 12:30 : herzelia --> 12:45 : tel aviv --> 13:10 : ashdod\n" + 
				"\n" + 
				"11:00 : haifa --> 12:00 : atlit --> 12:30 : hadera --> 13:20 : natanya --> 14:00 : herzelia --> 14:15 : tel aviv --> 14:40 : ashdod\n" + 
				"\n" +
				"12:30 : haifa --> 13:30 : atlit --> 14:00 : hadera --> 14:50 : natanya --> 15:30 : herzelia --> 15:45 : tel aviv --> 16:10 : ashdod\n" + 
				"\n" + 
				"14:00 : haifa --> 15:00 : atlit --> 15:30 : hadera --> 16:20 : natanya --> 17:00 : herzelia --> 17:15 : tel aviv --> 17:40 : ashdod\n" + 
				"\n" + 
				"15:30 : haifa --> 16:30 : atlit --> 17:00 : hadera --> 17:50 : natanya --> 18:30 : herzelia --> 18:45 : tel aviv --> 19:10 : ashdod\n" + 
				"\n" + 
				"17:00 : haifa --> 18:00 : atlit --> 18:30 : hadera --> 19:20 : natanya --> 20:00 : herzelia --> 20:15 : tel aviv --> 20:40 : ashdod\n" + 
				"\n" + 
				"18:30 : haifa --> 19:30 : atlit --> 20:00 : hadera --> 20:50 : natanya --> 21:30 : herzelia --> 21:45 : tel aviv --> 22:10 : ashdod\n\n",Support.getAllLinesDetails(tAllLines));
		//fail("Not yet implemented");
	}
	
	

}