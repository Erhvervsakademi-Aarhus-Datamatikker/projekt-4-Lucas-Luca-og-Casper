package gui;

import controller.Controller;
import javafx.application.Application;
import model.PladsType;
import storage.Storage;

import java.time.LocalDate;

public class App {


    public static void main(String[] args) {
        initStorage();
        //testPrint();
        Application.launch(Gui.class);
    }

    public static void initStorage() {
        Controller controller = new Controller();
        controller.createForestilling("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20));
        controller.createForestilling("Lykke Per", LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 10));
        controller.createForestilling("Chess", LocalDate.of(2023, 8, 21), LocalDate.of(2023, 8, 30));

        controller.createKunde("Anders Hansen", "11223344");
        controller.createKunde("Peter Jensen", "12345678");
        controller.createKunde("Niels Madsen", "12341234");

        for (int række = 1; række <= 15; række++) {
            for (int kolonne = 1; kolonne <= 20; kolonne++) {
                int pris =400;
                if (kolonne % 19 <= 2) {
                    if (række <= 5) {
                        pris = 450;
                    }
                } else {
                    if (række <= 5) {
                        pris = 500;
                    } else if (række <= 10) {
                        pris = 450;
                    }
                }
                PladsType pladsType = PladsType.STANDARD;
                if (kolonne >= 8 && kolonne <= 12) {
                    if (række == 10) {
                        pladsType = PladsType.KØRESTOL;
                    }
                    if (række == 11){
                        pladsType = PladsType.EKSTRABEN;
                    }
                }
                controller.createPlads(række,kolonne,pris,pladsType);
            }
        }
    }

    public static void testPrint(){
        System.out.println("Kunderne");
        for (Object object : Storage.getKunder()){
            System.out.println(object.toString());
        }
        System.out.println(" ");
        System.out.println("Forestillingerne");
        for (Object object : Storage.getForestillinger()){
            System.out.println(object.toString());
        }
        System.out.println(" ");
        System.out.println("Pladserne");
        for (Object object : Storage.getPladser()){
            System.out.println(object.toString());
        }
    }

}
