# We Learning

This project is first design to help me learn how to develop a complex applications. My goal is to go through all the steps of the development process of software. 

### Architecture

The following [excalidraw](https://excalidraw.com/#json=XljIHBPM4A7imo_lDipCT,X3f8NPVmWidlwlHi_YMoyg) show the design of the application. On a high level the application follow a microservice approach for the backend of the application. The application is divided in two parts:
- [Clients:](./clients/) This represent the application that will consume data from the backend. For now we have two clients. A *nextjs* web application for client to interact with the backend (create resources, take courses, or just play with using environment on a VM)
- [Services:](./services/) This represent the backend of the application and is divided in 3 main services. 
    a.) User management service: This service is responsible for handle user data as well as handling authentication and authorization.
    b.) Resource management service: This service allow user to create/update/delete or get resources (VM access)
    c.) Billing service: Responsible for computing charges apply to user resource requirements.
- [Docs:](./docs/) Documentation of the application
- [Docker:](./docker/) Is really helpful to setup local development environment.

### Technologies

The project uses the following technologies:
- [Nextjs]
- [trpc]
- [Quarkus]
- [Tuborepo]
- [Gradle]
- [Docker]
- [Digitalocean]


### Application Features

Coming soon

### Contribution

Coming soon.
