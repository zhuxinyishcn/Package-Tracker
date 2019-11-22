package edu.unl.cse.csce361.package_tracker.backend;

public class DatabaseReader {
    private static DatabaseQuerier dbq = new DatabaseQuerier();

    public static void addPatron(String name,String login, String streetName, String city, String zipCode){
        String address = streetName+", "+city+", NE "+zipCode;
        dbq.executeUpdate("INSERT INTO `Patron` (`PatronLogin`, `Name`, `Address`) VALUES ('"+login+"', '"+name+"', '"+address+"')",null);
        dbq.closeConn();
    }

    public static void addPackage(String inboundID,String OutboundID){
        dbq.executeUpdate("INSERT INTO `Package` (`InboundID`, `OutboundID`) VALUES ('"+inboundID+"', '"+OutboundID+"');",null);
    }

    public static void updatePackage(String packageID,String currentLocation){
        dbq.executeUpdate("UPDATE `Package` SET `CurrentLocation` = '"+currentLocation+"',`Status`='InTransit' WHERE `Package`.`PackageID` = "+packageID+";",null);
    }

    public static void deliverPackage(String packageID){
        dbq.executeUpdate("UPDATE `Package` SET `Status` = 'Delivered' WHERE `Package`.`PackageID` = "+packageID+";",null);
    }



    public static void main(String[] args) {
        addPatron("Pz3445","PZH","1234 Y ST","Lincoln","68508");
//        addPackage("3","5");
        //updatePackage("000000000000001","3");
        //deliverPackage("000000000000001");
    }

}