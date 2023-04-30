package nl.hva.ict.views;

import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import nl.hva.ict.models.BoekingsOverzicht;
import nl.hva.ict.models.ReserveringsOverzicht;

public class BoekingsoverzichtView extends View {

    private GridPane rootPane;
    private ListView<BoekingsOverzicht> boekingsOverzichtListView;

    public BoekingsoverzichtView() {
        rootPane = new GridPane();
        boekingsOverzichtListView = new ListView<>();
        createRoot();
    }

    private void createRoot() {
        boekingsOverzichtListView.setPrefWidth(800);
        boekingsOverzichtListView.setPrefHeight(800);
        rootPane.getChildren().addAll(boekingsOverzichtListView);
    }

    public ListView<BoekingsOverzicht> getBoekingsOverzichtListView() {
        return boekingsOverzichtListView;
    }

    @Override
    public Parent getRoot() {
        return rootPane;
    }
}
