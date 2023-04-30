package nl.hva.ict.models;

import nl.hva.ict.data.Identifable;

import java.sql.Date;
import java.time.LocalDate;

public class ReserveringsOverzicht implements Identifable {

    private Reiziger reiziger;
    private Reservering reservering;
    private Hotel hotel;
    private Lodge lodge;




    public ReserveringsOverzicht() {

    }


    public ReserveringsOverzicht(Reiziger reiziger, Hotel hotel, Lodge lodge, Reservering reservering) {
        this.reiziger = reiziger;
        this.hotel = hotel;
        this.lodge = lodge;
        this.reservering = reservering;
    }

    @Override
    public String toString() {
        return String.format("%s %s - reist van %s tot %s en verblijft in: %s in %s- %s",
                reiziger.getVoornaam(), reiziger.getAchternaam(),
                reservering.getAankomstDatum().toString(),reservering.getVertrekDatum().toString(),
                (hotel.getNaam() == null ? lodge.getNaam() : hotel.getNaam()),
                (hotel.getStad() == null ? lodge.getStad() : hotel.getStad()),
                (hotel.getNaam() == null ? lodge.getLand() : hotel.getLand())
                );
    }
}
