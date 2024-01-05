# Welcome to RecSys!

## Description
This is a prototype for streamlining the bank reconciliation process, developed as a bachelor thesis for and with Tritt AS
and NTNU. 

### Tech stack
* Database: PostgreSQL
* Backend: Java, Micronaut, Spock, Groovy, Gradle
* Frontend: Vue, TypeScript, Tailwind
* Containiarizing: Docker og Docker Compose



## Setup and run project

* Clone the project into a suitable folder
* Open the project with intellij
* Run the backend from IntelliJ by hitting the run button with the `Deploy` or `Deploy [debug]`configuration 
(Be sure to have docker installed and running).
* Run 'App'
* In a terminal, navigate to `pracsys-frontend` folder
* Install the front-end dependencies by running `yarn` from the terminal
* Run `yarn dev` from the terminal


## Credits

Thank you to NTNU for providing me with the knowledge and opportunity to take on this bachelor thesis, 
the most significant milestone in my academic journey.

My sincere appreciation to the client, Tritt and Conta, whose collaboration made this project possible. 
Their real-world challenges enriched my learning experience and added practical relevance to the theoretical 
concepts explored throughout this project.

I also wish to thank the team, Sølve Monterio, Regine Giskegjerde Urtegård, Simen Stokkeland Fuglseth and 
Nina Vinding Blindheim for their help and support. Special thanks to Sølve Monteiro for his cooperation 
and mentorship.


## Database

PostgreSQL is launched via Docker, which is managed by Docker Compose. To start the database, 
go to the "run" menu at the top right and find "Deploy". This runs (configured in .run/Deploy.xm) 
the database for you. Under Services (bottom bar in IntelliJ), 
you should now be able to see Docker/Docker-compose:recsys/database.

To connect to the database in IntelliJ, open the Database tab all the way to the right, 
and click the + icon to add a new data source:

Type: PostgreSQL
Host: localhost
User: postgres
Password: postgres
Database: postgres
If this data source has been added correctly, you should now see a Postgres database in this tab.


## Tests
This project includes unit testing and E2E-testing. 


### Cypress tests (E2E):
After running the project as described above, follow these steps: 
* In a terminal, navigate to `pracsys-frontend` folder
* Run all tests by running `yarn e2e:all`
* Open test environment by running `yarn e2e:open` from the terminal if you want to see the steps in Cypress. 


### Groovy tests
* Find the test you want to run in the groovy folder in the project (recsys/backend/src/test/groovy)
* Run test by clicking green arrow --> Run 'nameOfFileTest'


## Known issues
* BUG: upon refreshing site, old transactions are not removed when comparing in either 
the same or a new period. Total amounts and discrepancy is still correct, and 
if you want to save new period it has the correct data. 


