package img.imaginary.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class PreparedStatementSetter {

    private final Map<Class<?>, Setter<Object>> typesToSetters;

    protected PreparedStatementSetter() {
        this.typesToSetters = new HashMap<>();
    }

    public void setParameters(PreparedStatement statement, int index, Object value) throws SQLException {
        Optional.ofNullable(typesToSetters.get(value.getClass())).orElseGet(() -> getDefaultSetter(value))
                .set(statement, index, value);
    }

    protected final <T, R> void addSetter(Class<T> clazz, Setter<R> setter, Function<T, R> mapper) {
        typesToSetters.put(clazz, (statement, index, value) -> setter.set(statement, index, mapper.apply((T) value)));
    }

    protected final <T> void addSetter(Class<T> clazz, Setter<T> setter) {
        addSetter(clazz, setter, Function.identity());
    }

    protected final void addSetter(Class<?> clazz, Function<Object, Object> mapper) {
        typesToSetters.put(clazz, (statement, index, value) -> statement.setObject(index, mapper.apply(value)));
    }

    private static Setter<Object> getDefaultSetter(Object value) {
        return PreparedStatement::setObject;
    }

    @FunctionalInterface
    protected interface Setter<T> {
        void set(PreparedStatement statement, int index, T value) throws SQLException;
    }
}
