This is the "Application Client" component of the Discovery Server process. Such components usually, call other application services to implement their functionalities. In other words they depend on the other application services. The Application Client component makes use of the Discovery server not to register or deregister itself with the Discovery Server but rather to locate other application services.

In order to quickly bootstrap it, go to http://start.spring.io/ and use the Spring Initializr to stub it out.
Generate it with the dependencies Eureka Discovery, Devtools and the Actuator.

A client application has two different options to get handle to the application service through the Discovery Server:

EurekaClient from Netflix
DiscoveryClient from Spring Cloud

The annotation "@EnableDiscoveryClient" in the "ClientApplication" class turns it to a Spring DiscoveryClient, not an EurekaClient.
application.properties:

#Service name
spring.application.name=service-client

#Discovery Server location
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

#Since this is a Client component, it does not need to register itself with the Discovery server.
eureka.client.register-with-eureka=false 