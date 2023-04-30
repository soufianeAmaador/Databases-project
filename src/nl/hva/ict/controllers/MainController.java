package nl.hva.ict.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import nl.hva.ict.MainApplication;
import nl.hva.ict.views.MainView;
import nl.hva.ict.views.View;

import java.util.Optional;

public class MainController extends Controller {

    private MainView mainView;
    private ReizigerController reizigerController;
    private HotelController hotelController;
    private LodgeController lodgeController;
    private AboutController aboutController;
    private BoekingsOverzichtController boekingsOverzichtController;
    private BoekingsOverzichtPerKlantController boekingsOverzichtPerKlantController;
    private BoekingsGeboektOpController boekingsGeboektOpController;

    public MainController() {
        mainView = new MainView();

        //Maak controllers aan
        reizigerController = new ReizigerController();
        hotelController = new HotelController();
        lodgeController = new LodgeController();
        aboutController = new AboutController();
        boekingsOverzichtController = new BoekingsOverzichtController();
        boekingsOverzichtPerKlantController = new BoekingsOverzichtPerKlantController();
        boekingsGeboektOpController = new BoekingsGeboektOpController();

        //default header message
        mainView.getMessage().setText("Home");

        mainView.getStartschermMenuItem().setOnAction(event -> {
            MainApplication.switchController(mainView.createRoot());
            mainView.getMessage().setText("Home");
        });

        mainView.getCloseMenuItem().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Afsluiten?");
            alert.setHeaderText("Klik op OK als u de applicatie wilt afsluiten");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(0);
            }
        });

        mainView.getHotelMenuItem().setOnAction(event -> {
            MainApplication.switchController(hotelController.getView().getRoot());
            mainView.getMessage().setText("Hotels");
        });

        mainView.getLodgeMenuItem().setOnAction(event -> {
            MainApplication.switchController(lodgeController.getView().getRoot());
            mainView.getMessage().setText("Lodges");

        });

        mainView.getReizigersMenuItem().setOnAction(event -> {
            MainApplication.switchController(reizigerController.getView().getRoot());
            mainView.getMessage().setText("Reizigers");
        });

        mainView.getOverzichtAlleBoekingen().setOnAction(event -> {
            // laad hier de controller
            MainApplication.switchController(boekingsOverzichtController.getView().getRoot());
            mainView.getMessage().setText("Boekingsoverzicht");
        });

        mainView.getOverzichtPerKlant().setOnAction(event -> {
            MainApplication.switchController(boekingsOverzichtPerKlantController.getView().getRoot());
            mainView.getMessage().setText("Boekingsoverzicht per klant");
        });

        mainView.getOverzichtGeboektOp().setOnAction(event -> {
            MainApplication.switchController(boekingsGeboektOpController.getView().getRoot());
            mainView.getMessage().setText("Boekingsoverzicht op datum");
        });

        mainView.getAboutMenuItem().setOnAction(event -> {
            MainApplication.switchController(aboutController.getView().getRoot());
            mainView.getMessage().setText("Over deze applicatie");
        });
    }

    public BorderPane getBorderPane() {
        return mainView.getBorderPane();
    }

    @Override
    public View getView() {
        return mainView;
    }
}
