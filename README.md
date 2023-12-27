# quarkus_rest_client_security_layer


```shell script
./mvnw compile quarkus:dev
```
Two different scenarios:

### Scenario 1

With `SecurityIdentityAugmentorImpl`'s `testCallInAugmentor` set to false:
```shell script
curl http://localhost:8080/hello2
```
#### Console Result

```
2023-12-27 17:09:08,278 INFO  [com.exa.ClientDelegate] (vert.x-eventloop-thread-0) Call to no-auth...
2023-12-27 17:09:08,296 INFO  [com.exa.ClientHeadersFactoryImpl] (vert.x-eventloop-thread-0) Incoming headers size: 3
2023-12-27 17:09:08,344 DEBUG [org.jbo.res.rea.cli.log.DefaultClientLogger] (vert.x-eventloop-thread-0) Request: GET http://localhost:8080/ping/no-auth 	Headers[Accept=text/plain;charset=UTF-8 User-Agent=Resteasy Reactive Client], Empty body
2023-12-27 17:09:08,360 DEBUG [org.jbo.res.rea.cli.log.DefaultClientLogger] (vert.x-eventloop-thread-0) Response: GET http://localhost:8080/ping/no-auth, Status[200 OK], 	Headers[content-length=4 Content-Type=text/plain;charset=UTF-8], Body: pong
2023-12-27 17:09:08,364 INFO  [com.exa.ClientDelegate] (vert.x-eventloop-thread-0) Response from no-auth endpoint: "pong"
2023-12-27 17:09:08,365 INFO  [com.exa.ClientDelegate] (vert.x-eventloop-thread-0) Call to authorized...
```

### Scenario 2
With the variable set to true:
```shell script
curl http://localhost:8080/hello1
```

#### Console Result
```
2023-12-27 17:11:21,172 INFO  [com.exa.ClientDelegate] (vert.x-eventloop-thread-1) Call to no-auth...
2023-12-27 17:11:21,172 INFO  [com.exa.ClientHeadersFactoryImpl] (vert.x-eventloop-thread-1) Incoming headers size: 0
2023-12-27 17:11:21,175 DEBUG [org.jbo.res.rea.cli.log.DefaultClientLogger] (vert.x-eventloop-thread-1) Request: GET http://localhost:8080/ping/no-auth 	Headers[Accept=text/plain;charset=UTF-8 User-Agent=Resteasy Reactive Client], Empty body
```

### Conclusion

When the rest client is used within the security layer,
maybe because there is no RESTEasy reactive request context active,
the request times-out and the header aren't propagated in
`ClientHeadersFactoryImpl`