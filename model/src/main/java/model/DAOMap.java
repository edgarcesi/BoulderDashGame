package model;

import entity.Entity;
import entity.HelloWorld;
import entity.Map;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Map map = new Map();
        try {
            final String sql = "{call getSchemaByMap(?)}";
            final CallableStatement call = this.getConnection().prepareCall(sql);
            call.setInt(1, id);
            call.execute();
            final ResultSet resultSet = call.getResultSet();

            int nline = 0;
            ArrayList<String> line = new ArrayList<>();

            while (resultSet.next()){
                line.add(resultSet.getString("schema"));
                System.out.println(line.get(nline));
                nline++;
            }
                map = new Map(id, nline);

            for(int i = 0;i<nline;i++){
                map.setSchema(i,line.get(i));
            }

            return map;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map find(String code) {
        return null;
    }
}
