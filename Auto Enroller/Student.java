public class Student {
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
