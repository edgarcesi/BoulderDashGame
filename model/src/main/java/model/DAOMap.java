package model;

import entity.*;

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
        try {
            String sql = "{call getSchemaByMap(?)}";
            CallableStatement call = this.getConnection().prepareCall(sql);
            call.setInt(1, id);
            call.execute();
            ResultSet resultSet = call.getResultSet();

            int nline = 0; // Map height
            ArrayList<String> line = new ArrayList<>(); // Map length
            while (resultSet.next()){
                line.add(resultSet.getString("schema")); // Actual line
                System.out.println(line.get(nline));
                nline++;
            }

            Map map = new Map(id, nline,line.get(0).length());

            // Create map blocks
            for(int y = 0;y<nline;y++){
                for(int x = 0;x<line.get(y).length();x++){
                    BlockType type;
                    switch(line.get(y).charAt(x)){
                        case 'V':
                            type = BlockType.EMPTY;
                            break;
                        case 'C':
                            type = BlockType.ROCK;
                            break;
                        case 'T':
                            type = BlockType.DIRT;
                            break;
                        case 'X':
                            type = BlockType.WALL;
                            break;
                        case 'D':
                            type = BlockType.DIAMOND;
                            break;
                        default:
                            type = BlockType.WALL;
                            break;
                    }
                    Block block = new Block(x*16,y*16,type);
                    map.setBlocks(y, x, block);
                }
                // txt char line
                map.setSchema(y,line.get(y));
            }

            return map;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet mapInfo(int id) {
        try {
            String sql = "{call getMapInfo(?)}";
            CallableStatement call = this.getConnection().prepareCall(sql);
            call.setInt(1, id);
            call.execute();
            ResultSet resultSet = call.getResultSet();
            if(resultSet.next()) {
                return resultSet;
            } else  return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
