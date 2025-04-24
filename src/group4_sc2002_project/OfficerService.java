package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OfficerService extends OfficerDisplay {
	private Officer officer;
	private List<Project> projects = new ArrayList<Project>();
	private List<Project> handledProject = new ArrayList<Project>();
	
	OfficerService(Officer officer) {
		super(officer);
		this.officer = officer;
		handledProject = officer.getHandledProjects();
		projects = ProjectService.getProjectListing();
		// TODO Auto-generated constructor stub
	}

	public static void createRegistration(User user, Project project) {
		ArrayList<User> temp = ManagerService.getRegistrations().get(project);
		temp.add(user);
		ManagerService.getRegistrations().put(project, temp);
	}

	public boolean canRegister(Officer officer, Project project) {
		for (Project proj: officer.getHandledProjects()) {
			if (proj.isWithinApplicationPeriod(project.getOpeningDate())) {
				return false;
			}
		}
		return true;
	}
	
	public void ApplicationMenu() {
		int i = 1;
		int choice;
		System.out.println("Choose a project to register for: (enter 0 to exit)");
		for (Project project: projects) {
			if (handledProject.contains(project)) {
				System.out.println(i + ": " +project.getProjectName() + " (You already registered for this project!)");
				i++;
				continue;
			}
			System.out.println(i + ": " +project.getProjectName());
			i++;
		}
		choice = MainMenu.s.nextInt();
		while (choice < 1||choice > i||choice != 0||!canRegister(officer,projects.get(choice))) {
			System.out.println("Invalid Choice!");
		}
		if (choice == 0) {
			System.out.println("Returning to menu...");
		}
		else {
			List<String> flatTypes = new ArrayList<String>();
			flatTypes.add("2-room");
			flatTypes.add("3-room");
			Project project = projects.get(choice);
			ApplicationView app = new ApplicationView(project,officer);
			String room;
			
			System.out.println("Choose flat type: (2-room or 3-room)");
			room = MainMenu.s.next();
			if (!flatTypes.contains(room)) {
				System.out.println("Invalid choice!");
				room = MainMenu.s.next();
			}
			app.applyForProject(room);
			System.out.println("Successfully registered for project!");
				
		}
	}
	
	public void RegistrationMenu() {
		int i = 1;
		int choice;
		System.out.println("Choose a project to register for: (enter 0 to exit)");
		for (Project project: projects) {
			if (handledProject.contains(project)) {
				System.out.println(i + ": " +project.getProjectName() + " (You already registered for this project!)");
				i++;
				continue;
			}
			System.out.println(i + ": " +project.getProjectName());
			i++;
		}
		choice = MainMenu.s.nextInt();
		while (choice < 1||choice > i||choice != 0||!canRegister(officer,projects.get(choice))) {
			System.out.println("Invalid Choice!");
		}
		if (choice == 0) {
			System.out.println("Returning to menu...");
		}
		else {
			createRegistration(officer,projects.get(choice));
			System.out.println("Successfully registered for project!");
			
		}
		
		
	}

	@Override
	public void displayOfficerRegistrationStatus() {
		// TODO Auto-generated method stub
		System.out.println("Officer Registration Status: " + this.officer.getRegistrationStatus());
	}
	

	@Override
	public void displayProjectDetails(Project project) {
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));

	}

	public Receipt generateReceipt(Application application) {
		Receipt receipt = new Receipt(application.getApplicant(), application.getProject(), application.getFlatType(),
				LocalDate.now());
		return receipt;
	}
}
