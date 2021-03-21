public class EventGuests {
    // A class for storing guests at a (potentially large) event

    ISet guests;

    EventGuests(ISet guests) {
        this.guests = guests;
    }


    // record a new guest as coming (add the guest to the set)

    void addGuest(String newGuestName) {
        this.guests = guests.addElt(newGuestName);
    }



    // check whether a guest is coming (use hasElt() to see if
    // the guest is in the set)

    boolean isComing(String name) {
        return guests.hasElt(name);
    }

    // return the number of guests in the guest list
    int numGuests(){
        return guests.size();
    }
}
