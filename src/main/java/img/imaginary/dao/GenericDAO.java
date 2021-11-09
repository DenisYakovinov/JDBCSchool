package img.imaginary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import img.imaginary.dao.mapper.RowMapper;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.exception.DaoException;

public abstract class GenericDAO<T> {
    
    protected ConnectionPool connectionPool;
    
    private final PreparedStatementSetter statementSetter;
    
    protected GenericDAO(ConnectionPool connectionPool, PreparedStatementSetter statementSetter) {
        this.connectionPool = connectionPool;
        this.statementSetter = statementSetter;
    }
    
    
    protected int addNew(T entity, String query) {
        int entityID = 0;
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            mapFromEntity(statement, entity);
            if (statement.executeUpdate() > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next())
                    entityID = keys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("entity hasn't been added", e);
        }
        return entityID;
    }
    
    protected List<Integer> addAll(Set<T> entities, String query) {
        try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (T entity : entities) {
                mapFromEntity(statement, entity);
                statement.addBatch();
            }
            statement.executeBatch();
            ResultSet resultKeys = statement.getGeneratedKeys();
            List<Integer> keys = new ArrayList<>();
            while(resultKeys.next()) {
                keys.add(resultKeys.getInt(1));
            }
            return keys;
        } catch (Exception e) {
            throw new DaoException("entities have't been added", e);
        }
    }

    protected Set<T> findAll(String query, RowMapper<T> rowMapper) {
        Set<T> entities = new HashSet<>();
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("entity hasn't been found", e);
        }
        return entities;
    }
    
    protected <V> Set<T> findByfield(String query, V value, RowMapper<T> rowMapper) {
        Set<T> entities = new HashSet<>();
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.setParameters(statement, 1, value);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("entities hasn't been found", e);
        }
        return entities;
    }

    protected <V> void deleteByfield(String query, V value) {
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.setParameters(statement, 1, value);
            if (statement.executeUpdate() == 0)
                throw new SQLException("zero returned");
        } catch (SQLException e) {
            throw new DaoException("entity hasn't been deleted", e);
        }
    }

    protected <V> void updateByField(String query, T entity, int parameterIndex, V value) {
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statementSetter.setParameters(statement, 1, value);
            mapFromEntity(statement, entity);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("entity hasn't been updated");
            }
        } catch (SQLException e) {
            throw new DaoException("", e);
        }
    }

    protected abstract void mapFromEntity(PreparedStatement statement, T entity) throws SQLException;
}
