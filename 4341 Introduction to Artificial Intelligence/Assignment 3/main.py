import copy
import logging
from threading import *
import csv
import os

import agent
import board
import math
import boardgenerator
from dataentry import dataentry
import featurefunctions
from time import *


def update_nums_through_move(row, column, direction, action, board_used):
    """
    This is a helper that updates the row, column, and direction of
    the agent based on the action taken, as well as calculates the cost
    :param row: initial row
    :param column: initial column
    :param direction: initial direction
    :param action: action taken
    :param board_used: board used
    :return: List of [ row_after_move, column_after_move, direction_after_move, cost_of_move ]
    """
    cost = None
    if action == "FORWARD":
        if direction == "north":
            row -= 1
        elif direction == "east":
            column -= 1
        elif direction == "south":
            row += 1
        elif direction == "west":
            column += 1
        cost = board_used.get_cost(row, column)
    elif action == "BASH":
        if direction == "north":
            row -= 1
        elif direction == "east":
            column -= 1
        elif direction == "south":
            row += 1
        elif direction == "west":
            column += 1
        cost = 3
    elif action == "LEFT":
        if direction == "north":
            direction = "east"
        elif direction == "east":
            direction = "south"
        elif direction == "south":
            direction = "west"
        elif direction == "west":
            direction = "north"
        cost = math.ceil(board_used.get_cost(row,column) / 2)
    elif action == "RIGHT":
        if direction == "north":
            direction = "west"
        elif direction == "east":
            direction = "north"
        elif direction == "south":
            direction = "east"
        elif direction == "west":
            direction = "south"
        cost = math.ceil(board_used.get_cost(row, column) / 2)

    return [row, column, direction, cost]

def generate_data_from_path(board_used, actions, score):
    """
    This function takes in a path (list of actions) that was returned by A* for a certain board,
    then steps through each action in the path, collecting all of our feature data for each and saving
    the data into a dataentry (which is an object in the dataentry.py file)
    :param board_used: The board object that A* was run on
    :param actions: The path (list of actions) that A* returned
    :param score: The score A* got
    :return: A list of dataentries, one for each step in the path
    """

    # List of the data entries we're going to collect at each step of the path
    dataentries = []

    # Get the coordinates of the start and goal
    start = board_used.get_start()
    goal = board_used.get_goal()

    # Tracks the row our agent would be at at this point in the path
    curr_row = start[0]
    # Tracks the column our agent would be at at this point in the path
    curr_column = start[1]
    # Tracks the direction our agent would be facing at at this point in the path
    direction = 'north'
    # Tracks the cost it would have taken so far to get to this point in the path
    cost_so_far = 0

    for index in range(len(actions)):

        # This tracks our features for this step, currently they are:
        row = curr_row # Row
        column = curr_column # Column
        goal_row = goal[0]# Goal Row
        goal_column = goal[1] # Goal Column
        # Vertical Distance to Goal (calculated in data entry constructor from the previous features)
        # Horizontal Distance to Goal (calculated in data entry constructor from the previous features)
        # Direction
        cost_to_goal = (100 - score) - cost_so_far # Cost to Goal (the dependent variable that we are gonna try to estimate)
        sector_cost = featurefunctions.get_sector_cost(curr_row, curr_column, goal_row, goal_column, board_used)

        curr_dataentry = dataentry(row, column, goal_row, goal_column, direction, sector_cost, cost_to_goal)


        # Update variables for the next action being taken
        actionTaken = actions[index]
        updated_values = update_nums_through_move(curr_row, curr_column, direction, actionTaken, board_used)
        curr_row = updated_values[0] # the row after the move
        curr_column = updated_values[1] # the column after the move
        direction = updated_values[2] # the direction after the next move
        cost_so_far += updated_values[3] # tracks the cost thus far in the path

        # Add the data entry to the list of entries
        dataentries.append(curr_dataentry)

    # This creates the final dataentry, for the goal state we've reached, with cost to goal = 0
    curr_dataentry = dataentry(curr_row, curr_column, goal[0], goal[1], direction, 0, 0)

    # Add our final data entry
    dataentries.append(curr_dataentry)

    return dataentries

def write_to_csv(dataset):
    """
    This writes our dataentries to a CSVv
    :param dataset: list of dataentries
    """
    fields = ["Row", "Column", "Goal Row", "Goal Column", "Vertical Distance to Goal", "Horizontal Distance to Goal", "Direction", "Sector Cost", "Cost To Goal"]
    rows = []
    for data in dataset:
        rows.append(data.to_list())

    filename = "output.csv"
    with open(filename, 'w') as csvfile:
        # creating a csv writer object
        csvwriter = csv.writer(csvfile)

        # writing the fields
        csvwriter.writerow(fields)

        # writing the data rows
        csvwriter.writerows(rows)

# The threading that is done with thread_astar, ThreadWithReturnValue, and in main2 is
# just so that we don't waste time on really complicated boards that take over 30 seconds to solve
# when running our data collection
def thread_astar(a, heuristic):
    a_star = a.a_star(heuristic)
    return a_star
class ThreadWithReturnValue(Thread):
    def __init__(self, group=None, target=None, name=None,
                 args=(), kwargs={}, Verbose=None):
        Thread.__init__(self, group, target, name, args, kwargs)
        self._return = None
    def run(self):
        if self._target is not None:
            self._return = self._target(*self._args,
                                                **self._kwargs)
    def join(self, *args):
        Thread.join(self, *args)
        return self._return
# This is used to collect our feature data from multiple runs
def main2(heuristic_number, time_to_run):
    """
    Collects data on multiple A* runs to train a learner
    :param heuristic_number: The heuristic being used
    :param time_to_run: The time to run
    """

    # This is our master list of data entries that we collect
    all_data = []

    # Used to put a time limit on how long to run
    start = time()

    # Tracks the number of boards we've run A* on
    boardnumber = 0

    # Run until we reach our time limit
    while time() - start < time_to_run:

        # A print out to let us know it's running
        print(str(boardnumber))

        # Generate a random board of dimension <first-arg x first-arg>
        # and name it "board<boardnumber>.txt"
        boardgenerator.generate_board(6, boardnumber)

        board_filename = "board" + str(boardnumber) + ".txt"

        # Create a board object from that .txt file
        b = board.Board(board_filename)

        # Create an agent to run A* on that board
        a = agent.Agent(b.get_start(), b.get_goal(), b)
        #a_star = a.a_star(heuristic_number)

        # The threading below is just running A* on the board and collecting data.
        # I only thread so that we can put a time limit on it so we don't waste our time
        # on long boards that take > 30 seconds to solve
        run_time_start = time()
        passed_time_limit = False
        thread = ThreadWithReturnValue(target=thread_astar, args=(a, heuristic_number))
        thread.start()
        while thread.is_alive():
            if time() - run_time_start > 30:
                passed_time_limit = True
                break
        if passed_time_limit:
            print("Skipping long computation")
            os.remove(board_filename)
            continue
        a_star = thread.join()


        # Collect data from the path found by A*
        data = generate_data_from_path(b, a_star[3][::-1][1:], a_star[0])

        # Add our entries to our master list
        for d in data:
            all_data.append(d)

        # Increment the number of boards we've done
        boardnumber += 1

        # Delete the old board.txt file
        os.remove(board_filename)

    # Once we've run for long enough we are done and can write out master list of
    # data entries to a .csv
    print("done")
    write_to_csv(all_data)

# Old main used to run A* for assignment 1
def main(filename, heuristic_number):
    """
    Main function.
    :return: 1 on success and 0 on failure.
    """

    # Create a board
    b = board.Board(filename)
    print(b)
    # Create an agent
    a = agent.Agent(b.get_start(), b.get_goal(), b)
    # print(a)
    start = perf_counter()
    a_star = a.a_star(heuristic_number)
    end = perf_counter()
    # print(f"Time taken:{end - start}")
    print(f"Score:{a_star[0]}")
    print(f"Number of actions:{a_star[1]}")
    print(f"Nodes expanded:{a_star[2]}")
    print(f"Actions:")
    for action in a_star[3][::-1][1:]:
        print(f"\t{action.lower()}")

    #data = generate_data_from_path(b, a_star[3][::-1][1:], a_star[0])

    #for i in data:
    #    print(str(i))

def main3():
    #for h 5
    h5_nodes_expanded = []
    h5_solution_cost = []
    h5_num_actions = []
    h5_bf = []

    #for h 6
    h6_nodes_expanded = []
    h6_solution_cost = []
    h6_num_actions = []
    h6_bf = []

    #for h 7
    h7_nodes_expanded = []
    h7_solution_cost = []
    h7_num_actions = []
    h7_bf = []


    for i in range(10):
        boardgenerator.generate_board(6, 0)
        b = board.Board("board0.txt")

        a = agent.Agent(b.get_start(), b.get_goal(), b)
        a_star5 = a.a_star(5)
        a_star6 = a.a_star(6)
        a_star7 = a.a_star(7)

        h5_nodes_expanded.append(a_star5[2])
        h5_solution_cost.append(100 - a_star5[0])
        h5_num_actions.append(a_star5[1])
        h5_bf.append(math.pow(a_star5[2], 1/a_star5[1]))

        h6_nodes_expanded.append(a_star6[2])
        h6_solution_cost.append(100 - a_star6[0])
        h6_num_actions.append(a_star6[1])
        h6_bf.append(math.pow(a_star6[2], 1/a_star6[1]))

        h7_nodes_expanded.append(a_star7[2])
        h7_solution_cost.append(100 - a_star7[0])
        h7_num_actions.append(a_star7[1])
        h7_bf.append(math.pow(a_star7[2], 1/a_star7[1]))
    
    print("nodes",h5_nodes_expanded)
    print("sol_cost",h5_solution_cost)
    print("num actions",h5_num_actions)
    print("bf",h5_bf)

    print("Avg nodes exp for H5: ", sum(h5_nodes_expanded)/len(h5_nodes_expanded))
    print("Avg sol cost for H5: ", sum(h5_solution_cost)/len(h5_solution_cost))
    print("Avg num actions for H5: ", sum(h5_num_actions)/len(h5_num_actions))
    print("Avg branching f for H5: ", sum(h5_bf)/len(h5_bf))

    print("nodes",h6_nodes_expanded)
    print("sol_cost",h6_solution_cost)
    print("num actions",h6_num_actions)
    print("bf",h6_bf)

    print("Avg nodes exp for H6: ", sum(h6_nodes_expanded)/len(h6_nodes_expanded))
    print("Avg sol cost for H6: ", sum(h6_solution_cost)/len(h6_solution_cost))
    print("Avg num actions for H6: ", sum(h6_num_actions)/len(h6_num_actions))
    print("Avg branching f for H6: ", sum(h6_bf)/len(h6_bf))

    print("nodes",h7_nodes_expanded)
    print("sol_cost",h7_solution_cost)
    print("num actions",h7_num_actions)
    print("bf",h7_bf)

    print("Avg nodes exp for H7: ", sum(h7_nodes_expanded)/len(h7_nodes_expanded))
    print("Avg sol cost for H7: ", sum(h7_solution_cost)/len(h7_solution_cost))
    print("Avg num actions for H7: ", sum(h7_num_actions)/len(h7_num_actions))
    print("Avg branching f for H7: ", sum(h7_bf)/len(h7_bf))

if __name__ == "__main__":
    # main("board-6x6.txt", 7)
    # main2(5,7200)
    main3()
