package group4_sc2002_project;

import java.util.Map;

public class OfficerService extends OfficerDisplay {
	private HDBOfficer officer;

	OfficerService(HDBOfficer officer) {
		super(officer);
		this.officer = officer;

		// TODO Auto-generated constructor stub
	}

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
	public void displayOfficerRegistrationStatus() {
		// TODO Auto-generated method stub
		System.out.println("Officer Registration Status: " + this.officer.getRegistrationStatus());
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
