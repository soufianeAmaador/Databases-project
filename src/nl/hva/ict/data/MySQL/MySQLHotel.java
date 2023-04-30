package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Hotel;
import nl.hva.ict.models.Lodge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLHotel extends MySQL{
    private List<Hotel> hotels;

    public MySQLHotel() {
        hotels = new ArrayList<>();
        load();
    }

    private void load() {

        String sql = "SELECT Hotel.*, accommodatie.*" +
                "from hotel " +
                "INNER JOIN accommodatie ON Hotel.accommodatiecode = accommodatie.accommodatiecode";

        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                String accommodatiecode = rs.getString("accommodatiecode");
                String naam = rs.getString("naam");
                String stad = rs.getString("stad");
                String land = rs.getString("land");
                String kamer = rs.getString("kamer");
                int personen = rs.getInt("personen");
                double prijspernacht = rs.getDouble("prijs p.n");
                boolean ontbijt = rs.getBoolean("ontbijt");
                Hotel hotel = new Hotel(accommodatiecode, naam, stad, land, kamer, personen, prijspernacht, ontbijt);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List getAll() {
        return hotels;
    }

    @Override
    public Object get(String id) {

        String sql = "SELECT accommodatiecode,naam,stad,land,kamer,personen" +
                " FROM Accommodatie WHERE accommodatiecode = '" + id + "';";

        String sql2 = "SELECT `prijs p.n`,ontbijt FROM Hotel WHERE accommodatiecode = " + id +";";


        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            String accommodatiecode = rs.getString("accommodatiecode");
            String naam = rs.getString("naam");
            String stad = rs.getString("stad");
            String land = rs.getString("land");
            String kamer = rs.getString("kamer");
            int personen = rs.getInt("personen");

            ps = getStatement(sql2);
            rs = executeSelectPreparedStatement(ps);

            int prijsPerNacht = rs.getInt("'prijs p.n'");
            boolean ontbijt = rs.getBoolean("ontbijt");

            return new Lodge(accommodatiecode,naam,stad,land,kamer,personen,prijsPerNacht,ontbijt);
        }catch (SQLException E){
            E.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Object object) {
        Hotel hotel = (Hotel)object;

        String sql = "INSERT INTO Accommodatie (accommodatiecode,naam,stad,land,kamer,personen)" +
                "VALUES ('" + hotel.getAccommodatieCode() + "','" + hotel.getNaam() + "','" + hotel.getStad() + "','" +
                hotel.getLand() + "','" + hotel.getKamer() + "'," + hotel.getPersonen() + ");";

        String sql2 = "INSERT INTO Hotel (`prijs p.n`, ontbijt,accommodatiecode) VALUES (" +
                hotel.getPrijsPerNacht() + "," + hotel.isOntbijt() + ",'" + hotel.getAccommodatieCode() + "');";

        try{
            PreparedStatement ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);

            ps = getStatement(sql2);
            executeUpdatePreparedStatement(ps);
        }catch (SQLException E){
            E.printStackTrace();
        }


    }

    @Override
    public void update(Object object) {
        Hotel hotel = (Hotel)object;

        String sql = "UPDATE Accommodatie SET naam = '" + hotel.getNaam() + "',stad = '" + hotel.getStad() + "',land = " + hotel.getLand() +
                "',kamer = '" + hotel.getKamer() + "',personen = '" + hotel.getPersonen() + "' WHERE accommodatiecode = '" +
                hotel.getAccommodatieCode() + "';";


        String sql2 = "UPDATE Hotel SET `prijs p.n` = '" + hotel.getPrijsPerNacht() + "',autohuur = " +
                hotel.isOntbijt() + " WHERE accommodatiecode = '" + hotel.getAccommodatieCode() + "';";

        try{
            PreparedStatement ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);

            ps = getStatement(sql2);
            executeUpdatePreparedStatement(ps);
        }catch (SQLException E){
            E.printStackTrace();
        }
    }

    @Override
    public void remove(Object object) {

        Hotel hotel = (Hotel) object;

//        String sql = "DELETE from Accommodatie WHERE accommodatiecode = '" + hotel.getAccommodatieCode() + "';";
//        String sql2 = "DELETE from Hotel WHERE accommodatiecode = '" + hotel.getAccommodatieCode() + "';";
        String sql = "CALL verwijderAccommodatie('" + hotel.getAccommodatieCode() + "');";

        try {
            PreparedStatement ps = getStatement(sql);
//            executeUpdatePreparedStatement(ps);

            ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void reload(){
        hotels.clear();
        load();
    }
}
