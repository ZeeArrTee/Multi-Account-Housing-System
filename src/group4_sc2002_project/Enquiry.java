package group4_sc2002_project;

public class Enquiry {
	private int id;
	private Applicant applicant;
	private String content;
	private String reply;

	public Enquiry(int id, Applicant applicant, String message) {
		this.id = id;
		this.applicant = applicant;
		this.content = message;
		this.reply = "";
	}

	public int getId() {
		return id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String message) {
		reply = message;
	}

}
