CSX42: Assignment 5
Name:  Abhishek Patankar

Following are the commands and the instructions to run ANT on your project.
-----------------------------------------------------------------------

## To clean:
ant -buildfile genericCheckpointing/src/build.xml clean

-----------------------------------------------------------------------

## To compile: 
ant -buildfile genericCheckpointing/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile genericCheckpointing/src/build.xml run -Darg0=firstarg -Darg1=SECOND -Darg2=THIRD

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile genericCheckpointing/src/build.xml tarzip

-----------------------------------------------------------------------
## Description:

This assignment is to create a generic library to serialize and deserialize 
objects. The code should allow the conversion of objects into a wire format. 
The code should be designed using dynamic proxies and reflection so that 
addition of new objects or serialization formats causes minimal changes 
(reduces the ripple effect).
Values generated at the time of serialization :-
Randomly Generated values for Int, Long, Double, Short, Float.
Randomly generated values for Int, Double are between 0 to 100, randomly
generated value for long is between 0 to 1000.
Hardcoded the values for Char, boolean and string.
String  :- Checkpointing Objects
Char    :- Y
Boolean :- true.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.
This assignment's submission is my own work and I did not discuss with 
any other past or current student, nor did I have access to a previous 
submission of this assignment by another student."
[Date: 05/13/2018] 

-----------------------------------------------------------------------

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

