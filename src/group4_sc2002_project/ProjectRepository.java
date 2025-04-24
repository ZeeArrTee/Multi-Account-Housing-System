package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ProjectRepository {
	private final String projectFile = "ProjectList.csv";
	private static List<Project> projects;

	ProjectRepository() {
		projects = new ArrayList<Project>();
		projects = loadProjectsFromFile(projectFile);
	}

	public static List<Project> getProjects() {
		return projects;
	}

	public static Project getProject(String name) {
		for (Project project : projects) {
			if (project.getProjectName().compareTo(name) == 0) {
				return project;
			}
		}
		return null;
	}

	public static void createProject(Project project) {
		projects.add(project);
	}

	public List<Project> loadProjectsFromFile(String fileName) {
		
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
				String neighbourhood = parts[1];
				Map<String, Integer> units = new HashMap<String, Integer>();
				String[] pairs = parts[2].split(";");
				for (String pair : pairs) {
					String[] kv = parts[2].split("=");
					units.put(kv[0], Integer.parseInt(kv[1]));
				}
				LocalDate openDate = LocalDate.parse(parts[3]);
				LocalDate closeDate = LocalDate.parse(parts[4]);
				int officerSlots = Integer.parseInt(parts[5]);
				Manager managerInCharge = (Manager) AuthenticationService.getUser(parts[6]);
				managerInCharge.addManagedProject(null);
				Project project = new Project(projectName, neighbourhood, units, openDate, closeDate, officerSlots,
						managerInCharge);
				managerInCharge.addManagedProject(project);
				projects.add(project);

			}
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}

		return projects;
	}

	public void saveProjects() {
		boolean isFirstLine = true;
		String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + projectFile;
		try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
			for (Project project : projects) {
				if (isFirstLine) {
					isFirstLine = false;
					pw.println("Project Name, Neighbourhood, Opening Date, Closing Date, Officer Slots Remaining, Manager-In-Charge");
				}
				String units = project.getUnits().keySet().stream()
						.map(key -> key + "=" + project.getUnits().get(key).toString())
						.collect(Collectors.joining(";"));
				pw.println(String.join(",", project.getProjectName(), project.getNeighbourhood(), units,
						project.getOpeningDate().toString(), project.getClosingDate().toString(),
						String.valueOf(project.getOfficerSlots()), project.getManager().getUserID()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}