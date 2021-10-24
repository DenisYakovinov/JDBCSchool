package img.imaginary.dao;

import img.imaginary.dao.postgres.ConnectionPool;
import img.imaginary.dao.postgres.DaoFactoryPostgres;

public enum DaoFactoriesType {
   
    POSTGRES(DaoFactoryPostgres::new);
       
    private FactoryFunction<ConnectionPool, DaoFactory, PreparedStatementSetter> factoryFunction;
    
    private DaoFactoriesType(FactoryFunction<ConnectionPool, DaoFactory, PreparedStatementSetter> factoryFunction) {
        this.factoryFunction = factoryFunction;
    }
    
    public DaoFactory get(ConnectionPool connPool, PreparedStatementSetter setter) {
        if (connPool == null) {
            throw new IllegalArgumentException("connectionPool can't be null");
        }
        return this.factoryFunction.createDao(connPool, setter);
    }
    
    @FunctionalInterface
    public interface FactoryFunction<T, R, V> {
        R createDao(T t, V v);
    }
}
