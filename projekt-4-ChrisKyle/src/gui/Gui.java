package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bestilling;
import model.Forestilling;
import model.Kunde;
import model.Plads;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Car");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final Controller controller = new Controller();
    private final ListView<Forestilling> listViewForestillinger = new ListView<>(Storage.getForestillinger());
    private final ListView<Kunde> listViewKunder = new ListView<>(Storage.getKunder());
    private final ListView<Plads> listViewPladser = new ListView<>(Storage.getPladser());
    private final TextField textFieldForestillingsNavn = new TextField();
    private final TextField textFieldKundeNavn = new TextField();
    private final TextField textFieldKundeMobil = new TextField();
    private final DatePicker datePickerBestillingDato = new DatePicker();
    private final DatePicker datePickerStartDato = new DatePicker();
    private final DatePicker datePickerSlutDato = new DatePicker();
    private final Button buttonAddKunde = new Button("Opret kunde");
    private final Button buttonAddForestilling = new Button("Opret forestilling");
    private final Button buttonAddBestilling = new Button("Opret Bestilling");

    private void initContent(GridPane pane) {
        setupPane(pane);
        buttonAddForestilling.setOnAction(event -> addForestilling());
        buttonAddKunde.setOnAction(event -> addKunde());
        buttonAddBestilling.setOnAction(event -> addBestilling());
    }

    private void addBestilling() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Forestilling forestilling = listViewForestillinger.getSelectionModel().getSelectedItem();
        Kunde kunde = listViewKunder.getSelectionModel().getSelectedItem();
        LocalDate dato = datePickerBestillingDato.getValue();
        ArrayList<Plads> pladser = new ArrayList<>(listViewPladser.getSelectionModel().getSelectedItems());
        Bestilling bestilling = controller.createBestillingMedPladser(forestilling,kunde,dato,pladser);
        if (bestilling == null){
            alert.setContentText("Fault!");
            alert.showAndWait();
        }
        else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(bestilling.toString());
            alert.showAndWait();
        }

    }

    private void addForestilling() {
        if (textFieldForestillingsNavn.getText().isEmpty() || datePickerStartDato.getValue()==null || datePickerSlutDato.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Missing name or date input for forestilling. Fill the fields and try again");
            alert.showAndWait();
        }
        else {
            String navn = textFieldForestillingsNavn.getText();
            LocalDate startDato = datePickerStartDato.getValue();
            LocalDate slutDato = datePickerSlutDato.getValue();
            controller.createForestilling(navn, startDato, slutDato);
            textFieldForestillingsNavn.clear();
            datePickerStartDato.setValue(null);
            datePickerSlutDato.setValue(null);
        }
    }
    private void addKunde(){
        if (textFieldKundeNavn.getText().isEmpty() || textFieldKundeMobil.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Missing name or mobil input for kunde. Fill the fields and try again");
            alert.showAndWait();
        }
        else {
            String navn = textFieldKundeNavn.getText();
            String mobil = textFieldKundeMobil.getText();
            controller.createKunde(navn, mobil);
            textFieldKundeNavn.clear();
            textFieldKundeMobil.clear();
        }
    }

    private void setupPane (GridPane pane){
        pane.setVgap(10);
        pane.setHgap(20);
        pane.setPadding(new Insets(20));
        listViewPladser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //For Forestilling
        Label labelForestillinger = new Label("Forestillinger");
        pane.add(labelForestillinger,0,0,2,1);
        pane.add(listViewForestillinger, 0, 1,2,1);
        Label labelForestillingNavn = new Label("Forestillingens Navn");
        pane.add(labelForestillingNavn,0,3);
        pane.add(textFieldForestillingsNavn,1,3);
        Label labelStartDato = new Label("Start dato");
        pane.add(labelStartDato,0,4);
        pane.add(datePickerStartDato,1,4);
        Label labelSlutDato = new Label("Slut dato");
        pane.add(labelSlutDato,0,5);
        pane.add(datePickerSlutDato,1,5);
        pane.add(buttonAddForestilling,1,6);
        //For Kunder
        Label labelKunder = new Label("Kunder");
        pane.add(labelKunder,2,0,2,1);
        pane.add(listViewKunder, 2, 1,2,1);
        Label labelKundeNavn = new Label("Kunde navn");
        pane.add(labelKundeNavn,2,3);
        pane.add(textFieldKundeNavn,3,3);
        Label labelKundeMobil = new Label("Kunde mobil");
        pane.add(labelKundeMobil,2,4);
        pane.add(textFieldKundeMobil,3,4);
        pane.add(buttonAddKunde,3,6);
        //For Pladser /Bestilling
        Label labelPladser = new Label("Pladser");
        pane.add(labelPladser,4,0,2,1);
        pane.add(listViewPladser,4,1,2,1);
        Label labelBestillingDato = new Label("Dato");
        pane.add(labelBestillingDato,4,2);
        pane.add(datePickerBestillingDato,4,3);
        pane.add(buttonAddBestilling,4,4);
    }
}
