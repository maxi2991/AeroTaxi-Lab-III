package Clases;

import java.io.Serializable;

public class Gold extends Avion implements Serializable {
    private boolean wifi;

    //catering??? no se si ponerlo porque no entiendo que carajo hace

    public Gold(){}

    public Gold(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, String tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
    }

    public void setWifi() {
        //random entre 0 y 1
        this.wifi = Math.random() * 2 > 0;
    }

    public boolean isWifi() {
        return wifi;
    }

    @Override
    public String toString() {
        return "Tipo de avion: " + this.getClass().getSimpleName() +
                "\nWifi: " + wifi +
                "\n" + super.toString();
    }
}
