package group4_sc2002_project;

import java.io.*;
import java.util.*;

public class AuthenticationService
{
    private List<User> users = new ArrayList<>();

    public AuthenticationService()
    {
        loadUsers("applicants.csv");
        loadUsers("officers.csv");
        loadUsers("managers.csv");
    }

    private void loadUsers(String fileName)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;

            while((line = br.readLine()) != null)
            {
                String[] parts = line.split(",");

                if (parts.length<6) 
                {
                    continue;
                }

                String name = parts[0];
                String id = parts[1];
                String password = parts[2];
                int age = Integer.parseInt(parts[3]);
                String status = parts[4];
                String role = parts[5];

                users.add(new User(name,id,password,age,status,role));
            }
        }
        catch (IOException e)
        {
            System.out.println("File not found");
        }
    }

    private void saveUserToCSV(User user) 
    {
        String role = user.getRole().get(1);//index 1 cus 0 is the general user
        String fileName = switch (role) {
            case "Applicant" -> "applicants.csv";
            case "HDBOfficer" -> "officers.csv";
            case "HDBManager" -> "managers.csv";
            default -> throw new IllegalArgumentException("Unknown role: " + user.getRole());
        };

        try (FileWriter fw = new FileWriter(fileName, true)) 
        {
            fw.write(String.join(",", user.getName(),user.getUserID(), user.getPassword(), String.valueOf(user.getAge()), user.getMaritalStatus(), user.getRole().get(1)) + "\n");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void loginUser(String userID, String password)
    {
        for(User user:users)
        {
            if (user.getUserID().equals(userID)) 
            {
                if (user.getPassword().equals(password)) 
                {
                    System.out.println("Login successful. Role: " + user.getRole());
                } else 
                {
                    System.out.println("Incorrect password.");
                }
                return;
            }
        }
        System.out.println("User not found.");           
    }

    public void createUser (String name,String userID, String password, int age, String maritalStatus, String role)
    {
        for (User user : users) 
        {
            if (user.getUserID().equals(userID)) 
            {
                System.out.println("User already exists.");
                return;
            }
        }

        User newUser = new User(name, userID, password, age, maritalStatus, role);
        users.add(newUser);
        saveUserToCSV(newUser);
        System.out.println("User created successfully.");
    }

    public void changePassword(String userID, String newPassword)
    {
        boolean found = false;

        for(User user:users)
        {
            if(user.getUserID().equals(userID))
            {
                user.changePassword(newPassword);
                found = true;
                break;
            }
        }

        if (found) 
        {
            overwriteAllCSVs();
            System.out.println("Password changed successfully.");
        } 
        else
        {
            System.out.println("User not found.");
        }
        
    }

    private void overwriteAllCSVs() 
    {
        Map<String, List<User>> roleMap = new HashMap<>();
        roleMap.put("applicants.csv", new ArrayList<>());
        roleMap.put("officers.csv", new ArrayList<>());
        roleMap.put("managers.csv", new ArrayList<>());

        for (User user : users) 
        {
            String role = user.getRole().get(1);
            switch (role) 
            {
                case "Applicant" -> roleMap.get("applicants.csv").add(user);
                case "HDBOfficer" -> roleMap.get("officers.csv").add(user);
                case "HDBManager" -> roleMap.get("managers.csv").add(user);
            }
        }

        for (String file : roleMap.keySet()) 
        {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) 
            {
                for (User user : roleMap.get(file)) 
                {
                    pw.println(String.join(",",user.getName() ,user.getUserID(), user.getPassword(), String.valueOf(user.getAge()), user.getMaritalStatus(), user.getRole().get(1)));
                }
            } catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }


    
}