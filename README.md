# Trine Bachelor

## Description
Provide a brief overview of what the project does and why it is useful.

### Tech stack
* Database: PostgreSQL
* Backend: Java, Micronaut, Spock, Groovy, Gradle
* Frontend: Vue, Vite, Vitest, TS, Tailwind
* Sky: Google GCP / Firebase, Kubernetes
* Over alt: Docker og Docker Compose


## Table of contents
Include a table of contents for easy navigation

## Installation
Step-by-step instructions on how to install and set up the project. Include any dependencies, prerequisites, or system requirements.



## Usage
Explain here how to use the project. Include code examples, command-line usage, or screenshots if applicable.


## Configuration
If the project requires configuration settings, explain how to set them up.

## Credits
Acknowledge any individuals, libraries, or resources that you used or were inspired by in the project.

## License
Specify the license under which the project is distributed. This helps users understand the terms under which they can use, modify, and distribute the project.

## Tests
If there are tests included, provide instructions on how to run them.

## Known issues
Document any known bugs or issues that users should be aware of.







## Database

PostgreSQL is launched via Docker, which is managed by Docker Compose. To start the database, go to the "run" menu at the top right and find "Deploy". This runs (configured in .run/Deploy.xm) the database for you. Under Services (bottom bar in IntelliJ), you should now be able to see Docker/Docker-compose: recsys/database.

To connect to the database in IntelliJ, open the Database tab all the way to the right, and click the + icon to add a new data source:

Type: PostgreSQL
Host: localhost
User: postgres
Password: postgres
Database: postgres
If this data source has been added correctly, you should now see a Postgres database in this tab. Open the database and right-click on "public", then choose "New Query Console" to start running queries!