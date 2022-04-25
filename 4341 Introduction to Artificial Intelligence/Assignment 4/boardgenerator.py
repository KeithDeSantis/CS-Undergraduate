import math
import random


def generate_board(dimension, filenumber):
    """
    Generates a random board
    :param dimension: The dimension of the board (X by X)
    :param filenumber: The number that will be used to give the board a unique filename "boardX.txt"
    """

    file = open("board" + str(filenumber) + ".txt", 'w')

    board = []
    for row in range(dimension):
        board.append([])
        for col in range(dimension):
            board[row].append(0)

    number_of_terminals = random.randint(1, math.ceil((dimension * dimension) * 0.05))
    # Ensures there are terminals, anywhere from 1 to 5% of all states will be terminals

    for terminal in range(number_of_terminals):
        terminal_num = random.randint(-10, 10)  # Sets the terminals to a value between -10 and 10
        board[random.randrange(dimension)][random.randrange(dimension)] = terminal_num

    for row in range(dimension):
        for col in range(dimension):

            if col < dimension - 1:
                file.write(str(board[row][col]) + "\t")
            elif row < dimension - 1:
                file.write(str(board[row][col]) + "\n")
            else:
                file.write(str(board[row][col]))

    return f"board{filenumber}.txt"


if __name__ == "__main__":
    generate_board(6, 1)
