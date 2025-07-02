POST http://localhost:8080/login
{
	"email": "a@gmail.com",
	"password": "a@123"
}

HTTP 200

[Captures]
jwt: body

###
GET http://localhost:8080/test
Authorization: Bearer {{jwt}}

HTTP 200
