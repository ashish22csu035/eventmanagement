TEAM MEMBERS:- 1.Ashish Mehta(22csu035) and Aryan Arora(22csu031)
All services and functionality are working without errors.
work Distribution:-

Ashish Mehta:-BookingService,ConfigurationServer,ApiGateway,AuthenticationService

Aryan Arora(22csu031):-UserService,EventService,DiscoveryService 

Example calls for each end point:
eurekaserver
http://localhost:8761             -eureka server

bookingsservice
http://localhost:9004/bookings/all    -All Bookings
http://localhost:9004/bookings/1       -get booking by id 

eventService
http://localhost:9003/events/all      -All Events
http://localhost:9003/events/1          -get events by id 

userservice
http://localhost:9002/users/all      -All Users
http://localhost:9002/users/1          -get users by id 

apigateway
http://localhost:9005/bookings/all     All Bookings through apigateway
http://localhost:9005/events/all        All events through apigateway
http://localhost:9005/users/all          All users through apigateway

AuthenticationService
POST http://localhost:9006/auth/authenticate    -Authenticate Valid User with custom header
**Headers:**

Content-Type: application/json
Body (raw JSON):
json{
    "email": "ashish@test.com",
    "password": "mypassword123"
}

through api gateway:-

GET http://localhost:9005/bookings/all

Authorization Tab:
- Type: Basic Auth
- Username: ashish@test.com
- Password: mypassword123

Expected: 200 OK - List of bookings



