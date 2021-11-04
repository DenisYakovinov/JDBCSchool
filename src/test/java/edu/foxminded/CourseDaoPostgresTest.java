package edu.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.DaoFactoriesType;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.DefaultStatemetSetter;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.input.SQLExecutor;
import img.imaginary.service.entity.Course;

class CourseDaoPostgresTest {

    private static CourseDao courseDao;

    @BeforeAll
    static void createDataBase() {
        ConnectionPool connectionPool = new ConnectionPool("daoH2.properties");
        DaoFactory daoFactory = DaoFactoriesType.POSTGRES.get(connectionPool, new DefaultStatemetSetter());
        SQLExecutor executor = new SQLExecutor(connectionPool);
        executor.execute("creationTables.sql");
        executor.execute("insertTestStudents.sql");
        courseDao = daoFactory.getCourseDao();
    };

    @Test
    void getAllCourses_ShouldReturnAllCourses() {
        Set<Course> expected = new HashSet<>();
        expected.add(new Course(1, "Math", "Base Course"));
        expected.add(new Course(2, "Biology", "Base Course"));
        expected.add(new Course(3, "Economic", "Base Course"));
        expected.add(new Course(4, "History", "Base Course"));
        Set<Course> actual = courseDao.getAllCourses();
        assertEquals(expected, actual);
    }
    
    @Test
    void getCoursesByStudent_ShouldReturnCoursesLinkedWithStudentId_WhenStudentId() {
        Set<Course> expected = new HashSet<>();
        expected.add(new Course(1, "Math", "Base Course"));
        expected.add(new Course(2, "Biology", "Base Course"));
        expected.add(new Course(3, "Economic", "Base Course"));
        Set<Course> actual = courseDao.getCoursesByStudent(1);
        assertEquals(expected, actual);
    }
}
