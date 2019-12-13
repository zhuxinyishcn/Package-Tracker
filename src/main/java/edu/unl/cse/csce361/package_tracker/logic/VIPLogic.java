package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Address;
import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

public class VIPLogic {

	private static final logicFacade logic = logicFacade.getInstance();
	private static final BackendFacade backend = BackendFacade.getBackendFacade();

	public static void changeDestitation(String trackingNumber, String street, String city, String zipCode) {

		if (trackingNumber.length() >= 40) {
			Printer.printErrInput("Tracking number", "40");
		} else {
			if (street.length() <= 100 && city.length() <= 50 && zipCode.length() <= 10) {
				// Using @trackingNumber to set @receiver address
				Printer.printLogicLoading();
				GoogleGeocode geocode = logic.getLatLng(street, city, zipCode);
				String lat = geocode.getLat();
				String lng = geocode.getLng();
				Address address = new Address(street, city, zipCode, Double.parseDouble(lat), Double.parseDouble(lng));
				backend.editReceiverAddress(trackingNumber, address);
				for (int i = 0; i < logic.getDispatchingPackage().size(); i++) {
					if (logic.getDispatchingPackage().get(i).getTrackingNumber().equals(trackingNumber)) {
						logic.getDispatchingPackage().get(i).getReceiver().setDestination(
								logic.findClosestWarehouse(Double.parseDouble(lat), Double.parseDouble(lng))
										.getWarehouseID());
					}
				}
				Printer.printLogicRequestSuccess("edit receiver");
			} else {
				Printer.printLogicErrAddress();
			}
		}
	}

}
