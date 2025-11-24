package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Forestilling {
    private final String navn;
    private final LocalDate startDato;
    private final LocalDate slutDato;
    private final ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Forestilling(String navn, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public ArrayList<Bestilling> getBestillinger() {
        return new ArrayList<>(bestillinger);
    }

    public Bestilling createBestilling(LocalDate date, Kunde kunde, ArrayList<Plads> pladser){
        Bestilling bestilling = new Bestilling(this, date, kunde, pladser);
        bestillinger.add(bestilling);
        kunde.addBestilling(bestilling);
        return bestilling;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public boolean erPladsLedig(int række, int nr, LocalDate dato){
        for (Bestilling bestilling : bestillinger){
            if (bestilling.getDate().equals(dato)){
                for (Plads plads : bestilling.getPladser()) {
                    if (plads.getNr() == nr && plads.getRække() == række){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int antalBestiltePladserPåDag(LocalDate dato){
        int sumAntalDage = 0;
        for (Bestilling bestilling : bestillinger){
            sumAntalDage += bestilling.getPladser().size();
        }
        return sumAntalDage;
    }

    public LocalDate succesDato (){
        LocalDate dato = startDato;
        LocalDate endDato = slutDato.plusDays(1);
        LocalDate bestDato = startDato;
        int bestAntal = 0;
        for (int i = 0; dato.plusDays(i).isBefore(endDato); i++) {
            if (antalBestiltePladserPåDag(dato)>bestAntal){
                bestAntal = antalBestiltePladserPåDag(dato);
                bestDato = dato.plusDays(i);
            }
        }
        return bestDato;
    }

    @Override
    public String toString() {
        return "Forestilling{" +
                "navn='" + navn + '\'' +
                ", startDato=" + startDato +
                ", slutDato=" + slutDato +
                '}';
    }
}
