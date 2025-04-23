package group4_sc2002_project;

public class OfficerRegistrationService implements RegistrationView {

	public boolean registerForProject(HDBOfficer officer, Project project) {
		boolean success = project.addOfficer(officer);
		return success;
	}

}
