import java.util.LinkedList;

/**
 * This class represents a program that can smooth the average runTimes of the imputed LinkedList of Shows
 */
class DataSmooth1 {
  DataSmooth1(){}


    /**
     * calls the helper averageTimes
     * @param shows a list of shows
     * @return a smoothed list of average lengths of episodes for each show
     */
  public LinkedList<Double> dataSmooth(LinkedList<Show> shows){
      if(shows.size() == 0){
          return new LinkedList<Double>();
      }else {
          LinkedList<Double> avgTime = averageTimes(shows);
          LinkedList<Double> smoothBoy = new LinkedList<Double>();
          smoothBoy.add(avgTime.getFirst());
          for (int i = 1; i < (avgTime.size() - 1); i++) {
              double temp;
              temp = avgTime.get(i - 1) + avgTime.get(i) + avgTime.get(i + 1);
              temp /= 3;
              smoothBoy.add(temp);
          }
          smoothBoy.add(avgTime.getLast());
          return smoothBoy;
      }
  }

    /**
     * helper for dataSmooth
     * @param shows a list of shows(same as from dataSmooth)
     * @return a linkedList of average times for each show(unsmoothed)
     */
    public LinkedList<Double> averageTimes(LinkedList<Show> shows){
        LinkedList<Double> averageLengths = new LinkedList<Double>();
        for(Show show: shows){
            int numEpisodes = show.episodes.size();
            double runningAvg = 0;
            for(Episode episode: show.episodes){
                runningAvg += episode.runTime / numEpisodes;
            }averageLengths.add(runningAvg);
        }return averageLengths;
    }

}