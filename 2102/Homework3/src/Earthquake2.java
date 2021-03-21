import java.util.LinkedList;

/**
 * The class represents a program that finds the daily max values (based off of senor reading) from a single month
 */
class Earthquake2 {
  Earthquake2() {
  }

  // checks whether a datum is a date
  boolean isDate(double anum) {
    return (int) anum > 10000000;
  }

  // extracts the month from an 8-digit date
  int extractMonth(double dateNum) {
    return ((int) dateNum % 10000) / 100;
  }

  /**
   * Generate MaxHzReports for eachday in a given month
   * @param data LinkedList of Double
   * @param month integer 1-12
   * @return LinkedList of MaxHzReport
   */
  public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
    LinkedList<Double> cleanData = cleanData(data, month);
    LinkedList<Double> dates = getDates(cleanData);
    LinkedList<Double> maxes = new LinkedList<Double>();
    LinkedList<MaxHzReport> reports = new LinkedList<MaxHzReport>();

    for (Double date :
            dates) {
      maxes.add(getMaxForDay(cleanData, date));
    }


    for (Double datum :
            maxes) {
      reports.add(genRep(datum, cleanData));
    }


    return reports;
  }

  /**
   * Cleans the data to only contain data points from the correct month
   * @param data LinkedList of Double
   * @param month integer 1-12
   * @return LinkedList of Double containing only prevalent data
   */
  public LinkedList<Double> cleanData(LinkedList<Double> data, int month) {
    boolean inRange = false;
    LinkedList<Double> cleanData = new LinkedList<Double>();
    LinkedList<Double> toRemove = new LinkedList<Double>();
    for (Double datum :
            data) {
      if (isDate(datum) && extractMonth(datum) == month) {
        inRange = true;
      }else if(isDate(datum) && extractMonth(datum) != month){
        inRange = false;
      }
      if (inRange) {
        cleanData.add(datum);
      }
    }
    for (Double datum :
            cleanData) {
      if (datum < 0) {
        toRemove.add(datum);
      }
    }
    cleanData.removeAll(toRemove);
    return cleanData;
  }

  /**
   * Strips the LinkedList of Doubles of everything but values representing dates
   * @param data LinkedList of Double
   * @return LinkedList of Double containing the dates of the prevalent data
   */
  public LinkedList<Double> getDates(LinkedList<Double> data) {
    LinkedList<Double> dates = new LinkedList<Double>();
    for (Double datum :
            data) {
      if (isDate(datum)) {
        dates.add(datum);
      }
    }
    return dates;
  }

  /**
   * Gets the date when a datum was recorded
   * @param datum Double
   * @param data
   * @return Double that represents a date
   */
  public Double getDateOf(double datum, LinkedList<Double> data) {
    double currDate = -1;

    for (Double element :
            data) {
      if (isDate(element)) {
        currDate = element;
      } else if (element == datum) {
        return currDate;
      }
    }
    return -1.0;
  }

  /**
   * Determines the Max value for the given day
   * @param data LinkedList of Doubles
   * @param date Double representing the date year-month-day
   * @return
   */
  public double getMaxForDay(LinkedList<Double> data, double date) {
    LinkedList<Double> dataToMessWith = new LinkedList<Double>();
    for (Double datum :
            data) {
      dataToMessWith.add(datum);
    }
    LinkedList<Double> dataForTheDay = new LinkedList<Double>();
    int dateIndex = data.indexOf(date);
    while (dataToMessWith.getFirst() != data.get(dateIndex + 1)) {
      dataToMessWith.removeFirst();
    }
    int nextDateIndex = dataToMessWith.size();
    for (Double datum :
            dataToMessWith) {
      if(getDates(dataToMessWith).size()>0){
        nextDateIndex = dataToMessWith.indexOf(getDates(dataToMessWith).get(0));
      }

    }



    while(dataToMessWith.size() > nextDateIndex){
      dataToMessWith.removeLast();
    }

    for (Double datum :
            dataToMessWith) {
      if (!isDate(datum)) {
        dataForTheDay.add(datum);
      }
    }
    return getMaxForDayHelper(dataForTheDay);
  }

  /**
   * Returns the max value in the input LinkedList
   * @param data LinkedList of Double containing the data for one day only
   * @return The max value Double
   */
  public double getMaxForDayHelper(LinkedList<Double> data) {
    double max = -1;
    for (Double datum : data) {
      if (datum > max) {
        max = datum;
      }
    }
    return max;
  }

  /**
   * Generates a single MaxHzReport
   * @param max Double: the max data for the day
   * @param data Used for obtaining the date for the max value
   * @return MaxHzReport using the max value and the date
   */
  public MaxHzReport genRep(double max, LinkedList<Double> data) {
    return new MaxHzReport(getDateOf(max, data), max);
  }


  /**
   * Sub-tasks:
   * Method 2:
   * -Clean the data so that we only have to deal with data pertaining to the input month
   * -Get a list of all the dates that we need to create a MaxHzReport for
   * -Find the max values for all the dates
   * -Generate a MaxHzReport for each day using the max value of each day from above and use geDateOf to get the date of the datum
   */


}

