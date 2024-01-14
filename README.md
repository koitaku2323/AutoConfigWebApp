# Vehicle Valuation and Automotive Configuration Web App

## Overview

This project is a Java-based web application for vehicle valuation and automotive configuration. The application allows clients to request and configure car models through a client-server architecture using the Java.net API, Java Servlets, and Apache Tomcat.

## Key Features

- **Client-Server Architecture:** The application utilizes a client-server model, facilitating communication between the client-side user interface and the server-side processing.

- **Object-Oriented Design:** The project incorporates essential principles of object-oriented design, including encapsulation, association, containment, inheritance, and polymorphism.

- **CRUD Operations:** The system efficiently manages CRUD (Create, Read, Update, Delete) operations with an API on the server, a database, and a client user interface (UI).

## Project Structure

The project is organized into distinct components:

### Client

- **src:** Contains the source code for the client-side implementation.
  - **adapter:** Includes classes related to adapting and building the automobile.
  - **driver:** Holds classes for handling the communication between the client and server.
  - **exception:** Manages exceptions specific to the automotive model.
  - **model:** Defines the structure of the automobile and its components.
  - **scale:** Contains classes related to scaling and editing options.
  - **util:** Includes utility classes for file I/O and properties.

### Server

- **src:** Contains the source code for the server-side implementation.
  - **adapter:** Similar to the client-side adapter, contains classes related to adapting and building the automobile.
  - **driver:** Handles communication between the server and clients.
  - **exception:** Manages exceptions specific to the automotive model.
  - **model:** Defines the structure of the automobile and its components.
  - **scale:** Contains classes related to scaling and editing options.
  - **util:** Includes utility classes for file I/O and properties.

## How to Run

1. **Client:** Run the client-side application using the provided client classes.
2. **Server:** Start the server-side application using the server classes.
3. **Configurations:** Utilize the automotive configuration features through the UI.
4. **CRUD Operations:** Perform CRUD operations to manage automotive models.

## File Organization

- **Project Folders:** Organized based on functionality (e.g., adapter, driver, exception, model, scale, util).
- **Source Files:** Java files organized in packages corresponding to their functionalities.
- **Readme:** Includes essential information about the project.

## Usage

1. **Client-Server Interaction:** Understand the client-server interaction to enable seamless communication between the user interface and server processes.
2. **Automotive Configuration:** Explore the features for configuring automotive models, making use of the provided options and functionalities.
3. **CRUD Operations:** Manage automotive models through CRUD operations on the server, including creation, retrieval, updating, and deletion.

## Important Notes

- Ensure that the necessary dependencies (Java.net API, Java Servlets, Apache Tomcat) are properly configured.
- Review and follow the code organization and structure for efficient development and maintenance.

## Contributors

- Ryan Yee
