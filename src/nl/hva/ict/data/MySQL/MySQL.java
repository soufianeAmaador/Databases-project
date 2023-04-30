package nl.hva.ict.data.MySQL;

import nl.hva.ict.MainApplication;
import nl.hva.ict.data.Identifable;
import nl.hva.ict.data.Data;

import java.sql.*;
import java.util.Properties;

public abstract class MySQL <T extends Identifable> implements Data<T> {

    protected Connection connection;
    private Properties properties;


    public MySQL() {
        connect();
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", MainApplication.getMysqlUsername());
            properties.setProperty("password", MainApplication.getMysqlPassword());
        }
        return properties;
    }

    // connect database
    private void connect() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(MainApplication.getMysqlHost(), getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect DB
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement getStatement(String sql) throws SQLException {
        if (this.connection == null){ connect();}
        return connection.prepareStatement(sql);
    }


    public void executeUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
        if (this.connection == null){ connect();}
        ps.executeUpdate();
    }


    public ResultSet executeSelectPreparedStatement(PreparedStatement ps) throws SQLException {
        if (this.connection == null){ connect();}
        return ps.executeQuery();
    }

}
