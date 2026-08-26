package com.taskjam.repository;

import com.taskjam.entity.User;
import com.taskjam.mapper.UserSupplier;

import java.util.Optional;

public class UserRepository extends GenericRepository{

    public Optional<User> getUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
     return executeQuery(sql,UserSupplier::getUser,id);
    }

    public Optional<User> getUserByName(String name){
        String sql = "SELECT * FROM users WHERE username = ?";
        return executeQuery(sql,UserSupplier::getUser,name);
    }
    public Optional<User> getUserByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        return executeQuery(sql,UserSupplier::getUser,email);
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
