class Courses {
    constructor(courseId, courseName, capacity, enrolledStudents, enrollmentRule) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = enrolledStudents;
        this.enrollmentRule = enrollmentRule;
    }

    getCourseId() {
        return courseId;
    }
    
    getCourseName() {
        return courseName;
    }
    
    getCapacity() {
        return capacity;
    }
    
    getEnrolledStudents() {
        return enrolledStudents;
    }
    
    getEnrollmentRule() {
        return enrollmentRule;
    }
    
    setEnrollmentRule(enrollmentRule) {
        this.enrollmentRule = enrollmentRule;
    }
    
    enrollStudent(student) {
        enrolledStudents.push(student);
    }
}

