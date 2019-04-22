import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Collections;

public class GUI extends Application {

    public BorderPane registreerimisAken(){
        BorderPane jaotus = new BorderPane();

        //==============================================================================================================
        /**
         * Juhendav tekst akna ülevar ääres.
         */
        Text infoTekst = new Text(" Uue isiku registreerimine\n");
        infoTekst.setTextAlignment(TextAlignment.CENTER);


        //==============================================================================================================
        /**
         * Infoväljad iga sisestatava väärtuse jaoks.
         */
        VBox väljadeNimed = new VBox();
        väljadeNimed.setSpacing(22);
        väljadeNimed.getChildren().addAll(new Text(" Isikukood: "), new Text(" Eesnimi: "), new Text(" Perenimi: "),
                new Text(" Üksus: "), new Text(" Amet: "));


        //==============================================================================================================
        /**
         * Tekstiväljad info sisestamiseks
         */
        VBox tekstiVäljad = new VBox();
        tekstiVäljad.setSpacing(10);

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
        tekstiVäljad.getChildren().addAll(tekstiVäljadeMassiiv);

        //==============================================================================================================
        /**
         * Registreerimisnupp
         */
        Text registreerimisTagasiside = new Text();

        Button registreerimisNupp = new Button("Registreeri");
        registreerimisNupp.setDisable(false);
        registreerimisNupp.setOnMouseClicked(event -> {
            boolean võimalik = true;
            for (TextField laps: tekstiVäljadeMassiiv) {
                if (laps.getText().equals("")){
                    võimalik = false;
                    break;
                }
            }
            if (!Andmebaasid.getHashÜksused().containsKey(üksuseVäli.getText())){
                võimalik = false;
                registreerimisTagasiside.setText("Sellise numbriga üksust ei leidu.");
            }

            if (võimalik){
                Isik registreeritav = new Isik(isikukoodiVäli.getText(), eesnimeVäli.getText(),
                        perenimeVäli.getText(), üksuseVäli.getText(), ametiVäli.getText());
                Andmebaasid.lisaIsik(isikukoodiVäli.getText(), registreeritav);
                Aruanne.lisaInimene(registreeritav);
                registreerimisTagasiside.setText("Edukalt registreeritud: " + registreeritav.toString());
            }
        });

        väljadeNimed.getChildren().addAll(registreerimisNupp);




        //==============================================================================================================



        //Registreerimisnupu paigutus

        jaotus.setTop(infoTekst);
        jaotus.setLeft(väljadeNimed);
        jaotus.setCenter(tekstiVäljad);
        jaotus.setBottom(registreerimisTagasiside);




        //aken.getChildren().add(jaotus);

        return jaotus;
    }

    public Group ladudeAken(){
        Group aken = new Group();
        //todo: visuaalselt ladude seisud
        return aken;
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
        piir.setCenter(registreerimisAken());

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