package group4_sc2002_project;

import java.util.Map;

public class OfficerDisplay extends Display {
	private HDBOfficer officer;

	OfficerDisplay(HDBOfficer officer) {
		this.officer = officer;
	}

	String displayRegistrationStatus() {

	}

	public void displayProjectDetails() {
		Project project = officer.getHandledProject();
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}

	Application retrieveApplicationDetails(String userID) {

	}
}
