import java.util.LinkedList;

class ShowSummary {
	
	LinkedList<Show> daytime;
	LinkedList<Show> primetime;
	LinkedList<Show> latenight;
	
	ShowSummary() 
	{
		this.daytime = new LinkedList<Show>();
		this.primetime = new LinkedList<Show>();
		this.latenight = new LinkedList<Show>();
	}
	
	ShowSummary(LinkedList<Show> daytime, LinkedList<Show> primetime, LinkedList<Show> latenight)
	{
		this.daytime = daytime;
		this.primetime = primetime;
		this.latenight = latenight;
	}

	// the equals method for use in testing
	 public boolean equals(Object other) 
	 {
		if(!(other instanceof ShowSummary)) 
		{
			return false;
		}
		 
	    ShowSummary otherS = (ShowSummary) other;
	    
	    return this.equalsHelper(this.daytime, otherS.daytime) && 
	    	   this.equalsHelper(this.primetime, otherS.primetime) && 
	    	   this.equalsHelper(this.latenight, otherS.latenight);
	 }
	 
	 private boolean equalsHelper(LinkedList<Show> theseShows, LinkedList<Show> otherShows)
	 {	
		if(theseShows.size() != otherShows.size())
		{
			return false;
		}
		 
	    for(int i = 0; i < theseShows.size(); i++)
	    {
	    	Show thisShow = theseShows.get(i);
	    	Show otherShow = otherShows.get(i);
	    	
	    	if(!thisShow.title.equals(otherShow.title))
	    	{
	    		return false;
	    	}
	    	else if(thisShow.broadcastTime != otherShow.broadcastTime)
	    	{
	    		return false;
	    	}
	    }
	    
	    return true;
	 }
	  
	 // the toString method so that reports display when tests fail
	 public String toString()
	 {
		 return this.toStringHelper(this.daytime) + "--- \n\n" + this.toStringHelper(this.primetime) + "--- \n\n" + this.toStringHelper(this.latenight);  
	  }
	 
	 private String toStringHelper(LinkedList<Show> theseShows)
	 {
		String output = "";
		
	    for(Show thisShow : theseShows)
	    {
	    	output = output + 
	    			"Title: " + thisShow.title + " | " +
	    			"Broadcast Time: " + thisShow.broadcastTime + "\n";
	    	
	    	for(Episode thisEp : thisShow.episodes)
	    	{
	    		output = output + 
	    				"Episode Title: " + thisEp.epTitle + " | " +
	    				"Runtime: " + thisEp.runTime + "\n";
	    	}
	    	
	    	output = output + "\n";
	    }
	    
		return output;
	 }
}
