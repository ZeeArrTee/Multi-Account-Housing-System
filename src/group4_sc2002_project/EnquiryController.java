package group4_sc2002_project;

public class EnquiryController 
{

    public void submitEnquiry(Applicant applicant, String content) 
    {
        applicant.submitEnquiry(content);
    }

    public void editEnquiry(Applicant applicant, int enquiryId, String newMsg) 
    {
        applicant.editEnquiry(enquiryId, newMsg);
    }

    public void deleteEnquiry(Applicant applicant, int enquiryId) 
    {
        applicant.deleteEnquiry(enquiryId);
    }
}
