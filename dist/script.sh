#!/bin/bash

filter=${1}
inputImage=${2}
outputFolder=${3}
arguments=${4}

if [ -z "$arguments" ]
then
    java -jar input/Filters.jar -f ${filter} -i ${inputImage} -o ${outputFolder}
else
    java -jar input/Filters.jar -f ${filter} -i ${inputImage} -o ${outputFolder} -a ${arguments}
fi