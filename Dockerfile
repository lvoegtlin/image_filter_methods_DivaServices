FROM java:8

LABEL maintainer="lars.voegtlin@unifr.ch"

COPY dist/Filters.jar /input/Filters.jar
COPY dist/script.sh /input/script.sh