## Proxy Load Balancer (xbalancer)

## Motivation

Xbalancer is used to balance host by your own filter mechanism.
You can add to and remove from database and automatically it will added or removed from http proxy balancer.

#### [xbalancer-common](./xbalancer-common/README.md)

Provides common operations for all libraries.

#### [xbalancer-domain](./xbalancer-domain/README.md)

Includes persistence entities.

#### [xbalancer-proxy](./xbalancer-proxy/README.md)

Provides to Load Balancing Host by the given filter.

#### [xbalancer-persistence](./xbalancer-persistence/README.md)

Includes all Repositories for persistence entities.

#### [xbalancer-service](./xbalancer-service/README.md)

Provides services for persistence and proxy.

#### [xbalancer-security](./xbalancer-security/README.md)

Provides Json Token mechanism security.

#### [xbalancer-assets](./xbalancer-assets/README.md)

Provides Assets to represent

#### [xbalancer-web](./xbalancer-web/README.md)

Integrates all configuration ( Rest Servlet, Context, Datasource, Proxy Configuration, Assets Configuration ) by using Spring boot.

#### Project Detail [..more](./xbalancer-web/src/main/resources/docs/home.md)
- You can see Project Detail after install and run aplication.
- Also defined Project Detail in xbalancer/xbalancer-web/src/main/resources/docs folder

#### Run Project on any IDE

url: http://localhost:8181
username: admin
password: 123123

- Web Server : http://127.0.0.1:8181
- Proxy Server: http://127.0.0.1:8282
- Defined Dummy Servers :

Host1: http://127.0.0.1:8283
Host2: http://127.0.0.1:8284
Host3: http://127.0.0.1:8285


#### Proxy Server Configuration

#### Dynamic Proxy Configuration.

```yaml
  balancer:
    host:
      name: Balancer Server # proxy server name
      host: 127.0.0.1 # proxy server host
      port: 8282 # proxy
    dynamic: true  # default true. get proxies as manuel from property or dynamic from @ProxyService
```
* Example Calls

    - http://127.0.0.1:8282/Dummy
        - http://127.0.0.1:8283
        - http://127.0.0.1:8284
        
    - http://127.0.0.1:8282/Another
        - http://127.0.0.1:8284
        - http://127.0.0.1:8285
        
##### Manuel Proxy Server Configuration

* Example Calls

    - http://127.0.0.1:8282/h1h2
        - http://127.0.0.1:8283
        - http://127.0.0.1:8284
        
    - http://127.0.0.1:8282/h1h3
        - http://127.0.0.1:8283
        - http://127.0.0.1:8285
        
```yaml
  balancer:
      name: Balancer Server # proxy server name
      host: 127.0.0.1 # proxy server host
      port: 8282 # proxy
    dynamic: false  # get proxies as manuel from property or dynamic from @ProxyService
    all: "*"
    proxies: # proxy relations.
      h1h2: [Host1, Host2] # http://127.0.0.1:8080/h1h2 broadcasts **Host1** and **Host2**
      h1h2: [Host1, Host3] # http://127.0.0.1:8080/h1h3 broadcasts **Host1** and **Host3**
    hosts:
      - name: Host1
        host: 127.0.0.1
        port: 8283
      - name: Host2
        host: 127.0.0.1
        port: 8284
      - name: Host3
        host: 127.0.0.1
        port: 8285
```




        

