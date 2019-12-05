package edu.unl.cse.csce361.package_tracker.logic;

import java.math.BigDecimal;

public class CalculateDistance {

	public static void main(String[] args) {
		//https://google-developers.appspot.com/maps/documentation/utils/geocoder/
		System.out.println(distance(40.817663, -96.700037, 40.830192, -96.667434, "M") + " Miles"); // city union to east union
		System.out.println(distance(41.303222, -95.892392, 40.852008,-96.756752, "K") + " Kilometers"); // OMA to LNK
		System.out.println(distance(39.511154,116.411856, 40.64266,-73.77679, "N") + " Nautical Miles"); //PKX to JFK
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			BigDecimal bd = new BigDecimal(dist);
			return (bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
	}
}