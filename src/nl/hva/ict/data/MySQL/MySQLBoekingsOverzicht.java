package nl.hva.ict.data.MySQL;

import nl.hva.ict.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLBoekingsOverzicht extends MySQL {


    public List<ReserveringsOverzicht> getBoekingVoor(String reizigerscode) {

        List<ReserveringsOverzicht> reserveringen = new ArrayList<>();

        String sql = "SELECT reservering.*, accommodatie.*, reiziger.*, hotel.*, safari_lodge.*\n" +
                "FROM ((((reservering\n" +
                "INNER JOIN accommodatie ON reservering.accommodatiecpde = accommodatie.accommodatiecode)\n" +
                "INNER JOIN reiziger on reservering.reizigerscode = reiziger.reizigercode)\n" +
                "LEFT JOIN hotel ON (hotel.accommodatiecode IS NOT NULL AND accommodatie.accommodatiecode = hotel.accommodatiecode))\n" +
                "LEFT JOIN safari_lodge ON (safari_lodge.accommodatiecode IS NOT NULL AND accommodatie.accommodatiecode = safari_lodge.accommodatiecode))\n" +
                "WHERE reiziger.reizigercode = '" + reizigerscode + "';";

        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()) {

                String reizigersCode = rs.getString("reizigerscode");
                String accommodatiecode = rs.getString("accommodatiecpde");


                //accommodatie
                String accommodatieNaam = rs.getString("naam");
                String stad = rs.getString("stad");
                String landAccommodatie = rs.getString("land");
                String kamer = rs.getString("kamer");
                int personen = rs.getInt("personen");


                //subtype hotel
                int prijsPerNacht = rs.getInt("prijs p.n");
                boolean ontbijt = rs.getBoolean("ontbijt");
                Hotel hotel = new Hotel(accommodatiecode, accommodatieNaam, stad, landAccommodatie, kamer, personen,
                        prijsPerNacht, ontbijt);


                //subtype lodge
                int prijsPerWeek = rs.getInt("prijs per week");
                boolean autohuur = rs.getBoolean("autohuur");
                Lodge lodge = new Lodge(accommodatiecode, accommodatieNaam, stad, landAccommodatie, kamer, personen,
                        prijsPerNacht, ontbijt);


                //reiziger
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String adres = rs.getString("adres");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String landReiziger = rs.getString("land");
                String hoodsreiziger = rs.getString("hoofdreiziger");
                Reiziger reiziger = new Reiziger(reizigersCode, voornaam, achternaam, adres, postcode, plaats,
                        landReiziger, hoodsreiziger);


                //reeservering
                String reserveringId = rs.getString("idReserveringen");
                Date aankomstDateTime = rs.getDate("aankomstdatum");
                LocalDate aankomstDatum = aankomstDateTime.toLocalDate();
                aankomstDateTime = rs.getDate("vertrekdatum");
                LocalDate vertrekDatum = aankomstDateTime.toLocalDate();
                boolean betaald = rs.getBoolean("betaald");
                Reservering reservering = new Reservering(reserveringId, aankomstDatum, vertrekDatum, betaald,
                        reizigersCode, accommodatiecode);


                ReserveringsOverzicht reserveringsOverzicht = new ReserveringsOverzicht(reiziger, hotel, lodge,reservering);

//                System.out.println(reserveringsOverzicht);
                reserveringen.add(reserveringsOverzicht);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reserveringen;
    }

    public List<Reiziger> geboektOp(String pCode, LocalDate pDatum){

        List<Reiziger> reizigers = new ArrayList<>();

        String sql = "SELECT GeboektOp('" + pCode + "','" + pDatum + "');";
        String sql2 = "SELECT *" +
                "FROM reiziger " +
                "INNER JOIN reservering on reservering.reizigerscode = reiziger.reizigercode" +
                " WHERE reservering.accommodatiecpde = '" + pCode + "';";

        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);






            while(rs.next()){

                PreparedStatement ps2 = getStatement(sql2);
                ResultSet rs2 = executeSelectPreparedStatement(ps2);

                while(rs2.next()){

                    String reizigersCode = rs2.getString("reizigercode");
                    String voornaam = rs2.getString("voornaam");
                    String achternaam = rs2.getString("achternaam");
                    String adres = rs2.getString("adres");
                    String postcode = rs2.getString("postcode");
                    String plaats = rs2.getString("plaats");
                    String landReiziger = rs2.getString("land");
                    String hoodsreiziger = rs2.getString("hoofdreiziger");

                    Reiziger reiziger = new Reiziger(reizigersCode, voornaam, achternaam, adres, postcode, plaats,
                            landReiziger, hoodsreiziger);

                    reizigers.add(reiziger);
                }



            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return reizigers;
    }

    @Override
    public List getAll() {

        List<BoekingsOverzicht> boekingsOverzichtLijst = new ArrayList<>();

        String sql = "Select * from BoekingsOverzicht;";
        try{
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = executeSelectPreparedStatement(ps);

            while (rs.next()){
                String naam = rs.getString("naam");
                String stad = rs.getString("stad");
                String land = rs.getString("land");
                String kamer = rs.getString("kamer");
                String reizigerNaam = rs.getString("rezigerNaam");
                Date aankomstDateTime = rs.getDate("aankomstdatum");
                LocalDate aankomstDatum = aankomstDateTime.toLocalDate();
                int aantalDagenVerblijf = rs.getInt("dagenVerblijven");
                boolean betaald = rs.getBoolean("betaald");

                BoekingsOverzicht boekingsOverzicht = new BoekingsOverzicht(naam,stad,land,kamer,reizigerNaam,aankomstDatum,aantalDagenVerblijf,betaald);
                boekingsOverzichtLijst.add(boekingsOverzicht);


            }

        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }




        return boekingsOverzichtLijst;
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void remove(Object object) {

    }
}
