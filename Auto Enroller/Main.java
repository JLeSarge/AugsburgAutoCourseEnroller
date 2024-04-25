import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.SQLException;

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
            private boolean enrolled;

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

            public int getID(){
                return studentId;
            }
            public void setEnrolled(boolean i){
                enrolled = i; 
            }
            public boolean getEnrolled(){
                return enrolled;
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
        public void insertIntoDatabase(Student stu){
            // connect to database and prepare to insert student info
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.connectAndUpdate(stu);  
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

    public static class DatabaseConnector {
        private  String  URL = "jdbc:mysql://Localhost:3307/?user=root&password=password";
        private Student  student;
        private Connection myConn = null;
        private Statement myStat;
        private LocalDate event;
    
        public void setUSER(Student stu){
            student = stu;
        }
        public void createStatment(){
            try {
            myStat = myConn.createStatement();
            } 
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        public void createConnectionInstance(){
            try{
                myConn = DriverManager.getConnection(URL);
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        public void createEventTime(){
            event = LocalDate.now();
        }
        //the actual sql statment that inserts the information (name,studentid,date)
        public String createSQL(){
            int enrolled = 0;
            if (student.getEnrolled()){enrolled = 1;}
            System.out.println("insert into `basic-database`.student_info (name, augsburg_id, event_log, Enrollment_Condition) values ('"
                +student.getName()+"', '"+student.getID()+"', '"+event+"', '"+enrolled+"');");
            return
                    ("insert into `basic-database`.student_info (name, augsburg_id, event_log, Enrollment_Condition) values ('"
                +student.getName()+"', '"+student.getID()+"', '"+event+"', '"+enrolled+"');");
        }
        //uses mystat to create an update statment for sql
        public void executeSQL(){
            try{
            myStat.executeUpdate(createSQL());
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        //combines other methods to generate all the variables in one method.
        public void initalizeVariables(Student student){
            setUSER(student);
            createStatment();
            createEventTime();
        }
        //runs connect to database instance, intialize variables and executes the sql update
        public void connectAndUpdate(Student stu){
            
            try{
                loadDriver();
                createConnectionInstance();
                initalizeVariables(stu);
                executeSQL();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        // initalize driver
        public void loadDriver(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}