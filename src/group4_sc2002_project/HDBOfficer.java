package group4_sc2002_project;

public class HDBOfficer {
	private Project handledProject;
	private String registrationStatus;

	public HDBOfficer(Project proj, String status) {
		handledProject = proj;
		registrationStatus = status;
	}

	public String getRegisterationStatus() {
		return registrationStatus;
	}

	public Project getHandledProject() {
		return handledProject;
	}

}
