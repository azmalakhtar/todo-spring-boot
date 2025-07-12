POST http://localhost:8080/login
{
	"email": "a@gmail.com",
	"password": "a@123"
}

HTTP 200

[Captures]
jwt_a: body

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Spring Security",
	"description": "Learn JWT Authentication",
	"dueDate": "2025-07-10"
}


HTTP 201

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Spring Data JPA",
	"description": "Learn Postgresql",
	"dueDate": "2025-07-10",
	"isImportant": true
}


HTTP 201

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "A Liberated Mind",
	"description": "Read the values chapter",
	"dueDate": "2025-07-10",
	"isUrgent": true
}


HTTP 201

###

POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Gladitor",
	"description": "Write Essay on it",
	"dueDate": "2025-07-10",
	"isImportant": true,
	"isUrgent": true
}


HTTP 201

###
POST http://localhost:8080/todos
Authorization: Bearer {{jwt_a}}
{
	"title": "Not Today",
	"description": "Write Essay on it",
	"dueDate": "2025-07-11",
	"isImportant": true,
	"isUrgent": true
}


HTTP 201
