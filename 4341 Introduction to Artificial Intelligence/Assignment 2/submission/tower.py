import copy
import random
from InputReader import InputReader

# TODO implement these bad boys

'''
Some things to consider for crossover/mutation
- Will likely want to move in sections for cross over because order matters
- Might want to make mutation more likely because the starting values are shit
- Will have to allow either/both the crossover/mutation functions to alter the height of the organisms
'''


def crossover(tower1, tower2):
    child1 = copy.copy(tower1)
    child2 = copy.copy(tower2)

    child1.bottom = tower2.bottom  # T1 top, T2 bottom
    child2.bottom = tower1.bottom  # T2 top, T1 bottom

    # how to best merge the middles?

    middle1 = tower1.middle
    middle2 = tower2.middle

    child1.middle = middle1[0:(len(middle1) // 2)] + middle2[len(middle2) // 2: len(middle2)]
    child2.middle = middle2[0:(len(middle2) // 2)] + middle1[len(middle1) // 2: len(middle1)]

    return child1, child2


def half_crossover(tower1, tower2):
    child1 = copy.copy(tower1)
    child2 = copy.copy(tower2)

    child1.top = tower2.top  # T1 top, T2 bottom
    child2.top = tower1.top  # T2 top, T1 bottom

    # how to best merge the middles?

    middle1 = tower1.middle
    middle2 = tower2.middle

    child1.middle = middle1[0:(len(middle1) // 2)] + middle2[len(middle2) // 2: len(middle2)]
    child2.middle = middle2[0:(len(middle2) // 2)] + middle1[len(middle1) // 2: len(middle1)]

    return child1, child2


def sectional_crossover(tower1, tower2):
    c1 = copy.copy(tower1)
    c2 = copy.copy(tower2)

    c1.bottom = tower2.bottom  # T1 top, T2 bottom
    c2.bottom = tower1.bottom  # T2 top, T1 bottom

    min_middle = min(len(tower1.middle), len(tower2.middle))
    if min_middle == 0:
        if len(tower1.middle) == 0:
            c1.middle = tower2.middle.copy()
            c2.middle = tower2.middle.copy()
        else:
            c2.middle = tower1.middle.copy()
            c1.middle = tower1.middle.copy()
    else:
        slice_size = random.randint(1, min_middle)
        c1_start = random.randint(0, len(c1.middle) - slice_size)
        c2_start = random.randint(0, len(c2.middle) - slice_size)
        c1.middle[c1_start:c1_start + slice_size] = tower2.middle[c2_start:c2_start + slice_size].copy()
        c2.middle[c2_start:c2_start + slice_size] = tower1.middle[c2_start:c2_start + slice_size].copy()

    return c1, c2


'''
Pick between mutations?

Swap piece unused
Move pieces around
Delete piece
Add piece

'''

def shuffle_mutation(tower):
    pieces = tower.as_list()
    random.shuffle(pieces)
    tower.from_list(pieces)

def remove_mutation(tower):
    pieces = tower.as_list()
    num = random.randint(0,len(pieces) - 1)

    piece_removed = pieces.pop(num)
    tower.unused_pieces.append(piece_removed)
    tower.from_list(pieces)

def add_mutation(tower):
    pieces_unused = tower.unused_pieces
    if len(pieces_unused) > 0:
        pieces_used = tower.as_list()
        num = random.randint(0,len(pieces_unused) - 1)
        new_piece = pieces_unused.pop(num)
        pieces_used.insert(random.randint(0,len(pieces_used)),new_piece)
        tower.unused_pieces = pieces_unused
        tower.from_list(pieces_used)

def swap_unused_mutation(tower):
    pieces_unused = tower.unused_pieces
    len_unused = len(pieces_unused)
    if len_unused > 0:
        pieces_used = tower.as_list()
        minlen = min(len_unused, len(pieces_used))
        num_to_swap = random.randint(1,minlen)
        used_indices = random.sample(range(len(pieces_used)),k=num_to_swap)
        unused_indices = random.sample(range(len_unused), k=num_to_swap)
        indices = zip(used_indices,unused_indices)
        for i,j in indices: # i is index in used list, j is index in unused list
            temp_piece = pieces_used[i]
            pieces_used[i] = pieces_unused[j]
            pieces_unused[j] = temp_piece
        tower.unused_pieces = pieces_unused
        tower.from_list(pieces_used)




def random_mutation(tower):
    num = random.randint(0,3)
    if num == 0:
        shuffle_mutation(tower)
    elif num == 1:
        remove_mutation(tower)
    elif num == 2:
        add_mutation(tower)
    else:
        swap_unused_mutation(tower)





# This will not change
def fitness(tower):
    if tower.bottom.type != TowerPiece.DOOR:
        return 0
    if tower.top.type != TowerPiece.LOOKOUT:
        return 0

    for piece in tower.middle:
        if piece.type != TowerPiece.WALL:
            return 0

    t_list = tower.as_list()
    for i in range(len(t_list) - 1):
        if t_list[i].width < t_list[i + 1].width:
            return 0
        if t_list[i].strength < tower.height() - i - 1:
            return 0

    return 10 + pow(tower.height(), 2) - tower.total_cost()


""" Made this a class simply to make accessing data from it more intuitive """


class TowerPiece:
    DOOR = 0
    WALL = 1
    LOOKOUT = 2
    STR_TO_NUM = {'Door': DOOR, 'Wall': WALL, 'Lookout': LOOKOUT}

    def __init__(self, type, width, strength, cost):
        self.type = type
        self.width = width
        self.strength = strength
        self.cost = cost

    def __str__(self):
        type_string = ""
        if self.type == TowerPiece.DOOR:
            type_string = "DOOR"
        elif self.type == TowerPiece.WALL:
            type_string = "WALL"
        else:
            type_string = "LOOKOUT"
        return "[" + type_string + ",w=" + str(self.width) + ",s=" + str(self.strength) + ",c=" + str(self.cost) + "]"

    def __eq__(self, other):
        return (self.type, self.cost, self.strength, self.width) == (
        other.type, other.cost, other.strength, other.width)


class Tower:
    def __init__(self,
                 initial_pool,  # the initial pool of numbers the population is made from (as a list)
                 crossover_function=crossover,
                 mutation_function=random_mutation,
                 fitness_function=fitness
                 ):

        pool = initial_pool.copy()
        self.top = random.choice(pool)
        pool.remove(self.top)
        self.bottom = random.choice(pool)
        pool.remove(self.bottom)
        n = random.randint(0, min(7, len(pool)))
        self.middle = []  # lower indexes are the BOTTOM of the tower
        for i in range(n):
            self.middle.append(random.choice(pool))
            pool.remove(self.middle[i])

        self.initial_pool = initial_pool
        self.crossover_function = crossover_function
        self.mutation_function = mutation_function
        self.fitness_function = fitness_function
        self.unused_pieces = pool

        # calculate fitness once
        self.fitness = self.fitness_function(self)

    def height(self):
        return len(self.middle) + 2

    def total_cost(self):
        return int(sum(map(lambda p: p.cost, self.middle))) + self.top.cost + self.bottom.cost

    def as_list(self):
        li = [self.bottom]
        li = li + self.middle
        li.append(self.top)
        return li

    def from_list(self, li):
        self.bottom = li[0]
        self.top = li[-1]
        self.middle = li[1:-1]

    ''' Returns the calculated fitness of the organism '''

    def calculate_fitness(self):
        self.fitness = self.fitness_function(self)
        return self.fitness

    ''' Returns the two children created by the crossover of self with another parent '''

    def crossover(self, second_parent):
        child1, child2 = self.crossover_function(self, second_parent)
        self.post_process(child1)
        self.post_process(child2)
        child1.calculate_fitness()
        child2.calculate_fitness()
        return child1, child2

    ''' Mutates the self organism as defined by mutation_function '''

    def mutate(self):
        self.mutation_function(self)  # call the mutation function on self
        self.post_process(self)
        self.calculate_fitness()
        pass

    # TODO implement post process
    def post_process(self, child):
        cpool = child.initial_pool.copy()
        indices = []
        count = 0
        replacement_list = child.as_list()
        for piece in replacement_list:
            if piece in cpool:
                cpool.remove(piece)
            else:
                indices.append(count)
            count += 1

        for i in indices:
            if len(cpool) == 0:
                break

            replacement = random.choice(cpool)
            replacement_list[i] = replacement
            cpool.remove(replacement)

        child.from_list(replacement_list)
        self.unused_pieces = cpool

    def __str__(self):
        s = "Top" + "\n" + str(self.top) + "\n"
        for p in self.middle[::-1]:
            s += str(p) + "\n"

        s += str(self.bottom)
        return s + "\n" + "Bottom"

    def __gt__(self, other):
        return self.fitness > other.fitness

    ''' Overrides < based on fitness '''

    def __lt__(self, other):
        return self.fitness < other.fitness


def tower_pool_from_file(filename):
    ir = InputReader()
    raw = ir.read_tower_input(filename)
    pool = []
    for datum in raw:
        pool.append(TowerPiece(TowerPiece.STR_TO_NUM[datum[0]], int(datum[1]), int(datum[2]), int(datum[3])))

    return pool


if __name__ == "__main__":
    pool = tower_pool_from_file("ProblemTwoTest.txt")
    # tower = Tower(pool)
    # print(tower)
    # print("Height " + str(tower.height()))
    # print("Cost $" + str(tower.total_cost()))
    # print("Fitness " + str(tower.fitness))
    # print("\n")
    # print("\n")
    #
    # for _ in range(100):
    #     t = Tower(pool)
    #     if t.fitness > 0:
    #         print(t)
    #         print(t.fitness)
    #         print("\n")

    tower1 = Tower(pool)
    tower2 = Tower(pool)

    print(tower1)
    print(tower2)

    c1, c2 = tower1.crossover(tower2)
    print("\nChildren")
    print(c1)
    print(c2)
