FROM openjdk:11-jre
#ARG VERSION

RUN ln -fs /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN dpkg-reconfigure -f noninteractive tzdata

RUN mkdir /app
WORKDIR /app

COPY target/cryptowallet*.jar /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]

