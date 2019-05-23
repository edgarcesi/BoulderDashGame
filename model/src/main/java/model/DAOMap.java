package model;

import entity.Entity;
import entity.HelloWorld;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class DAOMap extends DAOEntity{


    /**
     * Instantiates a new DAO entity.
     *
     * @param connection the connection
     * @throws SQLException the SQL exception
     */
    public DAOMap(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public boolean create(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }

    @Override
    public Entity find(int id) {
        return null;
    }

    @Override
    public Entity find(String code) {
        return null;
    }
}
