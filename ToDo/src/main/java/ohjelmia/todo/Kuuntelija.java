package ohjelmia.todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Kuuntelija implements ActionListener {

    private JFrame frame;
    private JTextField Teksti;
    private JButton Nappi;
    private JButton Nappi2;
    private JButton Nappi3;
    private ArrayList<String> lista;
    private Kayttis K;
    private File Tiedosto;

    Kuuntelija(JFrame frame, JTextField teksti, JButton nappi, JButton nappi2, JButton nappi3) {
        this.Teksti = teksti;
        this.frame = frame;
        this.Nappi = nappi;
        this.Nappi2 = nappi2;
        this.Nappi3 = nappi3;
        this.Tiedosto = new File("Todo.txt");
        this.lista = new ArrayList<String>();
        this.K = new Kayttis(Tiedosto, lista);

        //Tarkastetaan onko todo-tiedostoa olemassa
        if (!Tiedosto.exists()) {
            //Disabloidaan load-nappi
            Nappi3.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Nappi) {
            //Vanhan listan poisto
            if (!lista.isEmpty()) {
                K.getFrame().dispose();
            }

            //Tekstin editointi
            if (Teksti.getText().isEmpty()) {
                lista.add(Teksti.getText());
            } else if (Teksti.getText().length() > 25) {
                //Raja tulee vastaan -> mitään ei tehdä
            } else {
                //Ensimmäinen kirjain isoksi
                lista.add(Teksti.getText().substring(0, 1).toUpperCase() + Teksti.getText().substring(1));
            }

            //Kirjoitetaan tiedostoon
            try {
                tiedostokirjoittaja();
            } catch (Exception ex) {
                Logger.getLogger(Kuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Listan päivitys
            K.paivitalista(lista);
            K.run();

            Teksti.setText("");
            Teksti.requestFocus();

            //Disabloidaan load-nappi
            Nappi3.setEnabled(false);

        } else if (e.getSource() == Nappi2) {
            //Poistaa submit-ruudun
            frame.dispose();

        } else if (e.getSource() == Nappi3) {
            //Lukee tiedoston
            try {
                luetiedosto();
            } catch (Exception ex) {
                Logger.getLogger(Kuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Listan päivitys
            K.paivitalista(lista);
            K.run();

            Teksti.setText("");
            Teksti.requestFocus();

            //Disabloidaan load-nappi
            Nappi3.setEnabled(false);
        }
    }

    public void tiedostokirjoittaja() throws Exception {
        FileWriter kirjoittaja = new FileWriter(Tiedosto);

        for (String string : lista) {
            kirjoittaja.write(string);

            if (lista.indexOf(string) != lista.size() - 1) {
                kirjoittaja.write(":");
            } else {
            }
        }
        kirjoittaja.close();
    }

    public void luetiedosto() throws Exception {
        Scanner lukija = new Scanner(Tiedosto);

        String merkkijono = "";
        String jakaja = ":";

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            merkkijono = rivi;
        }
        lukija.close();

        String[] apu;
        apu = merkkijono.split(jakaja);

        for (int i = 0; i < apu.length; i++) {
            lista.add(apu[i]);
        }
    }
}
