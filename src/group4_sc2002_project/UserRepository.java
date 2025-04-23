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

	public List<User> loadAllUsers() {
		List<User> users = new ArrayList<>();
		users.addAll(loadUsersFromFile(applicantFile));
		users.addAll(loadUsersFromFile(officerFile));
		users.addAll(loadUsersFromFile(managerFile));
		return users;
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
				if (parts.length < 5)
					continue;

				String name = parts[0];
				String id = parts[1];
				int age = Integer.parseInt(parts[2]);
				String status = parts[3];
				String pw = parts[4];
				String role;
				if (parts.length == 5) {
					role = "User";
				} else {
					role = parts[5];
				}
				loaded.add(new User(name, id, pw, age, status, role));
			}
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}

		return loaded;
	}

	public void saveUser(User user) {
		String file = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + file;
		try (FileWriter fw = new FileWriter(file, true)) {
			fw.write(String.join(",", user.getName(), user.getUserID(), user.getPassword(),
					String.valueOf(user.getAge()), user.getMaritalStatus(), user.getRole().get(1)) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void overwriteAllUsers(List<User> users) {
		Map<String, List<User>> roleMap = new HashMap<>();
		roleMap.put(applicantFile, new ArrayList<>());
		roleMap.put(officerFile, new ArrayList<>());
		roleMap.put(managerFile, new ArrayList<>());

		for (User user : users) {
			String file = getFileForRole(user.getRole().get(1));
			roleMap.get(file).add(user);
		}

		for (String file : roleMap.keySet()) {
			String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + file;
			try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
				for (User user : roleMap.get(file)) {
					pw.println(String.join(",", user.getName(), user.getUserID(), user.getPassword(),
							String.valueOf(user.getAge()), user.getMaritalStatus(), user.getRole().get(1)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getFileForRole(String role) {
		return switch (role) {
		case "Applicant" -> applicantFile;
		case "HDBOfficer" -> officerFile;
		case "HDBManager" -> managerFile;
		default -> throw new IllegalArgumentException("Invalid role: " + role);
		};
	}

}