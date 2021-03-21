cmake_minimum_required(VERSION 3.17)
project(Project3 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(Project3
        node0.c
        node1.c
        node2.c
        node3.c
        project3.c
        project3.h
        )
