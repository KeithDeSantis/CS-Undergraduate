import sys

from main import main


def q_learn():
    """
    Main function for q-learning.
    reads command line arguments and passes them to main.py
    :return:
    """
    if len(sys.argv) < 5:
        sys.exit("Invalid number of arguments\n"
                 "\t-Usage: python3 qlearn.py <filename> <time_to_run> <probability> <constant_reward>\n"
                 "\t-Example: python3 qlearn.py board.txt 1.3 0.9 -0.05")

    filename = sys.argv[1]
    time = float(sys.argv[2])
    prob_moving = float(sys.argv[3])
    constant_reward = float(sys.argv[4])
    main(filename, time, prob_moving, constant_reward)


if __name__ == "__main__":
    q_learn()
