package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class HDBManager extends User {
	private List<Project> managedProjects;

	public HDBManager(String name, String id, String pw, int age, String marital) {
		super(name, id, pw, age, marital, "Manager");
		managedProjects = new ArrayList<Project>();
	}

	public List<Project> getManagedProject() {
		return managedProjects;
	}

	public void addManagedProject(Project project) {
		managedProjects.add(project);
	}

}
