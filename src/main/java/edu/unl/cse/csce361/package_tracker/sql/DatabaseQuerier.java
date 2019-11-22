package edu.unl.cse.csce361.package_tracker.sql;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseQuerier {

    private Connection conn = null;

    public DatabaseQuerier () {
        createConnection();
    }

    public static void closeConnection (Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            // check ResultSet if still on
            if (rs != null && !rs.isClosed())
                rs.close();
            // check PreparedStatement if still on
            if (ps != null && !ps.isClosed())
                ps.close();
            // check Connection if still on
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }


    private void createConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConn () {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery (String query, ArrayList<?> inputs) {
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            if (inputs != null) {
                for (int i = 0; i < inputs.size(); i++) {
                    if (inputs.get(i) instanceof String)
                        ps.setString(i + 1, (String) inputs.get(i));
                    if (inputs.get(i) instanceof Integer)
                        ps.setInt(i + 1, (Integer) inputs.get(i));
                }
            }
            rs = ps.executeQuery();
            // remember to close rs
        } catch (SQLException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void executeUpdate (String query, ArrayList<?> inputs) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            if (inputs != null) {
                for (int i = 1; i < inputs.size(); i++) {
                    if (inputs.get(i - 1) instanceof String)
                        ps.setString(i, (String) inputs.get(i));
                    if (inputs.get(i - 1) instanceof Integer)
                        ps.setInt(i, (Integer) inputs.get(i));
                }
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}