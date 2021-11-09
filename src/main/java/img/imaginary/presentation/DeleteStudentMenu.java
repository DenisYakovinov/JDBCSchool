package img.imaginary.presentation;

import img.imaginary.service.StudentService;

public class DeleteStudentMenu extends MenuReader implements Menu {

    private String descrition = "Delete student";
    private final StudentService studentService;

    public DeleteStudentMenu(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String getDescription() {
        return descrition;
    }

    @Override
    public void execute() {
        int studentId = readInt("To delete a student input his id");
        studentService.deleteStudentById(studentId);
        out.println("The student has been deleted");
    }
}
