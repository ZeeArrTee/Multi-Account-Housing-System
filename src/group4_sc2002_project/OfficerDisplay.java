package group4_sc2002_project;

import java.util.*;

public abstract class OfficerDisplay extends Display {
	private Officer officer;
	private List<Project> projects = new ArrayList<Project>();

	OfficerDisplay(Officer officer) {
		this.officer = officer;
		this.projects = officer.getHandledProjects();
	}

	public void displayOfficerRegistrationStatus() {
		System.out.println("Registration Status: " + officer.getRegistrationStatus());
	}

	public void displayProjectDetails() {
		System.out.println("Select your project:");
		int i = 1;
		int choice = Integer.MAX_VALUE;
		
		for (Project project: projects) {
			System.out.println(i + ": "+ project.getProjectName());
			i++;
		}
		
		System.out.println();
		
		while (choice > i||choice < 0) {
			choice = MainMenu.s.nextInt();
			if (choice > i||choice < 0) {
				System.out.println("Invalid choice!");
			}
		}
		Project project = projects.get(choice-1);
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}

	public Application retrieveApplicationDetails() {
		System.out.println("Select your project:");
		int i = 1;
		int choice = Integer.MAX_VALUE;
		
		for (Project project: projects) {
			System.out.println(i + ": "+ project.getProjectName());
			i++;
		}
		if (i == 1) {
			System.out.println("No projects found.");
			return null;
		}
		System.out.println();
		
		while (choice > i||choice < 0) {
			choice = MainMenu.s.nextInt();
			if (choice > i||choice < 0) {
				System.out.println("Invalid choice!");
			}
		}
		Project project = projects.get(choice-1);
		
		System.out.println("Select your application:");
		i = 1;
		for (Application application: project.getApplications()) {
			System.out.println(i + ": " + application.getApplicant());
			i++;
		}
		
		if (i == 1) {
			System.out.println("No applicationss found.");
			return null;
		}
		while (choice > i||choice < 0) {
			choice = MainMenu.s.nextInt();
			if (choice > i||choice < 0) {
				System.out.println("Invalid choice!");
			}
		}
		return project.getApplications().get(choice-1);
	}
}
