package img.imaginary.service;

public abstract class ServiceFactory {

    public abstract CourseService getCourseService();

    public abstract GroupService getGroupService();

    public abstract StudentService getStudentService();

}
