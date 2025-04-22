package group4_sc2002_project;

import java.time.LocalDate;

public class Receipt {
	private int receiptId;
	private Applicant applicant;
	private Project project;
	private String flatType;
	private String unitId;
	private LocalDate bookingDate;

	Receipt(int receiptId, Applicant applicant, Project project, String flatType, String unitId,
			LocalDate bookingDate) {
		this.receiptId = receiptId;
		this.applicant = applicant;
		this.project = project;
		this.flatType = flatType;
		this.unitId = unitId;
		this.bookingDate = bookingDate;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public Project getProject() {
		return project;
	}

	public String getFlatType() {
		return flatType;
	}

	public String getUnitId() {
		return unitId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}
}
