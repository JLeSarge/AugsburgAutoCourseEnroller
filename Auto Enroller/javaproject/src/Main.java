package project;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // sample 
        List<Courses> availableCourses = new ArrayList<>();
        Courses course1 = new Courses(1, "English", 20);
        Courses course2 = new Courses(2, "Math", 25);
        availableCourses.add(course1);
        availableCourses.add(course2);

        EnrollmentRule enrollmentRule = new EnrollmentRule(30, new ArrayList<>(), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7));
        course1.setEnrollmentRule(enrollmentRule);
        
        EnrollmentManager enrollmentManager = new EnrollmentManager(availableCourses);

        Student student = new Student(1, "Aisha Jama", "jamaaa9@augburg.edu");

        // enroll student into courses
        enrollmentManager.enrollStudent(student, course1);
        enrollmentManager.enrollStudent(student, course2);
    }
}