import javax.swing.*;
import java.awt.*;

public class UserInterface {

    private JFrame window = new JFrame("Sterowanie temperaturą");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu control = new JMenu("Kontrola");
    private JMenu help = new JMenu("Pomoc");
    private JMenuItem c1, c2, h1, h2;

    // Panel kontrolny użytkownika, GridLayout(2, 3)
    private JPanel userPanel = new JPanel();
    // Pierwszy wiersz
    private JLabel infoTempLabel = new JLabel("Dzisiejsza temperatura:  ", JLabel.LEFT);
    private JLabel showTempLabel = new JLabel("Tu pojawi sie temp z excela", JLabel.LEFT);
    private JLabel celsiusLabel = new JLabel(" °C", JLabel.LEFT);
    // Drugi wiersz
    private JLabel setTempLabel = new JLabel("Ustaw oczekiwaną temperaturę:  ", JLabel.LEFT);
        private JTextField setTempField = new JTextField(JTextField.LEFT);
    private JButton acceptTempButton = new JButton("OK!");

    // Zmienna przekazywana klasie Fuzzyfication.class
    public static String getExcTemp;

    public UserInterface() {
        initGUI();
    }

    public void initGUI() throws NullPointerException, NumberFormatException {

        // Okno
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setLayout(new FlowLayout());
        window.setMinimumSize(new Dimension(600,300));

        // Menu
        window.setJMenuBar(menuBar);
        menuBar.add(control);
        menuBar.add(help);

        // Panel sterowania uzytkownika
        window.add(userPanel);
        userPanel.setLayout(new GridLayout(2, 3));
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
            if (exceptedTemp < 15 || exceptedTemp > 25) {
                showInvalidDialog("Wprowadź temperaturę z zakresu 15-25 stopni!");
            }
            else {
                showConfirmDialog();
                Fuzzy f = new Fuzzy();
                f.s = getExcTemp;
            }
        });

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
        new UserInterface();
    }

}
