package group4_sc2002_project;

import java.time.LocalDate;

public class Enquiry {
	private int id;
	private Applicant applicant;
	private String content;
	private String reply;

	Enquiry(int id, Applicant applicant, String content, Project project) {
		this.id = id;
		this.applicant = applicant;
		this.content = content;
		this.reply = "";
		this.project = project;
	}

	int getId() {
		return id;
	}

	Applicant getApplicant() {
		return applicant;
	}

	String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	String getReply() {
		return reply;
	}

	void setReply(String reply) {
		this.reply = reply;
	}

}
