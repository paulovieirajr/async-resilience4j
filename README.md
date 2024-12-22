#  Async Calls with Resilience4j

- Java 21
- Spring Boot 3
  
This sample is an basic example to use Async calls in microsservice but with Resilience4j. This repository contains two microsservices:

- [msrvc-movies-rating](https://github.com/paulovieirajr/async-resilience4j/tree/main/msrvc-movies-rating) (User rated movie collections)
- [msrvc-users-movies](https://github.com/paulovieirajr/async-resilience4j/tree/main/msrvc-users-movies) (User data)

Both service don't use database and their data are mocked for simplification.

![image](https://github.com/user-attachments/assets/c6a50a49-a090-4464-8910-2948fb20c669)

Basically, in ```msrvc-users-movies``` we fetch the user data in a mocked database and asyncrounously fetch their respective rated movie list in ```msrvc-movies-rating``` using OpenFeign.

Retry pattern as its name suggests, it will make a few attempts if the service is probably down, if all fail, we will return an empty list. Circuit Breaker works together with Retry monitoring the calls, I will leave an image below to make it easier to understand, but basically, there are three states:

- Closed: Allows all requests to pass through as the system is functioning normally.
- Open: Blocks all requests because the system has failed beyond a defined threshold.
- Half-Open: Allows a limited number of requests to test if the system has recovered from failures.


![image](https://github.com/user-attachments/assets/7abb1c48-dbb4-4b5f-83f5-0e6fb16c56f5)

To test it out Retry and Circuit Breaker Pattern, run only ```msrvc-users-movies``` and make a get request for:

```
localhost:8083/users-movies/search?userId=1
```
Observe the logs on the console related to Retry and Circuit Breaker. 

For Retry, at maximum 3 attempts will be made. 

Circuit Breaker was set up: 
```
slidingWindowSize: 6
minimumNumberOfCalls: 3
permittedNumberOfFailuresInHalfOpenState: 3
failureRateThreshold: 50
```
So, with 3 calls, it's possible to evaluate if their state can be changed.

Check with actuator to see the behavior of Circuit Breaker:
```
http://localhost:8083/users-movies/actuator/circuitbreakers
```

To see the correct behavior, just run up the other microservice and test it out again.

Check it out: [Resilience4j Documentation for Spring Boot](https://resilience4j.readme.io/docs/getting-started-3#aspect-order) 
