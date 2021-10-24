package img.imaginary.service.randomizing;

import java.util.HashSet;
import java.util.Set;

import img.imaginary.service.entity.Course;

public class CourseSupplier implements DataSupplier<Course> {

    @Override
    public Set<Course> supplyData() {
        HashSet<Course> courses = new HashSet<>();
        courses.add(new Course("math", "beginner mathematics course"));
        courses.add(new Course("biology", "human biology and anatomy"));
        courses.add(new Course("chemistry", "initial chemistry  "));
        courses.add(new Course("informatics", "general course"));
        courses.add(new Course("physics", "basic knowledge of mechanics"));
        courses.add(new Course("geometry", "extended course"));
        courses.add(new Course("geography", "geography initial"));
        courses.add(new Course("history", "history of the state"));
        courses.add(new Course("economy", "economy since ancient times"));
        courses.add(new Course("literature", "world literature"));
        return courses;
    }
}
