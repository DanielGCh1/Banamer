/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



/**
 *
 * @author dgcha
 */
public class Database {

    private static Database theInstance;

    public static Database instance() {
        if (theInstance == null) {
            theInstance = new Database();
        }
        return theInstance;
    }
    public static final String PROPERTIES_FILE_NAME = "/database.properties";
    Connection cnx;

    public Database() {
        cnx = this.getConnection();
    }

    public Connection getConnection() {
        try {
            Properties prop = new Properties();
            URL resourceUrl = getClass().getResource(PROPERTIES_FILE_NAME);
            //URL resourceUrl = getClass().getResource("C:/Users/dgcha/Documents/GitHub/Banamer/BanamerServidor/database.properties");
            File file = new File(resourceUrl.toURI());
            prop.load(new BufferedInputStream(new FileInputStream(file)));
            String driver = prop.getProperty("database_driver");
            String server = prop.getProperty("database_server");
            String port = prop.getProperty("database_port");
            String user = prop.getProperty("database_user");
            String password = prop.getProperty("database_password");
            String database = prop.getProperty("database_name");

            String URL_conexion = "jdbc:mysql://" + server + ":" + port + "/"
                    + database + "?user=" + user + "&password=" + password + "&serverTimezone=UTC";
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return cnx.prepareStatement(statement);
    }

    public int executeUpdate(PreparedStatement statement) {
        try {
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public ResultSet executeQuery(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
        }
        return null;
    }
}
