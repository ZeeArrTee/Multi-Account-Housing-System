package group4_sc2002_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

public class EnquiryRepository {
	private final String regFile = "EnquiriesList.csv";
	private static List<Enquiry> enquiries;
	
	EnquiryRepository() {
		enquiries = new ArrayList<Enquiry>();
		enquiries = loadEnquiriesFromFile(regFile);
	}
	
	public static List<Enquiry> getEnquiries(){
		return enquiries;
	}
	
	public static void addEnquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}
	
	public List<Enquiry> loadEnquiriesFromFile(String fileName){
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
				String applicant = parts[1];
				String content = parts[2];
				String reply = parts[3];
				
				Enquiry enquiry = Enquiry(id,applicant,content,reply);
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Failed to load: " + fileName);
		}
		return enquiries;
	}
}
