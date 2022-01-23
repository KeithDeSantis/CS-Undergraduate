import java.util.LinkedList;
//NEEDS TO CHANGE
/**
 * Holds a method to smooth a set of data
 */
class DataSmooth2 {
  DataSmooth2(){}

    /**
     * Takes a list of shows and give a data smoothed list of average run times
     * @param shows The list of shows being passed
     * @return List of smoothed average run times of the shows
     */
  public LinkedList<Double> dataSmooth(LinkedList<Show> shows) {
      LinkedList<Double> averages = new LinkedList<Double>();
      LinkedList<Double> smoothed = new LinkedList<Double>();
      LinkedList<LinkedList<Double>> triples = new LinkedList<LinkedList<Double>>();

      if (shows.size() == 0) {
          return new LinkedList<Double>();
      } else {
          for (int j = 0; j < shows.size(); j++) {
              averages.add(averageRuntimeHelper(shows.get(j)));
          }

          double first = averages.get(0);
          double last = averages.get(averages.size() - 1);

          //Adding triples to list
          for (int i = 1; i < averages.size() - 1; i++) {
              LinkedList<Double> tempList = new LinkedList<Double>();
              tempList.add(averages.get(i - 1));
              tempList.add(averages.get(i));
              tempList.add(averages.get(i + 1));
              triples.add(tempList);
          }

          //Finding average of each triple
          for (int i = 0; i < triples.size(); i++) {
              smoothed.add(this.averageOfListHelper(triples.get(i)));
          }

          smoothed.addFirst(first);
          smoothed.add(last);

          return smoothed;
      }

  }

    /**
     * Gets average runtime of a show
     * @param show the show whose episodes are being inspected
     * @return average run time of all episodes
     */
    public double averageRuntimeHelper(Show show) {
        double sum = 0;
        int count = 0;

        for(int i = 0; i < show.episodes.size(); i++) {
            sum = sum + show.episodes.get(i).runTime;
            count = count + 1;
        }

        return sum/count;
    }

    /**
     * Gets the average of a list of doubles.
     * @param list The list being averaged.
     * @return The average of the list.
     */
    public double averageOfListHelper(LinkedList<Double> list) {
        double sum = 0;
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i);
            count = count + 1;
        }

        return sum/count;
    }
}