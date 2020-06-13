package Clases;

import java.io.Serializable;

public class Silver extends Avion implements Serializable {



    //catering??? no se si ponerlo porque no entiendo que carajo hace
    public Silver(){
        super();
        isA = "Silver";
    }

    public Silver(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsion) {
        super(capacidadDeCombustible, capacidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsion);
        setTarifa(4000);
        isA = "Silver";
    }

    @Override
    public String toString() {
        return "Tipo de avion: " + this.getClass().getSimpleName() +
                "\nCon Catering" +
                "\n" + super.toString();
    }
}
