package Clases;

import java.io.Serializable;

public class Bronze extends Avion implements Serializable {
    public Bronze() {
        super();
        setTarifa(3000);
        isA = "Bronze";
    }

    public Bronze(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
        setTarifa(3000);
        isA = "Bronze";
    }

    @Override
    public String toString() {
        return "Tipo de avion: " + this.getClass().getSimpleName() +
                "\n" + super.toString();
    }

}
