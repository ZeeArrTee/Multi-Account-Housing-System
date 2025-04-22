package group4_sc2002_project;

import java.time.LocalDate;

public class Enquiry {
	private int id;
	private Applicant applicant;
	private String content;
	private LocalDate createdDate;
	private String reply;
	private Project project;

	public Enquiry(int id, Applicant applicant, Project project, String message, LocalDate createdDate) {
		this.id = id;
		this.applicant = applicant;
		this.content = message;
		this.createdDate = createdDate;
		this.reply = "";
		this.project = project;
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

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getProjectName() {
		return project.getProjectName();
	}

}
