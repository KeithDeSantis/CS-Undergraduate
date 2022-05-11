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
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

def find_substring_files(substring, source_folder, file_type=None):

    # Get command line arguments

    file_filter = file_type is not None


    original_directory = os.getcwd()

    if source_folder == ".":
        source_folder = str(os.getcwd())

    recursiveFindFiles(source_folder, substring, 0)

    os.chdir(original_directory)


# Function that takes in a file and prints out the file name and absolute path if
# it contains the desired substring
def containsSymbol(file, substring):
    name = file.name.upper()
    if substring in name: ##TODO add checking if we need to use the file type filter
        print(f"FOUND: {bcolors.WARNING}{bcolors.BOLD}{file.name}{bcolors.ENDC}\nAt: {bcolors.MAGENTA}{os.path.abspath(file)}{bcolors.ENDC}\n")


# Recursively searches for files that contain the substring in their name
def recursiveFindFiles(source_dir, substring, recursive_depth):

    dir_entries = os.scandir(source_dir)

    for entry in dir_entries:

        try: 
            if entry.is_dir():
                os.chdir(entry.path) # Set the working directory
                if(entry.name == "Steam" or entry.name == "Containers"): # a folder in steam is being weird so for now skip it Library/Containers hold weird duplicates of files so ignore it
                    os.chdir("..")
                    return
                recursiveFindFiles(entry, substring, recursive_depth+1)
                os.chdir("..") # Step back out
            else:
                containsSymbol(entry, substring)
        except Exception as e:
            n = 0 #print(f"Encountered Issue - {bcolors.FAIL}{e}{bcolors.ENDC} while accessing {bcolors.MAGENTA}{entry.path}{bcolors.ENDC}\n")

if __name__ == "__main__":

    if len(sys.argv) > 3:
        find_substring_files(sys.argv[1].upper(), sys.argv[2], sys.argv[3])
    else:
        find_substring_files(sys.argv[1].upper(), sys.argv[2])
