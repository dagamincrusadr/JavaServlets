package net.r;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, User> users;

    public UserManager(Map<String, User> users) {
        this.users = users;

    }

    public UserManager() {
        users = new HashMap<>();

    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void registerUser(User userToRegister) {
        if(userToRegister == null | userToRegister.getEmail() == "") throw new InvalidParameterException();
        if(users.containsKey(userToRegister.getEmail())) throw new IllegalStateException();
        users.put(userToRegister.getEmail(), userToRegister);

    }
    
    public User loginUser(User userToLogin) {
        if(userToLogin.getEmail() == null | userToLogin.getEmail() == "" | userToLogin.getPassword() == null | userToLogin.getPassword() == "") throw new InvalidParameterException();
        if(users.containsKey(userToLogin.getEmail())) {
            User loggingInUser = users.get(userToLogin.getEmail());
            if(loggingInUser.getPassword() == userToLogin.getPassword()) return loggingInUser;
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }
}
