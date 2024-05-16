# Hello HTTP/3

```
$ ./mvnw -q compile exec:java -Dexec.mainClass=com.example.Application

2024-05-16 12:56:09.034 DEBUG --- [lication.main()] reactor.netty.tcp.TcpResources           : [http] resources will use the default LoopResources: DefaultLoopResources {prefix=reactor-http, daemon=true, selectCount=12, workerCount=12}
2024-05-16 12:56:09.035 DEBUG --- [lication.main()] reactor.netty.tcp.TcpResources           : [http] resources will use the default ConnectionProvider: reactor.netty.resources.DefaultPooledConnectionProvider@7cca24c4
2024-05-16 12:56:09.036 DEBUG --- [lication.main()] r.netty.resources.DefaultLoopIOUring     : Default io_uring support : false
2024-05-16 12:56:09.039 DEBUG --- [lication.main()] r.netty.resources.DefaultLoopEpoll       : Default Epoll support : false
2024-05-16 12:56:09.040 DEBUG --- [lication.main()] r.netty.resources.DefaultLoopKQueue      : Default KQueue support : false
2024-05-16 12:56:09.096 DEBUG --- [ctor-http-nio-1] r.netty.transport.ServerTransport        : [e56a5924, L:/[0:0:0:0:0:0:0:0]:8443] Bound new server
```

```
$ curl -kv --http3 https://localhost:8443

* Host localhost:8443 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8443...
* Server certificate:
*  subject: CN=localhost
*  start date: May 17 03:56:08 2023 GMT
*  expire date: Dec 31 23:59:59 9999 GMT
*  issuer: CN=localhost
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
* Connected to localhost (::1) port 8443
* using HTTP/3
* [HTTP/3] [0] OPENED stream for https://localhost:8443/
* [HTTP/3] [0] [:method: GET]
* [HTTP/3] [0] [:scheme: https]
* [HTTP/3] [0] [:authority: localhost:8443]
* [HTTP/3] [0] [:path: /]
* [HTTP/3] [0] [user-agent: curl/8.8.0-DEV]
* [HTTP/3] [0] [accept: */*]
> GET / HTTP/3
> Host: localhost:8443
> User-Agent: curl/8.8.0-DEV
> Accept: */*
> 
* Request completely sent off
< HTTP/3 200 
< server: reactor-netty
< content-length: 13
< 
* Connection #0 to host localhost left intact
Hello HTTP/3!
```