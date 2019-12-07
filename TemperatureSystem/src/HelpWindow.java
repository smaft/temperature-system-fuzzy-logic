import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpWindow extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel panel;

    /**
     * Konstruktor bezparametrowy klasy <code>HelpWindow</code>
     */

    public HelpWindow() {
        initHelpWindow();
    }

    public void initHelpWindow() {
        this.setTitle("Pomoc");
        this.setVisible(true);
        this.setModal(false);
        this.setResizable(true);
        this.setSize(750,750);
        this.setBackground(Color.WHITE);

        this.addWindowListener	(new WindowAdapter(){ // obsluga zdarzenia okna
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }	// koniec metody windowClosing
        });

        Dimension dialogSize = getSize();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(dialogSize.height > screenSize.height)
            dialogSize.height = screenSize.height;
        if(dialogSize.width > screenSize.width)
            dialogSize.height = screenSize.width;

        setLocation((screenSize.width-dialogSize.width)/2,
                (screenSize.height-dialogSize.height)/2);

        panel = new JPanel();
        panel.add(new JLabel(new ImageIcon("resources\\help.png")));
        this.add(panel);
        panel.setBackground(Color.WHITE);

    }

}
