public class Dillo extends AbsAnimal {
    boolean isDead;

    Dillo(int length, boolean isDead) {
        super(length);
        this.isDead = isDead;
    }

    // determines whether Dillo is dead and longer than 60
    boolean canShelter() {
        return (this.isDead && this.length > 60);
    }

    // determines whether this dillo's length is between 2 and 3
    public boolean isNormalSize() {
        return isLenWithin(2, 3);
    }
}