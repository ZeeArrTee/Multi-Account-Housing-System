package group4_sc2002_project;

import java.time.LocalDate;

public class Enquiry {
	private int id;
	private Applicant applicant;
	private String content;
	private LocalDate createdDate;
	private String reply;
	private Project project;

	Enquiry(int id, Applicant applicant, Project project, String message, LocalDate createdDate) {
		this.id = id;
		this.applicant = applicant;
		this.content = message;
		this.createdDate = createdDate;
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

	LocalDate getCreatedDate() {
		return createdDate;
	}

	String getReply() {
		return reply;
	}

	void setReply(String reply) {
		this.reply = reply;
	}

	String getProjectName() {
		return project.getProjectName();
	}

}
