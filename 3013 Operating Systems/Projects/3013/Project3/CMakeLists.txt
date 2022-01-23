cmake_minimum_required(VERSION 3.17)
project(Project_3 C)

set(CMAKE_C_STANDARD 99)

add_executable(Project_3 goatmalloc.c test_goatmalloc.c)