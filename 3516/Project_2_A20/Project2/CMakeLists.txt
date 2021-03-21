cmake_minimum_required(VERSION 3.17)
project(Project2 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(Project2
        project2.c
        project2.h
        student2.c)
