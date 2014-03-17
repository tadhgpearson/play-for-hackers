# Play Framework for Hackers
### An interactive guide to some rapid prototyping features of the Play! Framework

This project is a presentation in the form of a web application which runs in Play.
The idea is that by following the steps of this presentation of the Play Framework in the source code, you can also see and understand how certain features of the framework operate in practice.

## Getting started

First, clone this project!

In order to run the application, you should download an install Play! Framework from www.playframework.com
In order to run Play Framework, you should ensure you are running at least Java 6r26 or Java 7r10. You can get this from [http://java.oracle.com]/
This version of the application is for Play! 2.2.2. Get the standard installation and unzip it to a path with no spaces (eg. D:\java)
When you unzip, a new play-2.2.2 folder will be created at that path, add this to your PATH environement variable (eg. D:\java\play-2.2.2)

Now, open a command prompt to the root of this project (the same folder as this README.md file) and enter
    play run
This will start the server on your local machine.	

Now open your web browser and navigate to [http://localhost:9000]. You will be prompted with a red screen asking you to apply a database migration. Hit Apply.

The splash screen of the application should now load.

## Opening in Eclipse

In order to open the application in Eclipse, from the root of the project (the same folder as this file), open a command prompt and enter
    play eclipse
This will generate the metadata required by Eclipse.

Now open your Eclipse workspace and go to File -> Import from the menu.
From the list, select 'Existing Project' and choose the root of this project again (the folder from which you ran the play eclipse command) and finish the import.
 
