package img.imaginary.service;

import java.util.Set;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.StudentDao;
import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Student;

public class StudentServicePostgres implements StudentService {

    private StudentDao studentDao;
    private CourseDao courseDao;

    public StudentServicePostgres(DaoFactory daoFactory) {
        studentDao = daoFactory.getStudentDao();
        courseDao = daoFactory.getCourseDao();
    }

    @Override
    public void addNewStudent(Student student) {
        studentDao.addNewStudent(student);
    }

    @Override
    public void addAllStudents(Set<Student> students) {
        studentDao.addAllStudents(students);
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentDao.deleteStudentById(studentId);
    }

    @Override
    public Set<Student> findStudentsRelatedCourse(String courseName) {
        return studentDao.findStudentsRelatedCourse(courseName);
    }
    
    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        studentDao.addStudentToCourse(studentId, courseId);
    }
    
    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        studentDao.removeStudentFromCourse(studentId, courseId);
    }    
    
    @Override
    public Set<Course> getCourses(int studentId) {
        return studentDao.getCoursesRelatedStudent(studentId, courseDao);
    }
}
