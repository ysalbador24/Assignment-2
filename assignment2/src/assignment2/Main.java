package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		HelpDesk helpDesk = new HelpDesk();
		
		// create at least 3 technicians, including two w/ the same name to demonstrate HashSet<Technician> behavior
		Technician tech1 = new Technician("Tom"); 
		Technician tech2 = new Technician("Tom");
		Technician tech3 = new Technician("Jerry");
		Technician tech4 = new Technician("Spike");
		
		// Demonstrate HashSet<Technician> behavior
		Set<Technician> technicians = new HashSet<>();
		technicians.add(tech1);
		technicians.add(tech2);
		technicians.add(tech3);
		technicians.add(tech4);
		
		System.out.println("Number of unique Technicians: " + technicians.size());
		System.out.println("Technicians in HashSet: " + technicians);
		
		// create 6 support requests, at least 2 of each request type
		DeviceSetupRequest deviceRequest1 = new DeviceSetupRequest(101, "New Laptop", 100, 1);
		DeviceSetupRequest deviceRequest2 = new DeviceSetupRequest(102, "Installing Monitors", 200, 5);
		
		NetworkIssueRequest netRequest1 = new NetworkIssueRequest(201, "Router Offline", 125, 5);
		NetworkIssueRequest netRequest2 = new NetworkIssueRequest(202, "Phone Offline", 50, 1);
		
		PasswordResetRequest passRequest1 = new PasswordResetRequest(301, "Reset Deb's password", 25, "deb9");
		PasswordResetRequest passRequest2 = new PasswordResetRequest(302, "Reset Mark's password", 25, "mark3");
		
		// add requests to help desk
		
		helpDesk.addRequest(deviceRequest1);
		helpDesk.addRequest(deviceRequest2);
		helpDesk.addRequest(netRequest1);
		helpDesk.addRequest(netRequest2);
		helpDesk.addRequest(passRequest1);
		helpDesk.addRequest(passRequest2);
		
		// assign at least 4 requests across at least 2 technicians
		helpDesk.assignRequest(tech1, 101);
		helpDesk.assignRequest(tech1, 102);
		helpDesk.assignRequest(tech3, 301);
		helpDesk.assignRequest(tech4, 202);
		
		// resolve at least 3 requests across different request types
		helpDesk.resolveRequest(tech1, 101);
		helpDesk.resolveRequest(tech3, 301);
		helpDesk.resolveRequest(tech4, 202);
		
		// print all requests
		helpDesk.printAllRequests();
		
		// print assignments
		helpDesk.printAssignmentsFor(tech1);
		helpDesk.printAssignmentsFor(tech3);
		helpDesk.printAssignmentsFor(tech4);
		
		// print final technician service point totals
		List<Technician> listTechnicians = new ArrayList<>();
		listTechnicians.add(tech1);
		listTechnicians.add(tech3);
		listTechnicians.add(tech4);
		System.out.println("Total points accross technicians: " + HelpDeskUtil.sumPoints(listTechnicians) + " points");
		HelpDeskUtil.printAll(listTechnicians);
		
		
		// Testing
		//helpDesk.addRequest(deviceRequest1); // Testing: adding same request twice
		//helpDesk.assignRequest(tech1, 101); // Testing: assigning the same request to the same technician, not allowed
		//helpDesk.assignRequest(tech3, 101); // Testing: assigning the same request to another technician, not allowed
		//helpDesk.resolveRequest(tech3, 301); // Testing: resolving a request twice is not allowed
		
		
		
	}

}
