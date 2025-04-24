package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Manager extends User {
	private List<Project> managedProjects;

	public Manager(String name, String userID, int age, String maritalStatus, String password) {
		super(name, userID, age, maritalStatus, "Manager", password);
		managedProjects = new ArrayList<Project>();
	}

	public List<Project> getManagedProject() {
		return managedProjects;
	}

	public void addManagedProject(Project project) {
		managedProjects.add(project);
	}

}
