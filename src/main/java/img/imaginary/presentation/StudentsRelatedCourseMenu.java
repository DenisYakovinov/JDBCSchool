package img.imaginary.presentation;

import img.imaginary.service.CourseService;
import img.imaginary.service.StudentService;

public class StudentsRelatedCourseMenu extends MenuItem implements Menu {

    private String descrition = "Find all students related to a course";
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentsRelatedCourseMenu(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public String getDescription() {
        return descrition;
    }

    @Override
    public void execute() {
        out.println("input course name from list below:");
        courseService.getAllCourses().forEach(out::println);
        String courseName = readString("input course name");
        out.println("There are:");
        studentService.findStudentsRelatedCourse(courseName).forEach(out::println);
    }
}
