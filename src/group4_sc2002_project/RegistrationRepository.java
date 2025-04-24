package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistrationRepository {
	private final String regFile = "RegistrationList.csv";
	private static Map<Project, ArrayList<User>> registrations;

	RegistrationRepository() {
		registrations = Collections.emptyMap();
		registrations = loadRegistrationsFromFile(regFile);
	}

	public static Map<Project, ArrayList<User>> getRegistrations() {
		return registrations;
	}

	public static ArrayList<User> getRegistration(Project project) {
		return registrations.get(project);
	}

	public Map<Project, ArrayList<User>> loadRegistrationsFromFile(String fileName) {

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

				String projectName = parts[0];
				Project project = ProjectRepository.getProject(projectName);
				ArrayList<User> regs = new ArrayList<User>();
				String[] data = parts[1].split(";");
				for (String userId : data) {
					regs.add(UserRepository.getUser(userId));
				}
				registrations.put(project, regs);

			}
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}

		return registrations;
	}

	public void saveregistrations() {
		boolean isFirstLine = true;
		String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + regFile;
		try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
			for (Project project : registrations.keySet()) {
				if (isFirstLine) {
					isFirstLine = false;
					pw.println("Project Name, UserIDs");
				}
				String values = registrations.get(project).stream().map(user -> user.getUserID())
						.collect(Collectors.joining(";"));
				pw.println(String.join(",", project.getProjectName(), values));
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}

	}

}