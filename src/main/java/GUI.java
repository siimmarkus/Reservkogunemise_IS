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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collections;

public class GUI extends Application {

    public VBox registreerimisAken(){
        VBox jaotus = new VBox();
        jaotus.setSpacing(20);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        //==============================================================================================================
        /**
         * Juhendav tekst akna ülevar ääres.
         */
        Text infoTekst = new Text(" Uue isiku registreerimine\n");


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
        isikukoodiVäli.setMaxWidth(90);

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

        //==============================================================================================================
        /**
         * Registreerimisnupp ja registreerimistingimuste kontroll(unikaalne isikukood, )
         */
        Text registreerimisTagasiside = new Text();

        Button registreerimisNupp = new Button("Registreeri");
        registreerimisNupp.setDisable(false);
        registreerimisNupp.setOnMouseClicked(event -> {
            boolean võimalik = true;
            for (TextField laps: tekstiVäljadeMassiiv) {
                if (laps.getText().equals("")){
                    võimalik = false;
                    registreerimisTagasiside.setText("Mõni lahter on veel täitmata.");
                    return;
                }
            }
            if (!Andmebaasid.getHashÜksused().containsKey(üksuseVäli.getText())){
                võimalik = false;
                registreerimisTagasiside.setText("Sellise numbriga üksust ei leidu.");
            }

            if (Andmebaasid.getHashIsikud().containsKey(isikukoodiVäli.getText())){
                võimalik = false;
                registreerimisTagasiside.setText("Selle isikukoodiga isik on juba registreeritud.");
            }

            if (võimalik){
                Isik registreeritav = new Isik(isikukoodiVäli.getText(), eesnimeVäli.getText(),
                        perenimeVäli.getText(), üksuseVäli.getText(), ametiVäli.getText());
                Andmebaasid.lisaIsik(isikukoodiVäli.getText(), registreeritav);
                Aruanne.lisaInimene(registreeritav);
                registreerimisTagasiside.setText("Edukalt registreeritud: " + eesnimeVäli.getText() + " " + perenimeVäli.getText());
            }
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
        //grid.setGridLinesVisible(true);
        grid.setVgap(10);
        grid.setHgap(50);

        vbox.setPadding(new Insets(40,0,0,40));

        Text pealkiri = new Text("Ladude hetkeseisud");
        pealkiri.setFont(new Font(20));
        pealkiri.setUnderline(true);
        vbox.getChildren().add(pealkiri);

        Ladu[] laod = Andmebaasid.getHashLaod().values().toArray(Ladu[]::new);

        for (int i = 0; i < laod.length; i++) {
            VBox üksLadu = new VBox();
            Ladu ladu = laod[i];
            Text laonimi = new Text("\nÜksuse " + ladu.getÜksus() + " ladu");
            laonimi.setFont(new Font(15));
            laonimi.setUnderline(true);
            üksLadu.getChildren().add(laonimi);

            Text[] väärtused = {
                    new Text("  pükse: " + ladu.getPüksid()),
                    new Text("  soblesid: " + ladu.getSobled()),
                    new Text("  frentše: " + ladu.getFrentšid()),
                    new Text("  särke: " + ladu.getSärgid()),
                    new Text("  magamiskotte: " + ladu.getMagamiskotid() + "\n")
            };
            üksLadu.getChildren().addAll(väärtused);
            grid.add(üksLadu, i%3, i/3);

        }
        vbox.getChildren().add(grid);
        return vbox;
    }

    public Group aruandeAken(){
        Group aken = new Group();
        VBox aruandeRead = new VBox();

        Collections.sort(Aruanne.formeerunud);
        if (Aruanne.formeerunud.size() > 0){
            Text üksus = new Text(Andmebaasid.getHashÜksused().get(Aruanne.formeerunud.get(0).getÜksus()));
            aruandeRead.getChildren().add(üksus);

            for (int i = 0; i < Aruanne.formeerunud.size()-1; i++) {
                HBox reaSisu = new HBox();
                if (Aruanne.formeerunud.get(i).getÜksus().equals(Aruanne.formeerunud.get(i+1).getÜksus())){
                    Text isikukood = new Text(Aruanne.formeerunud.get(i).getIsikukood());
                    Text nimi = new Text(Aruanne.formeerunud.get(i).getE_nimi() + " " +  Aruanne.formeerunud.get(i).getP_nimi());
                    Text amet = new Text(Aruanne.formeerunud.get(i).getAmet());
                    Text relvad = new Text(Andmebaasid.getHashRelvad().get(Aruanne.formeerunud.get(i).getIsikukood()).toString());
                    reaSisu.getChildren().addAll(isikukood, nimi, amet, relvad);
                    aruandeRead.getChildren().add(reaSisu);
                }
                else {
                    Text isikukood = new Text(Aruanne.formeerunud.get(i).getIsikukood());
                    Text nimi = new Text(Aruanne.formeerunud.get(i).getE_nimi() + " " +  Aruanne.formeerunud.get(i).getP_nimi());
                    Text amet = new Text(Aruanne.formeerunud.get(i).getAmet());
                    Text relvad = new Text(Andmebaasid.getHashRelvad().get(Aruanne.formeerunud.get(i).getIsikukood()).toString());
                    reaSisu.getChildren().addAll(isikukood, nimi, amet, relvad);
                    aruandeRead.getChildren().add(reaSisu);
                    üksus = new Text(Andmebaasid.getHashÜksused().get(Aruanne.formeerunud.get(i+1).getÜksus()));
                    aruandeRead.getChildren().add(üksus);
                }
            }

        }
        aken.getChildren().addAll(aruandeRead);
        //todo: (kuvada aruande preview?). Nupp aruande väljastamiseks.
        return aken;
    }

    @Override
    public void start(Stage peaLava) {
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