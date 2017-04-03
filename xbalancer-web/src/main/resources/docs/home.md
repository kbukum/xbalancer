## XBalancer

- This is rest home path. All information About methods you can find here.
- All ports defined in **application.yml**. You can change ports if you need.

#### Servers 

- Web Server : http://127.0.0.1:8181
- Proxy Server: http://127.0.0.1:8282

##### Dynamic Proxy

That's mean all proxy addresses read from Database.
Database is mysql. But you can change configuration in application.yml

Defined some manuel proxy in **xbalancer/xbalancer-web/src/main/resources/import.sql** spring will automatically put them to database.
- Proxy Server: http://127.0.0.1:8283
- Proxy Server: http://127.0.0.1:8284
- Proxy Server: http://127.0.0.1:8285

#### Manuel Proxy

You can find **application_manuel.yml** file which can used defined proxy in application.yml as static.

Defined same manuel clients as you can see follow:
- Proxy Server: http://127.0.0.1:8283
- Proxy Server: http://127.0.0.1:8284
- Proxy Server: http://127.0.0.1:8285
#### Crud Operations Standard Paths

- Get one : /rest/{entityName}s/{oid} GET
- Get all : /rest/{entityName}s GET
- Create : /rest/{entityName}s PUT
- Update : /rest/{entityName}s/{oid} POST
- Delete : /rest/{entityName}s/{oid} DELETE

* Example
    - Get Role : /rest/roles/{oid} GET
    - Get all : /rest/roles GET
    - Create : /rest/roles PUT
    - Update : /rest/roles/{oid} POST
    - Delete : /rest/roles/{oid} DELETE

#### Proxy Address (/rest/proxyAddress)

Proxy Address is holds Host address which will redirect by URL;

#### Proxy Pointer (/rest/proxyPointer)

Proxy Pointer is a path which is refers hosts to redirect them.

#### Security (/login)

Security is a simple spring security.
All user has roles and all roles has permissions.
Only permissions use as Authority. You can find authority column in Permission entity.

#### Role (/rest/roles)

#### Permission (/rest/permissions)

#### User (/rest/users)



