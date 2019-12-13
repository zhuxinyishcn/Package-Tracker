package edu.unl.cse.csce361.package_tracker.backend;

public class DronePackage {
	String trackingNumber;
	String status;

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DronePackage(String trackingNumber, String status) {
		super();
		this.trackingNumber = trackingNumber;
		this.status = status;
	}

}
