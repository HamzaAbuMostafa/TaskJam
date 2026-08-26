package com.taskjam.service;

import com.taskjam.DTO.UserDTO;
import com.taskjam.entity.User;
import com.taskjam.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public int registerUser(String username, String email, String rawPassword){
        if (username == null || email == null || rawPassword == null)
            throw new IllegalArgumentException("Fields cannot be empty");
        if(userRepo.getUserByEmail(email).isPresent())
            throw new IllegalArgumentException("Email is already registered");
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
        User user = new User(username, email, hashedPassword, LocalDateTime.now());
        return userRepo.addUser(user);
    }

    public Optional<User> authenticateUser(String email, String rawPassword){
        Optional<User> optionalUser = userRepo.getUserByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(BCrypt.checkpw(rawPassword, user.getPasswordHash())){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<UserDTO> getUserProfile(int id){
        if(id < 1)
            throw new IllegalArgumentException("Invalid ID");
        return userRepo.getUserById(id)
                .map(user -> new UserDTO(user.getId(), user.getUserName(), user.getEmail()));
    }

    public boolean deleteUser(int id){
        if(id < 1)
            throw new IllegalArgumentException("Invalid ID");
        return userRepo.deleteUserById(id);
    }
}
