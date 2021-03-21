cmake_minimum_required(VERSION 3.15)
project(2020HW3starter C)

set(CMAKE_C_STANDARD 11)

include_directories(src)

add_executable(2020HW3starter
        src/2020HW3starter.c
        src/AdjMat.c
        src/AdjMat.h
        src/LinkedList.c
        src/LinkedList.h
        src/production.c
        src/production.h
        src/Room.h
        src/tests.c
        src/tests.h
        src/KUSHSHAH.h)
