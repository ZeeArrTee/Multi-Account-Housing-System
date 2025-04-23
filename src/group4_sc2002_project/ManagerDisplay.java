package group4_sc2002_project;

import java.util.List;
import java.util.Map;

public class ManagerDisplay extends Display {
	HDBManager manager;

	ManagerDisplay(HDBManager manager) {
		this.manager = manager;
	}

	public void displayProjects() {
		List<Project> projectListing = ProjectService.getProjectListing();
		for (Project project : projectListing) {
			Map<String, Integer> units = project.getUnits();
			System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
					+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
					+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
					+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
		}

	}

	public void withdrawalMenu() {

	}

}
