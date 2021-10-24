package img.imaginary.service.randomizing;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;

public class GroupsSupplier implements DataSupplier<Group> {

    private static final Random random = new Random();
    
    private final Set<Student> students;
    
    public GroupsSupplier(Set<Student> students) {
        this.students = students;
    }
    
    public GroupsSupplier() {
        students = new HashSet<>();
    }

    @Override
    public Set<Group> supplyData() {

        Set<Group> groups = new HashSet<>();
        while (groups.size() < 10) {
            char randomChar = (char) ('a' + random.nextInt(25));
            String groupName = "" + randomChar + randomChar + "-" + random.nextInt(10) + random.nextInt(10);
            groups.add(new Group(groupName));
        }
        
        if(!students.isEmpty()) {
            groups = new StudentToGroupAdder().addStudentsToGroups(students, groups);
        }
        return groups;
    }   
}
