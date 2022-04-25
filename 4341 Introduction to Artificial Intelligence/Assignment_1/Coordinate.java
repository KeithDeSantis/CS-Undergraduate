import java.util.Objects;

public class Coordinate {
    int i;
    int j;
    int priority;

    public Coordinate(int i, int j, int priority) {
        this.i = i;
        this.j = j;
        this.priority = priority;
    }

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return i == that.i && j == that.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getPriority() {
        return priority;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
