public class Fish extends AbsAnimal {
    double salinity;

    Fish(int length, double salinity) {
        super(length);
        this.salinity = salinity;
    }

    //returns true if length is between 1 and 15
    public boolean isNormalSize() {
        return 1 <= this.length && this.length <= 15;
    }

    /*
    The Fish class does not need an isDangerToPeople method because certain
    fish would not pose any danger so there is no point in implementing a method that just returns false
     */
}