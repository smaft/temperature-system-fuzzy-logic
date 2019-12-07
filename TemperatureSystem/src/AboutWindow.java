import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutWindow extends JDialog {

    private static final long serialVersionUID = 1L;
    private JLabel l0, l1, l2, l3, l4, l5;
    private JLabel lBorder, lIcon;
    private Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
    private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
    private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
    private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
    private Border line = null;
    private JPanel panel;

    /**
     * Konstruktor bezparametryczny klasy <code>AboutWindow</code>
     */

    public AboutWindow() {
        initAboutWindow();
    }

    public void initAboutWindow() {
        this.setTitle("Informacje o programie");
        this.setVisible(true);
        this.setModal(true);
        this.setSize(360,240);
        this.setResizable(false);

        this.addWindowListener	(new WindowAdapter(){
            // obsluga wcisniecia przycisku zamkniecia okna
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });

        Dimension dialogSize = getSize();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(dialogSize.height > screenSize.height)
            dialogSize.height = screenSize.height;
        if(dialogSize.width > screenSize.width)
            dialogSize.height = screenSize.width;

        setLocation((screenSize.width-dialogSize.width)/2,
                (screenSize.height-dialogSize.height)/2);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);

        l0 = new JLabel("Aplikacja XYZ");
        l0.setFont(font1);
        l0.setHorizontalAlignment(SwingConstants.CENTER);

        l1 = new JLabel("wersja 1.0.1");
        l1.setFont(font1);
        l1.setHorizontalAlignment(SwingConstants.CENTER);

        l2 = new JLabel("Copyright (C) by 2019");
        l2.setFont(font2);
        l2.setHorizontalAlignment(SwingConstants.CENTER);

        l3 = new JLabel("Aleksandra Zajączkowska");
        l3.setFont(font3);
        l3.setHorizontalAlignment(SwingConstants.CENTER);

        l4 = new JLabel("Politechnika Koszalińska");
        l4.setFont(font3);
        l4.setHorizontalAlignment(SwingConstants.CENTER);

        l5 = new JLabel("aleksandrazajaczkowska1@gmail.pl");
        l5.setFont(font4);
        lBorder = new JLabel("");
        line = new EtchedBorder(EtchedBorder.LOWERED);

        panel = new JPanel();
        panel.add(new JLabel(new ImageIcon("resources\\icon2.png")));
        //this.add(panel);
        panel.setBackground(Color.WHITE);

        panel.setBounds(10,15,120,150);
        l0.setBounds(135,20,210,30);
        l1.setBounds(135,50,210,30);
        l2.setBounds(135,100,210,20);
        l3.setBounds(135,120,210,20);
        l4.setBounds(135,140,210,20);
        lBorder.setBounds(5,175,dialogSize.width-14,2);
        l5.setBounds(10,184,200,20);

        lBorder.setBorder(line);
        contentPane.add(l0);
        contentPane.add(l1);
        contentPane.add(l2);
        contentPane.add(l3);
        contentPane.add(l4);
        contentPane.add(l5);
        contentPane.add(panel);
        contentPane.add(lBorder);
    }

}
