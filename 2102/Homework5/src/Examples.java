import static org.junit.Assert.*;
        import org.junit.Before;
        import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;


public class Examples {

    public Examples() {}
    LinkedList<Double> temps;
    LinkedList<Double> temps2;
    LinkedList<Double> rainfall;
    LinkedList<Double> rainfall2;

    WeatherMonitor wm;
    WeatherMonitor wm1;


    LinkedList<Reading> reading;
    LinkedList<Time> times;




    @Before
    public void setup(){
        temps =     new LinkedList<Double>(Arrays.asList(35.7, 38.0, 38.0, 37.6, 32.0));
        temps2 =    new LinkedList<Double>(Arrays.asList(25.7, 28.0, 28.0, 27.6, 22.0));
        rainfall =  new LinkedList<Double>(Arrays.asList(4.6, 5.8, 3.8, 2.8, 4.3));
        rainfall2 = new LinkedList<Double>(Arrays.asList(5.6, 6.8, 4.8, 3.8, 5.3));
        reading =   new LinkedList<Reading>();
        times = new LinkedList<Time>(Arrays.asList(
                new Time(4, 13, false),
                new Time(5, 15, false),
                new Time(6,20,false),
                new Time(7, 45, false),
                new Time(8, 55, false) ));

        wm = new WeatherMonitor(new DailyWeatherReport(new GregorianCalendar(2019, Calendar.APRIL, 15), temps, rainfall));
        for (int i = 0; i < 5; i++) {
            reading.add(new Reading(temps2.get(i), rainfall2.get(i), times.get(i)));
        }

        wm.addDailyReport(new GregorianCalendar(2018, Calendar.MARCH, 13), reading);
        wm1 = new WeatherMonitor(new DailyWeatherReport(new GregorianCalendar(2006, Calendar.JULY, 12), new LinkedList<Double>(), new LinkedList<Double>()));

    }

    @Test
    public void testAverageTempForMonth(){
        assertEquals(wm.averageTempForMonth(3, 2019), 36.26, .1);
        assertEquals(wm.averageTempForMonth(2,2018),26.26,.1);
        assertEquals(wm1.averageTempForMonth(6, 2006), 0, .1);//no data
        assertEquals(wm1.averageTempForMonth(0,2001),0,.1); //does not exist
    }

    @Test
    public void testTotalRainfallForMonth(){
        assertEquals(wm.totalRainfallForMonth(3, 2019), 21.3, .1);
        assertEquals(wm.totalRainfallForMonth(2, 2018), 26.3, .1);
        assertEquals(wm1.totalRainfallForMonth(6, 2006), 0, .1);//no data
        assertEquals(wm1.totalRainfallForMonth(4,2098), 0, .1); //does not exist
    }




}
