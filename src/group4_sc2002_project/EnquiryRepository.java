package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EnquiryRepository {
	private final String enquiryFile = "EnquiriesList.csv";
	private static List<Enquiry> enquiries;

	EnquiryRepository() {
		enquiries = new ArrayList<Enquiry>();
		enquiries = loadEnquiriesFromFile(enquiryFile);
	}

	public static List<Enquiry> getEnquiries() {
		return enquiries;
	}

	public static void addEnquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}

	public static void removeEnquiry(Enquiry enquiry) {
		enquiries.remove(enquiry);
	}

	public List<Enquiry> loadEnquiriesFromFile(String fileName) {
		try (BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + fileName))) {
			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				String[] parts = line.split(",");
				if (parts.length < 5) {
					continue;
				}

				int id = Integer.parseInt(parts[0]);
				Applicant applicant = (Applicant) UserRepository.getUser(parts[1]);
				String content = parts[2];
				String reply = parts[3];
				Enquiry enquiry = new Enquiry(id, applicant, content);
				enquiry.setReply(reply);
				applicant.getApplication().getProject().addEnquiry(enquiry);

			}

		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}
		return enquiries;
	}

	public void saveEnquiries() {
		String filep = System.getProperty("user.dir") + "\\src\\group4_sc2002_project\\" + enquiryFile;
		try (PrintWriter pw = new PrintWriter(new FileWriter(filep))) {
			pw.println("Enquiry ID, Applicant ID, Content, Reply");
			for (Enquiry enquiry : enquiries) {

				pw.println(String.join(",", String.valueOf(enquiry.getId()), enquiry.getApplicant().getUserID(),
						enquiry.getContent(), enquiry.getReply()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
