package group4_sc2002_project;

public class HDBOfficer extends Account {
	private Project handledProject;
	private String registrationStatus;

	HDBOfficer(String userID, String password, int age, String maritalStatus, Project project) {
		super(userID, age, maritalStatus,"HDBOfficer");
		handledProject = project;
		registrationStatus = null;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public Project getHandledProject() {
		return handledProject;
	}

}
