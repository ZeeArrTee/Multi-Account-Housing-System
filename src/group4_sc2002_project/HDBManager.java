package group4_sc2002_project;

public class HDBManager extends User {
	private Project managedProject;

	public HDBManager(String id, String pw, int age, String marital) {
		super(id, pw, age, marital, "Manager");
		managedProject = null;
	}

	public Project getManagedProject() {
		return managedProject;
	}

	public void setManagedProject(Project project) {
		managedProject = project;
	}

//	public Boolean approveRejectWithdrawal(Application application) {
//		Scanner input = new Scanner(System.in);
//		System.out.println(application.getId() + application.getApplicant().getUserID()
//				+ application.getApplicant().getAge() + application.getFlatType());
//		System.out.println("Approve? (Y/N)");
//		while (true) {
//			String decision = input.next();
//			if (decision.compareTo("Y") == 0) {
//				application.setPendingWithdrawal(true);
//				input.close();
//				return true;
//
//			} else if (decision.compareTo("N") == 0) {
//				application.setPendingWithdrawal(false);
//				input.close();
//				return false;
//			}
//		}
//
//	}
}
