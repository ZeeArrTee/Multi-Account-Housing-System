package group4_sc2002_project;

import java.util.*;

public class AuthenticationService
{
    private List<User> users = new ArrayList<>();
    private final UserRepository userRepo;

    public AuthenticationService()
    {
        userRepo = new UserRepository();
        users = userRepo.loadAllUsers();
    }

    public void loginUser(String userID, String password) 
    {
        User user = findUserById(userID);

        if (user == null) 
        {
            System.out.println("User not found.");
            return;
        }


        if (user.getPassword().equals(password)) 
        {
            System.out.println("Login successful. Role: " + user.getRole().get(1));
        } else 
        {
            System.out.println("Incorrect password.");
        }
    }

    public void createUser(String name, String userID, String password, int age, String maritalStatus, String role) 
    {
        if (findUserById(userID) != null) 
        {
            System.out.println("User already exists.");
            return;
        }

        User newUser = new User(name, userID, password, age, maritalStatus, role);
        users.add(newUser);
        userRepo.saveUser(newUser);
        System.out.println("User created successfully.");
    }

    public void changePassword(String userID, String newPassword) 
    {
        User user = findUserById(userID);

        if (user != null) 
        {
            user.changePassword(newPassword);
            userRepo.overwriteAllUsers(users);
            System.out.println("Password changed successfully.");
        } else 
        {
            System.out.println("User not found.");
        }
    }

    private User findUserById(String userID) 
    {
        for (User user : users) 
        {
            if (user.getUserID().equals(userID)) 
            {
                return user;
            }
        }
        return null;
    }


    
}