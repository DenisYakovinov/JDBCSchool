package img.imaginary.dao;

import java.util.Set;

import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;
import img.imaginary.service.entity.StudentToCourse;

public interface StudentDao {
    
    int addNewStudent(Student student);
   
    void addAllStudents(Set<Student> students);
    
    Student findById(int id);
    
    void deleteStudentById(int studentId);
       
    Set<Student> findStudentsRelatedCourse(String courseName);
    
    void addToGroup(Student student, Group group);
    
    void setGroup(Student student, Group group);
    
    public void setStudentsToGroup(Set<Student> students, int groupId);
    
    void addStudentToCourse(int studentId, int courseId);
    
    void addBatchStudentsToCourse(Set<StudentToCourse> studentsToCourses);
    
    void removeStudentFromCourse(int studentId, int courseId);
    
    public Set<Course> getCoursesRelatedStudent(int studentId, CourseDao courseDao);
}
