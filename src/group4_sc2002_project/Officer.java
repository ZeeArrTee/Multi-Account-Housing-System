package group4_sc2002_project;
import java.util.*;

public class Officer extends Applicant {
	private List<Project> handledProject = new ArrayList<Project>();
	private String registrationStatus;

	public Officer(String name, String userID, int age, String maritalStatus, String password, Project project) {
		super(name, userID, age, maritalStatus, password);
		super.modifyRole("Officer");
		handledProject.add(project);
		registrationStatus = null;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public List<Project> getHandledProjects() {
		return handledProject;
	}

}
