FROM openjdk:17-oracle
EXPOSE 8000
ADD target/texttovideo.jar texttovideo.jar
ENTRYPOINT ["java","-jar","/texttovideo.jar"]