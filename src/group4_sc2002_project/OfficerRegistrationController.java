package group4_sc2002_project;

public class OfficerRegistrationController {
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

}
