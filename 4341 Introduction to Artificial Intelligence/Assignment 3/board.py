class Board:
    def __init__(self, filename):
        """
        Initializes the board
        :param filename: name of board file to be read
        """
        self.rows = None
        self.cols = None
        # read the board file and store it in a 2D list
        with open(filename, 'r') as f:
            self.board = " ".join(f.read().splitlines()).replace('\t', '').split()
            self.rows = len(self.board)-1
            self.cols = len(self.board[0])-1
            self.board = [list(x) for x in self.board]

    def __repr__(self):
        """
        Prints the board
        :return: The board formatted like the sample board
        """
        boardStr = ""
        for row in self.board:
            boardStr += "\t".join(row) + "\n"
        return "Board:\n" + boardStr

    def get_start(self):
        """
        Returns the start position of the board
        :return: Returns the start position of the board as a list
        """
        for i in range(self.rows+1):
            for j in range(self.cols+1):
                if self.board[i][j] == 'S':
                    return [i, j]
        return None

    def get_goal(self):
        """
        Returns the goal position of the board
        :return: Returns the goal position of the board as a list
        """
        for i in range(self.rows+1):
            for j in range(self.cols+1):
                if self.board[i][j] == 'G':
                    return [i, j]
        return None

    def get_cost(self, row, col):
        try:
            if self.board[row][col] == 'S':
                return 1
            elif self.board[row][col] == 'G':
                return -1
            else:
                return int(self.board[row][col])
        except IndexError:
            print(f"IndexError: {row}, {col}")
