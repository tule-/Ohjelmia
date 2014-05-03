package ohjelmia.todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Kuuntelija2 implements ActionListener {

    private ArrayList<String> Lista;
    private ArrayList<JCheckBox> Boxit;
    private Kayttis K;
    private JFrame frame;
    private int apu = 0;

    public Kuuntelija2(JFrame frame, ArrayList<String> lista, ArrayList<JCheckBox> boxit) {
        this.Lista = lista;
        this.Boxit = boxit;
        this.K = new Kayttis();
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
}
