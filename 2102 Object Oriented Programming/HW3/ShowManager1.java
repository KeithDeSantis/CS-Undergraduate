import java.util.LinkedList;

/**
 * Used to manage and perform functions on lists of shows.  Represents a manager of a network.
 */
class ShowManager1 {
	
	ShowManager1() {}

	/**
	 * Sorts a list of shows into a report (show summary) with only non-special daytime, primetime, and late night shows.
	 * @param shows the list of shows to sort through
	 * @return a show summary containing only the proper shows in the same order they were given
	 */
	public ShowSummary organizeShows(LinkedList<Show> shows) {
		LinkedList<Show> daytime = new LinkedList<Show>();
		LinkedList<Show> primetime = new LinkedList<Show>();
		LinkedList<Show> lateNight = new LinkedList<Show>();

		for(int i = 0; i < shows.size(); i++) {
			if(shows.get(i).isSpecial) {
			} else {
				if (600 < shows.get(i).broadcastTime && shows.get(i).broadcastTime < 1700) {
					daytime.add(shows.get(i));
				}
				if (1700 < shows.get(i).broadcastTime && shows.get(i).broadcastTime < 2200) {
					primetime.add(shows.get(i));
				}
				if (2200 < shows.get(i).broadcastTime || shows.get(i).broadcastTime < 100) {
					lateNight.add(shows.get(i));
				}
			}
		}
		return new ShowSummary(daytime, primetime, lateNight);
	}
	
}
