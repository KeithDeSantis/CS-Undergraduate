import sys
import numberbins
from numberbins import *
from InputReader import *
import tower
from tower import *
import geneticalgorithm
from geneticalgorithm import *

if __name__ == "__main__":

    if len(sys.argv) != 4:
        raise Exception("Wrong number of argument. Please input <puzzle # to solve> <input file> <seconds to run>")

    input_reader = InputReader()

    puzzle = int(sys.argv[1])
    input_file = sys.argv[2]
    run_time = float(sys.argv[3])

    pop = []
    pool = []

    #------NumberBins Parameters-------#
    numbin_crossover_function = numberbins.crossover_sectional # the function used for crossover for puzzle 1
    numbin_mutation_function = numberbins.one_number_swap_mutation # the function used for mutation for puzzle 1
    numbin_fitness_function = numberbins.score_based_fitness # the function used to determine fitness for puzzle 1
    numbin_selection_function = geneticalgorithm.basic_fitness_selection # GA's selection function for puzzle 1
    numbin_should_mutate_function = geneticalgorithm.good_chance # GA's should_mutate function for puzzle 1
    numbin_number_crossovers = 4

    # ------Tower Parameters-------#
    tower_crossover_function = tower.half_crossover # the function used for crossover for puzzle 2
    tower_mutation_function = tower.random_mutation # the function used for mutation for puzzle 2
    tower_fitness_function = tower.fitness # the function used to determine fitness for puzzle 2
    tower_selection_function = geneticalgorithm.power_fitness_selection # GA's selection function for puzzle 2
    tower_should_mutate_function = geneticalgorithm.always # GA's should_mutate function for puzzle 2

    if puzzle == 1:

        pop_size = 100
        ga_elitism_factor = 2
        ga_culling_factor = 0.2

        pool = input_reader.read_number_input(input_file)

        for org in range(pop_size):
            pop.append(NumberBins(pool,
                 crossover_function=numbin_crossover_function, # the function used for crossover
                 mutation_function=numbin_mutation_function, # the function used for mutation
                 fitness_function=numbin_fitness_function, # the function used to determine fitness
                 number_crossovers=numbin_number_crossovers # the number of crossovers done per child production
                 ))

        ga = GeneticAlgorithm(len(pop),
                              pop,
                              selection_function=numbin_selection_function,
                              should_mutate_function=numbin_should_mutate_function,
                              elitism_factor=ga_elitism_factor,
                              culling_factor=ga_culling_factor)

    elif puzzle == 2:

        pop_size = 300
        ga_elitism_factor = 0.16
        ga_culling_factor = 0.55

        pool = tower.tower_pool_from_file(input_file)

        for org in range(pop_size):
            pop.append(Tower(pool,
                crossover_function=tower_crossover_function,  # the function used for crossover
                mutation_function=tower_mutation_function,  # the function used for mutation
                fitness_function=tower_fitness_function  # the function used to determine fitness
                ))

        ga = GeneticAlgorithm(len(pop),
                              pop,
                              selection_function=tower_selection_function,
                              should_mutate_function=tower_should_mutate_function,
                              elitism_factor= ga_elitism_factor,
                              culling_factor= ga_culling_factor)

    top_organism = ga.run(run_time)

    print("Top Organism: \n" + str(top_organism))
    print("Score: " + str(top_organism.fitness))
    print("Number of generations run: " + str(ga.current_generation + 1))
    print("Best organism found at generation " + str(ga.best_org_generation + 1))