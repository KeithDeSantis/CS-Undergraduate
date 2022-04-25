
def identify_sector_of_agent(row, column, goal_row, goal_column):
    """
    Determines the sector which the agent is in relative to the goal
    :param row:
    :param column:
    :param goal_row:
    :param goal_column:
    :return:
    """

    top_left = "top_left"
    top_center = "top_center"
    top_right = "top_right"
    middle_left = "middle_left"
    middle_right = "middle_right"
    bottom_left = "bottom_left"
    bottom_center = "bottom_center"
    bottom_right = "bottom_right"
    in_center = "in_center"

    if row < goal_row - 1:

        if column < goal_column - 1:
            return top_left

        elif goal_column - 1 <= column and column <= goal_column + 1:
            return top_center

        elif column > goal_column + 1:
            return top_right

    elif goal_row - 1 <= row and row <= goal_row + 1:

        if column < goal_column - 1:
            return middle_left

        elif goal_column - 1 <= column and column <= goal_column + 1:
            return in_center

        elif column > goal_column + 1:
            return middle_right

    elif row > goal_row + 1:

        if column < goal_column - 1:
            return bottom_left

        elif goal_column - 1 <= column and column <= goal_column + 1:
            return bottom_center

        elif column > goal_column + 1:
            return bottom_right

def get_sector_cost(row, col, goal_row, goal_col, board):
    sector = identify_sector_of_agent(row, col, goal_row, goal_col)

    # checks if goal location could compromise sector cost calculations
    if(check_goal_not_edge_case(goal_row, goal_col, board)):
        edge_case_value = get_edge_case_sector_cost(goal_row, goal_col, board, sector)
        #below is true if robot is in a sector affected by the edge case
        if(edge_case_value is not None):
            return edge_case_value

    # check the cost near the goal based on location of robot
    if(sector == "top_left"):
        """
        [X][X][ ]
        [X][G][ ]
        [ ][ ][ ]
        """
        return board.get_cost(goal_row-1, goal_col-1) + board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row-1, goal_col)
    elif(sector == "top_center"):
        """
        [X][X][X]
        [ ][G][ ]
        [ ][ ][ ]
        """
        return board.get_cost(goal_row-1, goal_col-1) + board.get_cost(goal_row-1, goal_col+1) + board.get_cost(goal_row-1, goal_col)
    elif(sector == "top_right"):
        """
        [ ][X][X]
        [ ][G][X]
        [ ][ ][ ]
        """
        return board.get_cost(goal_row-1, goal_col) + board.get_cost(goal_row-1, goal_col+1) + board.get_cost(goal_row, goal_col+1)
    elif(sector == "middle_left"):
        """
        [X][ ][ ]
        [X][G][ ]
        [X][ ][ ]
        """
        return board.get_cost(goal_row-1, goal_col-1) + board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row+1, goal_col-1)
    elif(sector == "middle_right"):
        """
        [ ][ ][X]
        [ ][G][X]
        [ ][ ][X]
        """
        return board.get_cost(goal_row-1, goal_col+1) + board.get_cost(goal_row, goal_col+1) + board.get_cost(goal_row+1, goal_col+1)
    elif(sector == "bottom_left"):
        """
        [ ][ ][ ]
        [X][G][ ]
        [X][X][ ]
        """
        return board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row+1, goal_col-1) + board.get_cost(goal_row+1, goal_col)
    elif(sector == "bottom_center"):
        """
        [ ][ ][ ]
        [ ][G][ ]
        [X][X][X]
        """
        return board.get_cost(goal_row+1, goal_col-1) + board.get_cost(goal_row+1, goal_col) + board.get_cost(goal_row+1, goal_col+1)
    elif(sector == "bottom_right"):
        """
        [ ][ ][ ]
        [ ][G][X]
        [ ][X][X]
        """
        return board.get_cost(goal_row, goal_col+1) + board.get_cost(goal_row+1, goal_col) + board.get_cost(goal_row+1, goal_col+1)
    elif(sector == "in_center"):
        """
        [ ][ ][ ]
        [ ][G][ ]
        [ ][ ][ ]
        """
        return 0


def check_goal_not_edge_case(goal_row, goal_col, board):
    if(goal_row == 0 or goal_col == 0 or goal_row == board.rows or goal_col == board.cols):
        return True
    else:
        return False

def get_edge_case_sector_cost(goal_row, goal_col, board, sector):
    """
    Only checks affected sectors; if there would still be 3 values for a sector
    with an edge case, returns Null and value calculated in get_sector_cost method 
    """
    
    #check if corner
    if(goal_row == 0 and goal_col == 0):
        if(sector == "bottom_center"):
            return board.get_cost(goal_row+1, goal_col) + board.get_cost(goal_row+1, goal_col+1)
        elif(sector == "middle_right"):
            return board.get_cost(goal_row, goal_col+1) + board.get_cost(goal_row+1, goal_col+1)
    elif(goal_row == 0 and goal_col == board.cols):
        if(sector == "bottom_center"):
            return board.get_cost(goal_row+1, goal_col) + board.get_cost(goal_row+1, goal_col-1)
        elif(sector == "middle_left"):
            return board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row+1, goal_col-1)
    elif(goal_row == board.rows and goal_col == 0):
        if(sector == "top_center"):
            return board.get_cost(goal_row-1, goal_col) + board.get_cost(goal_row-1, goal_col+1)
        elif(sector == "middle_right"):
            return board.get_cost(goal_row, goal_col+1) + board.get_cost(goal_row-1, goal_col+1)
    elif(goal_row == board.rows and goal_col == board.cols):
        if(sector == "top_center"):
            return board.get_cost(goal_row-1, goal_col) + board.get_cost(goal_row-1, goal_col-1)
        elif(sector == "middle_left"):
            return board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row-1, goal_col-1)

    #check if along board edges
    elif(goal_row == 0 and not (goal_col == 0 or goal_col == board.cols)):
        if(sector == "middle_left"):
            return board.get_cost(goal_row, goal_col-1) + board.get_cost(goal_row+1, goal_col-1)
        elif(sector == "middle_right"):
            return board.get_cost(goal_row, goal_col+1) + board.get_cost(goal_row+1, goal_col+1)
    elif(goal_row == board.rows and not (goal_col == 0 or goal_col == board.cols)):
        if(sector == "middle_left"):
            return board.get_cost(goal_row-1, goal_col-1) + board.get_cost(goal_row, goal_col-1)
        elif(sector == "middle_right"):
            return board.get_cost(goal_row-1, goal_col+1) + board.get_cost(goal_row, goal_col+1)
    elif(goal_col == 0 and not (goal_row == 0 or goal_row == board.rows)):
        if(sector == "top_center"):
            return board.get_cost(goal_row-1, goal_col) + board.get_cost(goal_row-1, goal_col+1)
        elif(sector == "bottom_center"):    
            return board.get_cost(goal_row+1, goal_col) + board.get_cost(goal_row+1, goal_col+1)
    elif(goal_col == board.cols and not (goal_row == 0 or goal_row == board.rows)):
        if(sector == "top_center"):
            return board.get_cost(goal_row-1, goal_col-1) + board.get_cost(goal_row-1, goal_col)
        elif(sector == "bottom_center"):    
            return board.get_cost(goal_row+1, goal_col-1) + board.get_cost(goal_row+1, goal_col)
    
    # robot not in sector that is affected by the goal position
    else:
        return None