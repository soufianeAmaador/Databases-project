package nl.hva.ict.models;

public class Hotel extends Accommodatie{
    private String accommodatieCode;
    private String naam;
    private String stad;
    private String land;
    private String kamer;
    private int personen;
    private double prijsPerNacht;
    private boolean ontbijt;

    public Hotel(String accommodatieCode, String naam, String stad, String land, String kamer, int personen, double prijsPerNacht, boolean ontbijt) {
        this.accommodatieCode = accommodatieCode;
        this.naam = naam;
        this.stad = stad;
        this.land = land;
        this.kamer = kamer;
        this.personen = personen;
        this.prijsPerNacht = prijsPerNacht;
        this.ontbijt = ontbijt;
    }

    public Hotel() {
    }

    @Override
    public String toString() {
        return "Hotel: "+ naam + " gevestigd in: " + stad +", " +land + " met: "+ kamer;
    }

    @Override
    public String getAccommodatieCode() {
        return accommodatieCode;
    }

    public void setAccommodatieCode(String accommodatieCode) {
        this.accommodatieCode = accommodatieCode;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getKamer() {
        return kamer;
    }

    public void setKamer(String kamer) {
        this.kamer = kamer;
    }

    public int getPersonen() {
        return personen;
    }

    public void setPersonen(int personen) {
        this.personen = personen;
    }

    public double getPrijsPerNacht() {
        return prijsPerNacht;
    }

    public void setPrijsPerNacht(int prijsPerNacht) {
        this.prijsPerNacht = prijsPerNacht;
    }

    public boolean isOntbijt() {
        return ontbijt;
    }

    public void setOntbijt(boolean ontbijt) {
        this.ontbijt = ontbijt;
    }
}
