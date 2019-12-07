import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.CellValue;

public class ReadData {

    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    // Czytanie wszystkich danych w arkuszu /xls
    public void read() throws IOException  {

        File inputWorkbook = new File(inputFile);
        Workbook w;

        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            // Dzisiejsza data
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date todayDate = new Date();
            System.out.println("Dziś jest: " + dateFormat.format(todayDate));

            // Odczyt wszystkich elementow
            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {

                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();

                    // Odczytuje wszystkie komorki o formacie "LABEL"
                    if (type == CellType.LABEL) {
                        String label = cell.getContents();
                        //System.out.println(label);
                    }

                    // Odczytuje wszystkie komorki o formacie "DATE"
                    if (type == CellType.DATE) {
                        String d = cell.getContents();
                        //System.out.println(d);

                        // Sprawdza, ktora komorka z kolumny "Data" jest rowna dzisiejszej dacie
                        if (d.equals(dateFormat.format(todayDate))) {
                            System.out.println("Test porownania: " + d);
                            // Sprawdza, ktory to wiersz
                            String whichRow = String.valueOf(cell.getRow());
                            System.out.println(whichRow);
                            // Pobiera wartosci temperatury i wilgotnosci z tego wiersza (dla tej daty)
                            // NIE DZIALA JESZCZE ALE JEST BLISKO XD
                            int num = Integer.parseInt(whichRow);
                            String row = String.valueOf(sheet.getRow(num));
                            System.out.println(row);
                        }

                    }

                }
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
    } // koniec metody read()

    public static void main(String[] args) throws IOException {
        ReadData temperatures = new ReadData();
        temperatures.setInputFile("temperatures.xls");
        temperatures.read();
    }

}
