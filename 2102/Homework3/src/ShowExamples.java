import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;
import java.util.Arrays;

public class ShowExamples 
{
	ShowManager1 sm1 = new ShowManager1();
	LinkedList<Show> shows = new LinkedList<Show>();
	ShowSummary report1 = new ShowSummary();
	ShowSummary newReport = new ShowSummary();
	LinkedList<Show> threeShows = new LinkedList<Show>();
	LinkedList<Show> noShows = new LinkedList<Show>();
	ShowSummary noReport = new ShowSummary();

	public ShowExamples()
	{
		LinkedList<Episode> eps1 = new LinkedList<Episode>();
		eps1.add(new Episode("Best of Both Worlds", 88));
		eps1.add(new Episode("The Ultimate Computer", 49));
		eps1.add(new Episode("Trials and Tribble-ations", 43));		
		Show s1 = new Show("Star Trek", 1800, eps1, false);
		shows.add(s1);
		report1.primetime.add(s1);
		newReport.primetime.add(s1);
		
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		Show s2 = new Show("Futurama", 1900, eps2, false);
		shows.add(s2);
		report1.primetime.add(s2);
		newReport.primetime.add(s2);

		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		Show s3 = new Show("Animaniacs", 1630, eps3, false);
		shows.add(s3);
		report1.daytime.add(s3);
		newReport.daytime.add(s3);

		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		Show s4 = new Show("Sesame Street", 900, eps4, false);
		shows.add(s4);
		report1.daytime.add(s4);

		threeShows.add(s1);
		threeShows.add(s2);
		threeShows.add(s3);

	}
	
	@Test
	/**
	 * method1 sub-tasks:
	 * put daytime shows into daytime list
	 * put primetime shows into primetime list
	 * put latenight shows into latenight list
	 * create a ShowSummary using the constructor with 3 lists
	 *
	 * method2 sub-tasks:
	 * -Create a ShowSummary that will contain all shows
	 * -Sort the shows one by one into the ShowSummary
	 */
	public void instructorTestOrganizeShows() 
	{
		ShowSummary report2 = sm1.organizeShows(shows);
		assertEquals(report1, report2);
	}
	@Test
	public void studentTestOrganizeShows()
	{
		assertEquals(newReport, sm1.organizeShows(threeShows));
		assertEquals(noReport, sm1.organizeShows(noShows));

	}

}
