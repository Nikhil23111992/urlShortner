# urlShortner
This  service provide shortened url by passing long url as input

Application has been desgined using base62 conversion of unique counter maintain at application level.
I have used a sequence counter which has a range of 1 to 99999999999 . 

Assumptions made in the implementation of this applications is that authentication is not required for each request hence limiting the api exhaustion is out of scope.

I have used in memory db for the implementations which can be replaced by any RDBMS at later point.  At any point we can scale the service by creating multiple instances of the same service and manage it by a load balancer .

In memory db can be accessed using :

http://localhost:8080/h2-console/login.jsp

jdbcurl :-jdbc:h2:mem:urlShortDb

User Name:-	root

password :- root

Swagger api :
http://localhost:8080/swagger-ui.html#/

Application shortcode url is currently set to http://tinyurl/  which can be modified in application properties.


Things take care while implementation.
1. To avoid duplication of shorturl i have taken db sequence counter which will be unique and base62 conversion will the tinyurl alias .
2. Db consist of shorturl , longurl and timestamp of creation, which can be used for cleanup activites for better scaling of the application. Can be done using a cron job on db .
3. Ui can call the endpoints for creating a new shorturl or redirect shorturl after getting exact matach from the service.


Application flow diagram is added in applicationDiagram.jpeg

