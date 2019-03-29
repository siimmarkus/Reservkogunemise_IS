
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Koodi põhi võetud siit: https://www.thoughtco.com/example-java-code-for-building-a-simple-gui-application-2034066
public class GUI {


    public static void main(String[] args) {
        new GUI();
    }

// Konstruktor loob kõik akna elemendid
    public GUI()
    {
        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("OOP Rühmatöö");
        guiFrame.setSize(400,350);

//Paigutab akna ekraani keskele
        guiFrame.setLocationRelativeTo(null);

//Loon esimese JPaneli mis sisaldab Label-it ja tekstivälja
        final JPanel comboPanel = new JPanel();
        JLabel idKoodiLbl = new JLabel("Isikukood:");
        JTextField isikukood = new JTextField("                            ");
        JButton kontrolliID = new JButton( "Kontrolli");
        comboPanel.add(idKoodiLbl);
        comboPanel.add(isikukood);
        comboPanel.add(kontrolliID);




//Loon uue vaate, kus kuvatakse QR kood või tekst andmebaasis mitteeksisteerimisest
        final JPanel tagasinNupp = new JPanel();
        JButton tagasi = new JButton("Tagasi");
        tagasinNupp.setVisible(false);

        final JPanel qrKoodiPaneel = new JPanel();
        qrKoodiPaneel.add(new JLabel(new ImageIcon("QR.png")));
        qrKoodiPaneel.setVisible(false);


//The ActionListener class is used to handle the
//event that happens when the user clicks the button.
//As there is not a lot that needs to happen we can
//define an anonymous inner class to make the code simpler.
        kontrolliID.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
//When the fruit of veg button is pressed
//the setVisible value of the listPanel and
//comboPanel is switched from true to
//value or vice versa.
                qrKoodiPaneel.setVisible(!qrKoodiPaneel.isVisible());
                comboPanel.setVisible(!comboPanel.isVisible());
                tagasinNupp.setVisible(!comboPanel.isVisible());
            }
        });
//The JFrame uses the BorderLayout layout manager.
//Put the two JPanels and JButton in different areas.
        guiFrame.add(comboPanel, BorderLayout.NORTH);
        guiFrame.add(qrKoodiPaneel, BorderLayout.CENTER);
        guiFrame.add(tagasinNupp, BorderLayout.SOUTH);

//make sure the JFrame is visible
        guiFrame.setVisible(true);
    }
}