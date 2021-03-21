import java.util.Arrays;

public class Stone {

    String color;
    int numOfStones;
    String[] stones;
    public Stone(String color, int numOfStones) {
        this.color = color;
        this.numOfStones = numOfStones;
        stones = new String[numOfStones];
        Arrays.fill(stones, color);

    }

    public String getColor() {
        return color;
    }

    public int getNumOfStones() {
        return numOfStones;
    }

    public String[] getStones() {
        return stones;
    }

    public void setNumOfStones(int numOfStones) {
        this.numOfStones = numOfStones;
    }

    public void turn(int numToTurn){
        int placeToChange = (int) (Math.random() * stones.length);
        while(numToTurn> 0) {
            if(stones[placeToChange].equals(color)){
                stones[placeToChange]="*";
                numToTurn--;
                numOfStones--;
            }else{
                placeToChange = (int) (Math.random() * stones.length);
            }
        }
    }
}
