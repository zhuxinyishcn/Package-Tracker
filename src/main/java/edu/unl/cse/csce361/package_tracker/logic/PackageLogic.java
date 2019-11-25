package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Scaning;

public class PackageLogic {
	Scaning scan = new Scaning();

	public void checkPackageStatus() {

	}

	public void newPackage() {
		String login = null;
		System.out.println("Please input your login:");
		scan.userIn();
		System.out.println();
	}

	public void cancelPackage() {
		String login = null;
		System.out.println("Please input your login:");
		scan.userIn();
	}
}
