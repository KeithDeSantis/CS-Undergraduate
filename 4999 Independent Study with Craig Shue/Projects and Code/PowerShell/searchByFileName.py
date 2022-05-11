#!/Library/Frameworks/Python.framework/Versions/3.9/bin/python3

import os
import sys

class bcolors: # Used to color text
        HEADER = '\033[95m'
        OKBLUE = '\033[94m'
        OKCYAN = '\033[96m'
        OKGREEN = '\033[92m'
        WARNING = '\033[93m'
        FAIL = '\033[91m'
        MAGENTA = '\u001b[35m'
        BRIGHTGREEN = '\u001b[32;1m'
        YELLOW = '\u001b[33m'
        BRIGHTYELLOW = '\u001b[33;1m'
        ENDC = '\033[0m'
        BOLD = '\033[1m'
        UNDERLINE = '\033[4m'

class search_agent():

    def __init__(self):
        self.case_sensitive = False
        self.file_type = ""

    def find_substring_files(self, substring, source_folder): 
        # the .endswith() function is used to determine file extension, so "" is set to default as all names will end with ""

        # Save current working directory
        original_directory = os.getcwd()
        if source_folder == ".":
            source_folder = str(os.getcwd())

        # Recursive search
        self.recursiveFindFiles(source_folder, substring, 0)

        # Return to original directory
        os.chdir(original_directory)
        exit(0)


    # Function that takes in a file and prints out the file name and absolute path if
    # it contains the desired substring along with applied filters
    def containsSymbol(self, file, substring):
        
        search_term = substring
        name = file.name
        extension = self.file_type

        if not self.case_sensitive:
            name = file.name.upper()
            search_term = substring.upper()
            extension = self.file_type.upper()

        if search_term in name and name.endswith(extension):
            pass
            print(f"FOUND: {bcolors.WARNING}{bcolors.BOLD}{file.name}{bcolors.ENDC}\nAt: {bcolors.MAGENTA}{os.path.abspath(file)}{bcolors.ENDC}\n")

    # Recursively searches for files that contain the substring in their name
    def recursiveFindFiles(self, source_dir, substring, recursive_depth):

        dir_entries = os.scandir(source_dir)

        for entry in dir_entries:

            try: 
                if entry.is_dir():
                    os.chdir(entry.path) # Set the working directory

                    if(entry.name == "Steam" or entry.name == "Containers"): # a folder in steam is being weird so for now skip it Library/Containers hold weird duplicates of files so ignore it
                        os.chdir("..")
                        return

                    self.recursiveFindFiles(entry, substring, recursive_depth+1)

                    os.chdir("..") # Step back out

                else:

                    self.containsSymbol(entry, substring)

            except Exception as e:
                pass

    def print_help(self):

        print(f"\n{bcolors.FAIL}{bcolors.UNDERLINE}{bcolors.BOLD}findByName{bcolors.ENDC}\n")
        print(f"Syntax and arguments are as follows:\n\n{bcolors.FAIL}findByName{bcolors.ENDC} {bcolors.OKCYAN}<keyword>{bcolors.ENDC} {bcolors.MAGENTA}<source-directory>{bcolors.ENDC} case={bcolors.OKBLUE}<case-sensitivity>{bcolors.ENDC} type={bcolors.OKGREEN}<extension-type>{bcolors.ENDC}\n")
        print("Such that:\n")
        print(f"{bcolors.OKCYAN}<keyword>{bcolors.ENDC} is a keyword in a file name to be searched for.\n")
        print(f"{bcolors.MAGENTA}<source-directory>{bcolors.ENDC} is the directory from which to begin the recursive search.\n")
        print(f"{bcolors.OKBLUE}<case-sensitive>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument which will make the search case sensitive if set to \"true\" or \"t\".\n")
        print(f"{bcolors.OKGREEN}<extension-type>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument which will search only for files with the given file extension (i.e. .txt; .java; .py).\n")
        print("Example usage:\n")
        print(f"{bcolors.WARNING}findByName and ~/Desktop type=.txt{bcolors.ENDC}\n")
        print(f"This would search \"~/Desktop\" for files containing the substring \"and\" ({bcolors.UNDERLINE}not case-sensitive{bcolors.ENDC}) that have a \".txt\" file extension.\n")

    def resolve_conditional_args(self, args):

        for arg in args[3:]:

            try:
                arg_set = arg.split("=")
                arg_type = arg_set[0].lower()
                arg_val = arg_set[1]

                if arg_type == "case":
                
                    if arg_val.lower() == "true" or arg_val.lower() == "t":
                        self.case_sensitive = True

                elif arg_type == "type":

                    self.file_type = arg_val
                
                else:
                    raise Exception("Undefined argument")
            except:
                print(f"{bcolors.FAIL}Invalid syntax.{bcolors.ENDC} Try running \"findByName\" with no arguments for help.")
                exit(1)

if __name__ == "__main__":

    agent = search_agent()

    num_args = len(sys.argv)

    if num_args == 1:
       agent.print_help()
       exit(0)

    agent.resolve_conditional_args(sys.argv)
    
    agent.find_substring_files(sys.argv[1], sys.argv[2])