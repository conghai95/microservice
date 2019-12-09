FROM java:8
VOLUME /tmp
ADD ./account-service/target/account-service.jar account-service.jar
ENTRYPOINT ["java","-jar","account-service.jar"]
EXPOSE 8081