package img.imaginary.dao;

import java.util.Set;

import img.imaginary.service.entity.Course;

public interface CourseDao {
        
    void addNewCourse(Course course);
    
    void addAllCourses(Set<Course> courses);
    
    Set<Course> getAllCourses();
    
    Set<Course> getCoursesByStudent(int studentId);
    
}
