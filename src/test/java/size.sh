#!/bin/bash

#see http://stackoverflow.com/questions/52353/in-java-what-is-the-best-way-to-determine-the-size-of-an-object
java -javaagent:size.jar org.ict.nio.NullObjectTest
