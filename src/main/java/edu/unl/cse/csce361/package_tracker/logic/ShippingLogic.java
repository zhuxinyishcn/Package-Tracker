package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.Warehouse;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;

import java.util.ArrayList;

public class ShippingLogic {
    public static ArrayList<Warehouse> warehouse = new ArrayList<>();
    private static logicFacade logic = logicFacade.getInstance();

    public static void hasNextWareHouse (String trackingNumber) {
        int current = 0;
        int destination = -1;
        // TODO: using @trackingNumber to get current and destination.
        if (current == destination) {
            Printer.printLogicArrivePackage(trackingNumber);
        } else {
            nextWarehouse(trackingNumber, current, destination);
        }
    }

    public static void deliverToNext (String trackingNumbner, int nextWarehouse) {
        // TODO: //Count time.
        // TODO: using @trackingNumber @nextWarehouse set status
    }

    public static void nextWarehouse (String trackingNumbner, int current, int destination) {
        if (current < destination) {
            deliverToNext(trackingNumbner, current++);
        } else if (destination > current) {
            deliverToNext(trackingNumbner, current--);
        }
    }

}
