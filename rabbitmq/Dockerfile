# RabbitMQ Dockerfile
FROM dockerfile/ubuntu 
MAINTAINER Ankit Rana

# Expose ports. rabbit 5672  http 15672
EXPOSE 5672
EXPOSE 15672

ENTRYPOINT ["/bin/bash","/entrypoint.sh"]
CMD [""]

# Define mount points.
VOLUME ["/data/log", "/data/mnesia"]

# Define environment variables.
ENV RABBITMQ_LOG_BASE /data/log
ENV RABBITMQ_MNESIA_BASE /data/mnesia

# Install RabbitMQ.
RUN \
  wget -qO - http://www.rabbitmq.com/rabbitmq-signing-key-public.asc | apt-key add - && \
  echo "deb http://www.rabbitmq.com/debian/ testing main" > /etc/apt/sources.list.d/rabbitmq.list && \
  apt-get update && \
  DEBIAN_FRONTEND=noninteractive apt-get install -y rabbitmq-server && \
  rm -rf /var/lib/apt/lists/* 

RUN \
  rabbitmq-plugins --offline enable rabbitmq_management  && \
  rabbitmq-plugins --offline enable rabbitmq_shovel && \
  rabbitmq-plugins --offline enable rabbitmq_shovel_management && \
  echo '[{rabbit , [{default_user,<<"admin">>}, {default_pass,<<"Rated1234">>}, {loopback_users, []}]}].' > /etc/rabbitmq/rabbitmq.config 

# Add entrypoint
ADD entrypoint.sh /
RUN chmod a+x /entrypoint.sh