public class Boa extends AbsAnimal {
    String name;
    String eats;

    Boa(String name, int length, String eats) {
        super(length);
        this.name = name;
        this.eats = eats;
    }

    // determines whether this boa's length is between 5 and 10
    public boolean isNormalSize() {
        return isLenWithin(5, 10);
    }

    public boolean isDangerToPeople() {
        return eats.equals("people");
    }
}