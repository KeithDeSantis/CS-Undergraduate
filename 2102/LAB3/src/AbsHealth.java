public abstract class AbsHealth {
    Diet diet;
    Double grams;
    Workout workout;


    public AbsHealth(Diet diet,  Workout workoutRegimen) {
        this.diet = diet;
        this.workout = workoutRegimen;
    }
}
