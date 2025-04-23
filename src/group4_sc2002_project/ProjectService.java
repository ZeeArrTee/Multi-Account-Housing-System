package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectService implements ProjectView {
	private static List<Project> projectListing;

	public ProjectService() {
		projectListing = new ArrayList<Project>();
	}

	public static void displayProjects() {
		for (Project project : projectListing) {
			Map<String, Integer> units = project.getUnits();
			System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
					+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
					+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
					+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
		}

	}

	@Override
	public void createProject(String projectName, String neighbourhood, Map<String, Integer> units, LocalDate openDate,
			LocalDate closeDate, int officerSlots, HDBManager managerInCharge) {
		Project project = new Project(projectName, neighbourhood, units, openDate, closeDate, officerSlots,
				managerInCharge);
		projectListing.add(project);

	}

	@Override
	public void editProject(Project project) {
		// menu style pick which fields to edit

	}

	public static List<Project> getProjectListing() {
		return projectListing;
	}

	public Project findProjectName(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName().compareTo(projectName) == 0) {
				return project;
			}
		}
		return null;
	}

	public void addOfficer(String projectName, HDBOfficer officer) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.addOfficer(officer);
			}
		}
	}

	public void removeOfficer(String projectName, HDBOfficer officer) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.removeOfficer(officer);
			}
		}
	}

	public void toggleVisibility(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.toggleVisibilty();
			}
		}
	}

	public void setProjectName(String projectName, String newName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setProjectName(newName);
			}
		}
	}

	public void setUnits(String projectName, String flatType, int count) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setUnits(flatType, count);
			}
		}
	}

	public void setOpenDate(String projectName, LocalDate date) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setOpenDate(date);
			}
		}
	}

	public void setCloseDate(String projectName, LocalDate date) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setCloseDate(date);
			}
		}
	}

	public void deleteProject(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName)
				projectListing.remove(project);
		}
	}

	@Override
	public void displayProject(String name) {
		Project project = findProjectName(name);
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}

}
