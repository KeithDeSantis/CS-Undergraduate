import java.util.GregorianCalendar;
import java.util.LinkedList;

public interface IWeatherReport {

    public GregorianCalendar getDate();

    public double getTemp();

    public double getRainfall();
}
