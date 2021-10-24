package img.imaginary.presentation;

import java.util.Scanner;

import img.imaginary.service.CourseService;
import img.imaginary.service.GroupService;
import img.imaginary.service.ServiceFactory;
import img.imaginary.service.StudentService;
import img.imaginary.service.entity.Student;

public class UserCommand {

    private  CourseService courseService;
    private  GroupService groupService;
    private  StudentService studentService;

    private Scanner scanner = new Scanner(System.in);

    public UserCommand(ServiceFactory serviceFactory) {
        groupService = serviceFactory.getGroupService();
        courseService = serviceFactory.getCourseService();
        studentService = serviceFactory.getStudentService();        
    }

    public void executeInterface() {
        System.out.println("task 7 jdbc school\n"
                + "\n"
                + "choose a command and enter its number:\n"
                + "\n"
                + "1.Find all groups with less or equals student count\n"
                + "\n"
                + "2.Find all students related to course with given name\n"
                + "\n"
                + "3.Add new student\n"
                + "\n"
                + "4.Delete student by STUDENT_ID\n"
                + "\n"
                + "5.Add a student to the course (from a list)\n"
                + "\n"
                + "6.Remove the student from one of his or her courses\n");
        boolean dontExit = true;
        while (dontExit) {
            System.out.print("input number: ");
            String command = scanner.nextLine();
            switch (command) {
            case "1":
                System.out.print("input students count: ");
                int studentsCount = scanner.nextInt();
                groupService.findGroupsWithLessOrEqualsStudents(studentsCount).forEach(System.out::println);
                break;
            case "2":
                System.out.print("input course name from list below:\n");
                courseService.getAllCourses().forEach(System.out::println);
                System.out.print("input course name: ");
                String courseName = scanner.nextLine();
                System.out.println();
                studentService.findStudentsRelatedCourse(courseName).forEach(System.out::println);
                break;
            case "3":
                System.out.print("input first name: ");
                String firstName = scanner.nextLine();
                System.out.print("input last name: ");
                String lastName = scanner.nextLine();
                studentService.addNewStudent(new Student(firstName, lastName));
                System.out.print("new student was added");
                break;
            case "4":
                System.out.print("To delete student input his id: ");
                int studentId = scanner.nextInt();
                studentService.deleteStudentById(studentId);
                System.out.print("The student has been deleted");
                break;
            case "5":
                System.out.print("input course id from list below:\n");
                courseService.getAllCourses().forEach(System.out::println);
                System.out.print("input course name: ");
                int courseId = scanner.nextInt();
                System.out.println("courseId: " + courseId);
                System.out.print("input student id ");
                studentId = scanner.nextInt();
                System.out.print("student id: " + studentId);
                studentService.addStudentToCourse(studentId, courseId);
                System.out.print("The student has been added to the specified course");
                break;
            case "6":
                System.out.print("input student id ");
                studentId = scanner.nextInt();
                System.out.print("all courses for the specified student id:\n");
                studentService.getCourses(studentId).forEach(x -> System.out.println(x.getCourseId()));
                System.out.print("input course id for remove ");
                courseId = scanner.nextInt();
                studentService.removeStudentFromCourse(studentId, courseId);
                System.out.print("The student was removed from the specified course");
                break;
            default:
                System.out.print("incorrect input try again\n");
            }
            System.out.println("\nfor exit press E");
            if (scanner.nextLine().equals("E")) {
                dontExit = false;
            }
        }
    }
}
