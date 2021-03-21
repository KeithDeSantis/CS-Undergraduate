cmake_minimum_required(VERSION 3.15)
project(HW5)

set(CMAKE_CXX_STANDARD 14)

add_executable(HW5 main.cpp
               board.cpp
               board.h
               checker.cpp
               checker.h Production.cpp Production.h Tests.cpp Tests.h SAMPARKS.h LinkedList.cpp LinkedList.h)