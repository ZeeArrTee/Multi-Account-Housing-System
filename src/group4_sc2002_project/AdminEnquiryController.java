package group4_sc2002_project;

public class AdminEnquiryController extends EnquiryController {
	Application application;

	AdminEnquiryController(Application application) {
		this.application = application;
		this.project = application.getProject();
	}

	Enquiry getEnquiry(int id) {
		return project.getEnquiry(id);
	}

	void replyEnquiry(Enquiry enquiry, String reply) {

	}

}