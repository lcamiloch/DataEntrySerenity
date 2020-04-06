package Prueba;

import java.io.BufferedReader;
import java.io.FileReader;
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
        BufferedReader databuffer;
        String datos[][];
        String filas[];
        String LineaFeature = "";
        String ubicacion = "D:\\Camiloch\\Workspace\\DataEntrySerenity\\DataEntry\\Prueba.csv";
        int Fila = 0;
        int Columna = 0;
        int c = 0;


        {
            try {

                dataEntry = new FileReader(ubicacion);

                while(c != -1) {
                    c = dataEntry.read();
                    if(c == 13){
                        Fila++;
                    }
                    if(c == 59 && Fila == 0){
                        Columna++;
                    }
                }

                Fila = Fila + 1;
                Columna = Columna + 1;
                dataEntry = new FileReader(ubicacion);
                databuffer = new BufferedReader(dataEntry);
                datos = new String[Fila][Columna];
                filas = new String[Columna];
                int i=0;
                int j;

                while (LineaFeature != null) {
                    LineaFeature = databuffer.readLine();
                    if(LineaFeature != null) {
                        j=0;

                        filas = LineaFeature.split(";");

                        for (String palabra : filas)
                        {
                            datos[i][j] = filas[j];
                            System.out.println(palabra);
                            j++;
                        }
                        i++;
                    }
                }

                dataEntry.close();

                System.out.println("Hay " + Fila + " filas" + " y " + Columna + " Columnas");
                System.out.println("Revision ==> " + datos[3][4]);



            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        }
    }
}
