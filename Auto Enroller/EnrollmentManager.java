import java.util.List;
import java.util.ArrayList;
public class EnrollmentManager {
    private List<Course> availableCourses;
    public class Student {
        private String name;
    
        public Student(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
    }
    
    public class Course {
        private String courseName;
    
        public Course(String courseName) {
            this.courseName = courseName;
        }
    
        public String getCourseName() {
            return courseName;
        }

        public EnrollmentRule getEnrollmentRule() {
            return null;
        }

        public List<EnrollmentManager.Course> getEnrolledStudents() {
            return null;
        }

        public int getCapacity() {
            return 0;
        }

        public void enrollStudent(EnrollmentManager.Student student) {
        }
    
    }

    public EnrollmentManager(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }

    public EnrollmentManager() {
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
                    System.out.println("Student" + student.getName() + " enrolled in course " + course.getCourseName());
                } else {
                    System.out.println("Course " + course.getCourseName() + " is already full");
                } 
                 } else {
                    System.out.println("Student" + student.getName() + " is not eligible to enroll in course " + course.getCourseName());
                }
            }else {
                System.out.println("Course" + course.getCourseName() + " is not availible for enrollment." );
            }
              
    }
    

    public List<Course> getAvailableCourses() {
        List<Course> available = new ArrayList<>();
        for (Course course : availableCourses) {
            if (course.getEnrollmentRule() != null && course.getEnrollmentRule().isWithinEnrollmentPeriod()) {
                available.add(course);
            }
        }
        // returns available courses
        // retrieves courses that are available for enrollment
        return available;
    }

    
    
}
