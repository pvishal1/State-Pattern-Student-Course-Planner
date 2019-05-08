# State-Pattern-Student-Course-Planner

The execution takes 2 parameters (input.txt and output.txt), absolute path of all the files are provided.

Output:
if graduated:
Bnum: Courses taken till graduated: Number of semester in which graduated

if can't graduate:
Bnum: Courses taken when decided that he/she can't graduate: Can't Graduated

-----------------------------------------------------------------------

Algorithm implemented:
1. The electives are given as a course, irrespective of when it comes or after which course and the elective counter is incremented.

2. For Categories:
	- The courses that are lower than the previously alloted course of that category can only be taken. But as a co-req, the smaller/higher anyone can be taken.
	
	eg: if C is taken in first sem, afterwards only D can be taken. A and B can't be taken in future.
	if D and A come after C has been taken in previous semester, only D will be alloted as it is higher than C, but A wouldn't be allotted even if it's co-req, because A is smaller than previously taken course.
	But if C and A appears in same sem together and course greater than C and A are not taken previously, both would be taken together as a co-req, order doesn't matter.
	
	- If highest course is taken, without any co-req or as the first course of that respective category, the student can't graduate. Further processing of request is stopped.
	
	eg: If a student takes just the course "D" in the first semester, then the student will never graduate as a second course from that category cannot be added in any later semester.
	
	- If the highest course appears first from it's respective category, the course is put in waitlist. It waits for co-req, if the co-req is not found the student is set as can't graduate ever. Basically, waitlist is emptied after each semester.
	
	eg: If D comes as first before A/B/C, D goes in waitlist to wait for co-req. If co-req comes, then that course and D are allotted together. Else at the end of semester the waitlist subject is allotted and student is set to can't graduate.

3. If algorithm gets to know that the student can never graduate, it stops processing the more courses and returns all the courses taken till it was decided that he/she can't graduate.

States:
AcceptCourses.java (Initial state):
	Every time a new course comes, it goes to this state. GraduateCheck state returns to this state, when the student has graduated or has potential to graduate in future (to accept new course).
Electives.java:
	After receiving course, AcceptCourses sends the course to this state to check if it's elective or of any other category. Adds the course, if it is elective and set state as GraduatCheck. Else sets the state as Categories, to check it's pre-req and add it.
Categories.java:
	Check if pre-req is satisfied. If satisfies all the condition, the course is added, else it is added in Waitlist. The state is set to GraduateCheck.
GraduatCheck.java:
	Checks all the graduation requirements and the waitlist. Empties the waitlist before processing the new request (if waitlist is not empty and it's the end of the semester). Set state to CantGraduate, if it's confirm that student can't graduate according to the condition metioned above. Else set the the graduation flag to true, if student has graduated. Set the state as AcceptCourses to accept new course or exit if graduated.
CantGraduate.java:
	Student is in tjis state only when he/she can't graduate, so that the processing of courses is stopped.
	
Student.java (Context class):
This class handles all the functionality to be performed to when the course comes for that respective student. This holds all the data structures to be used and maintains the state of the student.

Results.java, implements the FileDisplayInterface and StdoutDisplayInterface
- In Driver class, the instance of Results is created, but is typecasted to it's respective interface based on the function it is suppose to perform. StdoutDisplayInterface instace is used for debugging purpose (to print in console), hence I have commented that code in Driver.
- Whereas, fileDisplayInterface instance does print the data in output file. If the file is not present, it creates it, and overwrite it with new data, if the output file is already present.

FileProcessor.java
- It reads all the line in the file and returns the ArrayList of String with all the lines that are read one after another in sequence.

Driver.java
It contains the main method.
