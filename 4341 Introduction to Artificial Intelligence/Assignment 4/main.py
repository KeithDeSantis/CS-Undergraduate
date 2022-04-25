from agent import Agent
from board import Board
from qtable import QTable


def main(filename=None, time_to_run=5, probability_moving=0.8, constant_reward=-0.04):
    """
    Main function for the program.
    :param filename: a txt file
    :param time_to_run: number of seconds to learn for
    :param probability_moving: probability of moving in the desired direction
    :param constant_reward: reward for moving (usually negative)
    :return: None
    """
    # Initialize the lookup table
    qtable = QTable()
    board = None
    # read the board from the file
    with open(filename, 'r') as f:
        board = f.read().split('\n')
        for index in range(len(board)):
            board[index] = board[index].split("\t")
        board = [list(x) for x in board]
    for row in board:
        for element in range(len(row)):
            row[element] = int(row[element])

    # Initialize the board
    board_object = Board(len(board), len(board[0]), board)
    # populate the lookup table with the movement reward as inital values
    board_object.populate_qtable(qtable, constant_reward)
    # Initialize the agent
    agent = Agent(qtable, board_object, time_to_run, probability_moving, constant_reward)
    # Run the agent (start learning)
    results = agent.run()
    # print the results
    print(results)


if __name__ == "__main__":
    main(filename="sample.txt", time_to_run=5, probability_moving=0.9, constant_reward=-0.05)
