import java.util.LinkedList;

/**
 * This class represents a program that sorts Shows based off of their brodcastTime
 */
class ShowManager2 {


	ShowManager2() {
	}

	/**
	 * Produces ShowSummary with the shows in the correct time categories
	 * @param shows LinkedList of Shows
	 * @return ShowSummary
	 */
	public ShowSummary organizeShows(LinkedList<Show> shows) {
		return sortShows(shows);
	}

	/**
	 * This method sorts the shows based off of their broadcastTime
	 * @param shows LinkedList of Show
	 * @return ShowSummary containing all shows sorted into the correct categories
	 */
	public ShowSummary sortShows(LinkedList<Show> shows) {
		ShowSummary summary = new ShowSummary();

		for (Show show : shows) {
			if (!show.isSpecial) {
				if (show.broadcastTime >= 0600 && show.broadcastTime < 1700) {
					summary.daytime.add(show);
				} else if (show.broadcastTime >= 1700 && show.broadcastTime < 2200) {
					summary.primetime.add(show);
				} else {
					summary.latenight.add(show);
				}
			}

		}
		return summary;
	}

	/**
	 * Sub-tasks
	 * Method 2:
	 * -Create a ShowSummary that will contain all shows
	 * -Sort the shows one by one into the ShowSummary
	 */
}
