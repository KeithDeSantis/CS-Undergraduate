import sys
from main import main


# get command line arguments
def run():
    if len(sys.argv) == 1 or sys.argv[1] == '-h':
        raise Exception('Usage: python3 astar.py <input_file>[text file] <heuristic_number>[1-7]')

    if len(sys.argv) != 3:
        raise Exception("Usage: python3 astar.py <input_file.txt> <heuristic_number>")

    filename = sys.argv[1]
    heuristic = int(sys.argv[2])
    if heuristic < 1 or heuristic > 7:
        raise ValueError("heuristic must be between 1 and 7")
    main(filename, heuristic)


if __name__ == '__main__':
    run()
