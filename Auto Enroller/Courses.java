import java.util.List;
import java.util.ArrayList;

public class Courses {
    private int courseId;
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;
    private EnrollmentRule enrollmentRule;

    public Courses(int courseId, String courseName, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    public EnrollmentRule getEnrollmentRule() {
        return enrollmentRule;
    }
    
    public void setEnrollmentRule(EnrollmentRule enrollmentRule) {
        this.enrollmentRule = enrollmentRule;
    }
    
    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
}


