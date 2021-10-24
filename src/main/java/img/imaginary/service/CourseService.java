package img.imaginary.service;

import java.util.Set;

import img.imaginary.service.entity.Course;

public interface CourseService {
    
    void addAllCourses(Set<Course> courses);
    
    Set<Course> getAllCourses();
}
