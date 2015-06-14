FROM java:8-jre
MAINTAINER Douglas Chimento "dchimento@gmail.com"
ADD build/libs/society-leagues-email.jar /srv/service.jar
EXPOSE 8080
ENTRYPOINT java -Xmx128m  -jar /srv/service.jar  $SERVER_ARGS
