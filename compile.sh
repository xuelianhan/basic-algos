#!/bin/bash
PROJECT_DIR=`pwd`;
MVN_SRC=${PROJECT_DIR}/src/main/java;

javac -d ${PROJECT_DIR}/classes -classpath ${PROJECT_DIR}/classes: -sourcepath ${MVN_SRC}: ${MVN_SRC}/org/ict/algorithm/sort/InsertSort.java -g -nowarn -target 1.5 -source 1.5 -encoding UTF-8
