package group4_sc2002_project;

import java.time.LocalDate;
import java.util.List;

public class Applicant extends User {
	private Application application;
	private List<Enquiry> enquiries;
	private int enquiryCount;

	Applicant(String id, String pw, int a, String marital) {
		super(id, pw, a, marital, "Applicant");
		application = null;
		enquiryCount = 0;

	}

	boolean isEligible(String flatType) {
		if (application != null) {
			return false;
		}
		if (getMaritalStatus() == "Single" && getAge() >= 35) {
			return flatType == "2-Room";
		} else if (getMaritalStatus() == "Married" && getAge() >= 21) {
			return true;
		} else {
			return false;
		}
	}

	void applyForProject(Project project, String flatType, int id) {
		if (isEligible(flatType)) {
			application = new Application(id, this, project, flatType, ApplicationStatus.Pending);
		}
	}

	Application viewApplication() {
		return application;
	}

	void requestWithdrawal() {
		application.setPendingWithdrawal(false);
	}

	void submitEnquiry(String message) {
		enquiryCount++;
		enquiries.add(new Enquiry(enquiryCount, this, message, LocalDate.now()));
	}

	void editEnquiry(Enquiry enquiry, String newMsg) {
	}

	void deleteEnquiry(Enquiry enquiry) {
	}

	void displayEnquiries() {
		for (Enquiry enquiry : enquiries) {
			System.out.println("ID: " + enquiry.getId() + " Created: " + enquiry.getCreatedDate());
			System.out.println(enquiry.getContent());
			System.out.println();
		}
	}

}
