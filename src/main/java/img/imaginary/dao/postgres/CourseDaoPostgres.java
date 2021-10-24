package img.imaginary.dao.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.GenericDAO;
import img.imaginary.dao.PreparedStatementSetter;
import img.imaginary.dao.mapper.CourseRowMapper;
import img.imaginary.dao.mapper.RowMapper;
import img.imaginary.service.entity.Course;

public class CourseDaoPostgres extends GenericDAO<Course> implements CourseDao {

    private static final String ADD_COURSE_QUERY = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";

    private RowMapper<Course> rowMapper = new CourseRowMapper();
        
    public CourseDaoPostgres(ConnectionPool connectionPool, PreparedStatementSetter statementSetter) {
        super(connectionPool, statementSetter);
    }

    @Override
    public void addNewCourse(Course course) {
        int id = addNew(course, ADD_COURSE_QUERY);
        course.setCourseId(id);
    }
    
    @Override
    public void addAllCourses(Set<Course> courses) {
        addAll(courses, ADD_COURSE_QUERY);
    }

    @Override
    public Set<Course> getAllCourses() {
        String query = "SELECT * FROM courses ORDER BY courses.course_id;";
        return findAll(query, rowMapper);
    }

    @Override
    protected void mapFromEntity(PreparedStatement statement, Course entity) throws SQLException {
        statement.setString(1, entity.getCourseName());
        statement.setString(2, entity.getCourseDescription());
    }
    
    @Override
    public Set<Course> getCoursesByStudent(int studentId) {
        String query = String.join(System.lineSeparator(),
                "SELECT cr.course_id, cr.course_name, cr.course_description",
                "FROM students AS std",
                "JOIN courses_students AS c_s ON std.student_id = c_s.student_id",
                "JOIN courses AS cr ON c_s.course_id = cr.course_id",
                "WHERE c_s.student_id = ?;");
        return findByfield(query, studentId, new CourseRowMapper());
    }
}
