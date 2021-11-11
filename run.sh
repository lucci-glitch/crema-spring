#!/bin/zsh

mvn install -DskipTests
docker build -t crema-spring .
docker-compose up -d