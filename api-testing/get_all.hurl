POST http://localhost:8080/login
{
	"email": "a@gmail.com",
	"password": "a@123"
}

HTTP 200

[Captures]
jwt_a: body

###

POST http://localhost:8080/login
{
	"email": "b@gmail.com",
	"password": "b@123"
}

HTTP 200

[Captures]
jwt_b: body

###

GET http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}

HTTP 200

### 

GET http://localhost:8080/todos
Authorization: Bearer {{jwt_b}}

HTTP 200
