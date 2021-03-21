cmake_minimum_required(VERSION 3.17)
project(Project2 C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -pthread")
add_executable(Project2 stage.h stage.c fedoops.c fedoops.h)