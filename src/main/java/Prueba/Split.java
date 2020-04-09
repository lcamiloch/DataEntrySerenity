package Prueba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Split {
    public static void main(String[] args) {
        Leer_split acceder = new Leer_split();
        acceder.leer();

    }
}

class Leer_split {

    public void leer() {
        FileReader dataEntry;
        FileWriter writer;
        BufferedReader databuffer;
        String [][] datos;
        String [] filas;
        String LineaFeature = "";
        String ubicacionDataE = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\DataEntry\\Prueba.csv";
        String ubicacionF = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\src\\main\\resources\\Features\\Prueba.feature";
        int Fila = 0;
        int Columna = 0;
        int c = 0;
        try {

            dataEntry = new FileReader(ubicacionDataE);

            while (c != -1) {
                c = dataEntry.read();
                if (c == 13) {
                    Fila++;
                }
                if (c == 59 && Fila == 0) {
                    Columna++;
                }
            }

            Columna = Columna + 1;
            dataEntry = new FileReader(ubicacionDataE);
            databuffer = new BufferedReader(dataEntry);
            datos = new String[Fila][Columna];
            filas = new String[Columna];
            int i = 0;
            int j;

            while (LineaFeature != null) {
                LineaFeature = databuffer.readLine();
                if (LineaFeature != null) {
                    j = 0;
                    filas = LineaFeature.split(";");

                    for (String palabra : filas) {
                        datos[i][j] = filas[j];
                        j++;
                    }
                    i++;
                }
            }

            dataEntry.close();
            System.out.println("Hay " + Fila + " filas" + " y " + Columna + " Columnas");

            for (String x[]:datos) {
                System.out.print("|");
                for(String y:x){
                    System.out.print(y + "|");
                }
                System.out.print("\n");
            }

            writer = new FileWriter(ubicacionF, true);

            for (String x[]:datos) {
                System.out.print("|");
                writer.write("\n" + "|");
                for(String y:x){
                    writer.write(y + "|");
                    System.out.print(y + "|");
                }
                System.out.print("\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
