package edu.unl.cse.csce361.package_tracker.backend;

public class WarehouseConstructor {
	int id;
	String address;
	double latitude;
	double longitude;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public WarehouseConstructor(int id, String address, double latitude, double longitude) {
		super();
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return address;
	}

}
