import java.util.Scanner;

public class Board {
    Scanner s = new Scanner(System.in);
    Stone g;
    Stone y;
    Stone o;
    boolean turn = true; //True when players turn
    // false when computers tur
    String[] colors;

    public Board() {
        g = new Stone("G", 3);
        y = new Stone("Y", 7);
        o = new Stone("O", 5);
        colors = new String[15];
        display();
    }

    boolean turnStone(Stone color, int numToTurn) {
        if (numToTurn > 0 && numToTurn <= color.getNumOfStones()) {
            color.turn(numToTurn);
            return true;
        } else {
            if (turn) {
                System.out.println("Sorry not enough stones in play.");
            }

            return false;
        }
    }

    void display() {
        System.arraycopy(g.stones, 0, colors, 0, 3);
        System.arraycopy(y.stones, 0, colors, 3, 7);
        System.arraycopy(o.stones, 0, colors, 10, 5);
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 5 - i; j > 1; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(colors[count] + " ");
                count++;
            }
            System.out.println();
        }
        System.out.printf("There are %d green stones remaining.\n", g.getNumOfStones());
        System.out.printf("There are %d yellow stones remaining.\n", y.getNumOfStones());
        System.out.printf("There are %d orange stones remaining.\n", o.getNumOfStones());
        System.out.println("-----------------------------------");
    }

    Stone getColorFromPlayer() {
        System.out.println("What color would you like to remove (G,Y, or O)");
        String colorString = s.next();
        switch (colorString.toUpperCase()) {
            case "G":
                return g;
            case "Y":
                return y;
            case "O":
                return o;
            default:
                System.out.println("Sorry that's not an option.");
                break;
        }
        return null;
    }

    int getNumFromPlayer() {
        System.out.println("How many would you like to remove?");
        return s.nextInt();
    }

    void play() {
        boolean worked = takeTurn();
        while (!worked) {
            worked = takeTurn();
        }
    }

    boolean takeTurn() {
        if (turn) {
            Stone stoneToPlay = getColorFromPlayer();
            while (stoneToPlay == null) {
                stoneToPlay = getColorFromPlayer();
            }
            int numToRemove = getNumFromPlayer();
            boolean worked = turnStone(stoneToPlay, numToRemove);
            if (worked) {
                System.out.println("Your turn has ended\nThe board now looks like:");
                turn = !turn;
                display();
                return true;
            } else {
                System.out.println("Something went wrong with the stone selection please try again");
                return false;
            }
        } else {
            System.out.println("Computer's Turn");
            System.out.println("---------------\n\n");
            int numGreen = g.getNumOfStones();
            int numYellow = y.getNumOfStones();
            int numOrange = o.getNumOfStones();

            int picker = (int) (Math.random() * 3);
            int remainingStones = -1;

            boolean worked = false;

            boolean oneLeft = onlyOneLeft();
            if (oneLeft) {
                return true;
            }
            if (picker == 0) {
                remainingStones = numYellow ^ numOrange;
                worked = turnStone(g, numGreen - remainingStones);
                if (worked) {
                    turn = !turn;
                    display();
                    return true;
                } else {
                    picker = (int) (Math.random() * 3) + 1;
                }
            }
            if (picker == 1) {
                remainingStones = numGreen ^ numOrange;
                worked = turnStone(y, numYellow - remainingStones);
                if (worked) {
                    turn = !turn;
                    display();
                    return true;
                } else {
                    picker = 2;
                }
            }
            if (picker == 2) {
                remainingStones = numGreen ^ numYellow;
                worked = turnStone(o, numOrange - remainingStones);
                if (worked) {
                    turn = !turn;
                    display();
                    return true;
                }
            }
            Stone[] pickFrom = {g, y, o};
            Stone chosenOne = pickFrom[(int) (Math.random() * 3)];
            int numToRemove = (int) (Math.random() * chosenOne.getNumOfStones() + 1);
            worked = turnStone(chosenOne, numToRemove);
            if (worked) {
                turn = !turn;
                display();
                return true;
            }
            return false;
        }
    }

    boolean gameOver() {
        int totalLeft = g.getNumOfStones() + y.getNumOfStones() + o.getNumOfStones();
        return totalLeft <= 0;
    }

    boolean onlyOneLeft() {
        int numGreen = g.getNumOfStones();
        int numYellow = y.getNumOfStones();
        int numOrange = o.getNumOfStones();
        if (numYellow + numOrange == 0) {
            turnStone(g, numGreen);
            turn = !turn;
            display();
            return true;
        }
        if (numGreen + numOrange == 0) {
            turnStone(y, numYellow);
            turn = !turn;
            display();
            return true;
        }
        if (numYellow + numGreen == 0) {
            turnStone(o, numOrange);
            turn = !turn;
            display();
            return true;
        }
        return false;
    }

}


