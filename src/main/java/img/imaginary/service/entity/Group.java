package img.imaginary.service.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Group {

    private int groupId;
    private String groupName;
    private Set<Student> students = new HashSet<>();

    public Group(String groupName) {
        this(0, groupName, new HashSet<>());
    }

    public Group(int groupId, String groupName) {
        this(groupId, groupName, new HashSet<>());
    }
    
    public Group(int groupId, String groupName, Set<Student> students) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.students.addAll(students);
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, students);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        Group other = (Group) obj;
        return groupId == other.groupId && Objects.equals(groupName, other.groupName)
                && Objects.equals(students, other.students);
    }

    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", groupName=" + groupName + ", students=" + students + "]";
    }  
}
