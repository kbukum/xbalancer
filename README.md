## Proxy Load Balancer (xbalancer)

## Motivation

Xbalancer is used to balance host by your own filter mechanism.
You can add to and remove from database and automatically it will added or removed from http proxy balancer.

#### [xbalancer-common](./xbalancer-common/README.md)
#### [xbalancer-domain](./xbalancer-domain/README.md)
#### [xbalancer-proxy](./xbalancer-proxy/README.md)
#### [xbalancer-persistence](./xbalancer-persistence/README.md)
#### [xbalancer-service](./xbalancer-service/README.md)
#### [xbalancer-security](./xbalancer-security/README.md)
#### [xbalancer-assets](./xbalancer-assets/README.md)
#### [xbalancer-web](./xbalancer-web/README.md)


#### Balancer Manuel Proxy

```
  balancer:
    host:
      name: Balancer Server
      host: 127.0.0.1
      port: 8282
    dynamic: false
    allPath: "dummy"
    proxies:
      p1p2: [Proxy1, Proxy2]
      p1p3: [Proxy1, Proxy3]
    hosts:
      - name: Proxy1
        host: 127.0.0.1
        port: 8283
      - name: Proxy2
        host: 127.0.0.1
        port: 8284
      - name: Proxy3
        host: 127.0.0.1
        port: 8285
```

        

