import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI_katsetused extends Application {

    @Override
    public void start(Stage peaLava) {
        BorderPane piir = new BorderPane();

        // tekstivälja loomine ja lisamine piiripaanile (üles)
        TextField tekst = new TextField();
        tekst.setText("mingi tekst");
        piir.setTop(tekst);

        // sündmuse lisamine tekstiväljale (klahvisündmus reageerib
        // ainult enter-i vajutamisele)
        tekst.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    tekst.setText("nüüd vajutasin ENTER");
                }
            }
        });

        // listivaate loomine ja lisamine piiripaanile (keskele)
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList("Esimene", "Teine",
                "Kolmas", "Neljas");
        list.setItems(items);
        piir.setCenter(list);

        // listivaate omaduse kuulamine (kui midagi valitakse,
        // siis see omadus muutub)
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                tekst.setText(newValue);
            }
        });


        // teine piiripaan, mis pannakse esimesele piiripaanile alla
        // teisele piiripaanile tuleb 2 nuppu, üks vasakule ja
        // üks paremale
        BorderPane piir2 = new BorderPane();
        Button nupp1 = new Button("1");
        piir2.setLeft(nupp1);
        Button nupp2 = new Button("2");
        piir2.setRight(nupp2);
        piir.setBottom(piir2);


        // hiiresündmuse lisamine teisele piiripaanile
        piir2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                tekst.setText("nüüd uus tekst");
            }
        });


        // klahvisündmuse lisamine esimele nupule
        nupp1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.DIGIT1) {
                    tekst.setText("nüüd 1");
                }
            }
        });


        // hiiresündmuse lisamine teisele nupule
        nupp2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                tekst.setText("nüüd 2");
            }
        });


        // aknasündmuse lisamine
        peaLava.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                // luuakse teine lava
                Stage kusimus = new Stage();
                // küsimuse ja kahe nupu loomine
                Label label = new Label("Kas tõesti tahad kinni panna?");
                Button okButton = new Button("Jah");
                Button cancelButton = new Button("Ei");

                // sündmuse lisamine nupule Jah
                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        kusimus.hide();
                    }
                });

                // sündmuse lisamine nupule Ei
                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        peaLava.show();
                        kusimus.hide();
                    }
                });

                // nuppude grupeerimine
                FlowPane pane = new FlowPane(10, 10);
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(okButton, cancelButton);

                // küsimuse ja nuppude gruppi paigutamine
                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(label, pane);

                //stseeni loomine ja näitamine
                Scene stseen2 = new Scene(vBox);
                kusimus.setScene(stseen2);
                kusimus.show();
            }
        }); //siin lõpeb aknasündmuse kirjeldus


        // stseeni loomine ja näitamine
        Scene stseen1 = new Scene(piir, 300, 500, Color.SNOW);
        peaLava.setTitle("Sündmused");
        peaLava.setResizable(false);
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}