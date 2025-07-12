POST http://localhost:8080/login
{
	"email": "a@gmail.com",
	"password": "a@123"
}

HTTP 200

[Captures]
jwt_a: body

###
GET http://localhost:8080/todos/date/2025-07-09
Authorization: Bearer {{jwt_a}}

HTTP 200

