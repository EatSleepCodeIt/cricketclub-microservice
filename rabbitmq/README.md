## MongoDB Dockerfile


This repository contains **Dockerfile** of [RabbitMQ](http://www.rabbitmq.com/) for [Docker](https://www.docker.com/)'s 
It is based in the public image image at  [Docker Hub Registry](https://registry.hub.docker.com/).


### Base Docker Image

* [dockerfile/ubuntu](http://dockerfile.github.io/#/ubuntu)


### Installation

1. Install [Docker](https://www.docker.com/).

2. Download  : `docker pull docker-repo.aws.ratedcloud.net/rabbitmq`

   (alternatively, you can build an image from Dockerfile: `docker build -t="docker-repo.aws.ratedcloud.net/rabbitmq" .`)



### Usage

#### Run RabbitMQ server 


    docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq ipdocker-repo.aws.ratedcloud.net/rabbitmq

#### Run server w/ persistent/shared directory

    docker run -d -p 5672:5672 -v <data-dir>:/data/mnesia -v <data-log>:/data/log --name rabbitmq docker-repo.aws.ratedcloud.net/rabbitmq

#### Run server w/ HTTP support

    docker run -d -p 5672:5672  -p 15672:15672 -v <data-dir>:/data/mnesia -v <data-log>:/data/log --name rabbitmq  docker-repo.aws.ratedcloud.net/rabbitmq
    
    Navigate to http://localhost:15672/ and login with username admin and password Rated1234

#### Checking out the logs of a rabbitmq container
    docker logs -f rabbitmq
   
