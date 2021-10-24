package img.imaginary.dao;

public interface DaoFactory {

    public CourseDao getCourseDao();

    public GroupDao getGroupDao();

    public StudentDao getStudentDao();
}
