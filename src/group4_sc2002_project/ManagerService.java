package group4_sc2002_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManagerService {
	HDBManager manager;
	Project project;
	List<Application> apps;

	private static Map<Project, ArrayList<User>> registrations;

	ManagerService(HDBManager manager, Project project) {
		this.manager = manager;
		this.project = project;
		this.apps = project.getApplications();
		registrations = new HashMap<Project, ArrayList<User>>();
	}

	public boolean processWithdrawal(Application application) {
		Scanner input = new Scanner(System.in);
		System.out.println(application.getApplicant().getUserID() + application.getApplicant().getAge()
				+ application.getFlatType());
		System.out.println("Approve Withdrawal? (Y/N)");
		while (true) {
			String decision = input.next();
			if (decision.compareTo("Y") == 0) {
				application.setPendingWithdrawal(true);
				input.close();
				return true;

			} else if (decision.compareTo("N") == 0) {
				application.setPendingWithdrawal(false);
				input.close();
				return false;
			}
		}

	}

	public static Map<Project, ArrayList<User>> getRegistrations() {
		return registrations;
	}

	public static void processRegistration(User user, Project project) {
		ArrayList<User> temp = registrations.get(project);
		temp.remove(user);
		registrations.put(project, temp);
	}

	public static List<User> getPendingRegistration(Project project) {
		return registrations.get(project);
	}

	public boolean processApplication(Application application, boolean decision) {
		if (application.getStatus() == ApplicationStatus.Booked
				|| application.getStatus() == ApplicationStatus.Successful) {
			System.out.println("Application already approved");
			return true;
		}

		while (true) {
			if (decision) {
				application.setStatus(ApplicationStatus.Successful);
				return true;

			} else {
				application.setStatus(ApplicationStatus.Unsuccessful);
				return false;
			}
		}

	}

	public void displayApplicant(Applicant applicant) {
		System.out.println("Name: " + applicant.getName() + " ID: " + applicant.getUserID() + " Marital Status: "
				+ applicant.getMaritalStatus() + "Age: " + applicant.getAge() + " Has pending application: "
				+ (applicant.getApplication() == null) != null ? "Yes" : "No");
	}

	public List<Application> getApplications() {
		return apps;
	}

	public List<Applicant> getApplicants() {
		return apps.stream().map(app -> app.getApplicant()).toList();
	}

	public void replyEnquiry(Enquiry enquiry, String message) {
		enquiry.setReply(message);
	}

	public boolean registerOfficer(User user, Project project) {
		if (project.getManager() != manager) {
			System.out.println("Access Denied");
			return false;
		}
		HDBOfficer officer = new HDBOfficer(user.getName(), user.getUserID(), user.getPassword(), user.getAge(),
				user.getMaritalStatus(), project);
		boolean success = project.addOfficer(officer);
		processRegistration(user, project);
		return success;
	}

}
