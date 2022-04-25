import random


def generate_board(dimension, filenumber):

    file = open("board" + str(filenumber) + ".txt", 'w')

    board = []

    for row in range(dimension):
        board.append([])
        for col in range(dimension):
            board[row].append(random.randint(1,9))

    start_row = random.randint(0,dimension-1)
    start_column = random.randint(0,dimension-1)
    end_row = random.randint(0,dimension-1)
    end_column = random.randint(0,dimension-1)

    while start_row == end_row and start_column == end_column:
        end_row = random.randint(0, dimension - 1)
        end_column = random.randint(0, dimension - 1)

    board[start_row][start_column] = 'S'
    board[end_row][end_column] = 'G'

    for row in range(dimension):
        for col in range(dimension):

            if col < dimension -1:
                file.write(str(board[row][col]) + "\t")
            elif row < dimension - 1:
                file.write(str(board[row][col]) + "\n")
            else:
                file.write(str(board[row][col]))