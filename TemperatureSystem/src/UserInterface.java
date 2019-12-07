import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInterface {

    private JFrame window = new JFrame("Temperature System");

    // Menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu control = new JMenu("Kontrola");
    private JMenu help = new JMenu("Pomoc");
    private JMenuItem c1 = new JMenuItem("Ustaw plan dnia");
    private JMenuItem c2 = new JMenuItem("Pokaż wykresy");
    private JMenuItem c3 = new JMenuItem("Wyjście z aplikacji");
    private JMenuItem h1 = new JMenuItem("Korzystanie z aplikacji");
    private JMenuItem h2 = new JMenuItem("O autorach");

    // Panel kontrolny użytkownika, GridLayout(2, 3)
    private JPanel userPanel = new JPanel(new GridLayout(2, 3));

    // Pierwszy wiersz userPanel
    private JLabel infoTempLabel = new JLabel("Dzisiejsza temperatura:  ", JLabel.LEFT);
    private JLabel showTempLabel = new JLabel(" ", JLabel.LEFT);
    private JLabel celsiusLabel = new JLabel(" °C", JLabel.LEFT);

    // Drugi wiersz userPanel
    private JLabel setTempLabel = new JLabel("Ustaw oczekiwaną temperaturę:  ", JLabel.LEFT);
        private JTextField setTempField = new JTextField(JTextField.LEFT);
    private JButton acceptTempButton = new JButton("OK!");

    // Panel wykresu
    private JPanel chartPanel = new JPanel(new GridLayout(1, 1));

    // Zmienna przekazywana klasie Fuzzyfication.class
    public static String getExcTemp;

    // Konstruktor
    public UserInterface() {
        initGUI();
    }

    public void initGUI() throws NullPointerException, NumberFormatException {

        // Okno
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setLayout(new FlowLayout());
        window.setMinimumSize(new Dimension(600,300));
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);

        // Menu
        window.setJMenuBar(menuBar);
        menuBar.add(control);
        menuBar.add(help);
        control.add(c1);
        control.add(c2);
        control.add(c3);
        help.add(h1);
        help.add(h2);

        // Panel sterowania uzytkownika
        window.add(userPanel);
        userPanel.setSize(200,300);
        // Dodanie komponentow do pierwszego wiersza panelu
        userPanel.add(infoTempLabel);
        userPanel.add(showTempLabel);
        userPanel.add(celsiusLabel);
        // Dodanie komponentow do drugiego wiersza panelu
        userPanel.add(setTempLabel);
        userPanel.add(setTempField);
        userPanel.add(acceptTempButton);

        // Akcja dla przycisku zatwierdzajacego ustawienie oczekiwanej temperatury
        acceptTempButton.addActionListener(actionEvent -> {
            getExcTemp = setTempField.getText();

            // Sprawdzenie poprawnosci wprowadzenia danej
            try {
                Float exceptedTemp = Float.valueOf(getExcTemp);
            }
            catch (NumberFormatException nfe) {
                showInvalidDialog("Wprowadź wartość numeryczną, np. 22 lub 23.5");
            }
            Float exceptedTemp = Float.valueOf(getExcTemp);
            if (exceptedTemp < 18 || exceptedTemp > 28) {
                showInvalidDialog("Wprowadź temperaturę z zakresu 18-28 stopni!");
            }
            else {
                showConfirmDialog();
                Fuzzy f = new Fuzzy();
                f.s = getExcTemp;
            }
        });

        // Akcja dla opcji "Ustaw plan dnia" w menu
        c1.addActionListener(actionEvent -> {

        });

        // Akcja dla opcji "Pokaż wykresy" w menu

        c2.addActionListener(actionEvent -> {

        });

        // Akcja dla opcji "Wyjdź z aplikacji" w menu
        c3.addActionListener(e1 -> closeTheWindow());

        // Akcja dla opcji "Korzystanie z aplikacji" w menu
        h1.addActionListener(actionEvent -> {
            new HelpWindow();
        });

        // Akcja dla opcji "O autorach"
        h2.addActionListener(actionEvent -> {
            new AboutWindow();
        });

        // Akcja zamknięcia okna
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeTheWindow();
            }
        });

    } // Koniec metody initGUI()

    // Metoda opisująca zamknięcie okna
    private void closeTheWindow() {
        int value = JOptionPane.showOptionDialog(null,
                "Czy jesteś pewien, że chcesz zakończyć działanie aplikacji?",
                "Wyjście z aplikacji",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Zamknij", "Anuluj"},
                "Zamknij");

        if (value == JOptionPane.YES_OPTION) {
            window.dispose();
            System.exit(0);
        }
    }

    // Wyskakujace okienko w przypadku powodzenia (poprawnie ustawiona oczekiwana temperatura)
    private void showConfirmDialog() {
        JOptionPane.showMessageDialog(window,
                "Ustawiono poprawnie");
    }

    // Wyskakujace okienko w przypadku niepowodzenia (niepoprawnie ustawiona oczekiwana temperatura)
    private void showInvalidDialog(String temp) {
        JOptionPane.showMessageDialog(window,
                temp,
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // W watku, aby uniknac zakleszczen przy tworzeniu GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                }
                catch (UnsupportedLookAndFeelException e) {
                    java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
                catch (ClassNotFoundException e) {
                    java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
                catch (InstantiationException e) {
                    java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
                catch (IllegalAccessException e) {
                    java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
                new UserInterface(); //Create and show the GUI.
            }
        });
    }

}
