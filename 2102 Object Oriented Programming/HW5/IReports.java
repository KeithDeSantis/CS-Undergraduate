import java.util.LinkedList;

public interface IReports {

    /**
     * Overrides equals method.
     * @param o The object being compared.
     * @return True if it is found to be equal by the proper class' implementation.
     */
    public boolean equals(Object o);

    /**
     * Checks if a given daily report was from the given month and year.
     * @param month Month in question.
     * @param year Year in question.
     * @return True if the report is from that month and year.
     */
    public boolean isMonthAndYear(int month, int year);

    /**
     * Gets the average temperature of a day.
     * @return The average temperature.
     */
    public double avgTemp();

    /**
     * Gets the total rainfall from a day.
     * @return The total rainfall.
     */
    public double totalRainfall();

    /**
     * Gets the number of elements in the data set.
     * @return The number of elements.
     */
    public int size();

    /**
     * Adds a daily weather report to the data structure.
     * @param rep The report being added.
     */
    public void addElt(DailyWeatherReport rep);

    /**
     * Gets reports from the given month and year.
     * @param month The month in question.
     * @param year The year in question/
     * @return A linked list of daily weather reports of only the month and year.
     */
    public LinkedList<DailyWeatherReport> getMonthReports(int month, int year);
}
