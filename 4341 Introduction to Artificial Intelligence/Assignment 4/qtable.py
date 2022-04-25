class QTable(dict):
    """
    QTable is a lookup table for Q-Learning, that extends the dict class
    """

    def __init__(self):
        super().__init__()

    def __setitem__(self, key, value):
        """
        Only allows the user to set values for the QTable if the value is a lst of 4 elements
        representing the 4 actions.that can be taken from any given space
        :param key: should be a tuple in the format (row, col)
        :param value: should be a list of 4 elements representing the 4 actions [UP, DOWN, LEFT, RIGHT]
        :return: None
        """
        if not isinstance(value, list) or len(value) != 4:
            raise ValueError("QTable values must be lists of length 4")
        super().__setitem__(key, value)

    def max_action(self, row, col):
        """
        This gets the index of the maximum action utility for a given space
        """
        action_values = super().__getitem__((row, col))
        return action_values.index(max(action_values))
