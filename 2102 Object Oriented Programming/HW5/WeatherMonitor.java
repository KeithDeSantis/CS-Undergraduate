import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * A monitor that holds lists of daily reports and can get data on weather patterns.
 */
public class WeatherMonitor {

    private IReports reports;

    public WeatherMonitor(IReports reports) {
        this.reports = reports;
    }

    /**
     * Adds a new daily report to the weather monitor's list.
     * @param date The date of the report.
     * @param readings The list of readings for that date.
     */
    public void addDailyReport(GregorianCalendar date, LinkedList<Reading> readings) {
        reports.addElt(new DailyWeatherReport(date, readings));
    }

    /**
     * Takes a list of reports and a month and parses out the reports from that month.
     * @param month The month of interest.
     * @param year The year in question.
     * @return A linked list of reports from that month.
     */
    public LinkedList<DailyWeatherReport> getMonthReports(int month, int year) {
        return this.reports.getMonthReports(month, year);
    }

    /**
     * Gets the average temperature for a certain month in the monitor's data.
     * @param month The month in question.
     * @return The average temperature during that month based on the monitor's data.
     */
    public double averageTempForMonth(int month, int year) {
        LinkedList<DailyWeatherReport> monthReports = this.getMonthReports(month, year);
        int count = 0;
        double sum = 0;
        for(int i = 0; i < monthReports.size(); i++) {
            sum = sum + monthReports.get(i).avgTemp();
            count++;
        }
        if(count == 0){ return 0; } // Maybe throw an exception?
        else { return sum/count; }
    }

    /**
     * Gets the total amount of rain in a month.
     * @param month The month in question.
     * @return The total rain from that month based on the monitor's data.
     */
    public double totalRainfallForMonth(int month, int year) {
        LinkedList<DailyWeatherReport> monthReports = this.getMonthReports(month, year);
        double totalRain = 0;
        for(int i = 0; i < monthReports.size(); i++) {
            totalRain = totalRain + monthReports.get(i).totalRainfall();
        }
        return totalRain;
    }

    // Getter
    /**
     * Gets the list of reports the monitor has.
     * @return Monitor's list of reports.
     */
    public IReports getReports() {return this.reports;}
}
