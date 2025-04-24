package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class HDBManager extends User {
	private List<Project> managedProjects;

	public HDBManager(String name, String userID, String password, int age, String maritalStatus) {
		super(name, userID, age, maritalStatus, password, "Manager");
		managedProjects = new ArrayList<Project>();
	}

	public List<Project> getManagedProject() {
		return managedProjects;
	}

	public void addManagedProject(Project project) {
		managedProjects.add(project);
	}

}
