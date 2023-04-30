package nl.hva.ict.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.hva.ict.MainApplication;
import nl.hva.ict.models.Hotel;
import nl.hva.ict.views.HotelView;
import nl.hva.ict.views.View;

public class HotelController extends Controller {
    private ObservableList<Hotel> hotels;
    private HotelView hotelView;

    public HotelController() {
        hotelView = new HotelView();
        hotelView.getHotelsViewListView().getSelectionModel().selectedItemProperty()
                .addListener(e -> getItemsInFields());
        hotelView.getBtDelete().setOnAction(e -> delete());
        hotelView.getBtNew().setOnAction(e -> insert());
        hotelView.getBtUpdateData().setOnAction(e -> refreshData());
        hotelView.getBtSave().setOnAction(e -> save());
        loadData();
    }
    private void loadData(){
        hotels = FXCollections.observableArrayList(MainApplication.getMySQLHotel().getAll());
        hotelView.getHotelsViewListView().setItems(hotels);

    }

    private void refreshData(){
        MainApplication.getMySQLHotel().reload();
    }



    private void save(){
        // bewaar (update) record
        Hotel hotel = new Hotel(hotelView.getTxtAccommodatieCode().getText(), hotelView.getTxtNaam().getText(),
                hotelView.getTxtStad().getText(), hotelView.getTxtLand().getText(), hotelView.getTxtKamertype().getText(),
                Integer.valueOf(hotelView.getTxtAantalPersonen().getText()),
                Double.valueOf(hotelView.getTxtPrijsPerNacht().getText()), Boolean.valueOf(hotelView.getCheckOntbijt().getText()));
        MainApplication.getMySQLHotel().update(hotel);
        refreshData();
    }

    private void delete(){
        // delete dit record
        Hotel hotel = hotelView.getHotelsViewListView().getSelectionModel().getSelectedItem();
        MainApplication.getMySQLHotel().remove(hotel);
        refreshData();
    }

    private void insert(){
        //Voeg toe
        Hotel hotel = new Hotel(hotelView.getTxtAccommodatieCode().getText(), hotelView.getTxtNaam().getText(),
                hotelView.getTxtStad().getText(), hotelView.getTxtLand().getText(), hotelView.getTxtKamertype().getText(),
                Integer.valueOf(hotelView.getTxtAantalPersonen().getText()),
                Double.valueOf(hotelView.getTxtPrijsPerNacht().getText()), Boolean.valueOf(hotelView.getCheckOntbijt().getText()));
        MainApplication.getMySQLHotel().add(hotel);
        refreshData();
    }

    private void getItemsInFields(){
        Hotel selectedHotel = hotelView.getHotelsViewListView().getSelectionModel().getSelectedItem();
        hotelView.getTxtAccommodatieCode().setText(selectedHotel.getAccommodatieCode());
        hotelView.getTxtNaam().setText(selectedHotel.getNaam());
        hotelView.getTxtStad().setText(selectedHotel.getStad());
        hotelView.getTxtLand().setText(selectedHotel.getLand());
        hotelView.getTxtKamertype().setText(selectedHotel.getKamer());
        hotelView.getTxtAantalPersonen().setText(String.valueOf(selectedHotel.getPersonen()));
        hotelView.getTxtPrijsPerNacht().setText(String.valueOf(selectedHotel.getPrijsPerNacht()));
        hotelView.getCheckOntbijt().setSelected(selectedHotel.isOntbijt());

    }


    @Override
    public View getView() {
        return hotelView;
    }
}
