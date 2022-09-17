import math

from numpy import float64

import geneticalgorithm
import numberbins
import tower
from InputReader import InputReader
from numberbins import *
from geneticalgorithm import GeneticAlgorithm
from tqdm import tqdm
import matplotlib.pyplot as plt
import numpy as np
import random
from tower import *

'''
Want a script that can easily compare our different settings 
(like crossover, selection, and mutation functions) of our 
genetic algorithm and visual the data to easily make decisions
Should we check with the same initial population? (don't want to use just one [1])
Check all crossover functions with the same IP, and use numerous IPs?
There are population level concerns (crossover, mutation, fitness etc)
*** Big issue is that how populations are made is problem specific ***
And there are algorithm level concerns (elitism, culling, selection, mutation chance, etc)
How to best handle these?
-> Have functions that generate a bunch of initial populations with different settings,
(can run this multiple times with the inputs to get numerous initial populations to satisfy [1])
and have functions that run a given initial population with different algorithm settings?
Given summary statistics for how a set of settings did, and give graphs for each trial
'''

'''def run_experiment(
        initial_population,
        run_time,
        fitness_functions=[],
        crossover_functions=[],
        mutation_functions=[],
        elitism_factors=[],
        culling_factors=[],
        selection_functions=[],
        should_mutate_functions=[]
        ):'''  # this is the end goal ?

'''
Initial attempts, making this extensible will take more thought than I realized
Since crossover seems to be an important parameter, I will likely start by making a system to test just that 
and then build from there
# Assume we have a way to generate an initial population given a pool, size, and crossover functions
def initial_population_with_crossover(pop_size, pool, crossover):
    return []
# Write just this function to test different crossovers to get a start somewhere
# Definitely a better way to handle defaults (pass None to just use default to GA and others?)
def test_crossovers(pop_size, pool, crossover_functions, elitism_factor=0, culling_factor=0):
    for crossover in crossover_functions:
        pop = initial_population_with_crossover(pop_size, pool, crossover)
        ga = GeneticAlgorithm(pop_size=pop_size, initial_population=pop,
                              elitism_factor=elitism_factor, culling_factor=culling_factor)
    pass
'''

'''
a DATASET is a tuple with 3 elements
The 0th element is a list of the best organism per for each generation
The 1st element is a list of the worst organism per for each generation
The 2nd element is a list of the median organism per for each generation
'''

BEST = 0
WORST = 1
MEDIAN = 2


def fitnesses(organisms):
    return list(map(lambda org: org.fitness, organisms))


def crossover_trial_data(pool, pop_size, trials, run_time, functions, ef=2, cf=0.2):
    data = np.zeros(shape=(len(functions), trials), dtype=list)
    crossover_num = 0
    for crossover in functions:
        for i in range(trials):
            pop = []
            for j in range(pop_size):
                pop.append(NumberBins(pool, crossover_function=crossover))

            ga = GeneticAlgorithm(pop_size=pop_size, initial_population=pop, elitism_factor=ef, culling_factor=cf)
            ga.run(run_time)
            data[crossover_num, i] = (ga.best_organisms_per_generation(),
                                      ga.worst_organisms_per_generation(),
                                      ga.middle_organisms_per_generation())
        crossover_num += 1

    return data


def tower_trial_data(pool, pop_size, trials, run_time, functions, ef=2, cf=0.2):
    data = np.zeros(shape=(len(functions), trials), dtype=list)
    crossover_num = 0
    for crossover in functions:
        for i in range(trials):
            pop = []
            for j in range(pop_size):
                pop.append(Tower(pool, crossover_function=crossover))

            ga = GeneticAlgorithm(pop_size=pop_size, initial_population=pop, elitism_factor=ef, culling_factor=cf,
                                  selection_function=geneticalgorithm.power_fitness_selection, should_mutate_function=geneticalgorithm.always)
            ga.run(run_time)
            data[crossover_num, i] = (ga.best_organisms_per_generation(),
                                      ga.worst_organisms_per_generation(),
                                      ga.middle_organisms_per_generation())
        crossover_num += 1

    return data


# data is a 2D array that is len(chart_names) by num_trials large, with each entry a DATASET
def plot_trial_data(data, num_trials, chart_names):
    fig = plt.figure()
    gs = fig.add_gridspec(1, len(chart_names), wspace=0)
    axs = gs.subplots(sharex=True, sharey=True)

    if len(chart_names) == 1:
        for j in range(num_trials):
            axs.plot(fitnesses(data[0, j][WORST]), color="red")
            axs.plot(fitnesses(data[0, j][MEDIAN]), color="orange")
            axs.plot(fitnesses(data[0, j][BEST]), color="green")

        axs.set(xlabel="Generations",
                ylabel="Fitness",
                title=chart_names[0])
    else:
        for i in range(len(chart_names)):
            for j in range(num_trials):
                axs[i].plot(fitnesses(data[i, j][WORST]), color="red")
                axs[i].plot(fitnesses(data[i, j][MEDIAN]), color="orange")
                axs[i].plot(fitnesses(data[i, j][BEST]), color="green")

        count = 0
        for ax in axs.flat:
            ax.set(xlabel="Generations",
                   ylabel="Fitness",
                   title=chart_names[int(count)])
            count += 1

        for ax in axs.flat:
            ax.label_outer()

    plt.show()

# data is a 2D array that is len(chart_names) by num_trials large, with each entry a DATASET
def plot_trial_data_average_trials(data, num_trials, chart_names):
    fig = plt.figure()
    gs = fig.add_gridspec(1, len(chart_names), wspace=0)
    axs = gs.subplots(sharex=True, sharey=True)

    if len(chart_names) == 1:
        min_len = math.inf
        for j in range(num_trials):
            min_len = min(min_len, len(fitnesses(data[0, j][BEST])))

        best_avg = np.zeros(min_len, float64)
        worst_avg = np.zeros(min_len, float64)
        median_avg = np.zeros(min_len, float64)
        for j in range(num_trials):
            worst_avg = worst_avg + np.array(fitnesses(data[0, j][WORST])[0:min_len])
            median_avg = median_avg + np.array(fitnesses(data[0, j][MEDIAN])[0:min_len])
            best_avg = best_avg + np.array(fitnesses(data[0, j][BEST])[0:min_len])

        worst_avg /= num_trials
        median_avg /= num_trials
        best_avg /= num_trials

        axs.plot(worst_avg, color="red")
        axs.plot(median_avg, color="orange")
        axs.plot(best_avg, color="green")

        axs.set(xlabel="Generations",
                ylabel="Fitness",
                title=chart_names[0])
    else:
        for i in range(len(chart_names)):
            min_len = math.inf
            for j in range(num_trials):
                min_len = min(min_len, len(fitnesses(data[0, j][BEST])))

            best_avg = np.zeros(min_len, float64)
            worst_avg = np.zeros(min_len, float64)
            median_avg = np.zeros(min_len, float64)
            for j in range(num_trials):
                worst_avg = worst_avg + np.array(fitnesses(data[i, j][WORST])[0:min_len])
                median_avg = median_avg + np.array(fitnesses(data[i, j][MEDIAN])[0:min_len])
                best_avg = best_avg + np.array(fitnesses(data[i, j][BEST])[0:min_len])

            worst_avg /= num_trials
            median_avg /= num_trials
            best_avg /= num_trials

            axs[i].plot(worst_avg, color="red")
            axs[i].plot(median_avg, color="orange")
            axs[i].plot(best_avg, color="green")

        count = 0
        for ax in axs.flat:
            ax.set(xlabel="Generations",
                   ylabel="Fitness",
                   title=chart_names[int(count)])
            count += 1

        for ax in axs.flat:
            ax.label_outer()

    plt.show()


def tower_test(pool, pop_size, trials, run_time, functions, names, ef=2, cf=0.2):
    data = tower_trial_data(pool, pop_size, trials, run_time, functions, ef, cf)
    plot_trial_data_average_trials(data, trials, names)

def crossover_test(pool, pop_size, trials, run_time, functions, names, ef=2, cf=0.2):
    data = crossover_trial_data(pool, pop_size, trials, run_time, functions, ef, cf)
    plot_trial_data_average_trials(data, trials, names)


# This code will be VERY SIMILAR to what we need for the analysis

if __name__ == "__main__":
    #pool = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5,
    #        6, 7, 8, 9, 10]
    # inputreader = InputReader()
    # pool = inputreader.read_number_input("problemoneexample.txt")
    pool = tower_pool_from_file('tower1.txt')
    # crossover_functions = [numberbins.crossover_sectional, numberbins.crossover_bin_for_bin]
    crossover_functions = [tower.half_crossover]
    crossover_names=['Baseline - ef=0.16, cf=0.55']
    # crossover_names = ["Sectional", "b4b"]
    trials = 5
    k = 300
    ef = 0.16
    cf = 0.55
    run_time = 15
    #
    # crossover_test(pool, k, trials, run_time, crossover_functions, crossover_names, ef=ef, cf=cf)
    tower_test(pool, k, trials, run_time, crossover_functions, crossover_names, ef=ef, cf=cf)
