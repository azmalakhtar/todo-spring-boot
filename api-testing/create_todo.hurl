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
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Spring Security",
	"description": "Learn JWT Authentication"
}


HTTP 201

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Spring Data JPA",
	"description": "Learn Postgresql"
}


HTTP 201

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_b}}
{
	"title": "A Liberated Mind",
	"description": "Read the values chapter"
}


HTTP 201

###

POST http://localhost:8080/todos
Authorization: Bearer {{jwt_b}}
{
	"title": "Gladitor",
	"description": "Write Essay on it"
}


HTTP 201
