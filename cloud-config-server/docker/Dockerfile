FROM arana198/baseimage:latest
MAINTAINER  Ankit Rana
EXPOSE  8762
ENTRYPOINT ["/bin/sh","/entrypoint.sh"]
CMD [""]
WORKDIR /


RUN mkdir -p /opt/webapp/config

ADD entrypoint.sh /
RUN chmod a+x /entrypoint.sh

ADD application.properties /opt/webapp/config/
ADD cloud-config-server.jar /opt/webapp/
