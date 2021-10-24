package img.imaginary.service;

import java.util.Set;

import img.imaginary.dao.CourseDao;
import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Student;

public interface StudentService {
    
    void addNewStudent(Student student);
    
    void addAllStudents(Set<Student> students);
    
    void deleteStudentById(int studentId);
       
    Set<Student> findStudentsRelatedCourse(String courseName);
    
    void addStudentToCourse(int studentId, int courseId);
    
    void removeStudentFromCourse(int studentId, int courseId);
    
    Set<Course> getCourses(int studentId);
}
