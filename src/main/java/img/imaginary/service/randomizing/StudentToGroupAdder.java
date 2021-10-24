package img.imaginary.service.randomizing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;

public class StudentToGroupAdder {

    private static final Random random = new Random();
    private static final int GROUPS_QUANTITY = 10;
    private static final int STUDENTS_QUANTITY = 200;

    public Set<Group> addStudentsToGroups(Set<Student> studentsSet, Set<Group> groups) {
        List<Student> students = new ArrayList<>(studentsSet);
        List<Group> groupsCopy = new ArrayList<>(groups);
        int studentsQuantity = STUDENTS_QUANTITY;
        for (int i = 0; i < GROUPS_QUANTITY; i++) {
            int numberInGroup = random.nextInt(31);
            if (numberInGroup < GROUPS_QUANTITY && numberInGroup != 0) {
                numberInGroup = numberInGroup + (GROUPS_QUANTITY - numberInGroup);
            }
            if (numberInGroup == 0) {
                continue;
            }
            if (numberInGroup > studentsQuantity) {
                numberInGroup = studentsQuantity;
            }
            for (int k = 0; k < numberInGroup; k++) {
                int studentIndex = random.nextInt(studentsQuantity);
                Student student = students.get(studentIndex);
                groupsCopy.get(i).addStudent(student);
                students.remove(studentIndex);
                studentsQuantity--;
            }
        }
        return new HashSet<>(groupsCopy);
    }
}
