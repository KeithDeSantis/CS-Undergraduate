public class Mouse extends AbsHealth implements ITestSubject{
    String ID;
    int age;
    double weight;


    public Mouse(String ID, int age, double weight, Diet diet, Workout workoutRegimen) {
        super(diet, workoutRegimen);
        this.ID = ID;
        this.age = age;
        this.weight = weight;
    }


}
