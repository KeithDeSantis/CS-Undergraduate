import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class EarthquakeExamples {
  Earthquake1 E1 = new Earthquake1();
  LinkedList<Double> noData = new LinkedList<Double>();
  LinkedList<Double> threeDates = new LinkedList<Double>();
  LinkedList<MaxHzReport> emptyList = new LinkedList<MaxHzReport>();
  LinkedList<MaxHzReport> NovReports = new LinkedList<MaxHzReport>();
  LinkedList<MaxHzReport> OctReports = new LinkedList<MaxHzReport>();
  LinkedList<Double> hwExample = new LinkedList<Double>(Arrays.asList(20151004.0, 200.0, 150.0, 175.0, 20151005.0, 0.002, 0.03, 20151007.0, 130.0, 0.54, 20151101.0, 78.0));
  LinkedList<MaxHzReport> hwExampleMaxes = new LinkedList<MaxHzReport>(Arrays.asList(new MaxHzReport(20151004, 200), new MaxHzReport(20151005, 0.03), new MaxHzReport(20151007, 130)));


  public EarthquakeExamples() {
    threeDates.add(20151013.0);
    threeDates.add(10.0);
    threeDates.add(5.0);
    threeDates.add(20151020.0);
    threeDates.add(40.0);
    threeDates.add(50.0);
    threeDates.add(45.0);
    threeDates.add(20151101.0);
    threeDates.add(6.0);
    NovReports.add(new MaxHzReport(20151101.0,6.0));
    OctReports.add(new MaxHzReport(20151013.0,10.0));
    OctReports.add(new MaxHzReport(20151020.0,50.0));
  }

  @Test
  /**
   * method1 sub-tasks:
   * clean data based on months
   * clean data based on negatives
   * find maxes for each date
   * create maxHzReports and add them to the list for each date
   *
   * method2 sub-tasks:
   * -Clean the data so that we only have to deal with data pertaining to the input month
   * -Get a list of all the dates that we need to create a MaxHzReport for
   * -Find the max values for all the dates
   * -Generate a MaxHzReport for each day using the max value of each day from above and use geDateOf to get the date of the datum
   */
  public void instructorTestEQ() { 
    assertEquals(NovReports, E1.dailyMaxForMonth(threeDates, 11));
  }
  @Test
  public void studentTests(){
    assertEquals(OctReports, E1.dailyMaxForMonth(threeDates, 10));
    assertEquals(emptyList, E1.dailyMaxForMonth(threeDates, 9));
    assertEquals(hwExampleMaxes, E1.dailyMaxForMonth(hwExample, 10));
    assertEquals(emptyList, E1.dailyMaxForMonth(noData, 8));
    assertEquals(emptyList, E1.dailyMaxForMonth(hwExample, -10));
  }

}
