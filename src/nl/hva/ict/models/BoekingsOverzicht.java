package nl.hva.ict.models;

import java.time.LocalDate;

public class BoekingsOverzicht {

    private String naam;
    private String stad;
    private String land;
    private String kamer;
    private String reizigerNaam;
    private LocalDate aankomstDatum;
    private int aantalDagenVerblijf;
    private boolean betaald;

    public BoekingsOverzicht(String naam, String stad, String land, String kamer, String reizigerNaam, LocalDate aankomstDatum, int aantalDagenVerblijf, boolean betaald) {
        this.naam = naam;
        this.stad = stad;
        this.land = land;
        this.kamer = kamer;
        this.reizigerNaam = reizigerNaam;
        this.aankomstDatum = aankomstDatum;
        this.aantalDagenVerblijf = aantalDagenVerblijf;
        this.betaald = betaald;
    }

    @Override
    public String toString() {
        return String.format("%s heeft een geboekt op %s om in %s - %s te blijven voor %d dagen",
                reizigerNaam, aankomstDatum.toString(), stad, land, aantalDagenVerblijf);
    }
}
