package img.imaginary.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import img.imaginary.service.entity.Student;

public class StudentRowMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet resultSet) throws SQLException {
        int studentId = resultSet.getInt("student_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new Student(studentId, firstName, lastName);
    }
}
