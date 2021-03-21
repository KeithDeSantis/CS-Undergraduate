

abstract class AbsAnimal implements IAnimal {
    int length;

    AbsAnimal(int length) {
        this.length = length;
    }

    // determines whether this.length lies between given bounds
    boolean isLenWithin(int low, int high) {
        return low <= this.length && this.length <= high;
    }


}










