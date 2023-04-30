package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.ReserveringsOverzicht;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.BoekingsoverzichtPerKlantView;
import nl.hva.ict.views.View;

public class BoekingsOverzichtPerKlantController extends Controller {

    private BoekingsoverzichtPerKlantView boekingsoverzichtPerKlantView;
    private ObservableList<Reiziger> reizigers;

    public BoekingsOverzichtPerKlantController() {
        boekingsoverzichtPerKlantView = new BoekingsoverzichtPerKlantView();
        boekingsoverzichtPerKlantView.getComboBox().setOnAction(e-> getBoekingsOverzicht());
        load();
    }

    private void getBoekingsOverzicht() {
        if (boekingsoverzichtPerKlantView.getComboBox().getSelectionModel().isEmpty()) return;
        Reiziger reiziger = (Reiziger)boekingsoverzichtPerKlantView.getComboBox().getSelectionModel().getSelectedItem();

//        List<ReserveringsOverzicht> list = MainApplication.getMySQLBoekingsOverzicht().get(reiziger.getReizigersCode());
        ObservableList<ReserveringsOverzicht> reserveringsOverzicht = FXCollections.observableList(MainApplication.getMySQLBoekingsOverzicht().getBoekingVoor(reiziger.getReizigersCode()));
        boekingsoverzichtPerKlantView.getBoekingsOverzichtListView().setItems(reserveringsOverzicht);
        load();
    }

    private void load(){
        reizigers = FXCollections.observableArrayList(MainApplication.getMySQLReizigers().getAll());
        boekingsoverzichtPerKlantView.getComboBox().setItems(reizigers);
    }

    @Override
    public View getView() {
        return boekingsoverzichtPerKlantView;
    }
}
