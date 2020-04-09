package Steps;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class BuiltFeature {
    public static void main(String[] args) throws IOException {
        String dataEntryLocation = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\DataEntry\\Prueba.xlsx";
        String featureLocation = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\src\\main\\resources\\Features\\Prueba.feature";

        writefeature(readExcel(dataEntryLocation), featureLocation);
    }

    public static String[][] readExcel(String file) throws IOException {

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
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        list[i][j] = currentCell.getStringCellValue();
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
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
            writer.write("\n" + "   " + "|");
            for(String y:x){
                writer.write(y + "|");
            }
        }
        writer.close();
    }
}
