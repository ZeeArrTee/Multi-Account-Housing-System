package group4_sc2002_project;

import java.util.Map;

public class OfficerService implements OfficerDisplay {
	public void registerOfficer(HDBOfficer officer, Project project) {
		project.addOfficer(officer);
	}

	public boolean canRegister(HDBOfficer officer, Project project) {
		if (officer.getHandledProject().isWithinApplicationPeriod(project.getOpeningDate())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String displayRegistrationStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayProjectDetails(Project project) {
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));

	}

	@Override
	public Application retrieveApplicationDetails(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
