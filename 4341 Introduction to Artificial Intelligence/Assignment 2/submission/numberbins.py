from functools import reduce

import numpy as np
import random
import copy

# FITNESS FUNCTIONS ----------------------------------------------------------
''' Calculates fitness based on the NumberBin's score '''
def score_based_fitness(number_bin):
    bin_one_score = 1
    for num in number_bin.bins[0]:
        bin_one_score = bin_one_score * num

    bin_two_score = 0
    for num in number_bin.bins[1]:
        bin_two_score = bin_two_score + num

    bin_three_score = number_bin.get_bin_three().max() - number_bin.get_bin_three().min() # bin 3 does max-min

    return bin_one_score + bin_two_score + bin_three_score # bin 4 is ignored


# CROSSOVER FUNCTIONS --------------------------------------------------------
# Ensure all crossover functions have at least the parameters of parent_one, parent_two, and number_crossovers=
''' Performs crossover by swapping a number of random bins between parent 1 and 2
    NOTE: the bin swapped is the same index (i.e. bin 2 would be swapped for both) '''
def crossover_bin_for_bin(parent_one, parent_two, number_crossovers=1):

    child_one = copy.deepcopy(parent_one) # this is being currently done in such a way that it passes on initial_pool and all functions used by parents to children
                                          # if we end up wanting to use different functions generation to generation then this will have to be changed
    child_two = copy.deepcopy(parent_two)

    swappable_bins = [0,1,2,3] # to track which bins have been swapped

    for number in range(number_crossovers):

        bin_number = swappable_bins[random.randint(0,len(swappable_bins)-1)]
        child_one.swap_bins(child_two, bin_number, bin_number)
        swappable_bins.remove(bin_number)

    return child_one, child_two

''' Morgan's 'sectional' crossover that takes a segment of a bin and swaps it with the other bins segment '''
def crossover_sectional(parent_one, parent_two, size=5, number_crossovers=4): # todo trying to figure out how to make these passable arguments in testing
    child_one = copy.deepcopy(parent_one)
    child_two = copy.deepcopy(parent_two)

    for swap in range(number_crossovers):

        offset = random.randint(0, 10 - size) # the amount we are able to move the indices without going out of bounds
        indices_of_section = [0 + offset, 0 + size + offset]

        first_bin = random.randint(0,3)
        second_bin = random.randint(0,3)

        # making the swap
        temp = copy.deepcopy(child_one.bins[first_bin][indices_of_section[0]:indices_of_section[1]])
        child_one.bins[first_bin][indices_of_section[0]:indices_of_section[1]] = copy.deepcopy(child_two.bins[second_bin][indices_of_section[0]:indices_of_section[1]])
        child_two.bins[second_bin][indices_of_section[0]:indices_of_section[1]] = temp

    return child_one, child_two


def product(arr):
    return reduce(lambda a, b: a*b, arr.copy())

def max_crossover(parent_one, parent_two):
    child_one = copy.deepcopy(parent_one)
    child_two = copy.deepcopy(parent_two)
    max_p1 = parent_one.get_bin_one()
    max_p1_value = product(max_p1)
    for bin in parent_one.get_bins():
        p = product(bin)
        if p > max_p1_value:
            max_p1 = bin
            max_p1_value = p

    max_p2 = parent_two.get_bin_one()
    max_p2_value = product(max_p2)
    for bin in parent_two.get_bins():
        p = product(bin)
        if p > max_p2_value:
            max_p2 = bin
            max_p2_value = p

    child_one.bins[0] = np.append(max_p1[0:5], max_p2[5:10])
    child_two.bins[0] = np.append(max_p2[0:5], max_p1[5:10])


    return child_one, child_two



# MUTATION FUNCTIONS ----------------------------------------------------------
''' Simple mutation that swaps one number in a bin for another '''
def one_number_swap_mutation(number_bin):

    first_bin_number = random.randint(0,3) # generate random indices
    second_bin_number = random.randint(0, 3)
    first_element_number = random.randint(0,9)
    second_element_number = random.randint(0,9)

    while first_element_number == second_element_number and first_bin_number == second_bin_number: # make sure they aren't the same index
        second_bin_number = random.randint(0, 3)
        second_element_number = random.randint(0, 9)

    number_bin.swap_elements(first_bin_number, first_element_number, second_bin_number, second_element_number) # swap

    pass

''' Mutation that swaps one bin for another '''
def mutation_swap_bins(number_bin):

    bin_one = random.randint(0,3)
    bin_two = random.randint(0,3)

    while bin_one == bin_two:
        bin_two = random.randint(0,3)

    number_bin.swap_bins(bin_one, bin_two)

    pass

''' Mutation which swaps slices of two bins '''
def mutation_slice(number_bin):
    bin_nums = np.random.choice(range(4), size=2, replace=False)
    slice_range = np.random.choice(range(10), size=2, replace=False)
    if slice_range[0] > slice_range[1]:
        slice_range = np.flip(slice_range)
    number_bin.slice_bins(bin_nums, slice_range)


class NumberBins:

    def __init__(self,
                 initial_pool, # the initial pool of numbers the population is made from (as a list)
                 crossover_function=crossover_sectional, # the function used for crossover
                 mutation_function=one_number_swap_mutation, # the function used for mutation
                 fitness_function=score_based_fitness, # the function used to determine fitness
                 number_crossovers=4
                 ):
        self.bins = np.array(initial_pool).astype('float')
        np.random.shuffle(self.bins)
        self.bins = self.bins.reshape((4,10))
        self.initial_pool = initial_pool
        self.crossover_function = crossover_function
        self.mutation_function = mutation_function
        self.fitness_function = fitness_function
        self.number_crossovers = number_crossovers
        self.fitness = self.calculate_fitness()
        pass

    ''' Returns the calculated fitness of the organism '''

    ''' Returns the calculated fitness of the organism '''

    ''' Returns the calculated fitness of the organism '''

    def calculate_fitness(self):
        self.fitness = self.fitness_function(self)  # use whatever fitness function was defined
        return self.fitness


    ''' Returns the two children created by the crossover of self with another parent '''

    def crossover(self, second_parent):
        if isinstance(self.crossover_function, list):
            child1, child2 = self.crossover_function[random.randrange(0,len(self.crossover_function))](self, second_parent, number_crossovers=self.number_crossovers) # could adjust to be weighted chance of choosing crossover later
        else:
            child1, child2 = self.crossover_function(self, second_parent)
        self.post_process(child1)
        self.post_process(child2)
        child1.calculate_fitness()
        child2.calculate_fitness()
        return child1, child2

    ''' Mutates the self organism as defined by mutation_function '''

    def mutate(self):
        self.mutation_function(self)  # call the mutation function on self
        self.calculate_fitness()
        pass

    ''' Ensures children are legal by replacing duplicate numbers with numbers that the 
    child lost during crossover'''

    def post_process(self, child):

        pool_of_numbers_lost = child.initial_pool.copy()
        indices_of_duplicates = []  # will be filled with the indices of duplicates (in the child's bins) that need to be swapped

        for row in range(len(child.bins)):  # for each row in the bins
            for index in range(len(child.bins[row])):  # for each element in the row
                if child.bins[row][index] in pool_of_numbers_lost:  # if that number is in the original pool...
                    pool_of_numbers_lost.remove(child.bins[row][
                                                    index])  # remove it from the original pool, leaving just numbers that were missed and need to be re-added
                else:
                    indices_of_duplicates.append(
                        (row, index))  # if a number is in the bins but not the original pool, it is a duplicate
                    # so now we have a list of tuples, each tuple being the [row][index] of duplicate numbers

        for duplicate_tuple in indices_of_duplicates:
            if not pool_of_numbers_lost:
                break
            replacement_number = pool_of_numbers_lost[
                random.randint(0, len(pool_of_numbers_lost) - 1)]  # the lost number we're adding back
            child.bins[duplicate_tuple[0]][duplicate_tuple[1]] = replacement_number  # replace the duplicate
            pool_of_numbers_lost.remove(
                replacement_number)  # remove the re-added number from the lost number list so we don't accidentally add it again

        pass

    ''' Overrides equals, NumberBins are equal is each of their bins contain corresponding numbers '''

    def __eq__(self, other):
        for row_index in range(len(self.bins)):
            first = self.bins[row_index]
            np.sort(first, axis=None)  # I believe this will sort the row of a numpy array
            second = other.get_bins()[row_index]
            np.sort(second, axis=None)
            if not ((first == second).all()):  # I think this is how you check equality of numpy arrays
                return False

        return True

    ''' Overrides > based on fitness '''

    def __gt__(self, other):
        return self.fitness > other.fitness

    ''' Overrides < based on fitness '''

    def __lt__(self, other):
        return self.fitness < other.fitness

    ''' Overrides str()  '''

    def __str__(self):
        string = "Bin 1: " + str(self.bins[0]) + "\n"
        string = string + "Bin 2: " + str(self.bins[1]) + "\n"
        string = string + "Bin 3: " + str(self.bins[2]) + "\n"
        string = string + "Bin 4: " + str(self.bins[3]) + "\n"
        return string


    ''' Swaps the position of the elements at [first_bin][first_index] and [second_bin][second_index]'''

    def swap_elements(self, first_bin, first_index, second_bin, second_index):
        first_element_temp = self.bins[first_bin][first_index]  # get the first element
        self.bins[first_bin][first_index] = self.bins[second_bin][second_index]  # perform the first half of the swap
        self.bins[second_bin][second_index] = first_element_temp  # finish the swap
        pass

    ''' Swaps the position of self's first_bin_number bin and other's second_bin_number bin
        NOTE: It is expected that this is only called during a crossover, if it is ever called outside 
        of that scenario, post_process needs to be called on both bins to make sure they remain legal'''

    def swap_bins(self, other, first_bin_number, second_bin_number):
        saved_bin = self.get_bins()[first_bin_number].copy()  # get the first's respective bin
        self.get_bins()[first_bin_number] = other.get_bins()[second_bin_number].copy()  # make one half of the swap
        other.get_bins()[second_bin_number] = saved_bin  # make the other half of the swap
        pass

    def slice_bins(self, bin_nums, slice_range):
        temp = self.bins.copy()
        self.bins[bin_nums[0]][slice_range[0]:slice_range[1]] = self.bins[bin_nums[1]][slice_range[0]:slice_range[1]]
        self.bins[bin_nums[1]][slice_range[0]:slice_range[1]] = temp[bin_nums[0]][slice_range[0]:slice_range[1]]
        pass

    ''' Getters '''

    def get_bin_one(self):
        return self.bins[0]

    def get_bin_two(self):
        return self.bins[1]

    def get_bin_three(self):
        return self.bins[2]

    def get_bin_four(self):
        return self.bins[3]

    def get_bins(self):
        return self.bins


if __name__ == "__main__":
    pool = [1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10]
    population = [NumberBins(pool), NumberBins(pool)]

    test = population[0]
    test2 = population[1]
    child1, child2 = test.crossover(test2)
    child1.mutate()
    num = child1.calculate_fitness()
    num2 = child2.calculate_fitness()
    boolcheck = child1 < child2
    boolcheck2 = child1 > child2
    boolcheck3 = child1 == child2
    doneWithTests = 1

    mutation_test = NumberBins(pool, mutation_function=mutation_slice)
    print(mutation_test.get_bins())
    mutation_test.mutate()
    print(mutation_test.get_bins())