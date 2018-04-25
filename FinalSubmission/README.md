Task Manager

A database driven to-do list manager written in Java using the Cliche Library

Project Overview

We use the Cliche Library to implement a shell that allows for mulitple commands/transactions can occur between the program and the database.
First we prompt the user for their ssh credentials and then the database credentials they are trying to access.
This information will be stored in User.java
Second a session is established so that an ssh tunnel occurs using the users ssh credentials. This will allow the user to connect to the onyx server if they have an account. This is done in JDBC.java
Third if the session is established/exists then a connection will be made to the database using the database credentials the user have inputted.
If both the session and connection are a success the user will be able to start adding tasks to the database using the main file TaskManager.java.
TaskManager.java contains the queries that the user can run based on the available functions. This would be things like add() so the user can add a task, show() so a user can see the tasks they still need to do, etc.
