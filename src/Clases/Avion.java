package Clases;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;


public class Avion implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private int capacidadDeCombustible;
    private int costoPorKM;
    private int capacidadMaximaDePasajeros;
    private int velocidadMaxima;
    private Propulsores tipoDePropulsion;
    private int tarifa;
    private boolean disponible;
    private LinkedList<String> fechas = new LinkedList<>();
    protected String isA;

    public Avion() {
        this.setRandomCapacidadDeCombustible();
        this.setRandomCapacidadMaximaDePasajeros();
        this.setRandomCostoPorKM();
        this.setRandomVelocidadMaxima();
        this.setTipoDePropulsion(Propulsores.randomPropulsor());
        this.disponible = true;
        isA = "Avion";

    }

    //constructor que va a servir para el admin
    public Avion(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsion) {
        this.capacidadDeCombustible = capacidadDeCombustible;
        setRandomCostoPorKM();
        this.capacidadMaximaDePasajeros = capacidadMaximaDePasajeros;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoDePropulsion = tipoDePropulsion;
        disponible = true;
        isA = "Avion";

    }

    public String getId() {
        return id;
    }


    public void setRandomVelocidadMaxima() {
        velocidadMaxima = (int) (Math.random() * 100 + 150);
    }

    public void setRandomCapacidadMaximaDePasajeros() {
        capacidadMaximaDePasajeros = (int) (Math.random() * 3 + 2);
    }

    public void setRandomCapacidadDeCombustible() {
        capacidadDeCombustible = (int) (Math.random() * 300 + 300);
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void agregarFechas(String date) {
        fechas.add(date);
    }

    public void quitarFecha(String date) {
        fechas.remove(date);
    }

    public int getCostoPorKM() {
        return costoPorKM;
    }

    public LinkedList<String> getFechas() {
        return fechas;
    }

    public void setRandomCostoPorKM() {
        //elije un numero random entre 150 y 300
        costoPorKM = (int) (Math.random() * 150 + 150);
    }

    public int getCapacidadMaximaDePasajeros() {
        return capacidadMaximaDePasajeros;
    }

    public void setTipoDePropulsion(Propulsores tipoDePropulsion) {
        this.tipoDePropulsion = tipoDePropulsion;
    }

    @Override
    public String toString() {
        if (disponible) {
            return "\ncapacidadDeCombustible: " + capacidadDeCombustible +
                    "\ncostoPorKM: " + costoPorKM +
                    "\ncapacidadMaximaDePasajeros: " + capacidadMaximaDePasajeros +
                    "\nvelocidadMaxima: " + velocidadMaxima +
                    "\ntipoDePropulsion: " + tipoDePropulsion +
                    "\nID Avion: " + id +
                    "\nEstado del Avion: DISPONIBLE.\n";
        } else {
            return "\ncapacidadDeCombustible: " + capacidadDeCombustible +
                    "\ncostoPorKM: " + costoPorKM +
                    "\ncapacidadMaximaDePasajeros: " + capacidadMaximaDePasajeros +
                    "\nvelocidadMaxima: " + velocidadMaxima +
                    "\ntipoDePropulsion: " + tipoDePropulsion +
                    "\nID Avion: " + id +
                    "\nEstado del Avion: NO DISPONIBLE.\n";
        }

    }
}
