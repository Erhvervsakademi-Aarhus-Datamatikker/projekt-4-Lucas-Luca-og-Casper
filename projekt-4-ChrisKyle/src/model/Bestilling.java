package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bestilling {
    private final LocalDate date;
    private Kunde kunde;
    private ArrayList<Plads> pladser = new ArrayList<>();
    private final Forestilling forestilling;

    public Bestilling(Forestilling forestilling, LocalDate date, Kunde kunde, ArrayList<Plads> pladser) {
        this.date = date;
        this.kunde = kunde;
        this.pladser = pladser;
        this.forestilling = forestilling;
    }

    public LocalDate getDate() {
        return date;
    }

    protected void setKunde(Kunde kunde) {
        this.kunde = kunde;
        kunde.addBestilling(this);
    }

    public ArrayList<Plads> getPladser() {
        return pladser;
    }

    public int getSamletPris(){
        int samletPris = 0;
        for (Plads plads : pladser){
            samletPris += plads.getPris();
        }
        return samletPris;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Forestilling getForestilling() {
        return forestilling;
    }

    @Override
    public String toString() {
        return "Bestilling{" +
                "date: " + date + "\n" +
                ", kunde :" + kunde.getNavn() + "\n" +
                pladser + "\n" +
                ", forestilling: " + forestilling.getNavn() +
                '}';
    }
}
