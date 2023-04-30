package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Lodge;
import nl.hva.ict.views.LodgeView;
import nl.hva.ict.views.View;

public class LodgeController extends Controller {
    private LodgeView lodgeView;
    private ObservableList<Lodge> lodges;

    public LodgeController() {
        lodgeView = new LodgeView();
        lodgeView.getLodgeViewListView().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsInFields());
        lodgeView.getBtDelete().setOnAction(e -> delete());
        lodgeView.getBtNew().setOnAction(e -> insert());
        lodgeView.getBtUpdateData().setOnAction(e -> refreshData());
        lodgeView.getBtSave().setOnAction(e -> save());
        loadData();
    }

    private void loadData(){
        lodges = FXCollections.observableArrayList(MainApplication.getMySQLLodges().getAll());
        lodgeView.getLodgeViewListView().setItems(lodges);

    }

    private void refreshData(){
        MainApplication.getMySQLLodges().reload();
    }



    private void save(){
        // bewaar (update) record
        Lodge lodge = new Lodge(lodgeView.getTxtAccommodatieCode().getText(), lodgeView.getTxtNaam().getText(),
                lodgeView.getTxtStad().getText(), lodgeView.getTxtLand().getText(), lodgeView.getTxtKamertype().getText(),
                Integer.valueOf(lodgeView.getTxtAantalPersonen().getText()),
                Double.valueOf(lodgeView.getTxtPrijsPerWeek().getText()), Boolean.valueOf(lodgeView.getCheckAutohuur().getText()));

        MainApplication.getMySQLLodges().update(lodge);

        refreshData();
    }

    private void delete(){
        // delete dit record
        Lodge lodge = lodgeView.getLodgeViewListView().getSelectionModel().getSelectedItem();
        MainApplication.getMySQLLodges().remove(lodge);

        refreshData();
    }

    private void insert(){
        //Voeg toe
        Lodge lodge = new Lodge(lodgeView.getTxtAccommodatieCode().getText(), lodgeView.getTxtNaam().getText(),
                lodgeView.getTxtStad().getText(), lodgeView.getTxtLand().getText(), lodgeView.getTxtKamertype().getText(),
                Integer.valueOf(lodgeView.getTxtAantalPersonen().getText()),
                Double.valueOf(lodgeView.getTxtPrijsPerWeek().getText()), Boolean.valueOf(lodgeView.getCheckAutohuur().getText()));
/*        System.out.println("all the values: " + lodgeView.getTxtAccommodatieCode().getText() + lodgeView.getTxtNaam().getText() +
                lodgeView.getTxtStad().getText() + lodgeView.getTxtLand().getText() + lodgeView.getTxtKamertype().getText() +
                Integer.valueOf(lodgeView.getTxtAantalPersonen().getText()) +
                Double.valueOf(lodgeView.getTxtPrijsPerWeek().getText()) + Boolean.valueOf(lodgeView.getCheckAutohuur().getText()));*/
        MainApplication.getMySQLLodges().add(lodge);

        refreshData();

    }

    private void getItemsInFields(){
        Lodge selectedLodge = lodgeView.getLodgeViewListView().getSelectionModel().getSelectedItem();
        lodgeView.getTxtAccommodatieCode().setText(selectedLodge.getAccommodatieCode());
        lodgeView.getTxtNaam().setText(selectedLodge.getNaam());
        lodgeView.getTxtStad().setText(selectedLodge.getStad());
        lodgeView.getTxtLand().setText(selectedLodge.getLand());
        lodgeView.getTxtKamertype().setText(selectedLodge.getKamer());
        lodgeView.getTxtAantalPersonen().setText(String.valueOf(selectedLodge.getPersonen()));
        lodgeView.getTxtPrijsPerWeek().setText(String.valueOf(selectedLodge.getPrijsPerWeek()));
        lodgeView.getCheckAutohuur().setSelected(selectedLodge.isAutohuur());

    }


    private void getItemsComboBox(){

    }
    @Override
    public View getView() {
        return lodgeView;
    }
}
