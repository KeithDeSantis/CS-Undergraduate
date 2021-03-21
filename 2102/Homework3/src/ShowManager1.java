import java.util.LinkedList;

/**
 * This class represents a program that sorts Shows based off of their brodcastTime
 */
class ShowManager1 {



	ShowManager1() {}

	/**
	 * sorts shows into either daytime, primetime, or latenight shows and puts them into a ShowSummary
	 * @param shows a list of shows to be divided into categories
	 * @return a ShowSummary containing the sorted shows
	 */
	public ShowSummary organizeShows(LinkedList<Show> shows)
	{
		LinkedList<Show> daytime = new LinkedList<Show>();
		LinkedList<Show> primetime = new LinkedList<Show>();
		LinkedList<Show> latenight = new LinkedList<Show>();

		if(shows.size() == 0){
			return new ShowSummary(); //fail safe in case of empty input list
		}
		for (Show show: shows) {
			if(show.broadcastTime >= 0600 && show.broadcastTime < 1700){
				daytime.add(show);
			}else if(show.broadcastTime >= 1700 && show.broadcastTime < 2200) {
				primetime.add(show);
			}else if(show.broadcastTime >= 2200 && show.broadcastTime < 0100) {
				latenight.add(show);
			}
		}
		return new ShowSummary(daytime, primetime, latenight);
	}
	
}
