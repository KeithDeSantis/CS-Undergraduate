cmake_minimum_required(VERSION 3.15)
project(2020HW2starter C)

set(CMAKE_C_STANDARD 11)

include_directories(src)

add_executable(2020HW2starter
        src/2020HW2starter.c
        src/LinkedList.c
        src/LinkedList.h
        src/marker.c
        src/marker.h
        src/production.c
        src/production.h
        src/space.c
        src/space.h
        src/tests.c
        src/tests.h
        src/TMSName.h src/brain.h src/brain.c)
