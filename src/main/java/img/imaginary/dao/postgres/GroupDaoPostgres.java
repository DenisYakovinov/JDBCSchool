package img.imaginary.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import img.imaginary.dao.GenericDAO;
import img.imaginary.dao.GroupDao;
import img.imaginary.dao.PreparedStatementSetter;
import img.imaginary.dao.StudentDao;
import img.imaginary.dao.mapper.GroupRowMapper;
import img.imaginary.dao.mapper.RowMapper;
import img.imaginary.exception.DaoException;
import img.imaginary.service.entity.Group;
import img.imaginary.service.entity.Student;

public class GroupDaoPostgres extends GenericDAO<Group> implements GroupDao {

    private static final String ADD_GROUP_QUERY = "INSERT INTO groups (group_name) VALUES (?)";
    
    private RowMapper<Group> rowMapper = new GroupRowMapper();
    
    public GroupDaoPostgres(ConnectionPool connectionPool, PreparedStatementSetter statementSetter) {
        super(connectionPool, statementSetter);
    }
    
    @Override
    public void addNewGroup(Group group) {
        int id = addNew(group, ADD_GROUP_QUERY);
        group.setGroupId(id);
    }

    @Override
    public void addAllGroups(Set<Group> groups, StudentDao studentDao) {
        List<Integer> keys = addAll(groups, ADD_GROUP_QUERY);
        int i = 0;
        for (Group group : groups) {
            group.setGroupId(keys.get(i));
            i++;
            Set<Student> students = group.getStudents();
            if (students != null && !students.isEmpty()) {
                studentDao.setStudentsToGroup(students, group.getGroupId());
            }
        }
    }

    @Override
    public Set<Group> findGroupsWithLessOrEqualsStudents(int studentsCount) {
        String query;
        if (studentsCount == 0) {
            query = String.join(System.lineSeparator(),
                    "SELECT  g.group_id, g.group_name FROM students AS s",
                    "RIGHT JOIN groups AS g ON s.group_id = g.group_id",
                    "WHERE s.student_id IS NULL;");      
        } else {             
            query = String.join(System.lineSeparator(),
                    "SELECT s.group_id, g.group_name, COUNT(*)",
                    "FROM students AS s",
                    "JOIN groups AS g ON s.group_id = g.group_id",
                    "GROUP BY s.group_id, g.group_name",
                    "HAVING COUNT(*) <= ",
                    studentsCount + ";");
        }        
        try (Connection connection = connectionPool.getConnection()) {
            Set<Group> groups = findAll(query, rowMapper);
            groups.forEach(x -> getStudents(x, connection));
            return groups;
        } catch (SQLException e) {
            throw new DaoException("Groups with less or equals students can't be found", e);
        }
    }
        
    @Override
    public Group getGroupById(int id) {
        String query = "SELECT group_id, group_name FROM groups WHERE group_id = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            Group group = findByfield(query, id, rowMapper).iterator().next();
            getStudents(group, connection);
            return group;
        } catch (SQLException e) {
            throw new DaoException("Group with specified id can't be found", e);
        }
    }
    
    private void getStudents(Group group, Connection connection) {
        String query = String.join(System.lineSeparator(),
                       "SELECT st.student_id, st.first_name, st.last_name,",
                       "gr.group_id, gr.group_name FROM students",
                       "AS st JOIN groups AS gr ON st.group_id = gr.group_id",
                       "WHERE gr.group_id =?;");           
        int id = group.getGroupId();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                group.addStudent(new Student(resultSet.getInt("student_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            throw new DaoException("can't retrieve a group from data base", e);
        }
    }    

    @Override
    protected void mapFromEntity(PreparedStatement statement, Group entity) throws SQLException {
        statement.setString(1, entity.getGroupName());
    }
}

