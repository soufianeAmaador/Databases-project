package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Reiziger;
import nl.hva.ict.views.ReizigersView;
import nl.hva.ict.views.View;

public class ReizigerController extends Controller {

    private ReizigersView reizigersView;
    private ObservableList<Reiziger> reizigers;

    public ReizigerController() {
        reizigersView = new ReizigersView();
        reizigersView.getReizigersViewListView().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsInFields());
        reizigersView.getComboReistSamenMet().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsComboBox());
        reizigersView.getBtSave().setOnAction(e-> save());
        reizigersView.getBtUpdateData().setOnAction(e-> refreshData());
        reizigersView.getBtNew().setOnAction(e-> insert());
        reizigersView.getBtDelete().setOnAction(e-> delete());
        loadData();
    }

    private void loadData(){
        //haal de waardes op uit de database
        reizigers = FXCollections.observableArrayList(MainApplication.getMongoReizigers().getAll());
        reizigersView.getReizigersViewListView().setItems(reizigers);
        reizigersView.getComboReistSamenMet().setItems(reizigers);

    }


    private void refreshData(){
        MainApplication.getMySQLReizigers().reload();
    }

    private void save(){
      Reiziger reiziger = new Reiziger(reizigersView.getTxtReizigersCode().getText(), reizigersView.getTxtVoornaam().getText(),
              reizigersView.getTxtAchternaam().getText(), reizigersView.getTxtAdres().getText(), reizigersView.getTxtPostcode().getText(),
              reizigersView.getTxtPlaats().getText(), reizigersView.getTxtLand().getText(), reizigersView.getComboReistSamenMet().getSelectionModel().getSelectedItem().getReizigersCode());
      MainApplication.getMySQLReizigers().update(reiziger);
        refreshData();
    }

    private void delete(){
        // delete dit record
        Reiziger reiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();
        MainApplication.getMySQLReizigers().remove(reiziger);
        refreshData();
    }

    private Reiziger get(String reizigerscode){
        return MainApplication.getMySQLReizigers().get(reizigerscode);
    }

    private void insert(){
        Reiziger reiziger = new Reiziger(reizigersView.getTxtReizigersCode().getText(), reizigersView.getTxtVoornaam().getText(),
                reizigersView.getTxtAchternaam().getText(), reizigersView.getTxtAdres().getText(), reizigersView.getTxtPostcode().getText(),
                reizigersView.getTxtPlaats().getText(), reizigersView.getTxtLand().getText(), reizigersView.getComboReistSamenMet().getSelectionModel().getSelectedItem().getReizigersCode());
        MainApplication.getMySQLReizigers().add(reiziger);
        refreshData();
    }

    private void getItemsInFields(){
        Reiziger currentReiziger = reizigersView.getReizigersViewListView().getSelectionModel().getSelectedItem();
        reizigersView.getTxtReizigersCode().setText(currentReiziger.getReizigersCode());
        reizigersView.getTxtVoornaam().setText(currentReiziger.getVoornaam());
        reizigersView.getTxtAchternaam().setText(currentReiziger.getAchternaam());
        reizigersView.getTxtAdres().setText(currentReiziger.getAdres());
        reizigersView.getTxtPostcode().setText(currentReiziger.getPostcode());
        reizigersView.getTxtPlaats().setText(currentReiziger.getPlaats());
        reizigersView.getTxtLand().setText(currentReiziger.getLand());
        reizigersView.getComboReistSamenMet().setValue(get(currentReiziger.getHoofdreiziger()));
    }

    private void getItemsComboBox(){
        reizigersView.getComboReistSamenMet().setItems(reizigers);

    }


    @Override
    public View getView() {
        return reizigersView;
    }
}
