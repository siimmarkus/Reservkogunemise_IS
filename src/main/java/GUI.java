import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI extends Application {

    public VBox registreerimisAken(){
        VBox aken = new VBox();
        aken.setSpacing(10);

        Text tekst = new Text("Uue isiku registreerimine");
        FlowPane isikukood = new FlowPane(new Text("Isikukood: "), new TextField());
        FlowPane eesnimi = new FlowPane(new Text("Eesnimi: "), new TextField());
        FlowPane perenimi = new FlowPane(new Text("Perenimi: "), new TextField());
        FlowPane üksus = new FlowPane(new Text("Üksus: "), new TextField());
        FlowPane amet = new FlowPane(new Text("Amet: "), new TextField());



        aken.getChildren().addAll(tekst, isikukood);

        return aken;
    }

    public Group ladudeAken(){
        Group aken = new Group();
        //todo: visuaalselt ladude seisud
        return aken;
    }

    public Group aruandeAken(){
        Group aken = new Group();
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
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
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




            }
        });
        piir.setLeft(list);





        // stseeni loomine ja näitamine
        Scene stseen1 = new Scene(piir, 1000, 500, Color.SNOW);
        peaLava.setTitle("Sündmused");
        peaLava.setResizable(false);
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}