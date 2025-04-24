package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
	private List<User> users = new ArrayList<>();
	private final UserRepository userRepo;

	public AuthenticationService() {
		userRepo = new UserRepository();
		users = userRepo.loadAllUsers();
	}

	public User loginUser(String userID, String password) {
		User user = findUserById(userID);
		if (user == null) {
			System.out.println("User not found.");
			return null;
		}

		if (user.getPassword().equals(password)) {
			System.out.println("Login successful. Role: " + user.getRole().get(1));
			return user;
		} else {
			System.out.println("Incorrect password.");
			return null;
		}
	}

	public User createUser(String name, String userID, int age, String maritalStatus, String password, String role) {
		if (findUserById(userID) != null) {
			System.out.println("User already exists.");
			return null;
		}

		User newUser = new User(name, userID, age, maritalStatus, password, role);
		users.add(newUser);
		userRepo.saveUser(newUser);
		System.out.println("User created successfully.");
		return newUser;
	}

	public void changePassword(String userID, String newPassword) {
		User user = findUserById(userID);

		if (user != null) {
			user.changePassword(newPassword);
			userRepo.overwriteAllUsers(users);
			System.out.println("Password changed successfully.");
		} else {
			System.out.println("User not found.");
		}
	}

	private User findUserById(String userID) {
		for (User user : users) {
			if (user.getUserID().equals(userID)) {
				return user;
			}
		}
		return null;
	}

}