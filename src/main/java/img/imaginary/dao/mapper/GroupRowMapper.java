package img.imaginary.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import img.imaginary.service.entity.Group;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet) throws SQLException {
        int groupId = resultSet.getInt("group_id");
        String groupName = resultSet.getString("group_name");
        return new Group(groupId, groupName);
    }
}
