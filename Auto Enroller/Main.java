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


    public static class Student {
        private int studentId;
        private String name;
        private String email;
    
        public Student(int studentId, String name, String email) {
            this.studentId = studentId;
            this.name = name;
            this.email = email;
        }
    
        public String getName() {
            return name;
        }
    
        public boolean hasCompletedCourse(int courseId) {
            return false;
        }
        
    }

    public static class Courses {
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

    public static class EnrollmentManager {
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
    
    }


    public static class EnrollmentRule {
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
    
    }

}