DELETE http://localhost:8080/all

###

POST http://localhost:8080/register
{
	"email": "a@gmail.com",
	"password": "a@123"
}

HTTP 201

###

POST http://localhost:8080/register
{
	"email": "b@gmail.com",
	"password": "b@123"
}

HTTP 201
