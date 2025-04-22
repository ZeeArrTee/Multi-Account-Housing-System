package group4_sc2002_project;

public class HDBOfficer extends Applicant {
	private Project handledProject;
	private String registrationStatus;

	public HDBOfficer(String userID, String password, int age, String maritalStatus, Project project) {
		super(userID, password, age, maritalStatus);
		super.modifyRole("HDBOfficer");
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
