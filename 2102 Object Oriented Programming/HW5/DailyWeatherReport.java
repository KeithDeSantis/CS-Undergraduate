import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * A report containing all readings from a given date.
 */
public class DailyWeatherReport {

    private GregorianCalendar date;

    private LinkedList<Double> temps;

    private LinkedList<Double> rainfalls;

    public DailyWeatherReport(GregorianCalendar date, LinkedList<Double> temps, LinkedList<Double> rainfalls) {
        this.date = date;
        this.temps = temps;
        this.rainfalls = rainfalls;
    }

    /**
     * An overloaded constructor that makes a daily report based on a date and that day's list of
     * reading.
     * @param date The date of the report.
     * @param readings That day's list of readings.
     */
    public DailyWeatherReport(GregorianCalendar date, LinkedList<Reading> readings) {
        LinkedList<Double> temp = new LinkedList<Double>();
        LinkedList<Double> rainfall = new LinkedList<Double>();
        for (int i = 0; i < readings.size(); i++) {
            temp.add(readings.get(i).getTemp());
            rainfall.add(readings.get(i).getRainfall());
        }
        this.date = date;
        this.temps = temp;
        this.rainfalls = rainfall;
    }

    /**
     * Overriding equals to make two daily reports equal if their fields are the same.
     * @param o The daily report being compared.
     * @return True if the fields of both reports have the same values/
     */
    public boolean equals(Object o) {
        DailyWeatherReport rep = (DailyWeatherReport) o;
        return this.date.equals(rep.date) &&
                this.rainfalls.equals(rep.rainfalls) &&
                this.temps.equals(rep.temps);
    }

    /**
     * Checks if the report is from the given year and month.
     * @param month The month in question.
     * @param year The year in question.
     * @return Returns true if the report does fall in that month of that year.
     */
    public boolean isMonthAndYear(int month, int year) {
        return this.date.get(GregorianCalendar.MONTH) ==  month && this.date.get(GregorianCalendar.YEAR) ==  year;
    }

    /**
     * Calculates the average temperature for the day.
     * @return The day's average temperature.
     */
    public double avgTemp() {
        int count = 0;
        double sum = 0;
        for(int i = 0; i < this.temps.size(); i++) {
            count++;
            sum = sum + temps.get(i);
        }
        return sum/count;
    }

    /**
     * Tallies total rain for the day.
     * @return The total amount of rain for the day.
     */
    public double totalRainfall() {
        double total = 0;
        for(int i = 0; i < this.rainfalls.size(); i++) {
            total = total + this.rainfalls.get(i);
        }
        return total;
    }

}
