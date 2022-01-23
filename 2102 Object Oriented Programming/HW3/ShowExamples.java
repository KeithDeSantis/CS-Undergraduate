import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;

public class ShowExamples 
{
	ShowManager1 sm1 = new ShowManager1();
	LinkedList<Show> shows = new LinkedList<Show>();
	LinkedList<Show> empty = new LinkedList<Show>();
	ShowSummary report1 = new ShowSummary();
	
	public ShowExamples()
	{
		LinkedList<Episode> eps1 = new LinkedList<Episode>();
		eps1.add(new Episode("Best of Both Worlds", 88));
		eps1.add(new Episode("The Ultimate Computer", 49));
		eps1.add(new Episode("Trials and Tribble-ations", 43));		
		Show s1 = new Show("Star Trek", 1800, eps1, false);
		shows.add(s1);
		report1.primetime.add(s1);
		
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		Show s2 = new Show("Futurama", 1900, eps2, false);
		shows.add(s2);
		report1.primetime.add(s2);
		
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		Show s3 = new Show("Animaniacs", 1630, eps3, false);
		shows.add(s3);
		report1.daytime.add(s3);
		
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		Show s4 = new Show("Sesame Street", 900, eps4, false);
		shows.add(s4);
		report1.daytime.add(s4);

		LinkedList<Episode> eps5 = new LinkedList<Episode>();
		eps5.add(new Episode("Christmas Special", 120));
		Show s5 = new Show("Star Wars Christmas Special", 900, new LinkedList<Episode>(), true);
		shows.add(s5);

		LinkedList<Episode> eps6 = new LinkedList<Episode>();
		eps6.add(new Episode("s1e1", 40));
		eps6.add(new Episode("s1e2", 40));
		Show s6 = new Show("Late Night with Bob", 200, new LinkedList<Episode>(), false);
		shows.add(s6);
	}
	
	@Test
	public void instructorTestOrganizeShows() 
	{
		ShowSummary report2 = sm1.organizeShows(shows);
		assertEquals(report1, report2);
	}

	@Test
	public void organizeShows1Empty() {
		ShowSummary report4 = sm1.organizeShows(empty);
		assertEquals(new ShowSummary(), report4);
	}

}

/*
ShowManager1 Subtasks:
1) for each show in list
2)		check if show is special
			if not:
3)			check if show is daytime
				add to daytime list
4)			check if show is primetime
				add to primetime list
5)			check is show is late night
				add to late night list
6) return show summary with proper lists.
*/

/*
ShowManager2 Subtasks:
1) sort list into daytime shows and non-daytime shows
2) sort non-daytime shows into primetime and non-primetime shows
3) pull late night shows out of non-primetime shows
4) remove specials from each list using helper
5) return summary with daytime, primetime, and late night lists
 */
