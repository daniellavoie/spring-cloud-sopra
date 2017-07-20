FROM java:openjdk-8u77-jre-alpine

MAINTAINER Daniel Lavoie <dlavoie@live.ca>

ADD target/sopra-product.jar /sopra-product/sopra-product.jar

EXPOSE 8080

CMD ["java", "-Xmx128m", "-jar", "/sopra-product/sopra-product.jar"]
