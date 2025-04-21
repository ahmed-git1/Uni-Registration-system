University Registration System – CS102 Project

Overview:

This command‑line application simulates a lightweight University Registration System written in Java for the CS102 course.  It allows three kinds of users—students, advisors, and administrators—to interact with course and user data that is stored in plain‑text files.  All business logic is contained in Java classes inside the Project package, while persistent data lives in users.txt and courses.txt.

Features:

Student

* View all available courses* Register / drop courses (prerequisites & seat limits enforced)* View timetable & validated courses* Update own credentials* Submit enrolment requests when a class is full* List a course’s full prerequisite chain

Advisor

* View all registered students & their schedules* Approve / deny student enrolment requests (propagated to all advisors)* Inspect a single student’s registered courses

Administrator

* Create, update, or delete courses (code, description, seats, credits …)* Manage user accounts (add / remove) * Inspect system‑wide statistics

Note: The application reads & writes the .txt files from the compiled output folder (/build/classes when using most IDEs).  Be sure the two text files are copied there before running.

Getting Started:

Prerequisites --

**Java 8 or later** JDK on your PATH  
*(Tested with Java 8 — compatibility flags used if compiled with newer JDKs)*

A terminal / command prompt

Building:

Compile the sources from the repository root:

javac -d bin -source 8 -target 8 Project\*.java

This places .class files in bin/Project.

Running:

cd bin
java Project.Interface

If you run from an IDE, simply execute Interface.main().

Data Files:

>users.txt --

Each line represents one user:

<username> <role> <password> <misc>

For students misc contains their registered course codes (e.g. BIO101/).
For advisors misc contains pending requests in the form student#COURSE/.

>courses.txt --

<code> <name> <prereqs|no_prerequisites> <description_with_underscores> <instructor> <max_seats> <credits> <enrolled_students>

Example:

BIO101 biology no_prerequisites study_of_living_things paul 5 3 student1/student2/

----

License:

Released under the MIT License.  See LICENSE for details.


