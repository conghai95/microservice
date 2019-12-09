FROM java:8
VOLUME /tmp
ADD ./auth-service/target/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","/auth-service.jar"]
EXPOSE 8080