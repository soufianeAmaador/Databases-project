package nl.hva.ict.models;

import java.time.LocalDate;

public class Reservering {
    private String idReserveringen;
    private LocalDate aankomstDatum;
    private LocalDate vertrekDatum;
    private boolean betaald;
    private String reizigersCode;
    private String accommodatiecode;

    public Reservering(String idReserveringen, LocalDate aankomstDatum, LocalDate vertrekDatum, boolean betaald, String reizigersCode, String accommodatiecode) {
        this.idReserveringen = idReserveringen;
        this.aankomstDatum = aankomstDatum;
        this.vertrekDatum = vertrekDatum;
        this.betaald = betaald;
        this.reizigersCode = reizigersCode;
        this.accommodatiecode = accommodatiecode;
    }

    public String getIdReserveringen() {
        return idReserveringen;
    }

    public void setIdReserveringen(String idReserveringen) {
        this.idReserveringen = idReserveringen;
    }

    public LocalDate getAankomstDatum() {
        return aankomstDatum;
    }

    public void setAankomstDatum(LocalDate aankomstDatum) {
        this.aankomstDatum = aankomstDatum;
    }

    public LocalDate getVertrekDatum() {
        return vertrekDatum;
    }

    public void setVertrekDatum(LocalDate vertrekDatum) {
        this.vertrekDatum = vertrekDatum;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public String getReizigersCode() {
        return reizigersCode;
    }

    public void setReizigersCode(String reizigersCode) {
        this.reizigersCode = reizigersCode;
    }

    public String getAccommodatiecode() {
        return accommodatiecode;
    }

    public void setAccommodatiecode(String accommodatiecode) {
        this.accommodatiecode = accommodatiecode;
    }
}
