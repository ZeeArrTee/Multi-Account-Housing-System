package group4_sc2002_project;

import java.util.Map;

public abstract class Display {
	public void displayProjectDetails(Project project) {
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}
}
