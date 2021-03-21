public class Reading {
    private double degrees;
    private double rainfall;
    private Time time;

    public Reading(double degrees, double rainfall, Time time) {
        this.degrees = degrees;
        this.rainfall = rainfall;
        this.time = time;
    }

    public double getDegrees() {
        return degrees;
    }

    public double getRainfall() {
        return rainfall;
    }

}
