# fisherman-api-springboot
REST backend 

## API (Server):
- Use Spring Boot to expose a REST API over HTTP.
- Store data in a relational DB (e.g. MySQL).
- Design the API so it can be fully tested in Postman with manual testing
- Should have the four relationships as outlined below
- Should have all HTTP verbs utilized in the API (GET, PUT, POST, DELETE)
- Project should be on GitHub with good evidence of branching and PR workflow on each repository
- The API should be in its own repo separate from the CLI

## Example Project Data Model

### There are 4 core entities:

- City: (id,name,state,population)
- Passenger: (id,firstName,lastName,phoneNumber)
- Airport:(id,name,code)
- Aircraft:(id, type, airlineName,numberOfPassengers)

### Relationships
- A City has many Airports
- A Passenger lives in one City and can fly on many Aircraft
- An Aircraft can carry many Passengers and use many Airports (for takeoff/landing)
- An Airport belongs to one City
