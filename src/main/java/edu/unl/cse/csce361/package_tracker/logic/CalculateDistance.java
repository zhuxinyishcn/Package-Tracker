package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.math.BigDecimal;

public class CalculateDistance {

    private static final logicFacade logic = logicFacade.getInstance();

    public static double distance (double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
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
            return (bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    public static int findClosestWarehouse (double lat, double lon) {
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
            Printer.printLogicErrNotInServiceRange(finaldistance);
            return -1;
        }
        return warehouseID;
    }

    public static double findPackageDistance (int warehouse1, int warehouse2) {
        return distance(logic.getWarehouse().get(warehouse1 - 1).getAddress().getLatitude(),
                logic.getWarehouse().get(warehouse1 - 1).getAddress().getLongitude(),
                logic.getWarehouse().get(warehouse2 - 1).getAddress().getLatitude(),
                logic.getWarehouse().get(warehouse2 - 1).getAddress().getLongitude(), "M");
    }

    public static void main(String[] args) {
        System.out.println(String.format("%-5s %-30s %-50s", "ID", "Name", "Address"));
        for (int i = 0; i <= logicFacade.getInstance().getWarehouse().size() - 1; i++) {
            System.out.println(logicFacade.getInstance().getWarehouse().get(i).toString());
        }
        System.out.println(findClosestWarehouse(40.817663,-96.700037));
    }

}