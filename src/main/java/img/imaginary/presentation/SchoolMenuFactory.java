package img.imaginary.presentation;

import img.imaginary.service.CourseService;
import img.imaginary.service.GroupService;
import img.imaginary.service.ServiceFactory;
import img.imaginary.service.StudentService;

public class SchoolMenuFactory {
    
    private final ServiceFactory serviceFactory;        
    private final MenuBuilder builder;
    
    public SchoolMenuFactory(ServiceFactory serviceFactory, MenuBuilder builder) {
        this.serviceFactory = serviceFactory;
        this.builder = builder;
    }
    
    public Menu buildSchoolMenu() {
        StudentService studentService = serviceFactory.getStudentService();
        CourseService courseService = serviceFactory.getCourseService();
        GroupService groupService = serviceFactory.getGroupService();
        return builder.subMenu("students actions: ")
                      .addItem(new AddStudentMenu(studentService))
                      .addItem(new DeleteStudentMenu(studentService)) 
                      .endMenu()
                      .subMenu("course related actions: ")
                      .addItem(new StudentsRelatedCourseMenu(studentService, courseService))
                      .addItem(new RemoveStudentFromCourseMenu(studentService))
                      .addItem(new AddStudentToCourseMenu(studentService, courseService))
                      .endMenu()
                      .subMenu("group related actions: ")
                      .addItem(new FindGroupsByStudentMenu(groupService))
                      .endMenu()
                      .build();       
    }
}
