package group4_sc2002_project;
import java.util.*;

public class OfficerEnquiryService implements OfficerEnquiryView {
	private Project project;

	@Override
	public void displayEnquiries() {	
		List<Enquiry>enquiries = project.getEnquiries();
		for (Enquiry enquiry: enquiries) {
			System.out.println("Enquiry ID: " +enquiry.getId() + ", Applicant: "+ enquiry.getApplicant().getUserID() + ", Enquiry:" + enquiry.getContent() +", Reply: " + enquiry.getReply());
		}
	}
	
	public void replyEnquiries(int EnquiryID, String reply) {
		List<Enquiry>enquiries = project.getEnquiries();
		for (Enquiry enquiry: enquiries) {
			if (enquiry.getId() == EnquiryID) {
				enquiry.setReply(reply);
			}
		}
	}
	
	
}
