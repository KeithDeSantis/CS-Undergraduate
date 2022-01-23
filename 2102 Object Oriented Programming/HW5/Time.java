/**
 * A time of day.
 */
public class Time {

    private int hour;

    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Getters
    /**
     * Gets the time's hour.
     * @return The time's hour.
     */
    public int getHour() {return this.hour;}
    /**
     * Gets the time's minute.
     * @return The time's minute.
     */
    public int getMinute() {return this.minute;}
}
