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

Getting Started:

Prerequisites --

**Java 8 or later** JDK on your PATH  
*(Tested with Java 8)*

A terminal / command prompt

Running:

run "Interface" class

Data Files:

>users.txt:

Each line represents one user:

[username] [role] [password] [misc]

For students misc contains their registered course codes (e.g. BIO101/).
For advisors misc contains pending requests in the form student#COURSE/.

>courses.txt:

[course code] [course name] [Prerequisites] [course description] [instructor] [max seats] [credits] [enrolled students] 

Example:

BIO101 biology no_prerequisites study_of_living_things paul 5 3 student1/student2/

----

License:

Released under the MIT License.  See LICENSE for details.


