package nl.hva.ict;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nl.hva.ict.controllers.MainController;
import nl.hva.ict.data.MongoDB.MongoReizigers;
import nl.hva.ict.data.MySQL.MySQLBoekingsOverzicht;
import nl.hva.ict.data.MySQL.MySQLHotel;
import nl.hva.ict.data.MySQL.MySQLLodges;
import nl.hva.ict.data.MySQL.MySQLReizigers;
import com.mongodb.MapReduceCommand;
import com.mongodb.*;
public class MainApplication extends Application {

    private static final String TITLE = "Practicumopdracht DB2";

    //MySQL
    private static final String MYSQL_HOST = "jdbc:mysql://localhost:3306/big_five_safari?autoReconnect=true&serverTimezone=UTC&useSSL=False";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";

    //Mongo NoSQL
    private static final String NOSQL_HOST = "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false";
    private static final String NOSQL_DATABASE = "kiesraadDoc";
    //    private static final String NOSQL_HOST = "mongodb+srv://root:root@cluster0-jsrp7.azure.mongodb.net/test?retryWrites=true&w=majority";
    //    private static final String NOSQL_DATABASE = "big_five_safari";

    //JavaFX
    private static Stage stage;
    private static MainController mainController;

    //Static dataconnectors
    private static MySQLReizigers mySQLReizigers = new MySQLReizigers();
    private static MySQLLodges mySQLLodges = new MySQLLodges();
    private static MySQLHotel mySQLHotel = new MySQLHotel();
    private static MySQLBoekingsOverzicht mySQLBoekingsOverzicht = new MySQLBoekingsOverzicht();
    //NoSql MongoDB
    private static MongoReizigers mongoReizigers = new MongoReizigers();

    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    public static void switchController(Parent pane) {
        mainController.getBorderPane().setCenter(pane);
    }

    public static String getMysqlHost() {
        return MYSQL_HOST;
    }

    public static String getMysqlUsername() {
        return MYSQL_USERNAME;
    }

    public static String getMysqlPassword() {
        return MYSQL_PASSWORD;
    }

    public static String getNosqlHost() {
        return NOSQL_HOST;
    }

    public static String getNosqlDatabase() {
        return NOSQL_DATABASE;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public static MySQLReizigers getMySQLReizigers() { return mySQLReizigers; }

    public static MySQLLodges getMySQLLodges() {
        return mySQLLodges;
    }

    public static MySQLHotel getMySQLHotel() {
        return mySQLHotel;
    }

    public static MySQLBoekingsOverzicht getMySQLBoekingsOverzicht() {
        return mySQLBoekingsOverzicht;
    }

    public static MongoReizigers getMongoReizigers() {
        return mongoReizigers;
    }

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        MainApplication.stage.setTitle(TITLE);
        MainApplication.stage.setWidth(WIDTH);
        MainApplication.stage.setHeight(HEIGHT);
        mainController = new MainController();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MainApplication.stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        MainApplication.stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);

        stage.setScene(new Scene(mainController.getView().getRoot()));
        stage.show();



    }
}

