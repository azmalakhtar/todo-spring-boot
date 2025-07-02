DELETE http://localhost:8080/todos/all

###

GET http://localhost:8080/todos
Accept: application/json

HTTP 200
[Asserts]
body == "[]"

### 

POST http://localhost:8080/todos
Content-Type: application/json

{
	"title": "Spring Boot",
	"description": "Finish Spring Boot Tutorial",
	"isDone": false,
	"isUrgent": false,
	"isImportant": true,
	"dueDate": "2025-08-06"
}

HTTP 201
[Captures]
todo1: body
id1: jsonpath "$.id"
### 

POST http://localhost:8080/todos
Content-Type: application/json

{
	"title": "Spring Boot",
	"description": "Finish Spring Boot Tutorial",
	"isDone": false,
	"isUrgent": false,
	"isImportant": true,
	"dueDate": "2025-08-06"
}

HTTP 201
[Captures]
todo2: body
id2: jsonpath "$.id"

### 

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
todo3: body
id3: jsonpath "$.id"

###

GET http://localhost:8080/todos
Accept: application/json

HTTP 200
[Asserts]
body == "[{{todo1}},{{todo2}},{{todo3}}]"


###

GET http://localhost:8080/todos/{{id2}}
Accept: application/json

HTTP 200
[Asserts]
jsonpath "$.id" == {{id2}}

###

DELETE http://localhost:8080/todos/{{id2}}

###

GET http://localhost:8080/todos/{{id2}}
Accept: application/json

HTTP 404
[Asserts]
body == ""
