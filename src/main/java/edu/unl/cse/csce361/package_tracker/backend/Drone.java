package edu.unl.cse.csce361.package_tracker.backend;

public class Drone {
	private int id;
	private String status;
	private int currentLocation;
	private String trackingNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Drone(int id, String status, int currentLocation, String trackingNumber) {
		super();
		this.id = id;
		this.status = status;
		this.currentLocation = currentLocation;
		this.trackingNumber = trackingNumber;
	}

	@Override
	public String toString() {
		return "Drone [id=" + id + ", status=" + status + ", currentLocation=" + currentLocation + "]";
	}

}
