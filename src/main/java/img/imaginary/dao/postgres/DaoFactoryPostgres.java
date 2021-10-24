package img.imaginary.dao.postgres;
import img.imaginary.dao.CourseDao;
import img.imaginary.dao.DaoFactory;
import img.imaginary.dao.GroupDao;
import img.imaginary.dao.PreparedStatementSetter;
import img.imaginary.dao.StudentDao;

public class DaoFactoryPostgres implements DaoFactory {

    private ConnectionPool connectionPool;
    private PreparedStatementSetter statementSetter;
    
    public DaoFactoryPostgres(ConnectionPool connectionPool, PreparedStatementSetter statementSetter) {
        this.connectionPool = connectionPool;
        this.statementSetter = statementSetter;
    }
    
    @Override
    public CourseDao getCourseDao() {
        return new CourseDaoPostgres(connectionPool, statementSetter);
    }

    @Override
    public GroupDao getGroupDao() {
        return new GroupDaoPostgres(connectionPool, statementSetter);
    }

    @Override
    public StudentDao getStudentDao() {
        return new StudentDaoPostgres(connectionPool, statementSetter);
    }
}
