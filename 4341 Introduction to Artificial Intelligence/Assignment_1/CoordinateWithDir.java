import java.util.Objects;

public class CoordinateWithDir {
    int i, j;
    Direction direction;

    public CoordinateWithDir(int i, int j, Direction direction) {
        this.i = i;
        this.j = j;
        this.direction = direction;
    }

    public static CoordinateWithDir makeCoorWithDir(int i, int j, Direction direction){
        return new CoordinateWithDir(i,j,direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateWithDir that = (CoordinateWithDir) o;
        return i == that.i && j == that.j && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, direction);
    }
}
