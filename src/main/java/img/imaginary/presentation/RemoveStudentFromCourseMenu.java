package img.imaginary.presentation;

import img.imaginary.service.StudentService;

public class RemoveStudentFromCourseMenu extends MenuReader implements Menu {

    private String descrition = "Remove a student from one of his or her courses";
    private final StudentService studentService;

    public RemoveStudentFromCourseMenu(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String getDescription() {
        return descrition;
    }

    @Override
    public void execute() {
        int studentId = readInt("input student id");
        out.println("all courses for the specified student id:");
        studentService.getCourses(studentId).forEach(x -> out.println(x.getCourseId()));
        int courseId = readInt("input course id for remove");
        studentService.removeStudentFromCourse(studentId, courseId);
        out.print("The student was removed from the specified course\n");
    }
}
