import java.util.LinkedList;

/**
 * Class to hold methods on earthquake data.
 */
class Earthquake1 {
  Earthquake1(){}

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
  public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
    LinkedList<Double> cleanedData = this.monthAndNegativeFilter(data, month); // List with unwanted months and negatives filtered out
    Double date = 0.0; // Variable to store day we're looking at
    LinkedList<Double> readings = new LinkedList<Double>(); // List to store readings from day we're looking at
    LinkedList<MaxHzReport> reportList = new LinkedList<MaxHzReport>(); // List of final reports
    int checkStart = 1; // Variable to avoid adding nonsense report of [0.0, 0.0] at beginning of list
    //This is needed since I'm using dates as indicators for when a previous day has ended
    //and when a new report should be made, so when I hit the first date in the list
    //it automatically creates a new empty report.
    for(double num : cleanedData) {
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
          if(cleanedData.indexOf(num) == cleanedData.size()-1) { //For the last reading in the list
            reportList.add(new MaxHzReport(date, this.maxReading(readings)));
        }
      }
    }
    return reportList;
  }

  /**
   * Takes a list of readings from a year and produces a list of only positive readings from a given month.
   * @param data Year's readings
   * @param month Desired month
   * @return List of data from desired month.
   */
  public LinkedList<Double> monthAndNegativeFilter(LinkedList<Double> data, int month) {
    LinkedList<Double> returnedList = new LinkedList<Double>();
    boolean continueAdding = false;
    for(double num : data) {
      if(this.isDate(num)){
        if(this.extractMonth(num) == month) {
          returnedList.add(num);
          continueAdding = true;
        } else {
          continueAdding = false;
        }
      }else {
        if(continueAdding == true && num > 0) {
          returnedList.add(num);
        }
      }
    }
    return returnedList;
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

}  

