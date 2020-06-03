package Clases;

import java.io.Serializable;

public class Bronze extends Avion implements Serializable {
    public Bronze() {
    }

    public Bronze(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, String tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
    }

    @Override
    public String toString() {
        return "Tipo de avion: " + this.getClass().getSimpleName() +
                "\n" + super.toString();
    }

}