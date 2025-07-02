POST http://localhost:8080/todos
Content-Type: application/json

{
	"title": "Spring Boot",
	"description": "Finish Spring Boot Tutorial",
	"isDone": false,
	"isUrgent": false,
	"isImportant": true,
	"dueDate": ""
}

HTTP 201
[Captures]
todo: body
id: jsonpath "$.id"

###

GET http://localhost:8080/todos/{{ id }}
Accept: application/json

HTTP 200
[Asserts]
body == {{todo}}
