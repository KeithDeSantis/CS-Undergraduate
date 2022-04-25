
import numberbins

class InputReader():

    def __init__(self):
        self.inputValue = []


    ''' Reads input from a file for the first problem, Number Bins '''
    def read_number_input(self, filename):

        input_file = open(filename, "r")

        self.inputValue = input_file.read().splitlines()  # should return a list of all numbers

        self.inputValue = list(map(lambda num: float(num), self.inputValue))
        return self.inputValue

        # print(self.inputValue)

    
    ''' Reads input from a file for the second problem, Towers '''
    def read_tower_input(self, filename): # should return a list of lists, each outer list containing a segment

        input_file = open(filename, "r")

        for line in input_file.read().splitlines():
            self.inputValue.append(line.split("\t"))

        # print(self.inputValue)

        return self.inputValue






if __name__ == "__main__":
    input_reader = InputReader()
    tower = input_reader.read_tower_input("ProblemTwoTest.txt")
    #initpool = input_reader.read_Number_Input('problemoneexample.txt')
    #bins_example = numberbins.NumberBins(initpool)
    print(tower)
