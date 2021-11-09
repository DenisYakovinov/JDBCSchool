package img.imaginary.presentation;

import img.imaginary.service.StudentService;
import img.imaginary.service.entity.Student;

public class AddStudentMenu extends MenuReader implements Menu {

    private String descrition = "Add student";
    private StudentService studentService;
    
    public AddStudentMenu(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @Override
    public String getDescription() {
        return descrition; 
    }
    
    @Override
    public void execute() {
        String firstName = readString("input first name");
        String lastName = readString("input last name");
        studentService.addNewStudent(new Student(firstName, lastName));
        out.println("new student was added");
    }
}
