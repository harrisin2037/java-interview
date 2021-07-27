**Create User**
----
  Returns json data of id about a single user

* **URL**

  /users

* **Method:**

  `POST`
  
*  **Data Params**

   **Required:**
 
   `id1=[string]`
   `id2=[string]`

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `{ userId : "string", id1 : "string", id2: "string" }`
 
* **Sample Call:**

  ```http
  POST /users HTTP/1.1
  Host: 127.0.0.1:8080
  Content-Type: application/json
  Cache-Control: no-cache

  {
    "id1": "id1",
    "id2": "id2"
  }
  ```

Sequence diagram of the API:

```sequenceDiagram
  participant C as Client
  participant A as App
  participant B as Database
  participant R as Redis

  C ->> A : [POST] CreateUser
  Note over C,A : Each request have a unique ID
  opt Validation
    A -->> A : Check if the request valid
  end
  A ->> R : Check if the record exist
  opt Concurrency
    R -->> R : Other operation occuring in the redis
  end
  R ->> A : Retrieve information
  opt 
    A -->> A : Check if the record exist
  end
  alt Cache available
    A ->> C: response by redis information
  else No cache
    A ->> B : Create record in the database
    opt Concurrency
      B -->> B : Other operation occuring in the database
    end
  B ->> A : Retrieve information
  A ->> C : Send requested information
  end
```