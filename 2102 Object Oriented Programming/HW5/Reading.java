/**
 * A reading from a certain time in a day, containing the time, temperature in Farenheit, and rainfall since
 * last reading at the time of the readings.
 */
public class Reading {

    private Time time;

    private double temp;

    private double rainfall;

    public Reading(Time time, double temp, double rainfall) {
        this.time = time;
        this.temp = temp;
        this.rainfall = rainfall;
    }

    // Getters
    /**
     * Gets the time of a reading.
     * @return The reading's time.
     */
    public Time getTime() {return this.time;}
    /**
     * Gets the temperature of a reading.
     * @return The reading's temperature.
     */
    public double getTemp() {return this.temp;}
    /**
     * Gets the rainfall of a reading.
     * @return The reading's rainfall.
     */
    public double getRainfall() {return this.rainfall;}

}
