package Clases;

import java.io.Serializable;
import java.util.Random;

public class Gold extends Avion implements Serializable {
    private boolean wifi;


    //catering??? no se si ponerlo porque no entiendo que carajo hace

    public Gold(){
        super();
        setTarifa(6000);
        setWifi();
    }

    public Gold(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
        setTarifa(6000);
        setWifi();
    }

    public void setWifi() {
            //metodo random que devuelve un booleando
        this.wifi = new Random().nextBoolean();
    }

    public boolean isWifi() {
        return wifi;
    }


    @Override
    public String toString() {
        if(wifi) {
            return "Tipo de avion: " + this.getClass().getSimpleName() +
                    "\nTiene Wifi" +
                    "\nCon Catering" +
                    "\n" + super.toString();
        }else {
            return "Tipo de avion: " + this.getClass().getSimpleName() +
                    "\nNo tiene Wifi" +
                    "\n" + super.toString();
        }

    }
}
