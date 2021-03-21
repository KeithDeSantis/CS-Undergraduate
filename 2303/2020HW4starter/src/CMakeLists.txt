cmake_minimum_required(VERSION 3.15)
project(src)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(src
        2020HW4starter.cpp
        Production.cpp
        Production.h
        Room.cpp
        Room.h
        Tests.cpp
        Tests.h
        LinkedList.cpp
        LinkedList.h
        AdjMat.cpp
        AdjMat.h)
