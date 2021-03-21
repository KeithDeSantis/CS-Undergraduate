import static org.junit.Assert.*;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class DataSmoothExamples 
{  
  LinkedList<Show> shows = new LinkedList<Show>();
  LinkedList<Double> showResults = new LinkedList<Double>();
  DataSmooth1 D1 = new DataSmooth1();
  LinkedList<Show> threeShows = new LinkedList<Show>();
	LinkedList<Show> noShows = new LinkedList<Show>();
	LinkedList<Double> noShowResults = new LinkedList<Double>();
	LinkedList<Double> threeShowResults = new LinkedList<Double>();
  
  public DataSmoothExamples() 
  {
		LinkedList<Episode> eps1 = new LinkedList<Episode>();
		eps1.add(new Episode("Best of Both Worlds", 88));
		eps1.add(new Episode("The Ultimate Computer", 49));
		eps1.add(new Episode("Trials and Tribble-ations", 43));		
		shows.add(new Show("Star Trek", 1800, eps1, false));
		
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		shows.add(new Show("Futurama", 1900, eps2, false));
		
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		shows.add(new Show("Animaniacs", 1630, eps3, false));
		
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		shows.add(new Show("Sesame Street", 900, eps4, false));

	    showResults.add(60.0);
	    showResults.add(29.75); //22.25
	    showResults.add(29.08333); //7
	    showResults.add(58.0);

	    threeShows.add(new Show("Star Trek", 1800, eps1, false));
	    threeShows.add(new Show("Futurama", 1900, eps2, false));
	    threeShows.add(new Show("Animaniacs", 1630, eps3, false));

	  threeShowResults.add(60.0);
	  threeShowResults.add(29.75); //22.25
	  threeShowResults.add(7.0);
  }
  
  @Test
  /**
   * method1 sub-tasks:
   * get number of episodes for each show
   * divide each show length by the num episodes
   * add them all together to get total average
   * add each into list of averages
   * smooth the data
   *
   * method2 sub-tasks2:
   * -Generate the average run time for a show
   * -Add that average to a list
   * -Smooth all but the first and last items in the list
   *      by taking the item before and after and summing it together with the current datum and then dividing by 3
   */
  public void instructorTestDS() 
  {
	  LinkedList<Double> runtimes = D1.dataSmooth(shows);
	  
	  for(int i = 0; i < runtimes.size(); i++)
	  {
		  assertEquals(runtimes.get(i), showResults.get(i), .01);
	  }
  }
  @Test
  public void studentTests(){
	  LinkedList<Double> runtimes2 = D1.dataSmooth(threeShows);
	  for(int i = 0; i < runtimes2.size(); i++) {
		  assertEquals(runtimes2.get(i), threeShowResults.get(i), .01);
	  }
	  LinkedList<Double> runtimes3 = D1.dataSmooth(noShows);
	  for(int i = 0; i < runtimes3.size(); i++) {
		  assertEquals(runtimes3.get(i), noShowResults.get(i), .01);
	  }
  }
  
}