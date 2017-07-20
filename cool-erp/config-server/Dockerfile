FROM java:openjdk-8u77-jre-alpine

MAINTAINER Daniel Lavoie <dlavoie@live.ca>

ADD target/sopra-config-server.jar /sopra-config-server/sopra-config-server.jar

EXPOSE 8888

CMD ["java", "-Xmx128m", "-jar", "/sopra-config-server/sopra-config-server.jar"]