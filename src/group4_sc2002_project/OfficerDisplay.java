package group4_sc2002_project;

import java.util.*;

public class OfficerDisplay extends Display {
	private Officer officer;
	private List<Project> projects = new ArrayList<Project>();
	private List<Project> outprojects = new ArrayList<Project>();

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
	
	public void enquiryMenu() {
		System.out.println("Select your project:");
		int j = 1;
		int choice = Integer.MAX_VALUE;
		
		for (Project project: projects) {
			System.out.println(j + ": "+ project.getProjectName());
			j++;
		}
		
		System.out.println();
		
		while (choice > j||choice < 0) {
			choice = MainMenu.s.nextInt();
			if (choice > j||choice < 0) {
				System.out.println("Invalid choice!");
			}
		}
		
		Project project = projects.get(choice-1);
		List<Enquiry> enquiries = project.getEnquiries().stream().filter(enq -> enq.getReply().isEmpty()).toList();
		for (int i = 1; i <= enquiries.size(); i++) {
			Enquiry enquiry = enquiries.get(i);
			System.out.println("Enquiry ID: " + enquiry.getId() + ", Applicant: " + enquiry.getApplicant().getUserID()
					+ ", Enquiry:" + enquiry.getContent());
		}
		
		System.out.println("Choose enquiryID to reply to");
		choice = MainMenu.s.nextInt();
		while (choice < 1 || choice > enquiries.size()) {
			System.out.println("Invalid Choice");
			choice = MainMenu.s.nextInt();
		}
		System.out.println("Reply:");
		String reply = MainMenu.s.next();
		OfficerEnquiryService eService = new OfficerEnquiryService(project);
		eService.replyEnquiries(enquiries.get(choice).getId(), reply);

	}
	 	
	
	public void displayOutstanding() {
		int totout = 0;
		for (Project project: projects) {
			boolean add = false;
			int out = 0;
			for (Application application: project.getApplications()) {
				if (application.getStatus().equals(ApplicationStatus.Successful)) {
					if (!add) {
						add = true;
						this.outprojects.add(project);
					}
					out++;
					totout++;
				}
				System.out.println(project.getProjectName() + " has " + out + " outstanding applications!");
			}
		System.out.println("You have " + totout + " outstanding applications remaining!");
		}
		
		String decision;
		for (Project project: outprojects) {
			List<Application> apps = project.getApplications();
			for (int i = 1; i <= apps.size(); i++) {
				Application application = apps.get(i);
				System.out.println(i + ". " + application.getApplicant().getUserID()
						+ application.getApplicant().getAge() + application.getFlatType());
				
				System.out.println("Resolve? (Y/N)");
				decision = MainMenu.s.next();
				while (!(decision.equals("Y")|| decision.equals("N"))) {
					System.out.println("Invalid Choice");
					System.out.println("Resolve? (Y/N)");
					decision = MainMenu.s.next();
				}
				
				if (decision.equals("Y")) {
					String flatType = application.getFlatType();
					int flatCount = project.getAvailableUnitsCount(flatType);
					
					application.setStatus(ApplicationStatus.Booked);
					project.setUnits(flatType, flatCount-1);
				}
			}
			
		}
		
		
	}
}
