package nl.hva.ict.models;

public class Lodge extends Accommodatie{
    private String accommodatieCode;
    private String naam;
    private String stad;
    private String land;
    private String kamer;
    private int personen;
    private double prijsPerWeek;
    private boolean autohuur;

    public Lodge(String accommodatieCode, String naam, String stad, String land, String kamer, int personen, double prijsPerWeek, boolean autohuur) {
        this.accommodatieCode = accommodatieCode;
        this.naam = naam;
        this.stad = stad;
        this.land = land;
        this.kamer = kamer;
        this.personen = personen;
        this.prijsPerWeek = prijsPerWeek;
        this.autohuur = autohuur;
    }

    public Lodge() {
    }

    @Override
    public String toString() {
        return "Lodge: "+ naam + " gevestigd in: " + stad +", " +land + " met: "+ kamer;
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

    public double getPrijsPerWeek() {
        return prijsPerWeek;
    }

    public void setPrijsPerWeek(double prijsPerWeek) {
        this.prijsPerWeek = prijsPerWeek;
    }

    public boolean isAutohuur() {
        return autohuur;
    }

    public void setAutohuur(boolean autohuur) {
        this.autohuur = autohuur;
    }
}
