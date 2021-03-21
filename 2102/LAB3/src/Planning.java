

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Planning {

    public Planning() {
    }

    /**
     * Computes the average rainfall of all data deemed 'correct'
     * @param rainfall list of collected rainfall data
     * @return double: average rainfall
     */
    public Double rainfall(LinkedList<Double> rainfall) {
        LinkedList<Double> data = rainfallClean(rainfall);
        if (!(data.size() > 1)) {
            return -1.0;
        }
        Double total = 0.0;
        for (Double element :
                data) {

            total += element;
        }
        return total / data.size();

    }


    /**
     * Removes all extraneous data from the list, leaving only the correct data to be averaged
     * @param rainfall list of collected rainfall data
     * @return list of Double
     */
    public LinkedList<Double> rainfallClean(LinkedList<Double> rainfall) {
        int last = rainfall.indexOf(-999.0);
        LinkedList<Double> toRemove = new LinkedList<Double>();
        while (rainfall.size() > last) {
            rainfall.removeLast();
        }
        for (Double element :
                rainfall) {
            if (element < 0) {
                toRemove.add(element);
            }
            }
        rainfall.removeAll(toRemove);
        return rainfall;
    }

    @Test
    public void checkRainfall() {
        LinkedList<Double> testList1 =
                new LinkedList<Double>(Arrays.asList(1.0, -2.0, 5.0, -999.0, 8.0));
        LinkedList<Double> testList2 =
                new LinkedList<Double>(Arrays.asList(-999.0));
        LinkedList<Double> testList3 =
                new LinkedList<Double>(Arrays.asList(1.0, 5.0, 12.0, -999.0, -8.0, 12.0));
        LinkedList<Double> testList4 =
                new LinkedList<Double>(Arrays.asList(-3.0,-2.0,1.0, 5.0, 12.0, -999.0, -8.0, 12.0));

        assertEquals(rainfall(testList1), 3.0, .01);
        assertEquals(rainfall(testList2), -1.0, .01);
        assertEquals(rainfall(testList3), 6.0, .01);
        assertEquals(rainfall(testList4), 6.0, .01);


    }
}

