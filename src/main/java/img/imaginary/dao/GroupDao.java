package img.imaginary.dao;

import java.util.Set;

import img.imaginary.service.entity.Group;

public interface GroupDao {
    
    void addNewGroup(Group group);
    
    void addAllGroups(Set<Group> groups, StudentDao studentDao);
    
    Set<Group> findGroupsWithLessOrEqualsStudents(int studentsCount);
        
    Group getGroupById(int id);
}
