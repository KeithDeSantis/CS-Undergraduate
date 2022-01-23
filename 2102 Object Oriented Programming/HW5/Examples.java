import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.*;
import static org.junit.Assert.*;

public class Examples {

    Time time1 = new Time(6, 30);
    Time time2 = new Time(8, 30);
    Time time3 = new Time(12,0);
    Time time4 = new Time(20, 30);
    Time time5 = new Time(23, 0);
    Time time6 = new Time(1, 43);
    Time time7 = new Time(2, 34);
    Time time8 = new Time(13, 40);

    Reading reading1 = new Reading(time1, 43.2, 0.0);
    Reading reading2 = new Reading(time2, 44.0, 2.2);
    Reading reading3 = new Reading(time3, 56.0, 3.2);
    Reading reading4 = new Reading(time4, 53.3, 0.1);
    Reading reading5 = new Reading(time5, 43.3, 0.2);
    Reading reading6 = new Reading(time6, 21.2, 4);
    Reading reading7 = new Reading(time7, 12.4, 32);
    Reading reading8 = new Reading(time8, 70.4, 0.3);

    GregorianCalendar May1 = new GregorianCalendar(2020, 4, 1);
    GregorianCalendar May4 = new GregorianCalendar(2020, 4, 4);
    GregorianCalendar May23 = new GregorianCalendar(2000, 4, 23);
    GregorianCalendar June4 = new GregorianCalendar(2020, 5, 4);

    WeatherMonitor weatherMonitor = new WeatherMonitor(new DailyWeatherReportLL());

    // Add Daily Report Tests
    @Test
    public void testAddDailyReport() {
        LinkedList<Reading> readings = new LinkedList<Reading>();
        readings.add(reading1);
        LinkedList<DailyWeatherReport> checkList = new LinkedList<DailyWeatherReport>();
        checkList.add(new DailyWeatherReport(May1, readings));
        DailyWeatherReportLL checker = new DailyWeatherReportLL(checkList);
        weatherMonitor.addDailyReport(May1, readings);
        assertEquals(weatherMonitor.getReports(), checker);

    }
    @Test
    public void testAddDailyEmpty() {
        assertEquals(weatherMonitor.getReports(), new DailyWeatherReportLL());
    }
    // Average Temperature for Month Tests
    @Test
    public void testAvgTempMonth() {
        LinkedList<Reading> readings = new LinkedList<Reading>();
        readings.add(reading1);
        readings.add(reading2);
        LinkedList<Reading> readings2 = new LinkedList<Reading>();
        readings2.add(reading4);
        readings2.add(reading7);
        LinkedList<Reading> readings3 = new LinkedList<Reading>();
        readings3.add(reading6);
        weatherMonitor.addDailyReport(June4, readings3);
        weatherMonitor.addDailyReport(May1, readings);
        weatherMonitor.addDailyReport(May4, readings2);
        assertEquals(weatherMonitor.averageTempForMonth(4, 2020), 38.225, 0.1);

    }
    @Test
    public void testAvgTempMonthWrongMonth() {
        LinkedList<Reading> badList = new LinkedList<Reading> ();
        badList.add(reading1);
        weatherMonitor.addDailyReport(June4, badList);
        assertEquals(weatherMonitor.averageTempForMonth(4,2020), 0, 0.1);
    }
    @Test
    public void testAvgTempMonthWrongYear() {
        LinkedList<Reading> badList = new LinkedList<Reading> ();
        badList.add(reading1);
        weatherMonitor.addDailyReport(May23, badList);
        assertEquals(weatherMonitor.averageTempForMonth(4,2020), 0, 0.1);
    }
    @Test
    public void testAvgTempMonthEmpty() {
        assertEquals(weatherMonitor.averageTempForMonth(4,2020), 0, 0.1);
    }
    // Total Rainfall for Month Tests
    @Test
    public void testTotalRainfallForMonth() {
        LinkedList<Reading> readings = new LinkedList<Reading>();
        readings.add(reading1);
        readings.add(reading2);
        LinkedList<Reading> readings2 = new LinkedList<Reading>();
        readings2.add(reading4);
        readings2.add(reading7);
        LinkedList<Reading> readings3 = new LinkedList<Reading>();
        readings3.add(reading6);
        weatherMonitor.addDailyReport(June4, readings3);
        weatherMonitor.addDailyReport(May1, readings);
        weatherMonitor.addDailyReport(May4, readings2);
        assertEquals(weatherMonitor.totalRainfallForMonth(4,2020),34.4,0.1);
    }
    @Test
    public void testTotalRainfallForMonthWrongMonth() {
        LinkedList<Reading> readings3 = new LinkedList<Reading>();
        readings3.add(reading6);
        weatherMonitor.addDailyReport(June4, readings3);
        assertEquals(weatherMonitor.totalRainfallForMonth(4,2020),0,0.1);
    }
    @Test
    public void testTotalRainfallForMonthWrongYear() {
        LinkedList<Reading> readings3 = new LinkedList<Reading>();
        readings3.add(reading6);
        weatherMonitor.addDailyReport(May23, readings3);
        assertEquals(weatherMonitor.totalRainfallForMonth(4,2020),0,0.1);
    }
    @Test
    public void testTotalRainfallForMonthEmpty() {
        assertEquals(weatherMonitor.totalRainfallForMonth(4,2020),0,0.1);
    }

}
