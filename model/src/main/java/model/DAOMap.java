package model;

import entity.Entity;
import entity.HelloWorld;
import entity.Map;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class DAOMap extends DAOEntity<Map> {

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
    public boolean create(Map entity) {
        return false;
    }

    @Override
    public boolean delete(Map entity) {
        return false;
    }

    @Override
    public boolean update(Map entity) {
        return false;
    }

    @Override
    public Map find(int id) {
        return null;
    }

    @Override
    public Map find(String code) {
        return null;
    }
}
