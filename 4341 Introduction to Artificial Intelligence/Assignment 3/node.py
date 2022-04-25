class Node:
    def __init__(self, row, col, parent, h, how_we_got_here,g=0, direction=None):
        """
        Initialize a node.
        :param row: row index on board
        :param col: col index on board
        :param parent: parent Node; None if root
        :param g: g score of node (cost from root to node) default 0 for root
        :param h: h score of node (cost from node to goal)
        """
        self.row = row
        self.col = col
        self.parent = parent
        self.edges = []
        self.h = h
        self.g = g
        self.how_we_got_here = how_we_got_here
        self.direction = direction
        # f score of node is g + h (total travel distance + estimated travel distance)
        self.f = g + h
        self.examined = False

    def __repr__(self):
        """
        String representation of node.
        :return: string representation of node
        """
        return f"{'Start' if self.parent is None else 'node' } [{self.row}, {self.col}] facing {self.direction} f={self.f}"

    def __gt__(self, other):
        if self.row == other.row and self.col == other.col and self.f > other.f:
            return True

    def set_parent(self, parent):
        """
        Set parent node.`
        :param parent: Node object
        :return: None
        """
        self.parent = parent

    def set_g(self, g):
        """
        Set g score of node.
        :param g: new g score
        :return: None
        """
        self.g = g

    def set_f(self):
        """
        recalculate f score of node.
        :return: new f score
        """
        self.f = self.g + self.h
        return self.f

    def set_examined(self,value=True):
        """
        Set node as examined.
        :return: None
        """
        self.examined = value

    def add_edge(self, edge):
        """
        Add edge to node.
        :param edge: Node object to add
        :return: list of edges for current node
        """
        self.edges.append(edge)
        return self.edges
