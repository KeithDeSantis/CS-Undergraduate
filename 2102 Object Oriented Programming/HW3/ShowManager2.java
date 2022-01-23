import java.util.LinkedList;

/**
 * Used to manage and perform functions on lists of shows.  Represents a manager of a network.
 */
class ShowManager2 {
	
	ShowManager2() {}

	/**
	 * Sorts a list of shows into a report (show summary) with only non-special daytime, primetime, and late night shows.
	 * @param shows the list of shows to sort through
	 * @return a show summary containing only the proper shows in the same order they were given
	 */
	public ShowSummary organizeShows(LinkedList<Show> shows) {
		ShowManager2 sm = new ShowManager2();

		LinkedList<Show> daytime = new LinkedList<Show>();
		LinkedList<Show> notDaytime = new LinkedList<Show>();
		LinkedList<Show> primetime = new LinkedList<Show>();
		LinkedList<Show> notDayOrPrime = new LinkedList<Show>();
		LinkedList<Show> lateNight = new LinkedList<Show>();

		for(int i = 0; i < shows.size(); i++) {
			if (600 < shows.get(i).broadcastTime && shows.get(i).broadcastTime < 1700) {
				daytime.add(shows.get(i));
			} else {
				notDaytime.add(shows.get(i));
			}
		}

		for(int j = 0; j < notDaytime.size(); j++) {
			if (1700 < shows.get(j).broadcastTime && shows.get(j).broadcastTime < 2200) {
				primetime.add(shows.get(j));
			} else {
				notDayOrPrime.add(shows.get(j));
			}
		}

		for(int g = 0; g < notDayOrPrime.size(); g++) {
			if (2200 < shows.get(g).broadcastTime || shows.get(g).broadcastTime < 100) {
				lateNight.add(shows.get(g));
			}
		}

		return new ShowSummary(sm.sortOutSpecials(daytime), sm.sortOutSpecials(primetime), sm.sortOutSpecials(lateNight));
	}

	/**
	 * Makes list with only non-special shows from passed list.
	 * @param uncleaned list with specials
	 * @return original list without specials
	 */
	public LinkedList<Show> sortOutSpecials(LinkedList<Show> uncleaned) {
		LinkedList<Show> cleaned = new LinkedList<Show>();

		for(int t = 0; t < uncleaned.size(); t++) {
			if (!uncleaned.get(t).isSpecial) {
				cleaned.add(uncleaned.get(t));
			}
		}

		return cleaned;
	}
	
}
