import java.util.GregorianCalendar;
import java.util.LinkedList;

public class DailyWeatherReport implements IWeatherReport{
    private GregorianCalendar date;
    private LinkedList<Double> temp;
    private LinkedList<Double> rainfall;

    public DailyWeatherReport(GregorianCalendar date, LinkedList<Double> temp, LinkedList<Double> rainfall) {
        this.date = date;
        this.temp = temp;
        this.rainfall = rainfall;
    }

    public double getTemp() {
        double total = 0.0;
        int elemt = 0;
        for(Double d : temp){
            total+=d;
            elemt++;
        }

        if(elemt==0){
            return 0;
        }
        return total/elemt;
    }

    public double getRainfall() {
        double total = 0.0;
        for (Double d : rainfall) {
            total += d;
        }
        return total;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
