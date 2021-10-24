package img.imaginary.service;

import java.util.Set;

import img.imaginary.service.entity.Group;

public interface GroupService {

    void addAllGroups(Set<Group> groups);

    Set<Group> findGroupsWithLessOrEqualsStudents(int studentsCount);
}
