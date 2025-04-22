package group4_sc2002_project;

public class ApplicantEnquiryController extends EnquiryController {
	// AdminEnquiryController projectEnquiryController;
	Application application;
	Applicant applicant;

	ApplicantEnquiryController(Applicant applicant) {
		this.application = applicant.getApplication();
		this.applicant = applicant;
		// projectEnquiryController = new AdminEnquiryController(application);
		this.project = application.getProject();

	}

	public void submitEnquiry(String content) {
		int id = project.makeEnquiry(applicant, content);
		applicant.updateEnquiryId(id);
	}

	public void editEnquiry(int enquiryId, String newMsg) {
		project.editEnquiry(enquiryId, newMsg);
	}

	public void deleteEnquiry(int enquiryId) {
		project.deleteEnquiry(enquiryId);
	}
}
