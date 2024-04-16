import java.util.List;

public class Courses {
    private int courseId;
    private String courseName;
    private int capacity;
    private String prerequisites;
    private boolean enrolled;
    public List<Courses> getEnrolledStudents() {
        return null;
    }
    public Object getCourseId() {
        return null;
    }
    public EnrollmentRule getEnrollmentRule() {
        return null;
    }
    public Courses(int courseId, String courseName, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.capacity = capacity;
    }
    public Courses() {
    }
    public int getCapacity() {
        return capacity;
    }
    public void enrollStudent(EnrollmentManager.Student student) {
    }
    public String getCourseName() {
        return null;
    }

}
