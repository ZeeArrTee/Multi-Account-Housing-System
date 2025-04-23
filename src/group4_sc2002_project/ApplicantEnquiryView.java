package group4_sc2002_project;

public interface ApplicantEnquiryView {
	public void submitEnquiry(String message);

	public void editEnquiry(int enquiryId, String message);

	public void deleteEnquiry(int enquiryId);
}
