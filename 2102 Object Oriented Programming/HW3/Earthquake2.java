import java.util.LinkedList;

/**
 * Class to hold methods on earthquake data.
 */
class Earthquake2 {
  Earthquake2(){}
      
  // checks whether a datum is a date
  boolean isDate(double anum) { return (int)anum > 10000000; }
  // extracts the month from an 8-digit date
  int extractMonth(double dateNum) { return ((int)dateNum % 10000) / 100; }

  /**
   * Takes a year's data and a month and gives a list of reports of maximum readings for days in that month.
   * @param data Year's reading data.
   * @param month Desired month to highlight.
   * @return A list of reports about the max readings of each day of the desired month.
   */
  public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data,
                                                  int month) {
    LinkedList<MaxHzReport> dailyReports = this.reportMaker(data);
    LinkedList<MaxHzReport> desiredReports = new LinkedList<MaxHzReport>();
    for(MaxHzReport rep : dailyReports) {
      if(this.extractMonth(rep.date) == month) {
        desiredReports.add(rep);
      }
    }
    return desiredReports;
  }

  /**
   * Takes a list of readings for a day and produces the max reading
   * @param readings The readings from a day
   * @return The maximum reading
   */
  public double maxReading(LinkedList<Double> readings) {
    double max = 0;
    for(double read : readings) {
      if(read > max) {
        max = read;
      }
    }
    return max;
  }

  /**
   * Turns a list of a year's readings into a list of daily reports.
   * @param data The year's data.
   * @return A list of daily reports with maximum readings.
   */
  public LinkedList<MaxHzReport> reportMaker(LinkedList<Double> data) {
    Double date = 0.0;
    LinkedList<Double> readings = new LinkedList<Double>();
    LinkedList<MaxHzReport> reportList = new LinkedList<MaxHzReport>();
    int checkStart = 1;
    for(double num : data) {
      if(this.isDate(num)) { //Use the individual dates to determine when a day's set of readings ends,
        //and therefore when to add a new report.
        if(checkStart == 0) {
          reportList.add(new MaxHzReport(date, this.maxReading(readings))); //This adds the reports to the final list
          date = num;
          readings = new LinkedList<Double>();
        } else{
          date = num;
          readings = new LinkedList<Double>();
          checkStart = 0;
        }
      } else {
        readings.add(num);
        if(data.indexOf(num) == data.size()-1) { //For the last reading in the list
          reportList.add(new MaxHzReport(date, this.maxReading(readings)));
        }
      }
    }
    return reportList;
  }
}