package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {
	private Application application;
	private List<Integer> enquiryId;
	ApplicantEnquiryController applicantEnquiryController;

	Applicant(String id, String pw, int a, String marital) {
		super(id, pw, a, marital, "Applicant");
		application = null;
		enquiryId = new ArrayList<Integer>();
		applicantEnquiryController = new ApplicantEnquiryController(this);

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

	Application getApplication() {
		return application;
	}

	void requestWithdrawal() {
		application.setPendingWithdrawal(false);
	}

	void submitEnquiry(String message) {
		int id = applicantEnquiryController.submitEnquiry(message);
		enquiryId.add(id);

	}

	void editEnquiry(int id, String newMsg) {
		applicantEnquiryController.editEnquiry(id, newMsg);
	}

	void deleteEnquiry(int id) {
		applicantEnquiryController.deleteEnquiry(id);
	}

	void displayEnquiries() {
		for (int id : enquiryId) {
			Enquiry enquiry = application.getProject().getEnquiry(id);
			System.out.println("ID: " + id + " Created: " + enquiry.getCreatedDate());
			System.out.println(enquiry.getContent());
			System.out.println();
		}
	}

}
