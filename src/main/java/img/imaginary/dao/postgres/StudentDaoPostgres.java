package img.imaginary.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import img.imaginary.dao.CourseDao;
import img.imaginary.dao.GenericDAO;
import img.imaginary.dao.PreparedStatementSetter;
import img.imaginary.dao.StudentDao;
import img.imaginary.dao.mapper.RowMapper;
import img.imaginary.dao.mapper.StudentRowMapper;
import img.imaginary.exception.DaoException;
import img.imaginary.service.entity.Course;
import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;
import img.imaginary.service.entity.StudentToCourse;

public class StudentDaoPostgres extends GenericDAO<Student> implements StudentDao {

    private static final String ADD_STUDENT_QUERY = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
        
    private RowMapper<Student> rowMapper = new StudentRowMapper();
      
    public StudentDaoPostgres(ConnectionPool connectionPool, PreparedStatementSetter statementSetter) {
        super(connectionPool, statementSetter);
    }

    @Override
    public int addNewStudent(Student student) {
        int id = addNew(student, ADD_STUDENT_QUERY);
        student.setStudentId(id);
        return id;
    }
    
    @Override
    public void addToGroup(Student student, Group group) {
        String query = "INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, group.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException("student can't be added", e);
        }
    }
    
    @Override
    public void setGroup(Student student, Group group) {
        String query = "UPDATE students SET group_id = ? WHERE student_id = ?";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, group.getGroupId());
            statement.setInt(2, student.getStudentId());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoException("student can't be updated", e);
        }
    }
    
    @Override
    public void setStudentsToGroup(Set<Student> students, int groupId) {
        String query = "UPDATE students SET group_id = ? WHERE student_id = ?";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            for (Student student : students) {
                statement.setInt(1, groupId);
                statement.setInt(2, student.getStudentId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("student can't be updated", e);
        }
    }
    
    @Override
    public void addAllStudents(Set<Student> students) {
        List<Integer> keys = addAll(students, ADD_STUDENT_QUERY);
        int i = 0;
        for (Student student : students) {
            student.setStudentId(keys.get(i));
            i++;
        }
    }

    @Override
    public void deleteStudentById(int studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        deleteByfield(query, studentId);
    }

    @Override
    public Student findById(int id) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        Student student = findByfield(query, id, rowMapper).iterator().next();
        student.setStudentId(id);
        return student;
    }
    
    @Override
    public Set<Student> findStudentsRelatedCourse(String courseName) {
        String query = String.join(System.lineSeparator(),
               "SELECT std.student_id, std.first_name, std.last_name, std.group_id",
               "FROM students AS std",
               "JOIN courses_students AS c_s ON std.student_id = c_s.student_id",
               "JOIN courses AS cr ON cr.course_id = c_s.course_id",
                "WHERE course_name = ?;");
        return findByfield(query, courseName, rowMapper);
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        String query = "INSERT INTO courses_students VALUES (?, ?)";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException("Student can't be added to Course", e);
        }
    }
    
    @Override
    public void addBatchStudentsToCourse(Set<StudentToCourse> studentsToCourses) {
        String query = "INSERT INTO courses_students VALUES (?, ?)";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            for (StudentToCourse studentCourse: studentsToCourses) {
                statement.setInt(1, studentCourse.getStudentId());
                statement.setInt(2, studentCourse.getCourseId());
                statement.addBatch();
            }   
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("Student can't be added to Course", e);
        }
    }
    
    @Override
    public Set<Course> getCoursesRelatedStudent(int studentId, CourseDao courseDao) {
        return courseDao.getCoursesByStudent(studentId);
    }
    
    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        String query = "DELETE FROM courses_students WHERE student_id = ? AND course_id = ?";
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate(); 
        } catch (SQLException e) {
            throw new DaoException("StudentToCourse can't be added removed", e);
        }
    }

    @Override
    protected void mapFromEntity(PreparedStatement statement, Student entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());        
    }
}
