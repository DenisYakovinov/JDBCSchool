package img.imaginary.service;

import img.imaginary.dao.DaoFactory;

public class ServiceFactoryDefault extends ServiceFactory {

    DaoFactory daoFactory;

    public ServiceFactoryDefault(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public CourseService getCourseService() {
        return new CourseServiceDefault(daoFactory);
    }

    @Override
    public GroupService getGroupService() {
        return new GroupServiceDefault(daoFactory);
    }

    @Override
    public StudentService getStudentService() {
        return new StudentServicePostgres(daoFactory);
    }
}
