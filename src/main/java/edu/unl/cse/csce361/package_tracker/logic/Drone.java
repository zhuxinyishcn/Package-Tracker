package edu.unl.cse.csce361.package_tracker.logic;

public class Drone {
	private String status;
	private int currentLocation;

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

	public Drone(String status, int currentLocation) {
		super();
		this.status = status;
		this.currentLocation = currentLocation;
	}

	@Override
	public String toString() {
		return "Drone [status=" + status + ", currentLocation=" + currentLocation + "]";
	}

}
