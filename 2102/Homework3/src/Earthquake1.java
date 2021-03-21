import javax.swing.event.ListDataEvent;
import java.util.LinkedList;

/**
 * The class represents a program that finds the daily max values (based off of senor reading) from a single month
 */
class Earthquake1 {
    Earthquake1() {
    }

    boolean isDate(double anum) {
        return (int) anum > 10000000;
    }

    int extractMonth(double dateNum) {
        return ((int) dateNum % 10000) / 100;
    }
    LinkedList<MaxHzReport> rest(LinkedList<MaxHzReport> list){
      list.remove(0);
      return list;
    }

    /**
     * creates a list of the maximum Hz readings for each day of a selected month
     * @param data linkedlist of doubles representing input from a sensor
     * @param month integer representing the month that info is desired for
     * @return LinkedList of MaxHzReport showing the date and max Hz for that day
     */
    public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
        LinkedList<Double> cleanData = cleanData(data, month); // cleans data
        LinkedList<MaxHzReport> maxHz = new LinkedList<MaxHzReport>();
        double dateMax = -1; // initializes to have a number lower than all maxes
        double currentDate = -1; // initializes to have a bad date
        for(Double datum: cleanData){
            if(isDate(datum)){
                if(isDate(currentDate)){ // used to not create false MaxHzReport on first date
                    maxHz.add(new MaxHzReport(currentDate, dateMax));
                }
                currentDate = datum; // sets new date for cycle
                dateMax = -1; //reset cycle to check maxes
            }else if(datum > dateMax){
                dateMax = datum; //set current max
            }
        }if(isDate(currentDate)){ // used to not create false MaxHzReport on first date
            maxHz.add(new MaxHzReport(currentDate, dateMax)); // instantiates last date
        }
        return maxHz;
    }

    /**
     * helper for dailyMaxForMonth that cleans the data to only the relevant data
     * @param data linkedlist of doubles representing input from a sensor
     * @param month integer representing the month that info is desired for
     * @return LinkedList of doubles containing only the datum that are legitimate and
     *          for the correct month
     */
    public LinkedList<Double> cleanData(LinkedList<Double> data, int month) {
        LinkedList<Double> cleanData = new LinkedList<Double>();
        boolean goodData = false;
        for (double datum: data){
            if(isDate(datum)){
                if(extractMonth(datum) == month){
                    goodData = true;
                }else{
                    goodData = false;
                }
            }if(datum > 0 && goodData){
                cleanData.add(datum);
            }
        }return cleanData;
    }
}  

