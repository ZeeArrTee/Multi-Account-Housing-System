package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
	public static Scanner s = new Scanner(System.in);
	public static Scanner menu = new Scanner(System.in);
	public static AuthenticationService service = new AuthenticationService();

	public static User Login() {
		String userID, password;
		User check = null;
		int choice;
		do {
			System.out.println("Login Page:");
			System.out.println("Enter your choice:");
			System.out.println("1) Login");
			System.out.println("2) Create User");
			System.out.println("3) Reset Password");
			System.out.println("4) Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				while (true) {
					System.out.println("Key in your userID: (Key in # to go back)");
					userID = s.next();
					if (userID.equals("#")) {
						break;
					}
					System.out.println("Key in your password:");
					password = s.next();
					check = service.loginUser(userID, password);
					if (check != null) {
						return check;
					}
				}
				break;
			case 2:
				List<String> married = new ArrayList<String>(2);
				married.add("Married");
				married.add("Single");
				int age = 0;
				String name = "\0";
				String maritalStatus = "\0";
				System.out.println("Key in your Name: (Key in # to go back)");
				name = s.next();
				if (name.equals("#")) {
					break;
				}
				System.out.println("Key in your userID: ");
				userID = s.next();
				System.out.println("Key in your password:");
				password = s.next();
				System.out.println("Key in your age:");
				age = s.nextInt();
				while (!married.contains(maritalStatus)) {
					System.out.println("Key in your marital status(Single/Married):");
					maritalStatus = s.next();
					if (!married.contains(maritalStatus)) {
						System.out.println("Invalid marital status!");
					}
				}
				check = service.createUser(name, userID, age, maritalStatus, password, "User");
				if (check != null) {
					return check;
				}
			case 3:
				System.out.println("Key in your userID: (Key in # to go back)");
				userID = s.next();
				if (userID.equals("#")) {
					break;
				}
				System.out.println("Key in your new password:");
				password = s.next();
				service.changePassword(userID, password);
				break;
			case 4:
				System.out.println("Returning to main menu...");
				break;
			}
		} while (choice < 4);
		return check;
	}

	public static void viewProjects(User user) {
		List<Project> projects = ProjectRepository.getProjects();
		if (user.getRole().contains("Manager")) {
			for (Project project : projects) {
				ProjectService.displayProject(project);
			}
		} else if (user.getRole().contains("Officer")) {
			Officer off = (Officer) user;
			for (Project project : projects) {
				Map<String, Integer> units = project.getUnits();
				boolean visible = project.getVisibility();
				for (String key : units.keySet()) {
					if (off.getMaritalStatus() == "Single" && units.keySet().contains("3-Room")) {
						visible = false;
					}
				}
				if (!visible) {
					visible = off.getHandledProjects().contains(project);
				}
				if (visible) {
					ProjectService.displayProject(project);
				}
			}
		} else if (user.getRole().contains("Applicant")) {
			Applicant app = (Applicant) user;
			for (Project project : projects) {
				Map<String, Integer> units = project.getUnits();
				boolean visible = project.getVisibility();
				for (String key : units.keySet()) {
					if (app.getMaritalStatus() == "Single" && units.keySet().contains("3-Room")) {
						visible = false;
					}
				}
				if (!visible) {
					visible = project.getApplications().contains(app.getApplication());
				}
				if (visible) {
					ProjectService.displayProject(project);
				}
			}
		} else {
			for (Project project : projects) {
				Map<String, Integer> units = project.getUnits();
				boolean visible = project.getVisibility();
				for (String key : units.keySet()) {
					if (user.getMaritalStatus() == "Single" && units.keySet().contains("3-Room")) {
						visible = false;
					}
				}
				if (visible) {
					ProjectService.displayProject(project);
				}
			}
		}
		System.out.println();
	}

	public static void projectsMenu(User user) {
		int choice;
		String projectName;
		do {
			System.out.println("Projects Page:");
			System.out.println("Enter your choice:");
			System.out.println("1) View Projects");
			System.out.println("2) Apply for project");
			System.out.println("3) Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				viewProjects(user);
				break;
			case 2:
				System.out.println("Project Name: ");
				projectName = s.next();
				Project project = ProjectRepository.getProject(projectName);
				ProjectService.displayProject(project);
				ApplicationView appView = new ApplicationView(project, user);
				System.out.println("Choose the flat type");
				String flatType = s.next();
				while (!project.getUnits().keySet().contains(flatType)) {
					System.out.println("Invalid flat type");
					flatType = s.next();
				}
				Applicant applicant = appView.applyForProject(flatType);
				if (applicant == null) {
					System.out.println("You are not eligible for this flat type");
					break;
				}
				UserRepository.updateUsers(user, applicant);
				user = applicant;
				break;
			default:
				return;
			}
		} while (choice < 3);
	}

	public static void viewApplications(Applicant applicant) {
		Application app = applicant.getApplication();
		ApplicationView.displayApplication(app);
	}

	public static void applicantMenu(Applicant applicant) {
		int choice = 7;
		ApplicantDisplay display = new ApplicantDisplay(applicant);
		ApplicantEnquiryService service = new ApplicantEnquiryService(applicant.getApplication().getProject(),
				applicant);
		String message = "";
		int id;
		do {
			System.out.println("Applicant Dashboard:");
			System.out.println("Enter your choice:");
			System.out.println("1) View Applications");
			System.out.println("2) Make Enquiry");
			System.out.println("3) Edit Enquiry");
			System.out.println("4) View Enquiry");
			System.out.println("5) View All Enquiries");
			System.out.println("6) Delete Enquiry");
			System.out.println("7) Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				viewApplications(applicant);
				break;
			case 2:
				System.out.println("Message:");
				message = s.next();
				service.submitEnquiry(message);
				break;
			case 3:
				System.out.println("Message:");
				message = s.next();
				System.out.println("Enquiry ID:");
				id = s.nextInt();
				service.editEnquiry(id, message);
				break;
			case 4:
				System.out.println("Enquiry ID:");
				id = s.nextInt();
				display.displayEnquiry(id);
				break;
			case 5:
				display.displayEnquiries();
				break;
			case 6:
				System.out.println("Enquiry ID:");
				id = s.nextInt();
				service.deleteEnquiry(id);
			default:
				System.out.println("Invalid option, exiting");
				break;
			}
		} while (choice < 7);
	}

	public static void managerDashboard(User user) {
		Manager manager = (Manager) user;
		ManagerDisplay display = new ManagerDisplay(manager);
		ManagerService service = null;
		int choice;
		Project chosen = null;
		do {
			System.out.println("Manager Dashboard:");
			System.out.println("Enter your choice:");
			System.out.println("1) Choose Current Project");
			System.out.println("2) Project Menu");
			System.out.println("3) Process Officer Registration");
			System.out.println("4) Process Application");
			System.out.println("5) Process Withdrawal Requests");
			System.out.println("6) View Enquiries");
			System.out.println("7) Reply to Enquiries");
			System.out.println("8) Generate Applicant List");
			System.out.println("9) Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				display.displayProjects();
				chosen = display.chooseCurrentProject();
				service = new ManagerService(manager, chosen);
				break;
			case 2:
				System.out.println("Project Menu");
				System.out.println("Enter your choice:");
				System.out.println("1) Create Project");
				System.out.println("2) Edit Project");
				System.out.println("3) Delete Project");
				System.out.println("4) Change Project Visibility");
				System.out.println("5) View Created Projects");
				System.out.println("6) Exit");
				int choice2 = s.nextInt();
				switch (choice2) {
				case 1:
					System.out.print("Enter project name: ");
					String name = MainMenu.s.nextLine();

					System.out.print("Enter neighbourhood: ");
					String neighbourhood = MainMenu.s.nextLine();
					System.out.println("Enter units by flat type. Enter '#' to stop.");
					Map<String, Integer> units = new HashMap<String, Integer>();
					while (true) {
						System.out.print("Enter flat type (e.g., 2-Room, 3-Room): ");
						String flatType = s.nextLine();
						if (flatType.equals("#"))
							break;
						System.out.print("Enter number of units for " + flatType + ": ");
						try {
							int count = Integer.parseInt(s.nextLine());
							units.put(flatType, count);
						} catch (NumberFormatException e) {
							System.out.println("Invalid number. Try again.");
						}
					}

					System.out.print("Enter open date (yyyy-mm-dd): ");
					LocalDate open = LocalDate.parse(MainMenu.s.nextLine());
					System.out.print("Enter close date (yyyy-mm-dd): ");
					LocalDate close = LocalDate.parse(MainMenu.s.nextLine());
					System.out.print("Enter officer slot count: ");
					int slots = MainMenu.s.nextInt();
					ProjectService.createProject(name, neighbourhood, units, open, close, slots, manager);
					break;
				case 2:
					if (chosen == null) {
						System.out.println("Select a project first");
						break;
					}
					ProjectService.editProject(chosen);
					break;
				case 3:
					System.out.println("Key in Project Name");
					String projectName = s.next();
					boolean success = ProjectService.deleteProject(projectName);
					if (!success) {
						System.out.println("Project not found");
					} else {
						System.out.println("Project removed");
					}
					break;
				case 4:
					if (chosen == null) {
						System.out.println("Choose a project first");
					}
					chosen.toggleVisibility();
					break;
				case 5:
					display.displayProjects();
					break;
				case 6:
					break;
				default:
					System.out.println("Invalid option, exiting");
					break;
				}

				break;
			case 3:
				display.processRegistrations();
				break;
			case 4:
				display.applicationMenu();
				break;
			case 5:
				display.withdrawalMenu();
				break;
			case 6:
				for (Project project : ProjectService.getProjectListing()) {
					OfficerEnquiryService.displayEnquiries(project);
				}
			case 7:
				if (chosen == null) {
					System.out.println("Choose a project first");
					break;
				}
				display.enquiryMenu();
				break;
			case 8:
				for (Applicant app : service.getApplicants()) {
					service.displayApplicant(app);

				}
				break;
			case 9:
				System.out.println("Returning to main menu...");
				break;
			}
		} while (choice < 4);

	}

	public static void OfficerProjectMenu(Officer officer) {
		int choice;
		OfficerService display = new OfficerService(officer);
		System.out.println("Enter your choice:");
		int i;
		do {
			System.out.println("Project Menu:");
			System.out.println("1) View project listing");
			System.out.println("2) View managed projects");
			System.out.println("3) Exit");
			System.out.println();
			choice = s.nextInt();
			switch (choice) {
			case 1:
				i = 1;
				for (Project project : ProjectService.getProjectListing()) {
					System.out.println(i + ": " + project.getProjectName());
					i++;
				}
				if (i == 1) {
					System.out.println("There are no ongoing or upcoming projects!");
				}
				break;
			case 2:
				System.out.println("Your registration status is currently: " + officer.getRegistrationStatus());
				choice = s.nextInt();
				do {
					System.out.println("Select your desired action: ");
					System.out.println("1) View project details");
					System.out.println("2) Handle project enquiries");
					System.out.println("3) Handle application matters");
					System.out.println("4) Back");
					switch (choice) {
					case 1:
						display.displayProjectDetails();
						break;
					case 2:
						display.enquiryMenu();
						break;
					case 3:
						display.displayOutstanding();
						break;
					case 4:
						break;
					}
				} while (choice < 4);
				break;

			case 3:

				System.out.println("Returning to dashboard...");
				break;
			}
		} while (choice < 3);
	}

	public static void officerDashboard(User user) {
		int choice;
		Officer officer = (Officer) user;
		OfficerService display = new OfficerService(officer);
		System.out.println("Currently logged in as: " + user.getName());
		do {
			System.out.println("Officer Dashboard:");
			System.out.println("Enter your choice:");
			System.out.println("1) Apply for a Project as an Applicant");
			System.out.println("2) Project Officer Registration");
			System.out.println("3) Project Menu");
			System.out.println("4) Exit");
			choice = menu.nextInt();
			switch (choice) {
			case 1:
				display.ApplicationMenu();
				break;
			case 2:
				display.RegistrationMenu();
				break;
			case 3:
				OfficerProjectMenu(officer);
				break;
			case 4:
				System.out.println("Returning to main menu...");
				break;
			}
		} while (choice < 4);
	}

	public static Manager projectCreation(User user) {
		Manager manager = new Manager(user.getName(), user.getUserID(), user.getAge(), user.getMaritalStatus(),
				user.getPassword());

		Map<String, Integer> units = new HashMap<String, Integer>();
		System.out.println("Project Name: ");
		String projectName = s.next();
		System.out.println("Neighbourhood: ");
		String neighbourhood = s.next();
		System.out.println("Open Date (YYYY-MM-DD): ");
		LocalDate openDate = LocalDate.parse(s.next());
		System.out.println("Close Date (YYYY-MM-DD): ");
		LocalDate closeDate = LocalDate.parse(s.next());
		System.out.println("Officer Slots (max 10): ");
		int officerSlots = s.nextInt();
		while (officerSlots < 1 || officerSlots > 10) {
			System.out.println("Invalid");
			System.out.println("Officer Slots (max 10): ");
			officerSlots = s.nextInt();
		}
		while (true) {
			System.out.print("Enter flat type (e.g., 2-Room, 3-Room): ");
			String flatType = MainMenu.s.nextLine();
			if (flatType.equals("#"))
				break;

			System.out.print("Enter number of units for " + flatType + ": ");
			try {
				int count = Integer.parseInt(MainMenu.s.nextLine());
				units.put(flatType, count);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Try again.");
			}
		}
		Project project = new Project(projectName, neighbourhood, units, openDate, closeDate, officerSlots, manager);
		System.out.println(project.getAvailableUnitsCount("2-room"));
		manager.addManagedProject(project);
		ProjectRepository.createProject(project);
		UserRepository.updateUsers(user, manager);

		System.out.println("Project " + projectName + " created!");
		return manager;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice;
		User user = null;
		do {
			System.out.println("Main Page:");
			System.out.println("Enter your choice:");
			System.out.println("1) Login");
			System.out.println("2) Projects");
			System.out.println("3) Applicant Menu");
			System.out.println("4) Officer Dashboard");
			System.out.println("5) Manager Dashboard");
			System.out.println("6) Apply to be manager");
			System.out.println("7) Exit");
			System.out.println();
			if (user == null) {
				System.out.println("Currently logged out.");
			} else {
				System.out.println("Currently logged in as: " + user.getName());
				System.out.println("Role: " + user.getRole().get(user.getRole().size() - 1));
			}
			choice = menu.nextInt();
			String log;
			switch (choice) {
			case 1:
				if (user != null) {
					System.out.println("Already logged in. Do you want to log out? (y/n)");
					log = s.next();
					if (log.equals("y")) {
						user = null;
						System.out.println("Logging out...");
					}
					break;
				}
				user = Login();
				break;
			case 2:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				}
				projectsMenu(user);
				break;
			case 3:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				}
				if (user.getRole().contains("Applicant") || user.getRole().contains("Officer")) {
					applicantMenu((Applicant) user);
				}

				break;
			case 4:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				} else if (!user.getRole().contains("Officer")) {
					System.out.println("Access Denied");
					break;
				}
				officerDashboard(user);
				break;
			case 5:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				} else if (!user.getRole().contains("Manager")) {
					System.out.println("Access Denied");
					break;
				}
				managerDashboard(user);
			case 6:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				}
				if (user.getRole().contains("Manager")) {
					System.out.println("Already a manager");
					break;
				} else if (user.getRole().contains("Applicant")) {
					System.out.println("Already an applicant, cannot be a manager");
					break;
				} else {
					user = projectCreation(user);
					break;
				}
			case 7:
				System.out.println("Exiting...");
				break;
			}
		} while (choice < 7);
		service.SaveAll();
	}

}
