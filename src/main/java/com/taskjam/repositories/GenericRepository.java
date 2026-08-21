package com.taskjam.repositories;

import com.taskjam.config.DatabaseConnectionManager;
import com.taskjam.mappers.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class  GenericRepository {
    public <T> Optional<T> executeQuery(String sql, RowMapper<T> mapper, Object... values){
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i+1,values[i]);
            }
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return Optional.of(mapper.mapRow(rs));
                }else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean executeUpdate(String sql, Object... values){
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i+1,values[i]);
            }
            int affectedRows = ps.executeUpdate();
            return (affectedRows > 0);

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int executeUpdateReturnGeneratedKey(String sql, Object... values){
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i+1,values[i]);
            }
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next())
                        return rs.getInt(1);
                }
            }
                return -1;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> executeQueryForList(String sql, RowMapper<T> mapper, Object... values){
        List<T> results = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i+1,values[i]);
            }
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    results.add(mapper.mapRow(rs));
                }
            }
            return results;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
