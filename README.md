# Rest Prime numbers calculator

This is an example of a resful service which purpose is to calculate prime numbers up to a limit. It has the following details: 

  - Implements two types of algorithms (serial and parallel)
  - Is based on Spring Boot
  - Has validation and exception handling
  
# Installation
In order to install and run locally just do: 

```sh
$ git clone https://github.com/zaharidichev/rest-prime-gen.git
$ cd rest-prime-gen
$ mvn spring-boot:run
```

# Usage
After starting the service you can use the service by visiting: 
http://localhost:8080/api/primenums

The endpoint for calculating prime numbers takes two mandatory arguments: 

* algoType - it can be either serial or parallel
* limit - the upper limit (inclusive) of the sequence you are searching for primes

The response which you will likely get back looks like this: 
```json
{
  "algoType": "serial",
  "numberOfPrimesFound": 4,
  "primes": [
    2,
    3,
    5,
    7
  ]
}
```

# Error handling

In case a parameter is omitted or misspelled or is incorrect in some way, you will receive feedback. 

For example, if we call: 
http://restprime.airconomist.com/api/primenums?limit=-10&algoType=serial

The result is: 

```json
{
  "errors": [
    {
      "error": "PARAM_INVALID",
      "message": "The limit parameter should be positive"
    }
  ]
}
```
In a similar way if we call 

http://restprime.airconomist.com/api/primenums?limit=-10&algoType=FOO, 

we get two errors:
```json
{
  "errors": [
    {
      "error": "PARAM_INVALID",
      "message": "The algoType parameter should be one of {serial,parallel}"
    },
    {
      "error": "PARAM_INVALID",
      "message": "The limit parameter should be positive"
    }
  ]
}
```


# Deployment
Currently there is a live instance of the service running on 
http://restprime.airconomist.com/api/primenums
This runs behind a load balancing reverse proxy as a docker container. The CircleCI config as well as the docker file are both included in the repo. CI is already setup so the process goes in the following way: 

* Push to the repo triggers a build
* The build system packages a docker image and pushes it to Docker Hub
* The image is automatically picked up by the Docker Cloud API and deployed on AWS

# Prime numbers search
There are two algorithms that are implemented. The serial algorithm is a Erathosthenos Sieve where the sieve is represented as an array of booleans. This algorithm cannot be easily parallelized without synchronisation as there is the sieve data structure.

A parallel version is implemented as well. The version uses a slightly optimised brute - force search. This allows for isolating the logic of testing whether a number is prime or not into a side effects free predicate. From then on turning that into a parallel computation is trivial via parallel streams.
