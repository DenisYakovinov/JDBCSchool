package edu.foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import img.imaginary.dao.DaoFactoriesType;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.DefaultStatemetSetter;
import img.imaginary.dao.GroupDao;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.input.SQLExecutorUtil;
import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;

class GroupDaoPostgresTest {

    private static GroupDao groupDao;

    @BeforeAll
    static void createDataBase() {
        ConnectionPool connectionPool = new ConnectionPool("daoH2.properties");
        DaoFactory daoFactory = DaoFactoriesType.POSTGRES.get(connectionPool, new DefaultStatemetSetter());
        SQLExecutorUtil.execute("creationTables.sql", connectionPool);
        SQLExecutorUtil.execute("insertTestStudents.sql", connectionPool);
        groupDao = daoFactory.getGroupDao();
    }

    @Test
    void findGroupsWithLessOrEqualsStudents_ShouldReturnGroups() {
        Set<Group> expected = new HashSet<>();
        Group firstGroup = new Group(2, "gg-75");
        firstGroup.addStudent(new Student(4,  "Andrew", "Parsons"));
        firstGroup.addStudent(new Student(5,  "Bylly", "Priston"));
        Group secondGroup = new Group(3, "zz-21");
        secondGroup.addStudent(new Student(6,  "Ivan", "Petorv"));
        secondGroup.addStudent(new Student(7,  "Jimmy", "Vinchetto"));
        Group thirdGroup = new Group(4, "cc-69");
        thirdGroup.addStudent(new Student(8,  "Roman", "Avdeev"));
        expected.add(firstGroup);
        expected.add(secondGroup);
        expected.add(thirdGroup);
        Set<Group> actual = groupDao.findGroupsWithLessOrEqualsStudents(2);
        assertEquals(expected, actual);
    }
    
    @Test
    void getGroupById_ShouldReturnGroup_WhenGroupId() {
        Group expected = new Group(3, "zz-21");
        expected.addStudent(new Student(6,  "Ivan", "Petorv"));
        expected.addStudent(new Student(7,  "Jimmy", "Vinchetto"));
        Group actual = groupDao.getGroupById(3);
        assertEquals(expected, actual);
    }
}
