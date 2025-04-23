package group4_sc2002_project;

public class ApplicantEnquiryService implements ApplicantEnquiryView {
	// AdminEnquiryController projectEnquiryController;
	private Project project;
	private Applicant applicant;

	ApplicantEnquiryService(Project project, Applicant applicant) {
		this.project = project;
		this.applicant = applicant;
	}

	public void submitEnquiry(String message) {
		Enquiry enquiry = new Enquiry(project.getEnquiryCount(), applicant, project, message);
		project.addEnquiry(enquiry);
		project.incrementEnquiryCount();
	}

	public void editEnquiry(int enquiryId, String message) {
		Enquiry enquiry = project.getEnquiry(enquiryId);
		enquiry.setContent(message);
	}

	public void deleteEnquiry(int enquiryId) {
		project.removeEnquiry(enquiryId);
	}

}
