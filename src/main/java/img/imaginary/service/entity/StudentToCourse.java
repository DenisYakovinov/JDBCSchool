package img.imaginary.service.entity;

import java.util.Objects;

public class StudentToCourse {
    
    private int studentId;
    private int courseId;
    
    public StudentToCourse(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }
    
    public int getCourseId() {
        return courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StudentToCourse)) {
            return false;
        }
        StudentToCourse other = (StudentToCourse) obj;
        return courseId == other.courseId && studentId == other.studentId;
    }  
}
