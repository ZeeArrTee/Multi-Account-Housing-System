package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerService {
	private Manager manager;
	private Project project;
	private List<Application> apps;

	private static ArrayList<User> registrations;

	ManagerService(Manager manager, Project project) {
		this.manager = manager;
		this.project = project;
		this.apps = project.getApplications();
		registrations = RegistrationRepository.getRegistration(project);
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

	public static ArrayList<User> getRegistrations() {
		return registrations;
	}

	public static void processRegistration(User user, Project project) {
		registrations.remove(user);
		Officer officer = new Officer(user.getName(), user.getUserID(), user.getAge(), user.getMaritalStatus(),
				user.getPassword(), project);
		UserRepository.updateUsers(user, officer);
		project.addOfficer(officer);
	}

	public boolean processApplication(Application application, boolean decision) {
		if (application.getStatus() == ApplicationStatus.Booked
				|| application.getStatus() == ApplicationStatus.Successful) {
			System.out.println("Application already approved");
			return true;
		}
		if (decision) {
			application.setStatus(ApplicationStatus.Successful);
			return true;

		} else {
			application.setStatus(ApplicationStatus.Unsuccessful);
			return false;

		}

	}

	public void displayApplicant(Applicant applicant) {
		String a = (applicant.getApplication() == null) ? "Yes" : "No";
		System.out.println("Name: " + applicant.getName() + " ID: " + applicant.getUserID() + " Marital Status: "
				+ applicant.getMaritalStatus() + "Age: " + applicant.getAge() + " Has pending application: " + a);
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
		Officer officer = new Officer(user.getName(), user.getUserID(), user.getAge(), user.getMaritalStatus(),
				user.getPassword(), project);
		boolean success = project.addOfficer(officer);
		processRegistration(user, project);
		return success;
	}

}
