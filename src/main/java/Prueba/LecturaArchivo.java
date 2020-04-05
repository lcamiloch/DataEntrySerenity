package Prueba;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class LecturaArchivo {

    public static void main(String[] args) {
        Leer_Fichero acceder = new Leer_Fichero();
        acceder.leer();
    }
}

class Leer_Fichero {

    public void leer() {
        FileReader entrada;
        BufferedReader miBuffer;
        {
            try {
                entrada = new FileReader("D:\\Automation\\DataentrySerenity\\src\\main\\resources\\Features\\Prueba.feature");
                miBuffer = new BufferedReader(entrada);

                String Linea = "";
                while (Linea != null) {
                    Linea = miBuffer.readLine();
                    if(Linea != null) {
                        System.out.println(Linea);
                    }
                }

                entrada.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
