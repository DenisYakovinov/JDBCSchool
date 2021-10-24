package img.imaginary.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import img.imaginary.service.entity.Course;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet) throws SQLException {
        return new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
                resultSet.getString("course_description"));
    }
}
