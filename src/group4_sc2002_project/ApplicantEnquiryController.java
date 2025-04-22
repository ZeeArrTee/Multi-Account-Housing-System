package group4_sc2002_project;

public class ApplicantEnquiryController {
	ProjectEnquiryController projectEnquiryController;
	Application application;

	ApplicantEnquiryController(Applicant applicant) {
		Application application = applicant.getApplication();
		projectEnquiryController = new ProjectEnquiryController(application);

	}

	public int submitEnquiry(String content) {
		int id = projectEnquiryController.makeEnquiry(content);
		return id;
	}

	public void editEnquiry(int enquiryId, String newMsg) {
		projectEnquiryController.editEnquiry(enquiryId, newMsg);
	}

	public void deleteEnquiry(int enquiryId) {
		projectEnquiryController.deleteEnquiry(enquiryId);
	}
}
