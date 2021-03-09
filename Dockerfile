FROM amazoncorretto:11-alpine-jdk
# copy fat WAR emp-mgmt-system-0.0.1-SNAPSHOT
COPY target/emp-mgmt-system-1.0-SNAPSHOT.jar emp-mgmt-system-1.0-SNAPSHOT.jar
# runs application 
#CMD ["java", "-jar", "/emp-mgmt-system-1.0-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","/emp-mgmt-system-1.0-SNAPSHOT.jar"]