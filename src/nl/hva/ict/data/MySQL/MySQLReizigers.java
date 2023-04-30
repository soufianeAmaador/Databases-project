package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Reiziger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLReizigers extends MySQL<Reiziger> {

    private List<Reiziger> reizigers;

    public MySQLReizigers() {
        reizigers = new ArrayList<>();
        load();
    }

    private void load() {

        String sql = "SELECT * FROM Reiziger";

        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {
                String reizigersCode = rs.getString("reizigerCode");
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String adres = rs.getString("adres");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String land = rs.getString("land");
                String hoofdreiziger = rs.getString("hoofdreiziger");
                Reiziger reiziger = new Reiziger(reizigersCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger);
                reizigers.add(reiziger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reiziger> getAll() {
        return reizigers;
    }

    @Override
    public Reiziger get(String id) {
        String sql = "SELECT * FROM Reiziger "+
                "WHERE reizigercode = '" + id + "';";

        try{

            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);
            if (rs.next()){
                String reizigersCode = rs.getString("reizigercode");
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String adres = rs.getString("adres");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String land = rs.getString("land");
                String hoofdreiziger = rs.getString("hoofdreiziger");
                return new Reiziger(reizigersCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger);
            }


        }catch (SQLException E){
            E.printStackTrace();
        }

        return null;
    }

    @Override
    public void add(Reiziger object) {
    String sql = "INSERT INTO Reiziger (reizigercode,voornaam,achternaam,adres,postcode,plaats,land,hoofdreiziger) " +
        "VALUES ('" + object.getReizigersCode() + "','" + object.getVoornaam() + "','" + object.getAchternaam() + "','" +
            object.getAdres() + "','" + object.getPostcode() + "','" + object.getPlaats() + "','" + object.getLand() + "','" +
            object.getHoofdreiziger() + "');";

    try {
        PreparedStatement ps = getStatement(sql);
        executeUpdatePreparedStatement(ps);
    }catch (SQLException E ){
        E.printStackTrace();
    }
    reload();

    }

    @Override
    public void update(Reiziger object) {
        String sql = "UPDATE Reiziger SET voornaam = '" + object.getVoornaam() + "', achternaam = '" + object.getAchternaam() +
                "', adres = '" + object.getAchternaam() + "', postcode = '" + object.getPostcode() + "', plaats ='" + object.getPostcode() +
                "', land = '" + object.getLand() + "', hoofdreizigers = '" + object.getHoofdreiziger() +
                "' WHERE reizigerscode = '" + object.getReizigersCode() + "';";

        try{
            PreparedStatement ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);
        }catch (SQLException E){
            E.printStackTrace();
        }
        reload();

    }

    @Override
    public void remove(Reiziger object) {
        String sql = "DELETE FROM Reiziger WHERE reizigercode = '" + object.getReizigersCode()+ "';";

        try {
            PreparedStatement ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);
        }catch (SQLException E){
            E.printStackTrace();
        }
        reload();


    }

    public void reload(){
        reizigers.clear();
        load();
    }

}
