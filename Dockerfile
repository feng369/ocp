FROM java:8-jdk

RUN mkdir -p /app/config

COPY ./ocp-srv/target/ocp-srv-*.jar /app/ocp.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","/app/ocp.jar"]