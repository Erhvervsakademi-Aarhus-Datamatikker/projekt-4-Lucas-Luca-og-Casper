package storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.util.ArrayList;

public class Storage {
    private static final ObservableList<Kunde> kunder = FXCollections.observableArrayList();
    private static final ObservableList<Plads> pladser = FXCollections.observableArrayList();
    private static final ObservableList<Forestilling> forestillinger = FXCollections.observableArrayList();


    public static void addKunde(Kunde kunde){
        if (!kunder.contains(kunde)) {
            kunder.add(kunde);
        }
    }

    public static void addPlads(Plads plads){
        if (!pladser.contains(plads)) {
            pladser.add(plads);
        }
    }

    public static void addForestilling(Forestilling forestilling){
        if (!forestillinger.contains(forestilling)) {
            forestillinger.add(forestilling);
        }
    }

    public static ObservableList<Forestilling> getForestillinger() {
        return forestillinger;
    }

    public static ObservableList<Kunde> getKunder() {
        return kunder;
    }

    public static ObservableList<Plads> getPladser() {
        return pladser;
    }



}
