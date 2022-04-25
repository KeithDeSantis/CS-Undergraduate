import node
import math
import featurefunctions


def direction_change_by_action(direction, action):
    if action == "FORWARD" or action == "BASH" or action == "BASHING":
        return direction

    if direction == "north":
        if action == "LEFT":
            return "east"
        elif action == "RIGHT":
            return "west"
    elif direction == "east":
        if action == "LEFT":
            return "south"
        elif action == "RIGHT":
            return "north"
    elif direction == "south":
        if action == "LEFT":
            return "west"
        elif action == "RIGHT":
            return "east"
    elif direction == "west":
        if action == "LEFT":
            return "north"
        elif action == "RIGHT":
            return "south"

class Agent:
    def __init__(self, start, goal, board):
        """
        Initialize the agent.
        :param start: Start position on the board.
        :param goal: Goal position on the board.
        :param board: Board object.
        """
        self.position = start
        self.goal = goal
        self.board = board
        self.balanced = True
        self.directions = ["north", "east", "south", "west"]
        self.direction = self.directions[0]
        self.points = 0

    def __repr__(self):
        """
        Represent the agent as a string.
        :return: A string with the agent's direction, position and score.
        """
        return f"Agent at position {self.position} facing {self.direction} with {self.points} points"

    def _move(self, bashing=False, curr_pos=None, curr_dir=None):
        """
        Move the agent in the direction it is facing.
        Cannot perform a move if the agent is not balanced.
        :param bashing: Boolean to determine if the agent is bashing or not.
        :return: False if the agent is not able to move, otherwise returns new position.
        """
        if not curr_pos:
            curr_pos = self.position
        if not curr_dir:
            curr_dir = self.direction
        save_position = curr_pos.copy()  # Saves current position before move
        if curr_dir == 'north' and (0 < curr_pos[0]):  # Move north if possible
            save_position[0] -= 1
        elif curr_dir == 'south' and (curr_pos[0] < self.board.rows):  # Move south if possible
            save_position[0] += 1
        elif curr_dir == 'east' and (curr_pos[1] < self.board.cols):  # Move east if possible
            save_position[1] += 1
        elif curr_dir == 'west' and (0 < curr_pos[1]):  # Move west if possible
            save_position[1] -= 1
        if save_position == curr_pos:  # If the agent is not moving, return false
            return save_position, 0

        # If the agent is not bashing, take the cost of the move from the score
        return save_position, self.board.get_cost(save_position[0], save_position[1])

    def _bash(self, curr_pos=None, curr_dir=None):
        """
        Bash the agent in the direction it is facing.
        Bashing means that the agent will move in the facing direction one unit ignoring the cost and instead costing 3
        After bashing the agent must move forward once to "regain balance"
        :return: new position after bashing
        """
        if not curr_pos:
            curr_pos = self.position
        if not curr_dir:
            curr_dir = self.direction
        cost_of_bash = 0
        save_position = self._move(True, curr_pos, curr_dir)[0]
        if not save_position[0]:
            return curr_pos, 0
        cost_of_bash += 3

        # save_position = self._move(True, [save_position[0], save_position[1]], curr_dir)[0]
        # if not save_position:
        #     return curr_pos, 0
        # cost_of_bash += self.board.get_cost(save_position[0], save_position[1])

        # self._change_score(-3)  # ignores space cost and instead costs 3
        return save_position, cost_of_bash

    def _change_score(self, points):
        """
        Change the score of the agent.
        :param points: The amount to adjust the score by.
        :return: the score after the adjustment.
        """
        self.points += points
        return self.points

    def _turn_left(self, curr_dir):
        """
        Turn the agent to the left.
        :return: new direction facing
        """
        # self.direction = self.directions[(self.directions.index(self.direction) - 1) % 4]
        return self.directions[(self.directions.index(curr_dir) - 1) % 4]

    def _turn_right(self, curr_dir):
        """
        Turn the agent to the right.
        :return: new direction facing
        """
        # self.direction = self.directions[(self.directions.index(self.direction) + 1) % 4]
        return self.directions[(self.directions.index(curr_dir) + 1) % 4]

    def _turn(self, curr_pos, direction, facing):
        """
        Turn the agent to the specified direction.
        Cannot perform a turn if the agent is not balanced.
        :param direction: left or right
        :return: new direction facing or False if the direction is invalid.
        """
        direction = direction.upper()  # Make sure the direction is uppercase
        options = {"LEFT": self._turn_left, "RIGHT": self._turn_right}  # Dictionary of valid directions
        # Run turn_left or turn_right depending on direction or False in the event of an invalid direction
        # self._change_score(math.ceil(self.board.board[self.position[1]][self.position[0]])/2)
        new_direction = options.get(direction)(facing) or False
        return curr_pos, math.ceil(self.board.get_cost(curr_pos[0], curr_pos[1]) / 2), new_direction

    def _get_heuristic(self, start=None, goal=None):
        h = 0
        return h

    def _get_edges(self,):
        start = self.position
        edges = []
        for i in range(4):
            for j in range(4):
                if i < 0 or i > self.board.rows:
                    continue
                if j < 0 or j > self.board.cols:
                    continue
                edges.append(self.board.get_cost(i, j))
        return edges

    def _get_path(self, curr):
        path = []
        actions = []
        while curr:
            path.append(curr)
            actions.append(curr.how_we_got_here)
            curr = curr.parent
        return path, actions

    def direction_number(self, direction):
        """
        Converts the dataentry to a list for writing to csv
        Direction is represented by a number since we're doing linear regression
        :return:
        """
        if direction == "north":
            return 1
        elif direction == "east":
            return 2
        elif direction == "south":
            return 3
        elif direction == "west":
            return 4

    def calc_heuristic(self, number, coord, direction):
        if number <= 0 or number > 7:
            raise ValueError("Number must be between 1 and 7")
        """
        Calculates heuristic between robot's current position
        and goal.
        :param number: type of heuristic
        :param coord: node coord that heuristic is calculated for
        :return: h value as an int
        """
        goal = self.board.get_goal()
        diff_r = abs(coord[0] - goal[0])
        diff_c = abs(coord[1] - goal[1])
        dir_num = self.direction_number(direction)
        if number == 1:
            return 0
        elif number == 2:
            h = min(diff_c, diff_r)
        elif number == 3:
            h = max(diff_c, diff_r)
        elif number == 4:
            h = diff_r + diff_c
        elif number == 5 or number == 6:

            h = math.sqrt((diff_c**2 + diff_r**2)) + ((diff_c + diff_r)/2) # Alyssa's H5

            #if coord[0] == goal[0] and coord[1] == goal[1]: # Keith's H5
            #   h = 0
            #elif self.goal[0] == self.board.get_start()[0] or self.goal[1] == self.board.get_start()[1]:
            #   h = abs(diff_r + diff_c)
            #else:
            #   h = abs(diff_r + diff_c) + 1

            #h = abs((diff_r + diff_c) -1) # todo i changed H5 to mine, not much better and shouldnt cause problems but we'll pick one
            #h += min(self._get_edges())
            if number == 6:
                h *= 3
        elif number == 7:
            if(goal[0] == coord[0] and goal[1] == coord[1]):
                return 0
            sector = featurefunctions.get_sector_cost(coord[0], coord[1], goal[0], goal[1], self.board)
            h = -0.1364 * coord[0] + 0.0183 * coord[1] + 0.0616 * goal[0] + 2.7938 * diff_r + 2.918 * diff_c + -0.2633 * dir_num + 0.0375 * sector + -0.0396  # todo our trained heuristic will go here
        return h

    def a_star(self, heuristic):
        start_node = node.Node(self.position[0], self.position[1], None, heuristic, "START", g=1,
                               direction='north')  # creates start node

        options_per_node = ["FORWARD", "LEFT", "RIGHT", "BASHING"]  # choices per node
        VISITED = list()
        OPEN = list()
        OPEN.append(start_node)
        while len(OPEN) > 0:
            # print(len(OPEN))
            curr = min(OPEN, key=lambda x: x.f)  # get lowest f-score
            # print(f"{curr} : {curr.parent}")
            if self.board.get_cost(curr.row, curr.col) == -1:  # check if goal reached. if reached print path
                len_of_path = -1
                # print("GOAL REACHED\n\n\n")
                goal_info = self._get_path(curr)
                path = goal_info[0]
                actions = goal_info[1]
                nodes_expanded = 0
                score = 0
                for step in path[::-1]:
                    len_of_path += 1
                    nodes_expanded = len(VISITED)
                    score = 100 - (curr.f + 1)
                    # print(step)
                return score, len_of_path, nodes_expanded, actions
            OPEN.remove(curr)
            VISITED.append(curr)
            for option in options_per_node:
                # print(f"{option} from {curr.row}, {curr.col}")
                if option == "FORWARD":
                    forward = self._move(curr_pos=[curr.row, curr.col], curr_dir=curr.direction)
                    new_pos = forward[0]
                    cost_of_move = forward[1]
                    if cost_of_move == 0:
                        continue
                    new_heuristic = self.calc_heuristic(heuristic, new_pos, direction_change_by_action(curr.direction, option))
                    curr.add_edge(
                        node.Node(new_pos[0], new_pos[1], curr, new_heuristic, "FORWARD", g=curr.g + cost_of_move,
                                  direction=curr.direction))
                    continue
                elif option == "LEFT":
                    if curr.how_we_got_here == "BASH" or curr.how_we_got_here == "RIGHT":
                        continue
                    left = self._turn([curr.row, curr.col], "LEFT", curr.direction)
                    if left:
                        new_pos = left[0]
                        cost_of_move = left[1]
                        new_heuristic = self.calc_heuristic(heuristic, new_pos, direction_change_by_action(curr.direction, option))
                        curr.add_edge(
                            node.Node(new_pos[0], new_pos[1], curr, new_heuristic, "LEFT", g=curr.g + cost_of_move,
                                      direction=left[2]))
                        continue
                elif option == "RIGHT":
                    if curr.how_we_got_here == "BASH" or curr.how_we_got_here == "LEFT":
                        continue
                    right = self._turn([curr.row, curr.col], "RIGHT", curr.direction)
                    if right:
                        new_pos = right[0]
                        cost_of_move = right[1]
                        new_heuristic = self.calc_heuristic(heuristic, new_pos, direction_change_by_action(curr.direction, option))
                        curr.add_edge(
                            node.Node(new_pos[0], new_pos[1], curr, new_heuristic, "RIGHT", g=curr.g + cost_of_move,
                                      direction=right[2]))
                        continue
                elif option == "BASHING":
                    if curr.how_we_got_here == "BASH":
                        continue
                    bash = self._bash(curr_pos=[curr.row, curr.col], curr_dir=curr.direction)
                    new_pos = bash[0]
                    cost_of_move = bash[1]
                    if cost_of_move == 0:
                        continue
                    new_heuristic = self.calc_heuristic(heuristic, new_pos, direction_change_by_action(curr.direction, option))
                    curr.add_edge(
                        node.Node(new_pos[0], new_pos[1], curr, new_heuristic, "BASH", g=curr.g + cost_of_move,
                                  direction=curr.direction))
                    continue
            for e in curr.edges:
                if (e not in VISITED and e not in OPEN) or e.f > curr.f:
                    OPEN.append(e)
