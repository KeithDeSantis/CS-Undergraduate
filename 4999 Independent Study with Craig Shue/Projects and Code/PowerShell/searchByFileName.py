#!/Library/Frameworks/Python.framework/Versions/3.9/bin/python3

import os
import sys
import time

# Used to color text
class bcolors:
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

# The agent searching the file system
class search_agent():

    def __init__(self):

        # Dictionary of flags and their associated values (set to default values)
        self.flags = {
            "source_dir": os.getcwd(),
            "case_sensitive": False,
            "file_type": "",
            "verbose": False,
            "timeout": -1
        }

        # List of functions related to flags to run on each filename
        # Each takes in the file and the substring and return true or false based on their filter
        # These will all be run on each file to determine if they pass all set flags
        self.filter_functions = [self.case_filter, self.file_type_filter]

        # Statistics used for verbose mode
        self.max_recursive_depth = 1
        self.number_dirs_searched = 0
        self.start_time = None

        # Original Directory
        self.original_directory = os.getcwd()

    """
    Returns whether the file passes the case-sensitive filter. 
    I.e. If the substring is in the name (exactly if case-sensitive, or at all if not case-sensitive)
    """
    def case_filter(self, file, substring):
        search_term = substring
        name = file.name
        if not self.flags["case_sensitive"]:
            name = file.name.upper()
            search_term = substring.upper()
        return search_term in name

    """
    Returns whether the file passes the file_type filter.
    I.e. has the desired file extension.
    """
    def file_type_filter(self, file, substring):
        extension = self.flags["file_type"]
        name = file.name
        return name.endswith(extension)

    """Setup and running of recursive search"""
    def find_substring_files(self, substring):
        
        source_folder = self.flags["source_dir"]

        if source_folder == ".":
            source_folder = str(os.getcwd())

        # Recursive search
        self.start_time = time.time()
        self.recursiveFindFiles(source_folder, substring, 0)
        elapsed = time.time() - self.start_time

        self.cleanup()

    """
    Takes in a file and prints out the file name and absolute path if
    it contains the desired substring along with applied filters
    File - the file being analyzed
    Substring - the substring being searched for
    """
    def containsSymbol(self, file, substring):
        
        pass_filters = True

        # Check that filename passes all filters
        for func in self.filter_functions:
            pass_filters = pass_filters and func(file, substring)
        if pass_filters:
            print(f"Found: {bcolors.WARNING}{bcolors.BOLD}{file.name}{bcolors.ENDC}\nAt: {bcolors.MAGENTA}{os.path.abspath(file)}{bcolors.ENDC}\n")

    """Recursively searches for files that contain the substring in their name"""
    def recursiveFindFiles(self, source_dir, substring, recursive_depth):

        # Book keeping for verbose mode
        self.update_recursive_depth(recursive_depth)
        self.number_dirs_searched = self.number_dirs_searched + 1

        # Checking for timeout mode
        if self.flags["timeout"] != -1 and ((time.time() - self.start_time) > self.flags["timeout"]):
            self.cleanup()
                

        dir_entries = os.scandir(source_dir)

        for entry in dir_entries:

            try: 
                if entry.is_dir():
                    os.chdir(entry.path) # Set the working directory

                    if(entry.name == "Steam" or entry.name == "Containers"): # a folder in steam is being weird so for now skip it Library/Containers hold weird duplicates of files so ignore it
                        os.chdir("..")
                        return
                    self.recursiveFindFiles(entry, substring, int(recursive_depth)+1)

                    os.chdir("..") # Step back out

                else:

                    self.containsSymbol(entry, substring)

            except Exception as e:
                pass

    """
    Run before search to determine which flags are present and set values appropriately
    Flags should be passed in the form <flag_name>=<flag_value>
    """
    def resolve_flags(self, args):

        # Check for args
        if len(args) < 2:
            agent.invalid_syntax()
        
        # Check for help flag
        if args[1].lower() == "help" or args[1].lower() == "h":
            agent.print_help()
            exit(0)

        # For each argument past the 1 required argument
        for arg in args[2:]:
            
            try:
                arg_set = arg.split("=")
                arg_type = arg_set[0].lower()
                arg_val = arg_set[1]
                
                # Source_Directory Flag
                if arg_type == "dir":
                    self.flags["source_dir"] = arg_val
                # Case_Sensitive Flag
                elif arg_type == "case":
                    if arg_val.lower() == "true" or arg_val.lower() == "t":
                        self.flags["case_sensitive"] = True
                # File_Type Flag
                elif arg_type == "type":
                    self.flags["file_type"] = arg_val
                # Verbosity Flag
                elif arg_type == "verbose":
                    if arg_val.lower() == "true" or arg_val.lower() == "t":
                        self.flags["verbose"] = True
                # Timeout Flag
                elif arg_type == "timeout":
                    self.flags["timeout"] = float(arg_val)
                
                else:
                    raise Exception("Undefined argument")
            except:
                self.invalid_syntax()
    
    """Print out for when syntax of command was found to be invalid"""
    def invalid_syntax(self):
        print(f"{bcolors.FAIL}Invalid syntax.{bcolors.ENDC} Try running \"findByName h\" or \"findByName help\" help menu.")
        exit(1)

    """Print a help menu for syntax"""
    def print_help(self):

        print(f"\n{bcolors.FAIL}{bcolors.UNDERLINE}{bcolors.BOLD}findByName{bcolors.ENDC}\n\n" +
            f"Syntax and arguments are as follows:\n\n{bcolors.FAIL}findByName{bcolors.ENDC} {bcolors.OKCYAN}<keyword>{bcolors.ENDC} dir={bcolors.OKGREEN}<source-directory>{bcolors.ENDC} case={bcolors.OKGREEN}<case-sensitivity>{bcolors.ENDC} type={bcolors.OKGREEN}<extension-type>{bcolors.ENDC} verbose={bcolors.OKGREEN}<verbosity>{bcolors.ENDC} timeout={bcolors.OKGREEN}<timeout>{bcolors.ENDC}\n\n\n" + 
            f"{bcolors.MAGENTA}{bcolors.UNDERLINE}Required Arguments:{bcolors.ENDC}\n\n" +
            f"{bcolors.OKCYAN}<keyword>{bcolors.ENDC} is a keyword in a file name to be searched for.\n\n\n" +
            f"{bcolors.MAGENTA}{bcolors.UNDERLINE}Optional Arguments:{bcolors.ENDC}\n\n" +
            f"{bcolors.OKGREEN}<source-directory>{bcolors.ENDC} is is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument that specifies the {bcolors.UNDERLINE}absolute path name{bcolors.ENDC} from which to begin the recursive search.\n\n" +
            "\tDefault value: Current Working Directory\n\n" +
            f"{bcolors.OKGREEN}<case-sensitivity>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument which will make the search case sensitive if set to \"true\" or \"t\".\n\n" +
            "\tDefault value: False\n\n" +
            f"{bcolors.OKGREEN}<extension-type>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument which will search only for files with the given file extension (i.e. .txt; .java; .py).\n\n" +
            "\tDefault value: None\n" +
            f"{bcolors.OKGREEN}<verbosity>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument that prints out statistics about the search once it has concluded when set to \"true\" or \"t\".\n\n" +
            "\tDefault value: False\n\n\n" +
            f"{bcolors.OKGREEN}<timeout>{bcolors.ENDC} is an {bcolors.UNDERLINE}optional{bcolors.ENDC} argument that can be used to set a number of seconds for the search to run before timing out.\n\n" +
            "\tDefault value: None\n\n\n" +
            "Example usage:\n\n" +
            f"{bcolors.WARNING}findByName and dir=~/Desktop type=.txt{bcolors.ENDC}\n\n" + 
            f"This would search \"~/Desktop\" for files containing the substring \"and\" ({bcolors.UNDERLINE}not case-sensitive{bcolors.ENDC}) that have a \".txt\" file extension.\n")

    """Tracks maximum recursive depth for verbose mode"""
    def update_recursive_depth(self, depth):
        if depth > self.max_recursive_depth:
            self.max_recursive_depth = depth

    """Function to call at end of program that handles exiting cleanly"""
    def cleanup(self):
        # Return to original directory
        os.chdir(self.original_directory)

        # Verbose Report
        if self.flags["verbose"]:
            print(f"Total time taken: {bcolors.OKGREEN}" + str(time.time()-self.start_time) + f"{bcolors.ENDC}")
            print(f"Maximum recursive depth: {bcolors.OKCYAN}" + str(self.max_recursive_depth) + f"{bcolors.ENDC}")
            print(f"Number of directories searched: {bcolors.WARNING}" + str(self.number_dirs_searched) + f"{bcolors.ENDC}")

        # Exit
        exit(0)


if __name__ == "__main__":

    agent = search_agent()
    agent.resolve_flags(sys.argv)
    agent.find_substring_files(sys.argv[1])
