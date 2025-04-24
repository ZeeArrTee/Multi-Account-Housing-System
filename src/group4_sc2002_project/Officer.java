package group4_sc2002_project;

public class Officer extends Applicant {
	private Project handledProject;
	private String registrationStatus;

	public Officer(String name, String userID, String password, int age, String maritalStatus, Project project) {
		super(name, userID, password, age, maritalStatus);
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
