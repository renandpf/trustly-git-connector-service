#Git Connector Service

##Operation
The system receives a Git repository URL, and the return is the number of lines and bytes of all the artifacts in the repository grouped by the file extension.

Files without extension, will be represented with an empty "extension name" ("")

Binary files, will not have number of lines

Only public repositories can be searched (otherwise an error has occurred)

##Technical information
* The system was created using ** Java ** (openjdk version ** "11.0.8" 2020-07-14 **).
* ** Spring Boot ** was used
* The system was developed with ** [TDD](https://pt.wikipedia.org/wiki/Test_Driven_Development) **
* The system was developed using ** [Clean Architecture](https://stackoverflow.com/tags/clean-architecture/info) **
* The system uses ** [Lombok](https://projectlombok.org/) **. For development it is necessary to install the plug in the IDE (otherwise there will be compilation errors).
* ** Docker ** was used. [Docker Hub](https://hub.docker.com/repository/docker/renandpf/trustly-git-connector-service.)
* The project is available on the platform ** [Heroku](https://dashboard.heroku.com/apps/trustly-git-connector-service) **.
* ** Swagger ** was used to document the api. To access, use the address: https://trustly-git-connector-service.herokuapp.com/swagger-ui/index.html
* 100% coverage of unit tests.
* Used concepts of SOLID and Design Patterns.

###Docker
PS: The project is available on the [docker hub](https://hub.docker.com/repository/docker/renandpf/trustly-git-connector-service.)

* If you want to generate the image, use (you need the lib (jar) the in local):
```sudo docker build -t renandpf/trustly-git-connector-service:1.1.0 .```

* Or if you just want to run, use the command:
```sudo docker container run --name trustly-git-connector-service -p 8080:35000 renandpf/trustly-git-connector-service:1.1.0```

##Backlogs and Technical Debits
* Implement screens (Angular)
* Search Git pages in parallel processes
* Currently the system works only with ** Github **. Information search must be implemented in GitLab, Bitbucket, etc.
* Add integrated fallback tests (there is currently only 1 integrated success test).
* When obtaining the file size (of the Git pages), only the measures ** Bytes, KBytes and MegaBytes ** are considered. For only these types were found on the pages of Github.

#Examples of API calls
**repositoryBaseUrl**: must contain the url to be used in the search

**Example of Success**


Request:

```
curl --location --request POST 'https://trustly-git-connector-service.herokuapp.com/trustly/git/extension-file-infos' \
--header 'Content-Type: application/json' \
--data-raw '{
   "repositoryBaseUrl": "https://github.com/renandpf/trustly-git-connector-service"
}'
```
Response:

```
[
    {
        "extensionName": "txt",
        "totalLineNumbers": 8,
        "totalBytes": 1075.2
    },
    {
        "extensionName": "java",
        "totalLineNumbers": 1995,
        "totalBytes": 72857.12
    },
    {
        "extensionName": "gitkeep",
        "totalLineNumbers": 0,
        "totalBytes": 0.0
    },

]
```

** Example of Error: ** Access to git repository not implemented (GitLab, Bitbucket, etc.). ** Only Github is implemented **

Request:

```
curl --location --request POST 'https://trustly-git-connector-service.herokuapp.com/trustly/git/extension-file-infos' \
--header 'Content-Type: application/json' \
--data-raw '{
   "repositoryBaseUrl": "https://gitlab.com/renandpf/trustly-git-connector-service"
}'
```

Response:

```
HTTP: 422
{
    "code": "tgc.error.unknownGitRepository",
    "message": "This git repository not implemented yet"
}
```

** Example of Error: ** protocol not implementing (ftp, ...)
Request:

```
curl --location --request POST 'https://trustly-git-connector-service.herokuapp.com/trustly/git/extension-file-infos' \
--header 'Content-Type: application/json' \
--data-raw '{
   "repositoryBaseUrl": "ftp://github.com/renandpf/trustly-git-connector-service"
}'
```

Response:

```
HTTP: 500
{
    "code": "tgc.error.connectorNotFound",
    "message": "This protocol not implemented yet."
}
```