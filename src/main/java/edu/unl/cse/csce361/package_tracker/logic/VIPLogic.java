package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class VIPLogic {
	public static void changeDestitation(String trackingNumber, String destitationLogin) {
		// TODO: Using @trackingNumber to set @receiver as @destitation
		Printer.printlogicChangeDestitation(trackingNumber, destitationLogin);
	}
}
