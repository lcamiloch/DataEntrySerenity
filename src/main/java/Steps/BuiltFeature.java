package Steps;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuiltFeature {
    public static void main(String[] args) throws IOException {
        String dataEntryLocation = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\DataEntry\\Prueba.xlsx";
        String featureLocation = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\src\\main\\resources\\Features\\Prueba.feature";

        List file = backupFeature(featureLocation);
        String[][] dataEntry = readDataEntry(dataEntryLocation);
        writefeature(dataEntry, featureLocation);
        restoreFeature(file, featureLocation);
    }

    public static String[][] readDataEntry(String file) throws IOException {

        String[][] list;

            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            int rows = datatypeSheet.getLastRowNum() + 1;
            int columns = datatypeSheet.getRow(0).getLastCellNum();
            list = new String[rows][columns];
            int i = 0;

            while (iterator.hasNext()) {
                int j = 0;
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        list[i][j] = currentCell.getStringCellValue();
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        list[i][j] = String.format("%.0f", currentCell.getNumericCellValue());
                    }
                    j++;
                }
                i++;
            }
            excelFile.close();
            return list;
    }

    public static void writefeature(String[][] array , String file) throws IOException {

        FileWriter writer = new FileWriter(file, true);

        for (String x[]:array) {
            writer.write("\n" + "       " + "|");
            for(String y:x){
                writer.write(y + "|");
            }
        }
        writer.close();
    }

    public static List backupFeature(String locationFile) throws IOException {
        FileReader dataEntry = new FileReader(locationFile);
        ArrayList<Integer> file = new ArrayList<>();
        int data = 0;

        while(data != -1){
            data = dataEntry.read();
            if(data != -1)
                file.add(data);
        }
        dataEntry.close();
        return file;
    }

    public static void restoreFeature(List data, String locationFile) throws IOException {
        FileOutputStream file = new FileOutputStream(locationFile);;
        int e = 0;
        for(int i = 0; i<data.size(); i++){
            e = (int) data.get(i);
            file.write(e);
        }
        file.close();
    }
}
