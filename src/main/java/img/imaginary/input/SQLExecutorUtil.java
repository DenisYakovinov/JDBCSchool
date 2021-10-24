package img.imaginary.input;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.exception.DaoException;

public class SQLExecutorUtil {

    private SQLExecutorUtil() {
        
    }
    
    public static void execute(String sqlfileName, ConnectionPool connectionPool) {
        String query = buildSQLQuery(sqlfileName);
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new DaoException("sql query can't be executed", e);
        }
    }

    private static String buildSQLQuery(String fileName) {
        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.readFile(fileName);
        return String.join(System.lineSeparator(), lines);
    }
}
