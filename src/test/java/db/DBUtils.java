package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/flightdb",
                "root",
                "Database@96145"
        );
    }

    public List<String> getFlights(String source, String destination) throws Exception {

        Connection conn = getConnection();

        String query = "SELECT flight_number FROM flights WHERE source=? AND destination=?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, source);
        ps.setString(2, destination);

        ResultSet rs = ps.executeQuery();

        List<String> flights = new ArrayList<>();

        while (rs.next()) {
            flights.add(rs.getString("flight_number"));
        }

        conn.close();
        return flights;
    }
}