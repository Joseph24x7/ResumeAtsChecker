FROM openjdk:17-oracle
EXPOSE 8001
ADD target/resumeatschecker.jar resumeatschecker.jar
ADD src/main/resources/en-token.bin /app/src/main/resources/en-token.bin
ADD src/main/resources/en-pos-maxent.bin /app/src/main/resources/en-pos-maxent.bin
ADD src/main/resources/stopwords.txt /app/src/main/resources/stopwords.txt
ENTRYPOINT ["java","-jar","/resumeatschecker.jar"]