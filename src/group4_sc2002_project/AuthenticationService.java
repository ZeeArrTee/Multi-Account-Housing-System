package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
	private static List<User> users = new ArrayList<>();
	private final UserRepository userRepo;
	private final ProjectRepository projRepo;
	private final ApplicationRepository appRepo;
	private final RegistrationRepository regRepo;

	public AuthenticationService() {
		userRepo = new UserRepository();
		users = userRepo.loadAllUsers();
		projRepo = new ProjectRepository();
		appRepo = new ApplicationRepository();
		regRepo = new RegistrationRepository();

	}

	public List<User> getUsers() {
		return users;
	}

	public void SaveAll() {
		userRepo.overwriteAllUsers(users);
		projRepo.saveProjects();
		appRepo.saveApplications();
		regRepo.saveregistrations();
	}

	public static User getUser(String userId) {
		for (User user : users) {
			if (user.getUserID() == userId) {
				return user;
			}
		}
		return null;
	}

	public User loginUser(String userID, String password) {
		User user = findUserById(userID);
		if (user == null) {
			System.out.println("User not found.");
			return null;
		}

		if (user.getPassword().equals(password)) {
			System.out.println("Login successful. Role: " + user.getRole().get(user.getRole().size() - 1));
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
		System.out.println("User created successfully.");
		return newUser;
	}

	public void changePassword(String userID, String newPassword) {
		User user = findUserById(userID);

		if (user != null) {
			user.changePassword(newPassword);
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