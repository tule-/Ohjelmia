package ohjelmia.todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Kuuntelija2 implements ActionListener {

    private ArrayList<String> Lista;
    private ArrayList<JCheckBox> Boxit;
    private JFrame frame;
    private JButton Editnappi;
    private JButton Loadnappi;
    private Kayttis K;
    private File Tiedosto;
    private ArrayList<String> Lista2;
    private int apu = 0;

    public Kuuntelija2(JFrame frame, ArrayList<String> lista, ArrayList<JCheckBox> boxit, JButton editnappi, JButton loadnappi, File tiedosto) {
        this.Lista = lista;
        this.Boxit = boxit;
        this.Editnappi = editnappi;
        this.Loadnappi = loadnappi;
        this.Lista2 = new ArrayList<>();
        this.K = new Kayttis(tiedosto, Lista2);
        this.Tiedosto = tiedosto;
        this.frame = frame;

        Loadnappi.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Editnappi) {
            Runtime rt = Runtime.getRuntime();

            File file = Tiedosto;

            try {
                Process p = rt.exec("notepad " + file);
            } catch (IOException ex) {
                Logger.getLogger(Kuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }

            Loadnappi.setEnabled(true);

        } else if (e.getSource() == Loadnappi) {
            frame.dispose();
            
            try {
                luetiedosto();
            } catch (Exception ex) {
                Logger.getLogger(Kuuntelija2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            K.paivitalista(Lista2);
            K.run();
            
            Loadnappi.setEnabled(false);
        }

        for (JCheckBox JCB : Boxit) {
            if (e.getSource() == JCB) {
                JCB.setEnabled(false);
                apu++;
            }
        }

        if (apu == Boxit.size()) {
            frame.dispose();
        }
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
            Lista2.add(apu[i]);
        }
    }
}
