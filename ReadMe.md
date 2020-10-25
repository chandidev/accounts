
The application uses Spring boot and JPA

Unit tests have been done for Repository and Service classes and controllers for some extent

it's assumed the user is authenticated and jwtToken present in the request

Ideally can use spring security to set the user in the security context

But for this assignment it's using just a service to return customer Id from the jwtToken

Test coverage is not 100% due to time constrains.

Also no blackbox testing is done.

just to populate the database for demo only, an end point is exposed as a quick and dirty solution.

Running the application:
./gradlew bootRun

Testing:
to populate test data
curl GET http://localhost:8080/test/populate

getAccounts call
curl GET   http://localhost:8080/accounts   -H 'access_token: fdsfas' 

getTransactions call
curl GET   http://localhost:8080/accounts/XX003/transactions   -H 'access_token: fdfd' 
