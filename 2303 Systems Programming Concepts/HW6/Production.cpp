/*
 * Production.cpp
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#include "Production.h"
#include <stdlib.h>
#include <iostream>

Production::Production() {
	// TODO Auto-generated constructor stub

}

Production::~Production() {
	// TODO Auto-generated destructor stub
}

bool Production::prod(int argc, char* argv[])
{
    Cell cell1;
    Watercraft wc1;
    Coordinate coord1;
    Stats stat1;

    Stats players[2];
    players[0].setNumHits(0);
    players[0].setNumMisses(0);
    players[0].setHitMissRatio(0.0);
    players[1].setNumHits(0);
    players[1].setNumMisses(0);
    players[1].setHitMissRatio(0.0);

    Cell playerOneGameBoard[ROWS][COLS];       /* Player one game board */
    Cell playerTwoGameBoard[ROWS][COLS];       /* Player two game board */

    Coordinate target;             /* x, y value of a target */
    Coordinate targetTemp;         /* x, y value that holds a temporary value*/
    Coordinate targetOrigin;       /* x, y value of the original target */
    Coordinate targetAI;           /* x, y value of the targets using AI technique */

    Watercraft ship[NUM_OF_SHIPS];
    for(int i = 0; i < NUM_OF_SHIPS; i++) // Setting up ship array
    {
        switch(i)
        {
            case 0:
                ship[0].setLength(5);
                ship[0].setSymbol('c');
                ship[0].setName("Carrier");
                break;
            case 1:
                ship[1].setLength(4);
                ship[1].setSymbol('b');
                ship[1].setName("Battleship");
                break;
            case 2:
                ship[2].setLength(3);
                ship[2].setSymbol('r');
                ship[2].setName("Cruiser");
                break;
            case 3:
                ship[3].setLength(3);
                ship[3].setSymbol('s');
                ship[3].setName("Submarine");
                break;
            case 4:
                ship[4].setLength(2);
                ship[4].setSymbol('d');
                ship[4].setName("Destroyer");
                break;
        }
    }

    bool    huntMode       = true;                     /* mode of randomly selecting a target */
    bool    targetMode     = false;                    /* mode when there is a hit */
    bool    flipper        = true;	                  /* flips values of boolean */
    bool    cardinals[4]   = {true, true, true, true}; /* represents the 4 cardinals, N, S, W, E */
    bool    hasAShipSunked = false;                    /* if a ship has sank */

    short sunkShip[2][NUM_OF_SHIPS] = {{5, 4, 3, 3, 2},
                                       {5, 4, 3, 3, 2}};  /* tracks parts of the ship destroyed */

    short player  = 0;	           /* 0 -> player1, 1 -> player2 */
    short shot    = 0;             /* holds temp value if ship has been shot */
    int   option  = 0;             /* option for player to place ship manually or randomly */
    int   north   = 0,             /* holds change of value when going north */
    south   = 0,             /* holds change of value when going south */
    east    = 0,             /* holds change of value when going east */
    west    = 0;             /* holds change of value when going west */
    int   i       = 0,
            counter = 1;             /* i and counter are used as counters */

    char  shipSymbol = '\0';       /* temporary value to save character symbol of the ship */

    FILE *outStream = fopen (LOG_FILE_NAME, "w");

    srand ((unsigned int) time (NULL));

    /**
     * Print welcome screen
     */
    this->welcomeScreen();
    //this->systemMessage ("                            Hit <ENTER> to continue!\n"); //TODO SYSTEM MESSAGE TOMFOOLERY
    std::cout << "                            Hit <ENTER> to continue!\n" << std::endl;
    //system ("clear");

    /**
	 * Each Player's game board should be initialized to all '~' indicating
	 * no ships have been placed on either board.
	 */
    cell1.initializeGameBoard (playerOneGameBoard);
    cell1.initializeGameBoard (playerTwoGameBoard);

    /**
     * Both players' fleets consist of 5 ships that are hidden from the enemy.
     * Each ship may be differentiated by its "size" (besides the Cruiser and
     * Submarine) or number of cells it expands on the game board.
     *      The Carrier has 5 cells,
     *      Battleship has 4 cells,
     *      Cruiser has 3 cells,
     *      Submarine has 3 cells, and
     *      the Destroyer has 2 cells.
     */

    /**
     * The program should be built such that the user is Player1 and the
     * computer is Player2.
     */

    /**
     * Before the game starts, Player1 should have the option to either
     * manually place each of the 5 ships in his/her fleet or to have them
     * randomly placed on the board. If Player1 decides to place the ships
     * manually, then he/she should be prompted to place the Carrier first,
     * Battleship second, Cruiser third, Submarine fourth, and the Destroyer
     * last.
     */
    printf ("> Please select from the following menu:\n");
    printf ("> [1] Manually\n");
    printf ("> [2] Randomly\n");
    printf ("> Enter Option: ");
    scanf ("%d", &option);

    switch (option) {
        case 1: cell1.manuallyPlaceShipsOnGameBoard (playerOneGameBoard, ship);
            break;
        case 2: cell1.randomlyPlaceShipsOnGameBoard (playerOneGameBoard, ship);
            break;
    }

    /**
     * Note that ships cannot be placed diagonally on the board, they can
     * only be placed vertically or horizontally. You program must check to
     * see if the user tries to place a ship outside the boundaries of the
     * board or on top of a ship that has already been placed. Each cell on
     * the board that contains part of the ship must be indicated by 'c' for
     * Carrier, 'b' for Battleship, 'r' for Cruiser, 's' for Submarine, or
     * 'd' for Destroyer.
     */

    /**
     * Player2's ships must be randomly placed. Note that the placement of
     * Player2's ships must be unknown. Thus, Player2's board will only
     * display '~' in each cell after the placement of each ship.
     */

   cell1.randomlyPlaceShipsOnGameBoard (playerTwoGameBoard, ship);
   printf ("> Player 2 (Computer's) board has been generated.\n");

    /**
      * The program should randomly select Player1 or Player2 to go first.
      */
    player = getRandomNumber (0, 1);
    printf ("> Player %d has been randomly selected to go first.\n", player + 1);
    //systemMessage ("> Hit <ENTER> to continue!\n"); //TODO SYSTEM MESSAGE TOMFOOLERY
    std::cout << "> Hit <ENTER> to continue!\n" << std::endl;
    //system ("clear");

    /**
	 * Once it has been decided on which player goes first, the game starts. 
	 * Whenever it's Player1's turn, a prompt should be displayed asking for 
	 * a position to target (specifying where to "shoot") on the enemy's 
	 * (Player2's) board (2-dimensional array). The position should be 
	 * specified in terms of a row and a column on the board. The row and 
	 * column should always be displayed along with the board. If the 
	 * position specified happens to hit a ship, then a '*' should replace 
	 * the '-' on Player2's board. If the positioned specified misses any 
	 * one of the ships in the fleet, then a 'm' should replace the '-' on 
	 * Player2's board. Note that from turn-to-turn each player should NOT 
	 * be allowed to enter the same position. Also, between turns clear the 
	 * screen (system("cls")). In one turn, a player can only take one shot 
	 * at the enemy's (other player's) fleet. When Player2 takes a shot at 
	 * Player1's board, each hit should be specified with a '*' and each 
	 * miss with a 'm' on Player1's board. The game is over win Player1 or 
	 * Player2 has sunk all of the ships in the fleet of the enemy. 
	 */

    /**
     * For each move made by Player1 and Player2, the results should be 
     * echoed to a file called "battleship.log". In this file, you should 
     * log the targeted position by each player on each move and whether it 
     * was a hit on one of the ships in the fleet. Also, if one of the ships 
     * happens to sink, then note this in the log file. 
     */
     
    //TODO HERE IS THE BIG PART
    while (true) {

        /* Write to battleship.log */
        fprintf (outStream, "Player %d's turn.\n", player + 1);

        /* switches back and forth between player 1 and player 2 */
        switch (player) {

            case PLAYER_ONE:
                /* Print player 2's game board*/
                printf ("> Player 2's Board:\n");
                cell1.printGameBoard (playerTwoGameBoard, false);
                printf ("> PLAYER 1'S TURN\n");

                /* Prompts user to enter target */
                do {
                    target = coord1.getTarget ();
                    shot = cell1.checkShot (playerTwoGameBoard, target);

                    /* prompts user that input is invalid or either a hit or miss cell */
                    if (shot == -1)
                        printf ("> Try inputting another target!\n");

                } while (shot == -1);

                /* saves the character of the targeted cell */
                shipSymbol = playerTwoGameBoard[target.getRow()][target.getColumn()].getSymbol();
                break;

            case PLAYER_TWO:

                /**** IMPLEMENTATION OF ARTIFICIAL INTELLIGENCE ****/

                /* Print player 1's game board */
                printf ("> Player 1's Board:\n");
                cell1.printGameBoard (playerOneGameBoard, true);
                printf ("> COMPUTER'S TURN\n");

                /**
                 * check from a previous cycle if a ship has sunk,
                 * if yes, it reinitializes values to go back to hunt mode
                 */
                if (hasAShipSunked) {
                    hasAShipSunked = false;
                    targetMode = false;
                    huntMode = true;
                }

                /**
                 * TARGET MODE 
                 * This mode is true when the computer randomly selects a target,
                 * and it happens to be a hit, this mode is set to true.
                 */
                if (targetMode) {
                    /* retains value of targetAI */
                    target = targetAI;

                    do {
                        if (cardinals[NORTH]) {        /* NORTH */
                            target.setRow(north);
                        } else if (cardinals[SOUTH]) { /* SOUTH */
                            target.setRow(south);
                        } else if (cardinals[WEST]) {  /* WEST */
                            target.setColumn(west);
                        } else if (cardinals[EAST]) {  /* EAST */
                            target.setColumn(east);
                        } else if (!cardinals[NORTH] && !cardinals[SOUTH] &&
                                   !cardinals[WEST]  && !cardinals[EAST]  &&
                                   !hasAShipSunked) {
                            /* All cardinals are false but has not sunk a ship */

                            /* reinitiliazes target to the original target value */
                            target = targetOrigin;
                            targetTemp = target;

                            /**
                             * Counter increments by one, when the loop cycles again and
                             * all cardinals are still false. This ensures that we are checking
                             * one cell over the adjacent cells 
                             */
                            north = target.getRow() - counter;
                            targetTemp.setRow(north);

                            /* checks if the value NORTH of the target is a hit or miss */
                            if (cell1.checkShot (playerOneGameBoard, targetTemp) != -1 && north >= 0) {
                                cardinals[NORTH] = true;
                            }

                            targetTemp = target;
                            south = target.getRow() + counter;
                            targetTemp.setRow(south);

                            /* checks if the value SOUTH of the target is a hit or miss */
                            if (cell1.checkShot (playerOneGameBoard, targetTemp) != -1 && south <= 9) {
                                cardinals[SOUTH] = true;
                            }

                            targetTemp = target;
                            west = target.getColumn() - counter;
                            targetTemp.setColumn(west);

                            /* checks if the value WEST of the target is a hit or miss */
                            if (cell1.checkShot (playerOneGameBoard, targetTemp) != -1 && west >= 0) {
                                cardinals[WEST] = true;
                            }

                            targetTemp = target;
                            east = target.getColumn() + counter;
                            targetTemp.setColumn(east);

                            /* checks if the value EAST of the target is a hit or miss */
                            if (cell1.checkShot (playerOneGameBoard, targetTemp) != -1 && east <= 9) {
                                cardinals[EAST] = true;
                            }

                            /**
                             * increments counter every cycle, serves as a addend to how
                             * many cells to check from the target cell
                             */
                            counter++;

                        } else  {
                            /* when all conditions are not met, it reverts back to Hunt mode */
                            targetMode = false;
                            huntMode = true;
                            break;
                        }

                        /* checks if the shot is a hit or miss */
                        shot = cell1.checkShot (playerOneGameBoard, target);

                    } while (shot == -1 && targetMode == true);

                    /**
                     * this loop flips the values of the cardinals when it is not needed
                     * takes the cardinal off the stack for directions to check 
                     */
                    if (shot == 1 && huntMode == false) {
                        for (i = 0; i < 4; i++) {
                            if (flipper == false)
                                cardinals[i] = false;

                            if (cardinals[i] == flipper)
                                flipper = false;
                        }
                    } else {
                        for (i = 0; i < 4; i++) {
                            if (flipper == true && cardinals[i] != false) {
                                cardinals[i] = false;
                                break;
                            }
                        }
                    }

                    /* reinitializes the value of flipper */
                    flipper = true;
                }

                /**
                 * HUNT MODE 
                 * This is starting mode of Player 2's turn. This pertains to the 
                 * computer randomly selecting cells as targets and checking if they are
                 * just plain water. When a cell equates to a Hit, then hunt mode is
                 * set to false and target mode is set to true
                 */
                if (huntMode) {

                    /* reinitializes important values */
                    counter = 1;
                    flipper = true;
                    for (i = 0; i < 4; i++)
                        cardinals[i] = true;

                    /* loop that gets a random number of a cell as target */
                    do {
                        target.setRow(getRandomNumber (0, 9));
                        target.setColumn(getRandomNumber (0, 9));

                        /* checks target if it is a miss or hit cell */
                        shot = cell1.checkShot (playerOneGameBoard, target);
                    } while (shot == -1);

                    /* if it is a Hit, this saves the value of the original target */
                    if (shot == 1) targetOrigin = target;
                }

                /**
                 * shot values (1 means craft is hit, 0 means missed and -1 means 
                 * it is already a hit or miss cell
                 */
                if (shot == 1) {

                    /**
                     * if all cardinals are false and no ship was sunk,
                     * reintializes target from value of original target 
                     */
                    if (!cardinals[NORTH] && !cardinals[SOUTH] &&
                        !cardinals[WEST]  && !cardinals[EAST]  &&
                        !hasAShipSunked) { target = targetOrigin; }

                    /* hunt mode is false during a target mode */
                    huntMode = false;
                    targetMode = true;
                    targetAI = target;

                    /**
                     * get values of adjacent cells and ensuring that
                     * that it is withing the bounds of gameboard for 
                     * NORTH, SOUTH, WEST & EAST
                     */
                    if (cardinals[NORTH] == true) {  /* NORTH */
                        north = (targetAI.getRow() - 1);
                        checkBoundsOfCardinal (cardinals, north, NORTH);
                        targetTemp = target;
                        targetTemp.setRow(north);
                        if (cell1.checkShot (playerOneGameBoard, targetTemp) == -1)
                            cardinals[NORTH] = false;
                    }

                    if (cardinals[SOUTH] == true) {  /* SOUTH */
                        south = targetAI.getRow() + 1;
                        checkBoundsOfCardinal (cardinals, south, SOUTH);
                        targetTemp = target;
                        targetTemp.setRow(south);
                        if (cell1.checkShot (playerOneGameBoard, targetTemp) == -1)
                            cardinals[SOUTH] = false;
                    }

                    if (cardinals[WEST] == true) {   /* WEST */
                        west  = targetAI.getColumn() - 1;
                        checkBoundsOfCardinal (cardinals, west, WEST);
                        targetTemp = target;
                        targetTemp.setColumn(west);
                        if (cell1.checkShot (playerOneGameBoard, targetTemp) == -1)
                            cardinals[WEST] = false;
                    }

                    if (cardinals[EAST] == true) {   /* EAST */
                        east  = targetAI.getColumn() + 1;
                        checkBoundsOfCardinal (cardinals, east, EAST);
                        targetTemp = target;
                        targetTemp.setColumn(east);
                        if (cell1.checkShot (playerOneGameBoard, targetTemp) == -1)
                            cardinals[EAST] = false;
                    }
                }

                /* saves the character of the targeted cell */
                shipSymbol = playerOneGameBoard[target.getRow()][target.getColumn()].getSymbol();
                break;
        }

        /**
         * Prompts player if it's a hit or miss 
         */
        if (shot == 1) { /* HIT */
            printf ("> %d, %d is a hit!\n", target.getRow(), target.getColumn());

            /* Write to battleship.log */
            fprintf (outStream, "%d, %d is a hit!\n", target.getRow(), target.getColumn());

            /* Keeps track so number of hits for stats */
            players[player].setNumHits(players[player].getNumHits() + 1);

            /* Checks if the ship has sank */
            if (player == 1)
                hasAShipSunked = wc1.checkSunkShip (sunkShip, !player, shipSymbol, outStream);
            else
                wc1.checkSunkShip (sunkShip, !player, shipSymbol, outStream);

        } else {        /* MISS */
            printf ("> %d, %d is a miss!\n", target.getRow(), target.getColumn());

            /* Write to battleship.log */
            fprintf (outStream, "%d, %d is a miss!\n", target.getRow(), target.getColumn());
            players[player].setNumMisses(players[player].getNumMisses() + 1);
        }

        if (player == 0) /* If Player 1 then update player 2's gameboard */
            cell1.updateGameBoard (playerTwoGameBoard, target);
        else             /* If Player 2 then update player 1's gameboard */
            cell1.updateGameBoard (playerOneGameBoard, target);

        /* Determins if there is a winner based on number of hits */
        if (stat1.isWinner (players, player)) {
            printf ("\n> Player %d wins!\n", player + 1);

            /* Write to battleship.log */
            fprintf (outStream, "\n>>>>> Player %d wins! <<<<<\n", player + 1);
            break;
        }

        //systemMessage ("> Hit <ENTER> to continue!\n"); //TODO SYSTEM MESSAGE TOMFOOLERY
        std::cout << "> Hit <ENTER> to continue!\n" << std::endl;

        /* switches from player 1 to player 2 */
        player = !player;

        //system ("clear");
    }

    /**
     * At the end of the game, Player1's and Player2's statistics should be 
     * written to "battleship.log". The stats include total number of hits, 
     * total number of misses, total number of shots, hits to misses ratio 
     * (as a percentage), and won or lost the game. Note that the statistics 
     * should be placed into a structure called Stats. You need two variables 
     * of type Stats, one for Player1 and one for Player2. Once the game has 
     * ended you should write the contents of each struct variable to the 
     * "battleship.log" file.
     */
    players[0].setTotalShots(players[0].getNumHits() + players[0].getNumMisses());
    players[0].setHitMissRatio(((double) players[0].getNumHits()/(double) players[0].getNumMisses()) * 100);
    players[1].setTotalShots(players[1].getNumHits() + players[1].getNumMisses());
    players[1].setHitMissRatio(((double) players[1].getNumHits()/(double) players[1].getNumMisses()) * 100);
    fprintf (outStream, "+===================================================\n");
    fprintf (outStream, "|                    PLAYER STATS                   \n");
    fprintf (outStream, "+---------------------------------------------------\n");
    fprintf (outStream, "| PLAYER 1 : %d hits                                \n", players[0].getNumHits());
    fprintf (outStream, "|            %d misses                              \n", players[0].getNumMisses());
    fprintf (outStream, "|            %d total shots                         \n", players[0].getTotalShots());
    fprintf (outStream, "|            %.2lf%% hit/miss ratio                 \n", players[0].getHitMissRatio());
    fprintf (outStream, "| PLAYER 2 : %d hits                                \n", players[1].getNumHits());
    fprintf (outStream, "|            %d misses                              \n", players[1].getNumMisses());
    fprintf (outStream, "|            %d total shots                         \n", players[1].getTotalShots());
    fprintf (outStream, "|            %.2lf%% hit/miss ratio                 \n", players[1].getHitMissRatio());
    fprintf (outStream, "+===================================================");

    fclose (outStream);

    //TODO THIS IS WHERE IM AT IN PRODUCTION
    bool answer = true;
	return answer;
}

int Production::getRandomNumber(int lowest, int highest)
{
    if(lowest == 0)
    {
        return rand() % ++highest;
    }
    if(lowest > 0)
    {
        return rand() % ++highest + lowest;
    }
}

void Production::checkBoundsOfCardinal(bool cardinals[], int bound, int direction)
{
    switch (direction) {
        case NORTH: //NORTH
            if (bound < 0)
                cardinals[0] = false;
            else
                cardinals[0] = true;
            break;

        case SOUTH: //SOUTH
            if (bound > 9)
                cardinals[1] = false;
            else
                cardinals[1] = true;
            break;

        case WEST: //WEST
            if (bound < 0)
                cardinals[2] = false;
            else
                cardinals[2] = true;
            break;

        case EAST: //EAST
            if (bound > 9)
                cardinals[3] = false;
            else
                cardinals[3] = true;
            break;
    }
}

void Production::welcomeScreen()
{
    printf("XXXXX   XXXX  XXXXXX XXXXXX XX     XXXXXX  XXXXX XX  XX XX XXXX\n");
    printf("XX  XX XX  XX   XX     XX   XX     XX     XX     XX  XX XX XX  XX\n");
    printf("XXXXX  XX  XX   XX     XX   XX     XXXX    XXXX  XXXXXX XX XXXX\n");
    printf("XX  XX XXXXXX   XX     XX   XX     XX         XX XX  XX XX XX\n");
    printf("XXXXX  XX  XX   XX     XX   XXXXXX XXXXXX XXXXX  XX  XX XX XX\n");
    printf("\n\n");
    printf("RULES OF THE GAME:\n");
    printf("1. This is a two player game.\n");
    printf("2. Player 1 is you and Player 2 is the computer.\n");
    printf("3. Player 1 will be prompted if user wants to manually input coordinates\n");
    printf("   for the game board or have the computer randomly generate a game board\n");
    printf("4. There are five types of ships to be placed by longest length to the\n");
    printf("   shortest; [c] Carrier has 5 cells, [b] Battleship has 4 cells, [r] Cruiser\n");
    printf("   has 3 cells, [s] Submarine has 3 cells, [d] Destroyer has 2 cells\n");
    printf("5. The computer randomly selects which player goes first\n");
    printf("6. The game begins as each player tries to guess the location of the ships\n");
    printf("   of the opposing player's game board; [*] hit and [m] miss\n");
    printf("7. First player to guess the location of all ships wins\n\n");
}
