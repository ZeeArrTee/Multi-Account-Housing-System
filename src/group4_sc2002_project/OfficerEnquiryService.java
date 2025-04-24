package group4_sc2002_project;

import java.util.List;

public class OfficerEnquiryService implements OfficerEnquiryView {
	private Project project;

	OfficerEnquiryService(Project project) {
		this.project = project;
	}

	public static void displayEnquiries(Project project) {
		List<Enquiry> enquiries = project.getEnquiries();
		for (Enquiry enquiry : enquiries) {
			System.out.println("Enquiry ID: " + enquiry.getId() + ", Applicant: " + enquiry.getApplicant().getUserID()
					+ ", Enquiry:" + enquiry.getContent() + ", Reply: " + enquiry.getReply());
		}
	}

	public void replyEnquiries(int EnquiryID, String reply) {
		List<Enquiry> enquiries = project.getEnquiries();
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == EnquiryID) {
				enquiry.setReply(reply);
			}
		}
	}

}
