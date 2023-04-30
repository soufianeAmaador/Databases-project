package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Accommodatie;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.GeboektOpView;
import nl.hva.ict.views.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoekingsGeboektOpController extends Controller {

    private GeboektOpView geboektOpView;

    public BoekingsGeboektOpController() {
        geboektOpView = new GeboektOpView();
        geboektOpView.getComboBoxAccommodaties().setOnAction(e -> selectAccommodatie());
        load();

    }

    private void selectAccommodatie() {
        Accommodatie geselecteerdeAccommodatie = (Accommodatie)geboektOpView.getComboBoxAccommodaties().getSelectionModel().getSelectedItem();
        LocalDate geselecteerdeDatum = geboektOpView.getDatePicker().getValue();
        if (geselecteerdeAccommodatie == null || geselecteerdeDatum == null) return;
        List<Reiziger> reizigerList = MainApplication.getMySQLBoekingsOverzicht()
                .geboektOp(geselecteerdeAccommodatie.getAccommodatieCode(), geselecteerdeDatum);

        ObservableList<Reiziger> reizigerObservableList = FXCollections.observableArrayList(reizigerList);

        geboektOpView.getBoekingsOverzichtListView().setItems(reizigerObservableList);
        load();
    }

    private void load(){

        List list = new ArrayList(MainApplication.getMySQLHotel().getAll());
        list.addAll(MainApplication.getMySQLLodges().getAll());

        ObservableList reizigerObservableList = FXCollections.observableArrayList(list);
        geboektOpView.getComboBoxAccommodaties().setItems(reizigerObservableList);
    }
    @Override
    public View getView() {
        return geboektOpView;
    }
}
