package group4_sc2002_project;

import java.util.List;

public class ApplicantDisplay extends Display {
	private Applicant applicant;
	private Application application;

	ApplicantDisplay(Applicant applicant) {
		this.applicant = applicant;
		Application application = applicant.getApplication();
	}

	public void displayEnquiries() {
		List<Integer> ids = applicant.getEnquiryIds();
		EnquiryController eCon = new EnquiryController(application.getProject());
		for (int id : ids) {
			eCon.viewEnquiry(id);
		}
	}

	public void displayApplicationStatus() {
		System.out.print("Status" + application.getStatus());
	}
}
