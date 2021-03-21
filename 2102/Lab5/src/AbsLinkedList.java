import java.util.LinkedList;

public class AbsLinkedList implements ISet{

    LinkedList<String> guests;

    public AbsLinkedList(LinkedList<String> guests) {
        this.guests = guests;
    }

    @Override
    public ISet addElt(String elt) {
        guests.add(elt);
        return this;
    }

    @Override
    public boolean hasElt(String elt) {
        return guests.contains(elt);
    }

    @Override
    public int size() {
        return guests.size();
    }
}
