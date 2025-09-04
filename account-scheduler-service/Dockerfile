FROM eclipse-temurin:19-jre-focal
MAINTAINER Mk_jd2_111_25

ADD ./target/account-scheduler-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/account-scheduler-service.jar"]

EXPOSE 8080 8089