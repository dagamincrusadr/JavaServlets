package net.r;

import java.io.Serializable;

public class User implements Serializable {

    private final static long serialVersionUID = 51l;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User otherUser = (User) obj;

        if(otherUser.getEmail().equals(email)) return true;
        else return false;
    }

    
    public boolean hashCode(Object obj) {
        
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User otherUser = (User) obj;

        if(otherUser.getEmail().hashCode() == email.hashCode()) return true;
        else return false;
    }
}
