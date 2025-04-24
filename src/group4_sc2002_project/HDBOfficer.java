package group4_sc2002_project;
import java.util.*;

public class HDBOfficer extends Applicant {
	private List<Project> handledProjects = new ArrayList<Project>();
	private String registrationStatus;

	public HDBOfficer(String name, String userID, int age, String maritalStatus, String password, Project project) {
		super(name, userID, age, maritalStatus, password);
		super.modifyRole("Officer");
		handledProjects.add(project);
		registrationStatus = null;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public List<Project> getHandledProjects() {
		return handledProjects;
	}

}
