package img.imaginary.service;

import java.util.Set;

import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.GroupDao;
import img.imaginary.dao.StudentDao;
import img.imaginary.service.entity.Group;

public class GroupServiceDefault implements GroupService {

    private GroupDao groupDao;
    private StudentDao studentDao;
    
    public GroupServiceDefault(DaoFactory daoFactory) {
        groupDao = daoFactory.getGroupDao();
        studentDao = daoFactory.getStudentDao();
    }

    @Override
    public void addAllGroups(Set<Group> groups) {
        groupDao.addAllGroups(groups, studentDao);
    }

    @Override
    public Set<Group> findGroupsWithLessOrEqualsStudents(int studentsCount) {
        return groupDao.findGroupsWithLessOrEqualsStudents(studentsCount);
    }
}
