## UserManagement Dockerfile


This repository contains **Dockerfile** of the user management of Rated Wheels for [Docker](https://www.docker.com/)



### Base Docker Image

* java:oracle-java8 


### Installation

1. Install [Docker](https://www.docker.com/).

2. Download  : `docker pull docker-repo.ratedcloud.net/usermanagement`

   (alternatively, you can build an image from Dockerfile: `docker build -t="docker-repo.ratedcloud.net/usermanagement" .`)
   
   


### Usage

#### Run the app

    docker run -d -p 8082:8082 --name usermanagement  docker-repo.ratedcloud.net/usermanagement
    
#### Run the app linked to a mongo docker instance 

e.g. for ip-172-31-34-106.eu-west-1.compute.internal/mysql 

	docker pull docker-repo.ratedcloud.net/mysqldatabase
	docker run -d  -p 3306:3306 -e  "MYSQL_ROOT_PASSWORD=123456" -e "MYSQL_ADMIN_PASSWORD=123456"  --name mysql docker-repo.ratedcloud.net/mysqldatabase 
	docker run -d -p 8082:8082 --name usermanagement --link mysql:mysql  docker-repo.ratedcloud.net/usermanagement

test using the following (suppose host is ec2-54-77-250-13.eu-west-1.compute.amazonaws.com )
http://ec2-54-171-206-22.eu-west-1.compute.amazonaws.com:8082/user-management    

  
 


