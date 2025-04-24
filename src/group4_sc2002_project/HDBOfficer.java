package group4_sc2002_project;

public class HDBOfficer extends Applicant {
	private Project handledProject;
	private String registrationStatus;

	public HDBOfficer(String name, String userID, int age, String maritalStatus, String password, Project project) {
		super(name, userID, age, maritalStatus, password);
		super.modifyRole("Officer");
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
