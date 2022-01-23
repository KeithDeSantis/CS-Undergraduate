import java.util.LinkedList;

/**
 * Holds a method to smooth a set of data
 */
class DataSmooth1 {
  DataSmooth1(){}

    /**
     * Takes a list of shows and give a data smoothed list of average run times
     * @param shows The list of shows being passed
     * @return List of smoothed average run times of the shows
     */
  public LinkedList<Double> dataSmooth(LinkedList<Show> shows) {
	  LinkedList<Double> averages = new LinkedList<Double>();
	  LinkedList<Double> smoothed = new LinkedList<Double>();

	  if (shows.size() == 0) {
	      return new LinkedList<Double>();
      } else {
          for (int j = 0; j < shows.size(); j++) {
              averages.add(averageRuntimeHelper(shows.get(j)));
          }

          smoothed.add(averages.get(0));

          for (int g = 1; g < averages.size() - 1; g++) {
              smoothed.add((averages.get(g) + averages.get(g + 1) + averages.get(g - 1)) / 3);
          }

          smoothed.add(averages.get(averages.size() - 1));

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
}