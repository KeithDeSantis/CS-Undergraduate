import java.util.LinkedList;

/**
 * A linked list of daily weather reports.
 */
public class DailyWeatherReportLL implements IReports{

    LinkedList<DailyWeatherReport> reports;

    public DailyWeatherReportLL() {
        this.reports = new LinkedList<DailyWeatherReport>();
    }

    /**
     * Overloaded constructor in case a given list is desired.
     * @param list The given list.
     */
    public DailyWeatherReportLL(LinkedList<DailyWeatherReport> list) {
        this.reports = list;
    }

    /**
     * Overridden equals for testing.
     * @param o Another linked list of daily weather reports.
     * @return True if the lists stored in each are equal by the definition written in
     * the daily weather report class.
     */
    public boolean equals(Object o) {
        DailyWeatherReportLL LL = (DailyWeatherReportLL) o;
        if(this.reports.equals(LL.reports)) { return true; }
        else { return false;}
    }

    /**
     * Appends a report to the end of the list.
     * @param rep The report being appended.
     */
    public void addElt(DailyWeatherReport rep) {this.reports.add(rep);}

    /**
     * Gets reports from the given month and year.
     * @param month The month in question.
     * @param year The year in question/
     * @return A linked list of daily weather reports of only the month and year.
     */
    public LinkedList<DailyWeatherReport> getMonthReports(int month, int year) {
        LinkedList<DailyWeatherReport> monthReports = new LinkedList<DailyWeatherReport>();
        for(int i = 0; i < reports.size(); i++) { // Parsing through for reports in the month.
            if(reports.get(i).isMonthAndYear(month, year)) {
                monthReports.add(reports.get(i));
            }
        }
        return monthReports;
    }

    // Below are "dummy methods".  They have to be included since DailyWeatherReportLL implements IReports, but
    // when they are actually called it is only ever on objects that are DailyWeatherReports, and therefore
    // will use the overridden method implementations in the DailyWeatherReports class.
    public boolean isMonthAndYear(int month, int year) {return false;};

    public double avgTemp() {return 0.0;};

    public double totalRainfall() {return 0.0;};

    public int size() {return this.reports.size();}
}
