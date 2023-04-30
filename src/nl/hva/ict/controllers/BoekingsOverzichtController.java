package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.BoekingsOverzicht;
import nl.hva.ict.views.BoekingsoverzichtView;
import nl.hva.ict.views.View;

public class BoekingsOverzichtController extends Controller {

    private BoekingsoverzichtView boekingsoverzichtView;
    private ObservableList<BoekingsOverzicht> boekingsOverzichtObservableList;

    public BoekingsOverzichtController() {
        boekingsoverzichtView = new BoekingsoverzichtView();
        boekingsOverzichtObservableList = FXCollections.observableList(MainApplication.getMySQLBoekingsOverzicht().getAll());

        boekingsoverzichtView.getBoekingsOverzichtListView().setItems(boekingsOverzichtObservableList);
    }

    @Override
    public View getView() {
        return boekingsoverzichtView;
    }
}
