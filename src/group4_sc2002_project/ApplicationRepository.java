package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRepository {
	private final String applicationFile = "ApplicationList.csv";
	private static List<Application> applications;

	ApplicationRepository() {
		applications = loadApplicationsFromFile(applicationFile);
	}

	public static List<Application> getApplications() {
		return applications;
	}

	public List<Application> loadApplicationsFromFile(String fileName) {

		List<Application> loaded = new ArrayList<Application>();

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

				String userId = parts[0];
				String flatType = parts[1];
				boolean pendingWithdrawal = parts[2] == "true";
				ApplicationStatus status = ApplicationStatus.Pending;
				for (ApplicationStatus stat : ApplicationStatus.values()) {
					if (stat.toString().compareTo(parts[3]) == 0) {
						status = stat;
						break;
					}
				}
				String projectName = parts[4];
				Application app = new Application((Applicant) AuthenticationService.getUser(userId),
						ProjectRepository.getProject(projectName), flatType, status);
				app.setPendingWithdrawal(pendingWithdrawal);
				loaded.add(app);

			}
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}

		return loaded;
	}

	public void saveApplications() {
		String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + applicationFile;
		try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
			for (Application app : applications) {
				pw.println(String.join(",", app.getApplicant().getUserID(), app.getFlatType(),
						String.valueOf(app.getPendingWithdrawal()), app.getStatus().toString(),
						app.getProject().getProjectName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}