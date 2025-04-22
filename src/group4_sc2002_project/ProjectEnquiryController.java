package group4_sc2002_project;

public class ProjectEnquiryController {
	Application application;
	Project project;

	ProjectEnquiryController(Application application) {
		this.application = application;
		this.project = application.getProject();
	}

	Enquiry getEnquiry(int id) {
		return project.getEnquiry(id);
	}

	int makeEnquiry(String content) {
		int id = project.makeEnquiry(application.getApplicant(), content);
		return id;
	}

	void editEnquiry(int id, String newMsg) {
		project.editEnquiry(id, newMsg);
	}

	void deleteEnquiry(int id) {
		project.deleteEnquiry(id);
	}

}
