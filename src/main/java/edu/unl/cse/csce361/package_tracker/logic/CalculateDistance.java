package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.math.BigDecimal;

public class CalculateDistance {
    private static final logicFacade logic = logicFacade.getInstance();
    private final double distance;
    private final int warehouseID;

    public CalculateDistance (double distance, int warehouseID) {
        super();
        this.distance = distance;
        this.warehouseID = warehouseID;
    }

    public static double distance (double lat1, double lon1, double lat2, double lon2, String unit) {
        // Unit can be N = natical miles, K = kilometers, other will be in miles.
        // Using Pythagoras theorem to calculate distance between two points by using
        // Geocode(GPS coordinates)
        if ((lat1 == lat2) && (lon1 == lon2)) {
            // everything is the same
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;// In miles
            if (unit.equals("K")) {// In kilometers
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {// In nautical miles
                dist = dist * 0.8684;
            }
            BigDecimal bd = new BigDecimal(dist);
            // return to round up to 2 decimal place.
            return (bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    public static CalculateDistance findClosestWarehouse (double lat, double lon) {
        // provide the Geocode to find the closest warehouse to user.
        double finaldistance = 1000000;
        int warehouseID = -1;
        double distance = 0;
        for (int i = 0; i <= logic.getWarehouse().size() - 1; i++) {
            distance = logic.CalculateDistance(lat, lon, logic.getWarehouse().get(i).getAddress().getLatitude(),
                    logic.getWarehouse().get(i).getAddress().getLongitude());
            if (finaldistance > distance) {
                finaldistance = distance;
                warehouseID = logic.getWarehouse().get(i).getWarehouseid();
            }
        }
        if (finaldistance > 10.01) {
            // not support user who are 10 mile away from one of our warehouse.
            Printer.printLogicErrNotInServiceRange(finaldistance);
            return new CalculateDistance(0, 0);
        } else {
            return new CalculateDistance(finaldistance, warehouseID);
        }
    }

    public static double findPackageDistance (int warehouse1, int warehouse2) {
        // Get the destence between two warehouse
        return distance(logic.getWarehouse().get(warehouse1 - 1).getAddress().getLatitude(),
                logic.getWarehouse().get(warehouse1 - 1).getAddress().getLongitude(),
                logic.getWarehouse().get(warehouse2 - 1).getAddress().getLatitude(),
                logic.getWarehouse().get(warehouse2 - 1).getAddress().getLongitude(), "M");
    }



    public double getDistance () {
        return distance;
    }

    public int getWarehouseID () {
        return warehouseID;
    }

}