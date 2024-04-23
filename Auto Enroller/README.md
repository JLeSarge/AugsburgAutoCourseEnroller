Auto Enrollment System
This is an enrollment system implemented in Java that allows students to enroll in courses based on availability, capacity, and eligibility criteria.

FILES
Course.java: Defines the Course class, representing individual courses offered by the institution. Each course has a unique ID, name, capacity, enrolled students, and enrollment rule.

Student.java: Defines the Student class, representing students who can enroll in courses. Each student has a unique ID, name, and email address.

EnrollmentRule.java: Defines the EnrollmentRule class, representing the eligibility criteria for enrolling in a course. It includes parameters such as maximum class size, prerequisites, and enrollment period.

EnrollmentSystem.java: Defines the EnrollmentSystem class, which manages the enrollment process. It allows students to enroll in available courses based on eligibility criteria and capacity constraints.

Main.java: Contains the main method to demonstrate the functionality of the enrollment system. It creates sample data, including courses, students, and enrollment rules, and demonstrates how students can enroll in courses.