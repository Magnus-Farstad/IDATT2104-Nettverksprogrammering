#syntax=docker/dockerfile:1
FROM gcc:latest
WORKDIR /compile
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY compile/main.cpp /compile/
RUN g++ -o main main.cpp
CMD ["./main"]