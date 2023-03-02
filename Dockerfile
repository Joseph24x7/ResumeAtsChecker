FROM openjdk:17-oracle
EXPOSE 8001
ADD target/resumeatschecker.jar resumeatschecker.jar
ENTRYPOINT ["java","-jar","/resumeatschecker.jar"]