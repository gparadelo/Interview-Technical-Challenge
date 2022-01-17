Technical Challenge Solution for interview by Gonzalo Paradelo
==============================================================

This code was created as a solution for a technical exercise for a job interview.
It consists of a system able to triangulate a position given three distances to known locations and a system to decode a message from a list of partially complete messages.
Both of these are exposed via an API. It can be run locally and it is hosted in Google Cloud Platform in the API below.

To run the application, run the following terminal command:
    ./mvnw spring-boot:run

Hosted service URL:
    https://advance-engine-326415.rj.r.appspot.com


API:
----

POST /topsecret/
{
	"satellites": [
		{
			"name": "kenobi",
			"distance" : 100.0,
			"message": ["este", "", "", "mensaje", ""]
		},
		{
			"name": "skywalker",
			"distance" : 115.5,
			"message": ["", "es", "", "", "secreto"]
		},
		{
			"name": "sato",
			"distance" : 142.7,
			"message": ["este", "", "un", "", ""]
		}
	]
}

To get set all satellites and get message and position.


POST /topsecret_split/{satellite_name}
{
	"distance": 100.0,
	"message": ["este", "", "", "mensaje", ""]
}

To set one satellite.


GET /topsecret_split/

To get message and position.