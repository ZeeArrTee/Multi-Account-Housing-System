package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
	private static final String applicantFile = "ApplicantList.csv";
	private static final String officerFile = "OfficerList.csv";
	private static final String managerFile = "ManagerList.csv";
	private static final String userFile = "UserList.csv";
	private static List<User> users;
	private static List<User> applicants;
	private static List<User> officers;
	private static List<User> managers;

	public List<User> loadAllUsers() {
		users = new ArrayList<>();
		applicants = new ArrayList<>();
		officers = new ArrayList<>();
		managers = new ArrayList<>();
		users.addAll(loadUsersFromFile(applicantFile));
		users.addAll(loadUsersFromFile(officerFile));
		users.addAll(loadUsersFromFile(managerFile));
		users.addAll(loadUsersFromFile(userFile));
		return users;
	}

	public static void updateUsers(User oldUser, User newUser) {
		users.remove(oldUser);
		users.add(newUser);
	}

	public static User getUser(String userId) {
		for (User user : users) {
			if (user.getUserID() == userId) {
				return user;
			}
		}
		return null;
	}

	private List<User> loadUsersFromFile(String fileName) {
		List<User> loaded = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + fileName))) {
			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				String[] parts = line.split(",");
				if (parts.length <= 5)
					continue;

				String name = parts[0];
				String id = parts[1];
				int age = Integer.parseInt(parts[2]);
				String status = parts[3];
				String pw = parts[4];
				String role = parts[5];
				switch (role) {
				case "User":
					loaded.add(new User(name, id, age, status, pw, role));
					break;
				case "Applicant":
					loaded.add(new Applicant(name, id, age, pw, status));
					break;
				case "Officer":
					loaded.add(new Officer(name, id, age, pw, status, null)); // add code to find their project
					break;
				case "Manager":
					loaded.add(new Manager(name, id, age, pw, status));
					break;
				}

			}
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}

		return loaded;
	}

	public void saveUser(User user) {
		String file = userFile;
		if (user.getRole().contains("Manager")) {
			file = managerFile;
		} else if (user.getRole().contains("Officer")) {
			file = officerFile;
		} else if (user.getRole().contains("Applicant")) {
			file = applicantFile;
		}
		file = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + file;
		try (FileWriter fw = new FileWriter(file, true)) {
			fw.write(String.join(",", user.getName(), user.getUserID(), String.valueOf(user.getAge()),
					user.getMaritalStatus(), user.getPassword(), user.getRole().get(user.getRole().size() - 1)) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void overwriteAllUsers(List<User> users) {
		boolean isFirstLine = true;
		Map<String, List<User>> roleMap = new HashMap<>();
		roleMap.put(applicantFile, new ArrayList<User>());
		roleMap.put(officerFile, new ArrayList<User>());
		roleMap.put(managerFile, new ArrayList<User>());
		roleMap.put(userFile, new ArrayList<User>());

		for (User user : users) {
			String file = getFileForRole(user.getRole().get(user.getRole().size() - 1));
			roleMap.get(file).add(user);
		}
//		System.out.println(roleMap);
		for (String file : roleMap.keySet()) {
			String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + file;
			try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
				isFirstLine = true;
				for (User user : roleMap.get(file)) {
					if (isFirstLine) {
						isFirstLine = false;
						if (user.getRole().getLast().equals("User")) {
							pw.println("Name, UserID, Age, Marital Status, Password, Roles");
						} else {
							pw.println("Name, UserID, Age, Marital Status, Password");
						}
					}
					if (user.getRole().getLast().equals("User")) {
						pw.println(String.join(",", user.getName(), user.getUserID(), String.valueOf(user.getAge()),
								user.getMaritalStatus(), user.getPassword(),
								user.getRole().get(user.getRole().size() - 1)));
					} else {
						pw.println(String.join(",", user.getName(), user.getUserID(), String.valueOf(user.getAge()),
								user.getMaritalStatus(), user.getPassword()));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getFileForRole(String role) {
		return switch (role) {
		case "User" -> userFile;
		case "Applicant" -> applicantFile;
		case "Officer" -> officerFile;
		case "Manager" -> managerFile;
		default -> throw new IllegalArgumentException("Invalid role: " + role);
		};
	}

}