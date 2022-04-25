class dataentry:
    """
    This class will hold all of our features values for a given data entry
    We will collect many of these and train our learner on them
    FEATURES:
    Agent Row
    Agent Column
    Goal Row
    Goal Column
    Vertical Distance to Goal
    Horizontal Distance to Goal
    Agent Direction
    Cost to Goal (this will be what our learner is trying to estimate)
    """
    def __init__(self, row, column, goal_row, goal_column, direction, sector_cost, cost_to_goal):
        self.row = row
        self.column = column
        self.goal_row = goal_row
        self.goal_column = goal_column
        self.vertical_distance_to_goal = abs(self.goal_row - self.row)
        self.horizontal_distance_to_goal = abs(self.goal_column - self.column)
        self.direction = direction
        self.sector_cost = sector_cost
        self.cost_to_goal = cost_to_goal

    def __str__(self):
        string = "Row: " + str(self.row) + "\n"
        string = string + "Column: " + str(self.column) + "\n"
        string = string + "Goal Row: " + str(self.goal_row) + "\n"
        string = string + "Goal Column: " + str(self.goal_column) + "\n"
        string = string + "Vertical Distance to Goal: " + str(self.vertical_distance_to_goal) + "\n"
        string = string + "Horizontal Distance to Goal: " + str(self.horizontal_distance_to_goal) + "\n"
        string = string + "Direction: " + self.direction + "\n"
        string = string + "Sector Cost: " + str(self.sector_cost) + "\n"
        string = string + "Cost to Goal: " + str(self.cost_to_goal)
        return string

    def to_list(self):
        """
        Converts the dataentry to a list for writing to csv
        Direction is represented by a number since we're doing linear regression
        :return:
        """
        if self.direction == "north":
            numerical_direction = 1
        elif self.direction == "east":
            numerical_direction = 2
        elif self.direction == "south":
            numerical_direction = 3
        elif self.direction == "west":
            numerical_direction = 4
        return [self.row, self.column, self.goal_row, self.goal_column, self.vertical_distance_to_goal, self.horizontal_distance_to_goal, numerical_direction, self.sector_cost, self.cost_to_goal]