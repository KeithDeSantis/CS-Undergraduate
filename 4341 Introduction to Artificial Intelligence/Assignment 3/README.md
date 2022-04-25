# AI-Assignment-3

This is the submission for Project 3: Machine Learning for CS 4341: Artificial Intelligence in C22 at WPI. \
Our implementation is written in python and was developed using the 3.9 interpreter.

All of the members of our group originally wrote our Assignment 1 in Java, however we prefered to use Python for this assignment, so we got permission from Kush Shah (kshah2@wpi.edu) to use his code from assignment 1. 

Run ```astar.py <board-file-path> <heuristic-number>``` to run the program on the given board with the given heuristic.
Our machine-learned heuristic is heuristic number 7.


This submission contains the following files:

The A* Base Code from Assignment 1, (with small adjustments for data collection):
* ```agent.py```
* ```astar.py```
* ```board.py```
* ```main.py```
* ```node.py```
  
A few new python files used for Assignment 3 data collection:
* ```boardgenerator.py```
* ```dataentry.py```
* ```featurefunctions.py```

Book-keeping and record files:
* ```README.md``` (this file)
* ```Assignment3WriteUp.pdf``` (our writeup)
* ```weka_output.txt``` (the output of our learner)
* ```output.csv``` (our training data collected from running H5 on 6x6 boards)

Sample Boards:
* ```board-6x6.txt```
* ```board-10x10.txt```
* ```GoodExampleOfNonAdmissableBoard.txt``` (a board that showcases how H7 is inadmissible but still better than H6 in some ways)

If you have any trouble running our code, feel free to contact us at jzhao5@wpi.edu, abmoore@wpi.edu, and kwdesantis@wpi.edu.