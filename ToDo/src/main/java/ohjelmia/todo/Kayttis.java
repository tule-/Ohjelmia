package ohjelmia.todo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Kayttis implements Runnable {
    
    private JFrame frame;
    private ArrayList<String> Lista;
    private File Tiedosto;
    
    public Kayttis(File tiedosto, ArrayList<String> lista){
        this.Tiedosto = tiedosto;
        this.Lista = lista;
    }

    @Override
    public void run() {
        frame = new JFrame();

        frame.setPreferredSize(new Dimension(200, Lista.size()*40));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        frame.setLocation((screenSize.width - 200), 0);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    

    public void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        
        JPanel paneeli = new JPanel(new GridLayout(Lista.size()+1, 1));
        JPanel paneeli2 = new JPanel(new GridLayout(1, 2));
        
        JButton editnappi = new JButton("Edit");
        JButton loadnappi = new JButton("Load");
        
        paneeli2.add(editnappi);
        paneeli2.add(loadnappi);
        
        paneeli.add(paneeli2);
        
        //Boksi lista
        ArrayList<JCheckBox> boxit = new ArrayList<>();
        
        for (String string : Lista) {
            JCheckBox check = new JCheckBox(string);
            
            check.setBackground(Color.WHITE);
            check.setForeground(Color.BLACK);
            check.setOpaque(true);
            
            boxit.add(check);
            
            paneeli.add(check);
        }
        
        container.add(paneeli);
        
        //Boksien toiminnallisuus
        Kuuntelija2 k2 = new Kuuntelija2(frame, Lista, boxit, editnappi, loadnappi, Tiedosto);
        
        editnappi.addActionListener(k2);
        loadnappi.addActionListener(k2);
        
        for (JCheckBox jCheckBox : boxit) {
            jCheckBox.addActionListener(k2);
        }
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void paivitalista(ArrayList<String> lista){
        this.Lista = lista;
    }
}
