import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class GUI extends Application {


    public String registreeri(String isikukood, String eesnimi, String perenimi, String üksus, String amet){
        String[] andmed = {isikukood, eesnimi, perenimi, üksus, amet};
        //Kontrollib, kas kõik lahtrid on täidetud.
        for (String laps: andmed) {
            if (laps.equals("")){
                return "Mõni lahter on veel täitmata.";
            }
        }

        try{
            Isik registreeritav = new Isik(isikukood, eesnimi,
                    perenimi, üksus, amet);
            Andmebaasid.lisaIsik(isikukood, registreeritav);
            Aruanne.lisaInimene(registreeritav);
            return "Edukalt registreeritud: " + eesnimi + " " + perenimi;
        } catch (mitteEksisteerivaÜksuseExeption | isikJubaRegistreeritudExeption e) {
            return e.getMessage();
        }
    }

    public VBox registreerimisAken(){
        VBox jaotus = new VBox();
        jaotus.setSpacing(20);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        //==============================================================================================================
        /**
         * Juhendav tekst akna ülevar ääres. Ja registreerimise tagasiside.
         */
        Text infoTekst = new Text(" Uue isiku registreerimine\n");
        Text registreerimisTagasiside = new Text();


        //==============================================================================================================
        /**
         * Infoväljad iga sisestatava väärtuse jaoks.
         */
        Node[] väljadeNimedMassiiv = {new Text(" Isikukood: "), new Text(" Eesnimi: "), new Text(" Perenimi: "),
                new Text(" Üksus: "), new Text(" Amet: ")};


        //==============================================================================================================
        /**
         * Tekstiväljad info sisestamiseks
         */


        TextField isikukoodiVäli = new TextField();
        isikukoodiVäli.setPromptText("39906257092");
        isikukoodiVäli.setMaxWidth(110);

        TextField eesnimeVäli = new TextField();
        eesnimeVäli.setPromptText("Toomas");
        eesnimeVäli.setMaxWidth(200);

        TextField perenimeVäli = new TextField();
        perenimeVäli.setPromptText("Tamm");
        perenimeVäli.setMaxWidth(200);

        TextField üksuseVäli = new TextField();
        üksuseVäli.setPromptText("21");
        üksuseVäli.setMaxWidth(50);

        TextField ametiVäli = new TextField();
        ametiVäli.setPromptText("Autojuht");
        ametiVäli.setMaxWidth(200);

        TextField[] tekstiVäljadeMassiiv = {isikukoodiVäli, eesnimeVäli, perenimeVäli, üksuseVäli, ametiVäli};
        //Suvalises tekstikastis enteri vajutamisel toimub registreerimine.
        for (TextField väli:tekstiVäljadeMassiiv) {
            väli.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER){
                    registreerimisTagasiside.setText(registreeri(isikukoodiVäli.getText(), eesnimeVäli.getText(),
                            perenimeVäli.getText(), üksuseVäli.getText(), ametiVäli.getText()));
                }
            });
        }

        //==============================================================================================================
        /**
         * Registreerimisnupp
         */

        Button registreerimisNupp = new Button("Registreeri");
        registreerimisNupp.setDisable(false);
        registreerimisNupp.setOnMouseClicked(event -> {
            registreerimisTagasiside.setText(registreeri(isikukoodiVäli.getText(), eesnimeVäli.getText(),
                    perenimeVäli.getText(), üksuseVäli.getText(), ametiVäli.getText()));
        });


        //==============================================================================================================
        /**
         * Lisan kõik väljade nimed ja tekstiväljad GridPane'le
         */
        for (int i = 0; i < väljadeNimedMassiiv.length; i++) {
            grid.add(väljadeNimedMassiiv[i], 0,i);
            grid.add(tekstiVäljadeMassiiv[i], 1,i);
        }
        grid.add(registreerimisNupp, 1, väljadeNimedMassiiv.length);


        //==============================================================================================================


        jaotus.getChildren().addAll(infoTekst, grid, registreerimisNupp, registreerimisTagasiside);
        return jaotus;
    }

    public VBox ladudeAken(){

        VBox vbox = new VBox();
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);


        vbox.setPadding(new Insets(40,0,0,40));

        Text pealkiri = new Text("Ladude hetkeseisud");
        pealkiri.setFont(new Font(25));
        vbox.getChildren().add(pealkiri);

        Ladu[] laod = Andmebaasid.getHashLaod().values().toArray(Ladu[]::new);

        for (int i = 0; i < laod.length; i++) {
            VBox üksLadu = new VBox();
            Ladu ladu = laod[i];
            Text laonimi = new Text("Üksuse " + ladu.getÜksus() + " ladu:");
            laonimi.setFont(new Font(18));
            //laonimi.setUnderline(true);
            üksLadu.getChildren().add(laonimi);

            Text[] väärtused = {
                    new Text("\n      pükse: " + ladu.getPüksid()),
                    new Text("      soblesid: " + ladu.getSobled()),
                    new Text("      frentše: " + ladu.getFrentšid()),
                    new Text("      särke: " + ladu.getSärgid()),
                    new Text("      magamiskotte: " + ladu.getMagamiskotid())
            };
            üksLadu.getChildren().addAll(väärtused);
            üksLadu.setPadding(new Insets(20,20,20,20));
            grid.add(üksLadu, i%3, i/3);

        }
        vbox.getChildren().add(grid);
        return vbox;
    }

    public VBox aruandeAken() {
        VBox aruandeRead = new VBox();

        Collections.sort(Aruanne.formeerunud);
        if (Aruanne.formeerunud.size() > 0){
            Text üksus = new Text(Andmebaasid.getHashÜksused().get(Aruanne.formeerunud.get(0).getÜksus()));
            aruandeRead.getChildren().add(üksus);

            int j = 1;
            Text järjek;
            Text isikukood;
            Text nimi;
            Text amet;
            Text relvad;
            for (int i = 0; i < Aruanne.formeerunud.size(); i++) {
                HBox reaSisu = new HBox();

                järjek = new Text(j+". ");
                isikukood = new Text(Aruanne.formeerunud.get(i).getIsikukood());
                nimi = new Text(" " + Aruanne.formeerunud.get(i).getE_nimi() + " " +  Aruanne.formeerunud.get(i).getP_nimi());
                amet = new Text(" " + Aruanne.formeerunud.get(i).getAmet());

                if (Andmebaasid.getHashRelvad().get(Aruanne.formeerunud.get(i).getIsikukood()) == null){
                    relvad = new Text(" -");
                }
                else {
                    relvad = new Text(Andmebaasid.getHashRelvad().get(Aruanne.formeerunud.get(i).getIsikukood()).toString());
                }

                reaSisu.getChildren().addAll(järjek, isikukood, nimi, amet, relvad);
                aruandeRead.getChildren().add(reaSisu);
                j++;
                if (i < Aruanne.formeerunud.size()-1){
                    if (!Aruanne.formeerunud.get(i).getÜksus().equals(Aruanne.formeerunud.get(i+1).getÜksus())){
                        üksus = new Text(Andmebaasid.getHashÜksused().get(Aruanne.formeerunud.get(i+1).getÜksus()));
                        aruandeRead.getChildren().add(üksus);
                        j = 1;
                    }
                }
            }
        }
        Button trüki = new Button("Trüki");
        trüki.setOnMouseClicked(event -> {
            try {
                Aruanne.kirjutaAruanne();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        aruandeRead.getChildren().add(trüki);
        return aruandeRead;
    }

    @Override
    public void start(Stage peaLava){
        BorderPane piir = new BorderPane();
        Group avaleht = new Group();
        Text tutvustus = new Text("Kaitseväe infosüsteemi GUI");
        avaleht.getChildren().addAll(tutvustus);

        //Siin peaks olema "avaleht", katsetamise ajal muudan
        piir.setCenter(ladudeAken());

        ListView<String> list = new ListView<String>();
        list.setMaxWidth(150);
        ObservableList<String> items = FXCollections.observableArrayList(
                "Avaleht",
                "Registreerimine",
                "Ladude seisud",
                "Aruanne");
        list.setItems(items);
        list.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            switch (newValue) {
                case "Avaleht":
                    piir.setCenter(avaleht);
                    break;
                case "Registreerimine":
                    piir.setCenter(registreerimisAken());
                    break;
                case "Ladude seisud":
                    piir.setCenter(ladudeAken());
                    break;
                case "Aruanne":
                    piir.setCenter(aruandeAken());
                    break;
                default: piir.setCenter(avaleht);
            }




        });
        piir.setLeft(list);


        // stseeni loomine ja näitamine
        Scene stseen1 = new Scene(piir, 1000, 500, Color.SNOW);
        peaLava.setTitle("Kaitseväe infosüsteem");
        peaLava.setResizable(false);
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    public static void main(String[] args) throws Exception{
        Andmebaasid.looAndmebaasid();
        launch(args);
    }
}