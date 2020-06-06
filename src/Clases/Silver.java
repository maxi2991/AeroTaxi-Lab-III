package Clases;

import java.io.Serializable;

public class Silver extends Avion implements Serializable {

    //catering??? no se si ponerlo porque no entiendo que carajo hace
    public Silver(){}

    public Silver(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, String tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
        setTarifa(4000);
    }

    @Override
    public String toString() {
        return "Tipo de avion: " + this.getClass().getSimpleName() +
                "\n" + super.toString();
    }
}
