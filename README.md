# demo_users
Learning Java repo #1

The app exposes `/users` endpoint. 

POST request with {firstName:"Test", lastName:"Tester"} body will add a new user to DB. 

GET request returns JSON array with all users.

GET request to `/users/:id` with return a specific user with that ID.