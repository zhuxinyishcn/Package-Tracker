package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
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
		Printer.printLogicLoading();
		BACKEND_FACADE.editSenderStatus(userLogin);
		Printer.printLogicRequestSuccess("upgrade to VIP");
	}

	public static void returnPackage(String trackingNumber) {
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			BACKEND_FACADE.editPackageReceiver(trackingNumber);
			Printer.printLogicRequestSuccess("return package");
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}

	public static void checkPackageByTrackingNumber(String trackingNumber) {
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			Package packageInfo = BACKEND_FACADE.searchPackage(trackingNumber);
			Printer.printLogicPackageByTrackingNumber(packageInfo);
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}

	public static void checkPackageByUserName(String login) {
		Printer.printLogicLoading();
		Sender sender = BACKEND_FACADE.searchSender(login);
		Set<Package> packages = sender.getPackageSet();
		Printer.printLogicPackageByUserName(packages);
	}

	public static void newPackage(String userName, String receiver, String street, String city, String zipCode) {
		GoogleGeocode desitationGeocode = GoogleGeocode.getLatLng(street, city, zipCode);
		Printer.printLogicLoading();
		Sender user = BACKEND_FACADE.searchSender(userName);

		CalculateDistance desitationWarehouse = logic.findClosestWarehouse(
				Double.parseDouble(desitationGeocode.getLat()), Double.parseDouble(desitationGeocode.getLng()));
		CalculateDistance senderWarehouse = logic.findClosestWarehouse(user.getAddress().getLatitude(),
				user.getAddress().getLongitude());
		double senderToWarehouse = logic
				.findClosestWarehouse(user.getAddress().getLatitude(), user.getAddress().getLongitude()).getDistance();
		double travelDistance = logic.CalculateDistance(Double.parseDouble(desitationGeocode.getLat()),
				Double.parseDouble(desitationGeocode.getLng()), user.getAddress().getLatitude(),
				user.getAddress().getLongitude());
		if (desitationWarehouse.getDistance() != 0 && senderWarehouse.getDistance() != 0) {
			// BUG
			Address address = new Address(street, city, zipCode, user.getAddress().getLatitude(),
					user.getAddress().getLongitude());
			Receiver receiverInfo = new Receiver(address, receiver, desitationWarehouse.getWarehouseID());
			String trackingNumber = BACKEND_FACADE.addPackageRecord(user, receiverInfo,
					senderWarehouse.getWarehouseID(), travelDistance);
			Printer.printLogicNewPackage(trackingNumber);

			// Pickup
			DronePickUp R1 = new DronePickUp("pick_up_Package" + senderWarehouse, trackingNumber, senderToWarehouse,
					logic.findClosestWarehouse(user.getAddress().getLatitude(), user.getAddress().getLongitude())
							.getWarehouseID());
			R1.start();
		}
	}

	public static void cancelPackage(String trackingNumber) { // Without return services
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			BACKEND_FACADE.deletePakcageRecord(trackingNumber);
			for (int i = 0; i < logic.getDispatchingPackage().size(); i++)
				if (logic.getDispatchingPackage().get(i).getTrackingNumber().equals(trackingNumber)) {
					logic.getDispatchingPackage().get(i).getReceiver()
							.setDestination(logic.getDispatchingPackage().get(i).getCurrentLocation());
				}
			Printer.printLogicCencelPackage(trackingNumber);
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}

	public static void holdAtWarehouse(String trackingNumber) {
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			BACKEND_FACADE.editPackageStatus(trackingNumber, "Hold");
			int warehouseID = BACKEND_FACADE.searchPackage(trackingNumber).getCurrentLocation();
			Printer.printLogicHoldWarehouse(warehouseID);
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}

	public static void estimatePackage(String trackingNumber) {
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			String time = BACKEND_FACADE.searchPackage(trackingNumber).getEstimateTime();
			final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.from(date.parse(time));
			Printer.printLogicEstimateTime(dateTime);
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}


	public static void confirmReceive (String trackingNumber) {
		if (logic.isSender(trackingNumber)) {
			Printer.printLogicLoading();
			BACKEND_FACADE.editPackageArrived(trackingNumber);
			Printer.printLogicRequestSuccess("confirm package" + trackingNumber + "received");
		} else {
			Printer.printLogicNotSender(trackingNumber);
		}
	}
}
