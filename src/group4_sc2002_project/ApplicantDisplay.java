package group4_sc2002_project;

import java.util.List;

public class ApplicantDisplay extends Display {
	private Applicant applicant;
	private Application application;
	private EnquiryController eCon;

	ApplicantDisplay(Applicant applicant) {
		this.applicant = applicant;
		application = applicant.getApplication();
		System.out.println(application.getFlatType());
		eCon = new EnquiryController(application.getProject());
	}

	public void displayEnquiries() {
		List<Integer> ids = applicant.getEnquiryIds();

		for (int id : ids) {
			eCon.viewEnquiry(id);
		}
	}

	public void displayEnquiry(int id) {
		eCon.viewEnquiry(id);
	}

	public void displayApplicationStatus() {
		System.out.print("Status" + application.getStatus());
	}
}
