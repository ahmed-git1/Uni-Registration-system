University Registration System – CS102 Project

Overview

This command‑line application simulates a lightweight University Registration System written in Java for the CS102 course.  It allows three kinds of users—students, advisors, and administrators—to interact with course and user data that is stored in plain‑text files.  All business logic is contained in Java classes inside the Project package, while persistent data lives in users.txt and courses.txt.

Features

Role

Capabilities

Student

* View all available courses* Register / drop courses (prerequisites & seat limits enforced)* View timetable & validated courses* Update own credentials* Submit enrolment requests when a class is full* List a course’s full prerequisite chain

Advisor

* View all registered students & their schedules* Approve / deny student enrolment requests (propagated to all advisors)* Inspect a single student’s registered courses

Administrator

* Create, update, or delete courses (code, description, seats, credits …)* Manage user accounts (add / remove) * Inspect system‑wide statistics

Project Structure

Project/
├── admin.java        # Administrator workflow
├── advisor.java      # Advisor workflow
├── data.java         # Shared data access & utilities
├── student.java      # Student workflow
├── Interface.java    # Entry point with `main()`
├── courses.txt       # Course catalogue & enrolments
└── users.txt         # User accounts & requests

Note: The application reads & writes the .txt files from the compiled output folder (/build/classes when using most IDEs).  Be sure the two text files are copied there before running.

Getting Started

Prerequisites

Java 17 (or later) JDK on your PATH

A terminal / command prompt

Building

Compile the sources from the repository root:

javac -d bin src/Project/*.java

This places .class files in bin/Project.

Running

cd bin
java Project.Interface

If you run from an IDE, simply execute Interface.main().

Data Files

users.txt

Each line represents one user:

<username> <role> <password> <misc>

For students misc contains their registered course codes (e.g. BIO101/).
For advisors misc contains pending requests in the form student#COURSE/.

courses.txt

<code> <name> <prereqs|no_prerequisites> <description_with_underscores> <instructor> <max_seats> <credits> <enrolled_students>

Example:

BIO101 biology no_prerequisites study_of_living_things paul 5 3 student1/student2/

Usage Guide

1. Logging in / Signing up

At start‑up the program asks whether you already have an account.  If No, you will be guided through sign‑up.  Otherwise, provide your existing username and password.  Your role determines the menu that appears.

2. Student Workflow

View available courses to decide what to take.

Register if you satisfy prerequisites and seats are available.

Drop courses or view schedule at any time.

If a course is full, submit a request—an advisor can later approve it.

3. Advisor Workflow

Inspect all student accounts or a single student’s courses.

Handle pending enrolment requests.  Accepting automatically registers the student and updates all advisors’ queues.

4. Administrator Workflow

Manage Courses: add, edit, or remove courses with live validation (seat limits, unique codes, etc.).

Manage Users: quickly add new accounts or delete obsolete ones.

View Stats: see total counts plus formatted listings of users and courses.

Extending the Project

Replace text files with a relational database for better scalability.

Add GUI (JavaFX / Swing) for a more user‑friendly interface.

Implement password hashing and role‑based access checks.

Introduce unit tests with JUnit to improve maintainability.

Known Limitations

All data is held in memory while the app runs—no concurrent access safety.

There is no input sanitisation beyond simple menu checks.

Schedules are dummy (hour‐slots increment from 08:00); no real timetable logic.

Author

Your Name – CS102, Your University

License

Released under the MIT License.  See LICENSE for details.


