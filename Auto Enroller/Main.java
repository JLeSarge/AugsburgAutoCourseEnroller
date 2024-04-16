public class Main {
    public static void main(String[] args) {

        EnrollmentManager manager = new EnrollmentManager();

        // retreive student and course data
        Student student = new Student();
        Courses course = new Courses();


        // enroll students in courses
        manager.enrollStudent(student, course);
    }
}