import java.util.List;
import java.time.LocalDateTime;

public class EnrollmentRule {
    // define enrollment criteria and methods to check if eligible
    private int maxClassSize;
    private List<Courses> prerequisites;
    private LocalDateTime enrollmentStartTime;
    private LocalDateTime enrollmentEndTime;

    public EnrollmentRule(int maxClassSize, List<Courses> prerequisites, LocalDateTime enrollmentStartTime, LocalDateTime enrollmentEndTime) {
        this.maxClassSize = maxClassSize;
        this.prerequisites = prerequisites;
        this.enrollmentStartTime = enrollmentStartTime;
        this.enrollmentEndTime = enrollmentEndTime;
    }

    public boolean isEligible(Student student, Courses course) {
        return meetsClassSizeLimit(course) && meetsPrerequisites(student, course) && isWithinEnrollmentPeriod();
    }

    private boolean meetsClassSizeLimit(Courses course) {
        // check if class size is within limit
        return course.getEnrolledStudents().size() < maxClassSize;
    }

    private boolean meetsPrerequisites(Student student, Courses course) {
        // check if student meets all prereqs for course
        for (Courses prerequisite : prerequisites) {
            if (!student.hasCompletedCourse(prerequisite.getCourseId())) {
                return false;
            }
        }
        return true;
    }

    boolean isWithinEnrollmentPeriod() {
        // check if current date and time is within enrollment period
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isAfter(enrollmentStartTime) && currentTime.isBefore(enrollmentEndTime);
    }

    public boolean isEligible(EnrollmentManager.Student student, EnrollmentManager.Course course) {
        return false;
    }

    public boolean isEligible(EnrollmentManager.Student student, Courses course) {
        return false;
    }


}
