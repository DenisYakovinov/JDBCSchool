package img.imaginary.service.randomizing;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import img.imaginary.service.entity.StudentToCourse;

public class StudentToCourseSupplier implements DataSupplier<StudentToCourse> {

    private static final Random random = new Random();
    private static final int STUDENTS_QUANTITY = 200;
    private static final int COURSE_QUANTITY = 10;

    @Override
    public Set<StudentToCourse> supplyData() {
        Set<StudentToCourse> studentsToCourses = new HashSet<>();
        for (int i = 1; i < STUDENTS_QUANTITY + 1; i++) {
            int courseQuantity = random.nextInt(3) + 1;
            Set<Integer> duplicatesCourses = new HashSet<>();
            for (int k = 0; k < courseQuantity; k++) {
                int courseId = random.nextInt(COURSE_QUANTITY) + 1;
                while (duplicatesCourses.contains(courseId)) {
                    courseId = random.nextInt(COURSE_QUANTITY) + 1; 
                }                    
                studentsToCourses.add(new StudentToCourse(i, courseId));
                duplicatesCourses.add(courseId);
            }
        }
        return studentsToCourses;
    }
}
