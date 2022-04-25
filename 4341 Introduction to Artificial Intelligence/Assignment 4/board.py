class Board:
    def __init__(self, rows, columns, read_board):
        # icons for readable policy
        self.dirs = {0: "▲", 1: "▶", 2: "▼", 3: "◀"}
        # initialize board with 0's
        self.board = [[0 for _ in range(columns)] for _ in range(rows)]
        self.rows = rows
        self.cols = columns
        self.read_board = read_board

    def __repr__(self):
        """
        String representation of the board
        :return: representation of the board
        """
        value = ""
        for row in self.read_board:
            for col in row:
                value += f"{col}\t"
            value += "\n"

        value += "-" * (len("".join(value.split("\n"))) // 3) + "\n"

        for row in self.board:
            for col in row:
                value += f"{col}\t"
            value += "\n"
        return f"{value}"

    def set_to_directional(self, qtable):
        """
        sets the board to use directional icons for readability
        :param qtable: qtable object
        :return: None
        """
        for item in qtable.items():
            key = item[0]
            value = self.dirs[item[1].index(max(item[1]))]
            if self.check_if_terminal(key[0], key[1]):
                self.board[key[0]][key[1]] = max(item[1])  # "🏁"
            else:
                self.board[key[0]][key[1]] = value

    def check_if_terminal(self, row, col):
        """
        checks if the given position is a terminal state
        :param row: row to check
        :param col: col to check
        :return: Boolean
        """
        t = not self.read_board[row][col] == 0
        return not self.read_board[row][col] == 0

    def populate_qtable(self, qtable, default_value=0):
        """
        populates the qtable with the boards values and default value as initial values
        :param qtable: qtable object
        :param default_value: initial value for the qtable
        :return: True on success, error  on failure
        """
        for row_count, row in enumerate(self.read_board):
            for col_count, col in enumerate(self.read_board[row_count]):
                if self.check_if_terminal(row_count, col_count):
                    qtable[(row_count, col_count)] = [self.read_board[row_count][col_count] for _ in range(4)]
                else:
                    qtable[(row_count, col_count)] = [default_value for _ in range(4)]
        return True
