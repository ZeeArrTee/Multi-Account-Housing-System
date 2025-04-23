package group4_sc2002_project;

import java.util.List;
import java.util.Map;

public class ManagerDisplay extends Display {
	private HDBManager manager;
	private Project project;
	private List<Project> projectListing;
	private ManagerService service;

	ManagerDisplay(HDBManager manager) {
		this.manager = manager;
		projectListing = ProjectService.getProjectListing();
		service = null;
		project = null;

	}

	public void displayProjects() {

		for (Project project : projectListing) {
			Map<String, Integer> units = project.getUnits();
			System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
					+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
					+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
					+ units.keySet().stream().map(key -> key + ": " + units.get(key)));
		}

	}

	public void chooseCurrentProject() {
		System.out.println("Choose Project (Key in name of project)");
		boolean found = false;
		String name = MainMenu.s.next();
		for (Project project : projectListing) {
			if (project.getProjectName().compareTo(name) == 0) {
				this.project = project;
				found = true;
				service = new ManagerService(manager, project);
				break;
			}
		}
		if (!found) {
			System.out.println("Project not found");
		}

	}

	public void withdrawalMenu() {
		if (project == null) {
			System.out.println("No project selected");
			return;
		}
		List<Application> pending = project.getApplications().stream().filter(app -> app.getPendingWithdrawal())
				.toList();
		System.out.println("Choose application number");
		for (int i = 0; i < pending.size(); i++) {
			System.out.println(i + ". " + pending.get(i).getApplicant().getName());
		}
		int choice = MainMenu.s.nextInt();
		while (choice > pending.size() || choice < 0) {
			System.out.println("Invalid choice");
			choice = MainMenu.s.nextInt();
		}
		service.processWithdrawal(pending.get(choice));
	}

	public void applicationMenu() {
		System.out.println("1. Reject all applications to full projects");
		System.out.println("2. Process applications to current project");
		int choice = MainMenu.s.nextInt();
		switch (choice) {
		case 1:
			for (Project project : projectListing) {
				Map<String, Integer> units = project.getUnits();
				boolean clear = true;
				for (String key : units.keySet()) {
					if (units.get(key) != 0) {
						clear = false;
						break;
					}
				}

				if (clear) {
					for (Application app : project.getApplications()) {
						service.processApplication(app, false);
					}
				}

			}
			break;
		case 2:
			System.out.println("Choose application");
			List<Application> apps = this.project.getApplications();
			for (int i = 1; i <= apps.size(); i++) {
				Application application = apps.get(i);
				System.out.println(i + ". " + application.getApplicant().getUserID()
						+ application.getApplicant().getAge() + application.getFlatType());
			}
			int chosen = MainMenu.s.nextInt();

			while (chosen > apps.size() || chosen < 1) {
				System.out.println("Invalid Choice");
				chosen = MainMenu.s.nextInt();
			}
			System.out.println("Approve? (Y/N)");
			String decision = MainMenu.s.next();
			while (!(decision == "Y" || decision == "N")) {
				System.out.println("Invalid Choice");
				System.out.println("Approve? (Y/N)");
				decision = MainMenu.s.next();
			}
			service.processApplication(apps.get(chosen), (decision == "Y"));
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}
	}

}
