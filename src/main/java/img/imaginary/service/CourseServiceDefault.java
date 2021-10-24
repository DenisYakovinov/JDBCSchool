package img.imaginary.service;

import java.util.Set;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.DaoFactory;
import img.imaginary.service.entity.Course;

public class CourseServiceDefault implements CourseService {

    private CourseDao courseDao;

    public CourseServiceDefault(DaoFactory daoFactory) {
        courseDao = daoFactory.getCourseDao();
    }

    @Override
    public void addAllCourses(Set<Course> courses) {
        courseDao.addAllCourses(courses);
    }

    @Override
    public Set<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
}
