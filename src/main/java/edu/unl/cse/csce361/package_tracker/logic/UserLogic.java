package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.Sender;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class UserLogic {
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
	private final static logicFacade logic = logicFacade.getInstance();

	public static String checkUser(String userName) {

		return BACKEND_FACADE.searchUserStatus(userName);
	}

	public static void becomeVIP(String userLogin) {
		Printer.printLogicRequestSuccess("upgrade to VIP");
		BACKEND_FACADE.editSenderStatus(userLogin);
	}

	public static void returnPackage(String trackingNumber) {
		BACKEND_FACADE.editPackageReceiver(trackingNumber);
		Printer.printLogicRequestSuccess("return package");
	}

	public static void checkPackageByTrackingNumber(String trackingNumber) {
		Package packageInfo = BACKEND_FACADE.searchPackage(trackingNumber);
		Printer.printLogicPackageByTrackingNumber(packageInfo);
	}

	public static void checkPackageByUserName(String login) {
		Sender sender = BACKEND_FACADE.searchSender(login);
		Set<Package> packages = sender.getPackageSet();
		Printer.printLogicPackageByUserName(packages);
	}

	public static void newPackage(String userName, String street, String city, String zipCode) {
		GoogleGeocode desitationGeocode = GoogleGeocode.getLatLng(street, city, zipCode);
		Sender user = BACKEND_FACADE.searchSender(userName);

		int desitationWarehouse = logic.findClosestWarehouse(Double.parseDouble(desitationGeocode.getLat()),
				Double.parseDouble(desitationGeocode.getLng()));
		int senderWarehouse = logic.findClosestWarehouse(user.getAddress().getLatitude(),
				user.getAddress().getLongitude());
		double travelDistance = logic.CalculateDistance(Double.parseDouble(desitationGeocode.getLat()),
				Double.parseDouble(desitationGeocode.getLng()), user.getAddress().getLatitude(),
				user.getAddress().getLongitude());

		// TODO: @login and @desinationLogin to create new package.
		// need the disatnce
		String trackingNumber = null;
		Printer.printLogicNewPackage(trackingNumber);
	}

	public static void cancelPackage(String trackingNumber) { // Without return services
		BACKEND_FACADE.deletePakcageRecord(trackingNumber);
		Printer.printLogicCencelPackage(trackingNumber);
	}

	public static void holdAtWarehouse(String trackingNumber) {
		// TODO: Set @trackingNumber to hold.
		// TODO: Get current location to @warehouseID.
		int warehouseID = 1;
		BACKEND_FACADE.editPackageStatus(trackingNumber, "Hold");
		// BACKEND_FACADE.getCurrentLocation(trackingNumber);
		Printer.printLogicHoldWarehouse(warehouseID);
	}

	public static void estimatePackage(String trackingNumber) {
		String time = BACKEND_FACADE.searchPackage(trackingNumber).getEstimateTime();
		final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.from(date.parse(time));
		Printer.printLogicEstimateTime(dateTime);
	}

	public static void arriveNotify(String trackingNumber) {
		// TODO: How to know package arrived.
		Printer.printLogicArriveNotify(trackingNumber);
	}

	public static void confirmReceive(String trackingNumber) {
		BACKEND_FACADE.editPackageArrived(trackingNumber);
		Printer.printLogicRequestSuccess("confirm package" + trackingNumber + "received");
	}
}
