package group4_sc2002_project;

import java.util.Map;

public abstract class OfficerDisplay extends Display {
	private HDBOfficer officer;
	private Project project;

	OfficerDisplay(HDBOfficer officer) {
		this.officer = officer;
		this.project = officer.getHandledProject();
	}

	public void displayOfficerRegistrationStatus() {
		System.out.println("Registration Status: " + officer.getRegistrationStatus());
	}

	public void displayProjectDetails() {
		Project project = officer.getHandledProject();
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}

	public Application retrieveApplicationDetails(String userId) {
		Application app = project.getApplication(userId);
		if (app == null) {
			System.out.println("User not found");
		}
		return app;
	}
}
