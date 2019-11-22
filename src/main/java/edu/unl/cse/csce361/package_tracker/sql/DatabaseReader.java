package edu.unl.cse.csce361.package_tracker.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader {
    private static DatabaseQuerier dbq = new DatabaseQuerier();

    public static void addPatron(String name,String login, String street, String city){
        dbq.executeUpdate("INSERT INTO `Address` (`Street`, `City`, `State`, `Type`) VALUES ('"+street+"', '"+city+"', 'NE', 'Patron')",null);
        int addressId = 9999;
        try {
            ResultSet rs = dbq.executeQuery("SELECT LAST_INSERT_ID() FROM Address", null);
            while (rs.next()) {
                addressId = rs.getInt("LAST_INSERT_ID()");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbq.executeUpdate("INSERT INTO `Patron` (`PatronLogin`, `Name`,`AddressID`) VALUES ('"+login+"','"+name+"','"+addressId+"' )",null);
        dbq.closeConn();
    }


    public static void main(String[] args) {
        //addPatron("ha","pz","1234","Lincoln");
    }

}