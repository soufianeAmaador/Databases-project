package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.Lodge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLLodges extends MySQL {
    private List<Lodge> lodges;

    public MySQLLodges() {
        lodges = new ArrayList<>();
        load();
    }

    private void load() {

        String sql = "SELECT safari_lodge.*, accommodatie.*" +
                "from safari_lodge " +
                "INNER JOIN accommodatie ON safari_lodge.accommodatiecode = accommodatie.accommodatiecode";

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
                double prijsPerWeek = rs.getDouble("prijs per week");
                boolean autohuur = rs.getBoolean("autohuur");
                Lodge reiziger = new Lodge(accommodatiecode, naam, stad, land, kamer, personen, prijsPerWeek, autohuur);
                lodges.add(reiziger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List getAll() {
        return lodges;
    }

    @Override
    public Object get(String id) {

        String sql = "SELECT accommodatiecode,naam,stad,land,kamer,personen" +
                " FROM Accommodatie WHERE accommodatiecode = '" + id + "';";
        String sql2 = "SELECT 'prijs per week',autohuur FROM safari_lodge WHERE accommodatiecode = '" + id +"';";


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

            int prijsPerWeek = rs.getInt("'prijs per week'");
            boolean autohuur = rs.getBoolean("autohuur");

            return new Lodge(accommodatiecode,naam,stad,land,kamer,personen,prijsPerWeek,autohuur);
        }catch (SQLException E){
            E.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Object object) {
        Lodge lodge = (Lodge)object;

        String sql = "INSERT INTO Accommodatie (accommodatiecode,naam,stad,land,kamer,personen)" +
                "VALUES ('" + lodge.getAccommodatieCode() + "','" + lodge.getNaam() + "','" + lodge.getStad() + "','" +
                lodge.getLand() + "','" + lodge.getKamer() + "','" + lodge.getPersonen() + "');";
        String sql2 = "INSERT INTO safari_lodge (`prijs per week`, autohuur,accommodatiecode) VALUES (" +
                lodge.getPrijsPerWeek() + "," + lodge.isAutohuur() + ",'" + lodge.getAccommodatieCode() + "');";

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
        Lodge lodge = (Lodge)object;

        String sql = "UPDATE Accommodatie SET naam = '" + lodge.getNaam() + "',stad = '" + lodge.getStad() + "',land = '" + lodge.getLand() +
                "',kamer = '" + lodge.getKamer() + "',personen = " + lodge.getPersonen() + " WHERE accommodatiecode = '" +
                lodge.getAccommodatieCode() + "';";

        String sql2 = "UPDATE safari_lodge SET `prijs per week` = '" + lodge.getPrijsPerWeek() + "',autohuur = " +
                lodge.isAutohuur() + " WHERE accommodatiecode = '" + lodge.getAccommodatieCode() + "';";

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

        Lodge lodge = (Lodge) object;

        String sql = "DELETE from Accommodatie WHERE accommodatiecode = '" + lodge.getAccommodatieCode() + "';";

        String sql2 = "DELETE from safari_lodge WHERE accommodatiecode = '" + lodge.getAccommodatieCode() + "';";
        try {
            PreparedStatement ps = getStatement(sql2);
            executeUpdatePreparedStatement(ps);

            ps = getStatement(sql);
            executeUpdatePreparedStatement(ps);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void reload(){
        lodges.clear();
        load();
    }
}
