package Steps;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuiltFeature {

    public static void main(String[] args) throws IOException {
        String dataEntryLocation = "D:\\Automation\\DataentrySerenity\\DataEntrySerenity\\DataEntry\\Prueba.xlsx";
        String featureLocation = "D:\\Automation\\DataentrySerenity\\DataEntrySerenity\\src\\main\\resources\\features\\Prueba.feature";

        List<String> fileFeature = backupFeature(featureLocation);
        List<String> dataEntry = readDataEntry(dataEntryLocation);
        writeFeature(dataEntry, featureLocation, fileFeature);
        //restoreFeature(fileFeature, featureLocation);
    }

    public static List<String> readDataEntry(String file) throws IOException {

        FileInputStream excelFile = new FileInputStream(new File(file));
        Workbook workbook = new XSSFWorkbook(excelFile);
        ArrayList<String> data = new ArrayList<>();

        for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet datatypeSheet = workbook.getSheetAt(i);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        data.add(currentCell.getStringCellValue());
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        data.add(String.format("%.0f", currentCell.getNumericCellValue()));
                    }
                }
                data.add("EndLine");
            }
            data.add("EndSheet");
        }
        excelFile.close();
        workbook.close();
        return data;
    }

    public static void writeFeature(List dataEntry , String featureLocation, List featureFile) throws IOException {

        FileWriter file = new FileWriter(featureLocation);
        String convertFeature = "";
        int j = 0;
        for(int i = 0; i < featureFile.size(); i++){
            String convertDataEntry = "";
            convertFeature = featureFile.get(i).toString();
            if(convertFeature.contains("Examples")){
                file.write(featureFile.get(i) + "\n" + "        ");
                while(convertDataEntry != "EndSheet") {
                    convertDataEntry = dataEntry.get(j).toString();
                    if(convertDataEntry != "EndSheet") {
                        if (convertDataEntry != "EndLine"){
                            file.write("| " + convertDataEntry + " ");
                        }
                        else{
                            file.write("|\n      ");
                        }
                    }
                    j++;
                }
                file.write("\n");
            }
            else {
                file.write(featureFile.get(i) + "\n");
            }
        }
        file.close();
    }

    public static List<String> backupFeature(String locationFile) throws IOException {
        FileReader fileOrigin = new FileReader(locationFile);
        BufferedReader bufferRead = new BufferedReader(fileOrigin);
        ArrayList<String> file = new ArrayList<>();

        String line = "";
        while(line != null){
            line = bufferRead.readLine();
            if(line != null) {
                file.add(line);
            }
        }
        fileOrigin.close();
        return file;
    }

    public static void restoreFeature(List <String> data, String locationFile) throws IOException {
        FileWriter file = new FileWriter(locationFile);
        for(int i = 0; i<data.size(); i++){
            file.write(data.get(i) + "\n");
        }
        file.close();
    }
}
