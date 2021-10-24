package img.imaginary.dao;

import java.sql.PreparedStatement;
import java.util.function.Function;

public class DefaultStatemetSetter extends PreparedStatementSetter {

    public DefaultStatemetSetter() {
        super();
        addSetter(String.class, PreparedStatement::setString, Function.identity());
        addSetter(Integer.class, PreparedStatement::setInt, Function.identity());
    }
}
