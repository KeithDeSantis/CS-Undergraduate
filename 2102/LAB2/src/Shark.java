public class Shark extends Fish {
    int attacks;

    Shark(int length, int attacks) {
        super(length, 3.75);
        this.attacks = attacks;
    }

    public boolean isNormalSize() { //This work because the lowest
        return super.length > 6;  // method in the class hierarchy gets called

    }


    public boolean isDangerToPeople() {
        return attacks > 0;
    }

}
