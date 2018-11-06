#!/bin/bash

filter='gaussian'
inputImage=${1}
outputFolder=${2}
arguments=${3}

if [ -z "$arguments" ]
then
    java -jar input/Filters.jar -f ${filter} -i ${inputImage} -o ${outputFolder}
else
    java -jar input/Filters.jar -f ${filter} -i ${inputImage} -o ${outputFolder} -a ${arguments}
fi