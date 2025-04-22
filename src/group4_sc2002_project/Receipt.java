package group4_sc2002_project;

import java.time.LocalDate;

public class Receipt {
	private Applicant applicant;
	private Project project;
	private String flatType;
	private LocalDate bookingDate;

	public Receipt(Applicant applicant, Project project, String flatType, LocalDate bookingDate) {
		this.applicant = applicant;
		this.project = project;
		this.flatType = flatType;
		this.bookingDate = bookingDate;
	}

	public void display() {
		System.out.println("Applicant: " + "Project: " + "Flat Type: " + flatType + "Booking Date" + bookingDate);
	}
}
