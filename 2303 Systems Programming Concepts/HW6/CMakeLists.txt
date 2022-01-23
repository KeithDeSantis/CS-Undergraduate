cmake_minimum_required(VERSION 3.17)
project(HW6)

set(CMAKE_CXX_STANDARD 14)

include_directories(src)

add_executable(HW6
        src/2020HW6starter.cpp
        src/Production.cpp
        src/Production.h
        src/Tests.cpp
        src/Tests.h
        src/Stats.cpp
        src/Stats.h
        src/Coordinate.cpp
        src/Coordinate.h
        src/Cell.cpp
        src/Cell.h
        src/Watercraft.cpp
        src/Watercraft.h)
