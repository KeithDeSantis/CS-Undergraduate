import java.util.LinkedList;

/**
 * This class represents a program that can smooth the average runTimes of the imputed LinkedList of Shows
 */
class DataSmooth2 {
    DataSmooth2(){}

    /**
     *Calls helper functions that treat the data in the appropriate way
     * @param shows LinkedList of Shows
     * @return LinkedList of Double containing the smoothed data
     */
    public LinkedList<Double> dataSmooth(LinkedList<Show> shows)
    {
        if(shows.size() == 0){
            return new LinkedList<Double>();}
        LinkedList<Double> runTimeAvg = new LinkedList<Double>();
        for (Show show : shows){
            runTimeAvg.add(getAvg(show.episodes));
        }
        return smooth(runTimeAvg);
    }

    /**
     * Uses the runTime of all episode of a show to calculate a shows average runtime
     * @param episodes LinkedList of Episodes
     * @return Double: the average runtime for a show
     */
    public Double getAvg(LinkedList<Episode> episodes){
        Double total = 0.0;
        for (Episode episode :
                episodes) {
            total+=episode.runTime;
        }
        return total/episodes.size();
    }


    /**
     * Takes in a LinkedList of double containing the data to be smoothed according to specifications
     * @param avg LinkedList of Double
     * @return LinkedList of double that is smoothed
     */
    public LinkedList<Double> smooth(LinkedList<Double> avg){
        LinkedList<Double> smooth = new LinkedList<Double>();
        smooth.add(avg.getFirst());
        for (int i = 1; i <avg.size()-1 ; i++) {
            smooth.add((avg.get(i-1)+avg.get(i)+avg.get(i+1))/3);
        }
        smooth.add(avg.getLast());
        return smooth;
    }

    /**
     * Sub-tasks
     * Method 2:
     * -Generate the average run time for a show
     * -Add that average to a list
     * -Smooth all but the first and last items in the list
     *      by taking the item before and after and summing it together with the current datum and then dividing by 3
     */
}