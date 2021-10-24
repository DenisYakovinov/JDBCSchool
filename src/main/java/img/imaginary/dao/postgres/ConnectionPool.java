package img.imaginary.dao.postgres;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import img.imaginary.exception.DaoException;
import img.imaginary.input.ConfigReader;

public class ConnectionPool {

    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_CLASS_NAME = "db.class";

    private final BasicDataSource dataSource;
    private final ConfigReader configReader;

    public ConnectionPool(String configFile) {
        configReader = new ConfigReader(configFile);
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(configReader.getProperty(DB_CLASS_NAME));
        dataSource.setUrl(configReader.getProperty(DB_URL));
        dataSource.setUsername(configReader.getProperty(DB_LOGIN));
        dataSource.setPassword(configReader.getProperty(DB_PASSWORD));
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DaoException("database connection wasn't established", e);
        }
    }

}
