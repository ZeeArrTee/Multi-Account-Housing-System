package group4_sc2002_project;

import java.util.List;
import java.util.Scanner;

public class ManagerService {
	HDBManager manager;
	Project project;
	List<Application> apps;

	ManagerService(HDBManager manager, Project project) {
		this.manager = manager;
		this.project = project;
		this.apps = project.getApplications();
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

	public boolean processApplication(Application application) {
		if (application.getStatus() == ApplicationStatus.Booked
				|| application.getStatus() == ApplicationStatus.Successful) {
			System.out.println("Application already approved");
			return true;
		}
		Scanner input = new Scanner(System.in);
		System.out.println(application.getApplicant().getUserID() + application.getApplicant().getAge()
				+ application.getFlatType());
		System.out.println("Approve? (Y/N)");
		while (true) {
			String decision = input.next();
			if (decision.compareTo("Y") == 0) {
				application.setStatus(ApplicationStatus.Successful);
				input.close();
				return true;

			} else if (decision.compareTo("N") == 0) {
				application.setStatus(ApplicationStatus.Unsuccessful);
				input.close();
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

}
