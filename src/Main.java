import Clases.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*Gold gold = new Gold(321321,200,1500, Propulsores.HELICE);
        Silver silver = new Silver(321321,200,1500, Propulsores.PISTONES);
        Bronze bronze = new Bronze(321321,200,1500, Propulsores.REACCION);*/

        ArrayList<Avion> aviones = new ArrayList<>();
        /*aviones.add(gold);
        aviones.add(silver);
        aviones.add(bronze);

        File file = new File("Mi Archivo.json");*/

        /*if (!file.exists()) {
            file.createNewFile();
        }*/
        //Sistema.escribirAviones(file,aviones);

            aviones = Sistema.leerAviones(new File("Mi Archivo.json"));

            System.out.println(aviones.size());

            for(Avion avion:aviones) {
                System.out.println(avion);
            }


    }
}
