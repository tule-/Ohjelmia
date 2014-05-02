package ohjelmia.todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Kuuntelija implements ActionListener {

    private JFrame frame;
    private JTextField Teksti;
    private JButton Nappi;
    private JButton Nappi2;
    private ArrayList<String> lista;
    private Kayttis K;

    Kuuntelija(JFrame frame, JTextField teksti, JButton nappi, JButton nappi2) {
        this.Teksti = teksti;
        this.frame = frame;
        this.Nappi = nappi;
        this.Nappi2 = nappi2;

        this.lista = new ArrayList<String>();
        this.K = new Kayttis();
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
                lista.add(Teksti.getText().substring(0, 1).toUpperCase() + Teksti.getText().substring(1).toLowerCase());
            }

            //Listan päivitys
            K.paivitalista(lista);
            K.run();

            Teksti.setText("");
            Teksti.requestFocus();

        } else if (e.getSource() == Nappi2) {
            frame.dispose();
        }
    }
}
