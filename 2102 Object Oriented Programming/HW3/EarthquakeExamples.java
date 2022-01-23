import static org.junit.Assert.*;

import jdk.jfr.StackTrace;
import org.junit.Test;
import java.util.LinkedList;

public class EarthquakeExamples {
  Earthquake1 E1 = new Earthquake1();
  LinkedList<Double> noData = new LinkedList<Double>();
  LinkedList<Double> threeDates = new LinkedList<Double>();
  LinkedList<MaxHzReport> NovReports = new LinkedList<MaxHzReport>();

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
    threeDates.add(-5.3);
    NovReports.add(new MaxHzReport(20151101.0, 6.0));
  }

  @Test
  public void monthAndNegativeFilterTest() {
    LinkedList<Double> expected = new LinkedList<Double>();
    expected.add(20151013.0);
    expected.add(10.0);
    expected.add(5.0);
    expected.add(20151020.0);
    expected.add(40.0);
    expected.add(50.0);
    expected.add(45.0);
    assertEquals(E1.monthAndNegativeFilter(threeDates, 10), expected);
  }

  @Test
  public void maxReadingTest() {
    assertEquals(E1.maxReading(threeDates), 20151101.0, 0.1);
  }

  @Test
  public void instructorTestEQ() {
    assertEquals(NovReports,
            E1.dailyMaxForMonth(threeDates, 11));
  }

  @Test
  public void emptyMaxForMonths1Test() {
    assertEquals(new LinkedList<MaxHzReport>(), E1.dailyMaxForMonth(noData, 10));
  }
}

/* Earthquake1 Subtasks:
1) Create a list with all non-negative data from the desired month.
2) For each day's set of data in the new list:
    find the maximum reading,
    create a new MaxHzReport and add it to a list of reports.
3) Return list of reports.
*/

/* Earthquake2 Subtasks:
1) For each day:
    find the maximum,
    create a new MaxHzReport and add it to a list of reports
2) For each report in the list:
    check if the report's date falls in the desired month and add it to a new list if it does.
3) Return new list of reports.
 */

