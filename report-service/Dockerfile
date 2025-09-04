FROM eclipse-temurin:19-jre-focal
MAINTAINER Mk_jd2_111_25

ADD ./target/report-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/report-service.jar"]

EXPOSE 8080 8089