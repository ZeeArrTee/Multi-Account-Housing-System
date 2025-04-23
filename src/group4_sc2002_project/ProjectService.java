package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProjectService implements ProjectView {
	private static List<Project> projectListing;

	public ProjectService() {
		projectListing = new ArrayList<Project>();
	}

	@Override
	public void createProject(String projectName, String neighbourhood, Map<String, Integer> units, LocalDate openDate,
			LocalDate closeDate, int officerSlots, HDBManager managerInCharge) {
		Project project = new Project(projectName, neighbourhood, units, openDate, closeDate, officerSlots,
				managerInCharge);
		projectListing.add(project);

	}

	@Override
	public void editProject(Project project) 
	{
		// menu style pick which fields to edit
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n--- Edit Project: " + project.getProjectName() + " ---");
			System.out.println("1. Edit Project Name");
			System.out.println("2. Edit Neighbourhood");
			System.out.println("3. Edit Units");
			System.out.println("4. Edit Open Date");
			System.out.println("5. Edit Close Date");
			System.out.println("6. Edit Officer Slots");
			System.out.println("7. Toggle Visibility");
			System.out.println("8. Done Editing");
			System.out.print("Choose an option: ");
			choice = sc.nextInt();
			sc.nextLine(); 

			switch (choice) {
				case 1 -> 
				{
					System.out.print("Enter new project name: ");
					String newName = sc.nextLine();
					project.setProjectName(newName);
					System.out.println("Project name updated.");
				}
				case 2 -> 
				{
					System.out.print("Enter new neighbourhood: ");
					String newNeighbourhood = sc.nextLine();
					project.setNeighbourhood(newNeighbourhood);
					System.out.println("Neighbourhood updated.");
				}
				case 3 -> 
				{
					System.out.println("Edit units by flat type. Enter '#' to stop.");
					while (true) {
						System.out.print("Enter flat type (e.g., 2-Room, 3-Room): ");
						String flatType = sc.nextLine();
						if (flatType.equals("#")) break;
				
						System.out.print("Enter number of units for " + flatType + ": ");
						try 
						{
							int count = Integer.parseInt(sc.nextLine());
							project.setUnits(flatType, count);
							System.out.println("Updated " + flatType + " to " + count + " units.");
						} 
						catch (NumberFormatException e) 
						{
							System.out.println("Invalid number. Try again.");
						}
					}
				}
				case 4 -> 
				{
					System.out.print("Enter new open date (yyyy-mm-dd): ");
					LocalDate open = LocalDate.parse(sc.nextLine());
					project.setOpenDate(open);
					System.out.println("Open date updated.");
				}
				case 5 -> 
				{
					System.out.print("Enter new close date (yyyy-mm-dd): ");
					LocalDate close = LocalDate.parse(sc.nextLine());
					project.setCloseDate(close);
					System.out.println("Close date updated.");
				}
				case 6 -> 
				{
					System.out.print("Enter new officer slot count: ");
					int slots = sc.nextInt();
					sc.nextLine();
					project.setOfficerSlots(slots);
					System.out.println("Officer slots updated.");
				}
				case 7 -> 
				{
					project.toggleVisibility();
					System.out.println("Visibility toggled.");
				}
				case 8 -> System.out.println("Exiting edit menu...");
				default -> System.out.println("Invalid choice. Try again.");
			}
		} while (choice != 8);

		sc.close();
	}



	public static List<Project> getProjectListing() {
		return projectListing;
	}

	public Project findProjectName(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName().compareTo(projectName) == 0) {
				return project;
			}
		}
		return null;
	}

	public void addOfficer(String projectName, HDBOfficer officer) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.addOfficer(officer);
			}
		}
	}

	public void removeOfficer(String projectName, HDBOfficer officer) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.removeOfficer(officer);
			}
		}
	}

	public void toggleVisibility(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.toggleVisibility();
			}
		}
	}

	public void setProjectName(String projectName, String newName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setProjectName(newName);
			}
		}
	}

	public void setUnits(String projectName, String flatType, int count) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setUnits(flatType, count);
			}
		}
	}

	public void setOpenDate(String projectName, LocalDate date) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setOpenDate(date);
			}
		}
	}

	public void setCloseDate(String projectName, LocalDate date) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName) {
				project.setCloseDate(date);
			}
		}
	}

	public void deleteProject(String projectName) {
		for (Project project : projectListing) {
			if (project.getProjectName() == projectName)
				projectListing.remove(project);
		}
	}

	public void displayProject(String name) {
		Project project = findProjectName(name);
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
	}

}
