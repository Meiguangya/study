FROM java:8

EXPOSE 8080

ARG JAR_FILE
ADD target/${JAR_FILE}  /water33.jar

ENTRYPOINT ["java","-jar","/water33.jar"]
