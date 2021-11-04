package edu.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.DaoFactoriesType;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.DefaultStatemetSetter;
import img.imaginary.dao.StudentDao;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.input.SQLExecutor;
import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Student;

@ExtendWith(MockitoExtension.class)
class StudentDaoPostgresTest {

    private static StudentDao studentDao;

    @Mock
    CourseDao courseDao;

    @BeforeAll
    static void createDataBase() {
        ConnectionPool connectionPool = new ConnectionPool("daoH2.properties");
        DaoFactory daoFactory = DaoFactoriesType.POSTGRES.get(connectionPool, new DefaultStatemetSetter());
        SQLExecutor executor = new SQLExecutor(connectionPool);
        executor.execute("creationTables.sql");
        executor.execute("insertTestStudents.sql");
        studentDao = daoFactory.getStudentDao();
    }

    @Test
    void findById_ShouldReturnStudentWithSpecifiedId_WhenStudentId() {
        Student expected = new Student(1, "Jonh", "Doe");
        Student actual = studentDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void findStudentsRelatedCourse_ShouldReturnStudentsRelatedCourse_WnenCourseName() {
        Set<Student> expected = new HashSet<>();
        expected.add(new Student(1, "Jonh", "Doe"));
        expected.add(new Student(2, "Winston", "Smith"));
        expected.add(new Student(3, "Stenly", "Obrien"));
        Set<Student> actual = studentDao.findStudentsRelatedCourse("Math");
        assertEquals(expected, actual);
    }

    @Test
    void getCoursesRelatedStudent_ShoudReturnCoursesRelatedStudentId_WhenStudentId() {
        Set<Course> expected = new HashSet<>();
        expected.add(new Course(1, "Math", "Base Course"));
        expected.add(new Course(2, "Biology", "Base Course"));
        expected.add(new Course(3, "Economic", "Base Course"));
        Mockito.when(courseDao.getCoursesByStudent(1)).thenReturn(expected);
        Set<Course> actual = studentDao.getCoursesRelatedStudent(1, courseDao);
        assertEquals(expected, actual);
    }
}
