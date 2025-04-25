package group4_sc2002_project;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerDisplay extends Display {
	private Manager manager;
	private Project project;
	private List<Project> projectListing;
	private ManagerService service;

	ManagerDisplay(Manager manager) {
		this.manager = manager;
		projectListing = ProjectService.getProjectListing();
		service = null;
		project = null;

	}

	public void displayProjects() {
		System.out.println("Filter projects? (Y/N)");
		String choice = MainMenu.s.next();

		if (choice.compareToIgnoreCase("Y") == 0) {
			System.out.println("Choose Field");
			System.out.println("1. Name");
			System.out.println("2. Neighbourhood");
			System.out.println("3. Opening Date");
			System.out.println("4. Closing Date");
			System.out.println("5. Officer Slots");
			System.out.println("6. Flat types");
			System.out.println("7. None");
			int filter = MainMenu.s.nextInt();
			while (filter < 1 || filter > 7) {
				System.out.println("Invalid option");
				filter = MainMenu.s.nextInt();
			}
			String date;
			switch (filter) {
			case 1:
				System.out.println("What value are you looking for?");
				String name = MainMenu.s.next();
				projectListing = projectListing.stream().filter(proj -> proj.getProjectName().compareTo(name) == 0)
						.toList();
				break;
			case 2:
				System.out.println("What neighbourhood are you looking for?");
				String neighbourhood = MainMenu.s.next();
				projectListing = projectListing.stream()
						.filter(proj -> proj.getNeighbourhood().compareTo(neighbourhood) == 0).toList();
				break;
			case 3:
				System.out.println("Format: YYYY-MM-DD");
				date = MainMenu.s.next();
				projectListing = projectListing.stream()
						.filter(proj -> proj.getOpeningDate().toString().compareTo(date) == 0).toList();
				break;
			case 4:
				System.out.println("Format: YYYY-MM-DD");
				date = MainMenu.s.next();
				projectListing = projectListing.stream()
						.filter(proj -> proj.getClosingDate().toString().compareTo(date) == 0).toList();
				break;
			case 5:
				System.out.println("What minimum officer count?");
				int count = MainMenu.s.nextInt();
				projectListing = projectListing.stream().filter(proj -> proj.getOfficerSlots() >= count).toList();
				break;
			case 6:
				System.out.println("Flat type: ");
				String type = MainMenu.s.next();
				count = MainMenu.s.nextInt();
				projectListing = projectListing.stream().filter(proj -> proj.getUnits().get(type) >= count).toList();
				break;
			default:

				break;
			}
		}
		for (Project project : projectListing) {
			Map<String, Integer> units = project.getUnits();
			System.out.println("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
					+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
					+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
					+ units.keySet().stream().map(key -> key + ": " + units.get(key)).collect(Collectors.joining(" ")));
		}

	}

	public Project chooseCurrentProject() {
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
		return this.project;

	}

	public void processRegistrations() {
		List<User> pending = ManagerService.getPendingRegistration(project);
		if (pending == null) {
			System.out.println("No pending registrations");
			return;
		}
		for (int i = 1; i <= pending.size(); i++) {
			User user = pending.get(i);
			System.out.println(i + ". " + user.getName() + " Open Date: " + project.getOpeningDate() + " Close Date: "
					+ project.getClosingDate());
		}

		int chosen = MainMenu.s.nextInt() - 1;
		while (chosen > pending.size() - 1 || chosen < 0) {
			chosen = MainMenu.s.nextInt() - 1;
		}
		System.out.println("Approve? (Y/N)");
		String decision = MainMenu.s.next();
		while (!(decision == "Y" || decision == "N")) {
			System.out.println("Invalid Choice");
			System.out.println("Approve? (Y/N)");
			decision = MainMenu.s.next();
		}
		ManagerService.processRegistration(pending.get(chosen), project);
		service.registerOfficer(pending.get(chosen), project);

	}

	public void withdrawalMenu() {
		if (project == null) {
			System.out.println("No project selected");
			return;
		}
		List<Application> pending = project.getApplications().stream().filter(app -> app.getPendingWithdrawal())
				.toList();
		System.out.println("Choose application number");
		for (int i = 1; i <= pending.size(); i++) {
			System.out.println(i + ". " + pending.get(i - 1).getApplicant().getName());
		}
		int choice = MainMenu.s.nextInt() - 1;
		while (choice > pending.size() - 1 || choice < 0) {
			System.out.println("Invalid choice");
			choice = MainMenu.s.nextInt() - 1;
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
			for (Application application : apps) {
				if (application.getProject().getUnits().get(application.getFlatType()) == 0) {
					apps.remove(application);
					service.processApplication(application, false);
				}
			}
			for (int i = 1; i <= apps.size(); i++) {
				Application application = apps.get(i - 1);
				System.out.println(i + ". " + application.getApplicant().getUserID()
						+ application.getApplicant().getAge() + application.getFlatType());
			}
			int chosen = MainMenu.s.nextInt() - 1;

			while (chosen > apps.size() - 1 || chosen < 0) {
				System.out.println("Invalid Choice");
				chosen = MainMenu.s.nextInt() - 1;
			}
			System.out.println("Approve? (Y/N)");
			String decision = MainMenu.s.next();
			while (!(decision.compareTo("Y") == 0|| decision.compareTo("N") == 0)) {
				System.out.println("Invalid Choice");
				System.out.println("Approve? (Y/N)");
				decision = MainMenu.s.next();
			}
			service.processApplication(apps.get(chosen), (decision.compareTo("Y") == 0));
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}
	}

	public void enquiryMenu() {
		List<Enquiry> enquiries = project.getEnquiries().stream().filter(enq -> enq.getReply().isEmpty()).toList();
		for (int i = 0; i < enquiries.size(); i++) {
			Enquiry enquiry = enquiries.get(i - 1);
			System.out.println("Enquiry ID: " + enquiry.getId() + ", Applicant: " + enquiry.getApplicant().getUserID()
					+ ", Enquiry:" + enquiry.getContent());
		}
		System.out.println("Choose enquiryID to reply to");
		int choice = MainMenu.s.nextInt();
		while (choice < 0 || choice >= enquiries.size()) {
			System.out.println("Invalid Choice");
			choice = MainMenu.s.nextInt();
		}
		System.out.println("Reply:");
		String reply = MainMenu.s.next();
		OfficerEnquiryService eService = new OfficerEnquiryService(project);
		eService.replyEnquiries(enquiries.get(choice).getId(), reply);

	}
}
