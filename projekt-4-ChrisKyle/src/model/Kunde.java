package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kunde {
    private final String navn;
    private final String mobil;
    private final ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Kunde(String navn, String mobil) {
        this.navn = navn;
        this.mobil = mobil;
    }

    public String getNavn() {
        return navn;
    }

    public String getMobil() {
        return mobil;
    }

    protected void addBestilling(Bestilling bestilling){
        if (!bestillinger.contains(bestilling)){
            bestillinger.add(bestilling);
            bestilling.setKunde(this);
        }
    }

    public ArrayList<Plads> bestiltePladserTilForestillingPåDat(Forestilling forestilling, LocalDate dato){
        ArrayList<Plads> pladserPåDagTilForestilling = new ArrayList<>();
        for (Bestilling bestilling : bestillinger) {
            if (bestilling.getDate() == dato){
                if (bestilling.getForestilling()==forestilling){
                    pladserPåDagTilForestilling.addAll(bestilling.getPladser());
                }
            }
        }
        return pladserPåDagTilForestilling;
    }


    @Override
    public String toString() {
        return "Kunde{" +
                "navn='" + navn + '\'' +
                ", mobil='" + mobil + '\'' +
                '}';
    }
}
