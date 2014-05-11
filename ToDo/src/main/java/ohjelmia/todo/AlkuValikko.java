package ohjelmia.todo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AlkuValikko implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("ToDo-list SetUp");

        frame.setPreferredSize(new Dimension(250, 100));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        frame.setLocation((screenSize.width / 2) - 125, (screenSize.height / 2) - 50);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        JTextField teksti = new JTextField();
        
        JButton nappi = new JButton("Submit");
        nappi.setMnemonic('s');
        JButton nappi3 = new JButton("Load");
        nappi3.setMnemonic('l');
        JButton nappi2 = new JButton("Ready");
        nappi2.setMnemonic('r');
        
        JPanel paneeli = new JPanel(new GridLayout(1, 3));
        paneeli.add(nappi);
        paneeli.add(nappi3);
        paneeli.add(nappi2);

        container.add(teksti);
        container.add(paneeli);

        Kuuntelija kuuntelija = new Kuuntelija(frame, teksti, nappi, nappi2, nappi3);
        nappi.addActionListener(kuuntelija);
        nappi2.addActionListener(kuuntelija);
        nappi3.addActionListener(kuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }
}
