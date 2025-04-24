package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Manager extends User {
	private List<Project> managedProjects;

	public Manager(String name, String userID, String password, int age, String maritalStatus) {
		super(name, userID, password, age, maritalStatus, "Manager");
		managedProjects = new ArrayList<Project>();
	}

	public List<Project> getManagedProject() {
		return managedProjects;
	}

	public void addManagedProject(Project project) {
		managedProjects.add(project);
	}

}
