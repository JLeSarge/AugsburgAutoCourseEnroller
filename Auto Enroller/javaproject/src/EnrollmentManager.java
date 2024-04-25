package project;

import java.util.List;
import java.util.ArrayList;

public class EnrollmentManager {
    private List<Courses> availableCourses;

    public EnrollmentManager(List<Courses> availableCourses) {
        this.availableCourses = availableCourses;
    }

    public void enrollStudent(Student student, Courses course) {
        // check if the course is available for enrollment
        if (availableCourses.contains(course)) {
            // check eligibility based on enrollement rule
            EnrollmentRule enrollmentRule = course.getEnrollmentRule();
            if (enrollmentRule != null && enrollmentRule.isEligible(student, course)) {
                // check if the course has reached capacity
                if (course.getEnrolledStudents().size() < course.getCapacity()) {
                    // enroll student
                    course.enrollStudent(student);
                    student.setEnrolled(true);
                    System.out.println("Student" + student.getName() + " enrolled in course " + course.getCourseName());
                } else {
                    student.setEnrolled(false);
                    System.out.println("Course " + course.getCourseName() + " is already full");
                } 
                 } else {
                    student.setEnrolled(false);
                    System.out.println("Student" + student.getName() + " is not eligible to enroll in course " + course.getCourseName());
                }
            }else {
                student.setEnrolled(false);
                System.out.println("Course" + course.getCourseName() + " is not availible for enrollment." );
            }
            insertIntoDatabase(student);
            
    }
    

    public List<Courses> getAvailableCourses() {
        List<Courses> available = new ArrayList<>();
        for (Courses course : availableCourses) {
            if (course.getEnrollmentRule() != null && course.getEnrollmentRule().isWithinEnrollmentPeriod()) {
                available.add(course);
            }
        }
        // returns available courses
        // retrieves courses that are available for enrollment
        return available;
    }
    // method that calls database connector method to update db
    public static void insertIntoDatabase(Student stu){
        // connect to database and prepare to insert student info
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.connectAndUpdate(stu);  
    }

}
