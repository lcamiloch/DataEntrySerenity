package Prueba;

import java.io.*;

public class Functions {

    public static void main(String[] args) {
        Leer_Feature acceder = new Leer_Feature();
        acceder.leer();
    }
}

class Leer_Feature {

    public void leer() {
        FileReader feature;
        BufferedReader featureBuffer;
        FileReader dataEntry;
        int c = 0;

        {
            try {
                feature = new FileReader("D:\\Camiloch\\Workspace\\DataEntrySerenity\\src\\main\\resources\\Features\\Prueba.feature");
                featureBuffer = new BufferedReader(feature);
                dataEntry = new FileReader("D:\\Camiloch\\Workspace\\DataEntrySerenity\\DataEntry\\Prueba.csv");

                String LineaFeature = "";

                while (LineaFeature != null) {
                    LineaFeature = featureBuffer.readLine();
                    if(LineaFeature != null) {
                        System.out.println(LineaFeature);
                    }
                }
                while(c != -1){
                    c = dataEntry.read();
                    char letra = (char)c;

                    if ((c != 13) && (c != 10)){
                        System.out.print(letra);
                    }

                    //System.out.println(c + " " + letra);
                }

                feature.close();
                dataEntry.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
