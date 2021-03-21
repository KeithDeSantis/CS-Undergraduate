import java.util.GregorianCalendar;
import java.util.LinkedList;

public class WeatherMonitor {

    private LinkedList<IWeatherReport> dailyWeatherReports = new LinkedList<IWeatherReport>();

    public WeatherMonitor(IWeatherReport dailyWeatherReports) {
        this.dailyWeatherReports.add(dailyWeatherReports);
    }

    /**
     * Complies all data to get average temperature for a given moth
     *
     * @param month type int (1-12)
     * @param year  type int
     * @return type double representing the average temperature of the given month
     */
    public double averageTempForMonth(int month, int year) {
        LinkedList<Double> temps = new LinkedList<Double>();
        for (IWeatherReport report : dailyWeatherReports) {
            GregorianCalendar date = report.getDate();
            if (date.get(GregorianCalendar.YEAR) == year && date.get(GregorianCalendar.MONTH) == month) {
                temps.add(report.getTemp());
            }
        }
        double total = 0.0;
        int elemt = 0;

        for (Double d : temps) {
            total += d;
            elemt++;
        }

        if(elemt==0){
            return 0;
        }
        return total / elemt;
    }

    /**
     * Compiles all data to get total rainfall for the given month
     *
     * @param month type int (1-12)
     * @param year  type int
     * @return type double representing the total rainfall for the given month
     */
    public double totalRainfallForMonth(int month, int year) {
        LinkedList<Double> rainfall = new LinkedList<Double>();
        for (IWeatherReport report : dailyWeatherReports) {
            GregorianCalendar date = report.getDate();
            if (date.get(GregorianCalendar.YEAR) == year && date.get(GregorianCalendar.MONTH) == month) {
                rainfall.add(report.getRainfall());
            }
        }
        double total = 0.0;
        for (Double d : rainfall) {
            total += d;
        }
        return total;
    }

    /**
     * generates a DailyWeatherReport with the given information
     *
     * @param date type GregorianCalendar for the DailyWeatherReport
     * @param data type LinkedList<Readings> used to create the DailyWeatherReport
     */
    public void addDailyReport(GregorianCalendar date, LinkedList<Reading> data) {
        LinkedList<Double> temp = new LinkedList<Double>();
        LinkedList<Double> rainfall = new LinkedList<Double>();
        for (Reading r : data) {
            temp.add(r.getDegrees());
            rainfall.add(r.getRainfall());
        }
        dailyWeatherReports.add(new DailyWeatherReport(date, temp, rainfall));
    }

}
