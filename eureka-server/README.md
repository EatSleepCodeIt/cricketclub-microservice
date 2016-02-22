# Eureka Server

Eureka is a REST (Representational State Transfer) based service that is primarily used for locating services for the purpose of load balancing and failover of middle-tier servers.

Run this project as a Spring Boot app. It will start up on port 8761 and serve the Eureka API from "/eureka".

## Resources

| Path             | Description  |
|------------------|--------------|
| /                        | Home page (HTML UI) listing service registrations          |
| /eureka/apps         | Raw registration metadata |