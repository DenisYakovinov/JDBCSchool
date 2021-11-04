package img.imaginary;

import java.util.Set;

import img.imaginary.presentation.MenuBuilder;
import img.imaginary.presentation.MenuContainer;
import img.imaginary.dao.DaoFactoriesType;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.DefaultStatemetSetter;
import img.imaginary.dao.StudentDao;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.input.SQLExecutor;
import img.imaginary.presentation.SchoolMenuFactory;
import img.imaginary.service.ServiceFactory;
import img.imaginary.service.ServiceFactoryDefault;
import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;
import img.imaginary.service.entity.StudentToCourse;
import img.imaginary.service.randomizing.CourseSupplier;
import img.imaginary.service.randomizing.GroupsSupplier;
import img.imaginary.service.randomizing.StudentSupplier;
import img.imaginary.service.randomizing.StudentToCourseSupplier;

public class SchoolStarter {

    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool("dao.properties");
        SQLExecutor executor = new SQLExecutor(connectionPool);
        executor.execute("creationTables.sql");
        DaoFactory daoFactory = DaoFactoriesType.POSTGRES.get(connectionPool, new DefaultStatemetSetter());
        Set<Student> students = new StudentSupplier().supplyData();
        daoFactory.getStudentDao().addAllStudents(students);
        Set<Group> groups = new GroupsSupplier(students).supplyData();
        Set<Course> courses = new CourseSupplier().supplyData();
        Set<StudentToCourse> studentsToCourses = new StudentToCourseSupplier().supplyData();
        StudentDao studentDao = daoFactory.getStudentDao();
        daoFactory.getGroupDao().addAllGroups(groups, studentDao);
        daoFactory.getCourseDao().addAllCourses(courses);
        studentDao.addBatchStudentsToCourse(studentsToCourses);
        ServiceFactory serviceFactory = new ServiceFactoryDefault(daoFactory);
        SchoolMenuFactory menuFactory = new SchoolMenuFactory(serviceFactory,
                new MenuBuilder(new MenuContainer("task 7 jdbc")));
        menuFactory.buildSchoolMenu().execute();
    }
}
