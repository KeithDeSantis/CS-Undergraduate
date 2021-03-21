cmake_minimum_required(VERSION 3.15)
project(2020HW1starter C)

set(CMAKE_C_STANDARD 11)

include_directories(src)

add_executable(2020HW1starter
        src/2020HW1starter.c
        src/HW1.c
        src/production.c
        src/production.h
        src/tests.c
        src/tests.h
        src/TMSName.h)
