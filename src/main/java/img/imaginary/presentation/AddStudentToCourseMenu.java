package img.imaginary.presentation;

import img.imaginary.service.CourseService;
import img.imaginary.service.StudentService;

public class AddStudentToCourseMenu extends MenuItem implements Menu {

    private String descrition = "Add a student to the course (from a list)";
    private StudentService studentService;
    private CourseService courseService;

    public AddStudentToCourseMenu(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public String getDescription() {
        return descrition;
    }

    @Override
    public void execute() {
        out.println("input course id from list below");
        courseService.getAllCourses().forEach(out::println);
        int courseId = readInt("input course id");
        out.println("courseId: " + courseId);
        int studentId = readInt("input student id: ");
        out.println("student id: \n" + studentId);
        studentService.addStudentToCourse(studentId, courseId);
        out.println("The student has been added to the specified course\n");
    }
}
