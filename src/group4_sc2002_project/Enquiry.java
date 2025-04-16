package group4_sc2002_project;

import java.time.LocalDate;

public class Enquiry {
	private int id;
	private Applicant applicant;
	private String content;
	private LocalDate createdDate;
	private String reply;

	Enquiry(int id, Applicant applicant, String message, LocalDate createdDate) {
		this.id = id;
		this.applicant = applicant;
		this.content = message;
		this.createdDate = createdDate;
		this.reply = "";
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

	LocalDate getCreatedDate() {
		return createdDate.now();
	}

	String getReply() {
		return reply;
	}

	void setReply(String reply) {
		this.reply = reply;
	}

}
