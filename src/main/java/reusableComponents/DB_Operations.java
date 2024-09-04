package reusableComponents;

import java.sql.*;
import java.util.HashMap;
/**
 * Author: Srinivas
 */
public class DB_Operations {
    
    public static HashMap<String, String> getSqlResultInMap(String sql) {
        String connectionUrl = "jdbc:sqlserver://bimservicesdb-qa.database.windows.net;databaseName=BimSys;user=bimx05dbqa;password=jVs4xpUsVd35eyr5;encrypt=true;trustServerCertificate=true";
        HashMap<String, String> dataMap = new HashMap<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    // Get column name
                    String columnName = metaData.getColumnName(i);
                    // Get value from ResultSet and store in HashMap
                    String value = resultSet.getString(i);
                    dataMap.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
        } finally {
            // Close resources in finally block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly
            }
        }

        return dataMap;
    }

    public static int executeUpdate(String sql) {
        String connectionUrl = "jdbc:sqlserver://bimservicesdb-qa.database.windows.net;databaseName=BimSys;user=bimx05dbqa;password=jVs4xpUsVd35eyr5";
        Connection connection = null;
        Statement statement = null;
        int rowsAffected = 0;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
        } finally {
            // Close resources in finally block to ensure they are always closed
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly
            }
        }

        return rowsAffected;
    }
}
