package com.taskjam.repositories;

import com.taskjam.entities.User;
import com.taskjam.mappers.UserSupplier;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepository extends GenericRepository{

    public Optional<User> getUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
     return executeQuery(sql,UserSupplier::getUser,id);
    }

    public int addUser(User user){
        String sql = "INSERT INTO users (username,email,password_hash,created_at) VALUES (?,?,?,?)";
        return executeUpdateReturnGeneratedKey(sql,
                user.getUserName(),user.getEmail(),user.getPasswordHash(),user.getCreatedAt());
    }

    public boolean deleteUserById(int id){
        String sql = "DELETE FROM users WHERE id = ?";
        return executeUpdate(sql,id);
    }
}
