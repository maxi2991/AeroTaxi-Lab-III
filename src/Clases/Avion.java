package Clases;

import java.io.Serializable;

public abstract class Avion implements Serializable {
    private int capacidadDeCombustible;
    private int costoPorKM;
    private int capacidadMaximaDePasajeros;
    private int velocidadMaxima;
    private String tipoDePropulsion;
    private boolean ocupado;

    public Avion(){}

    public Avion(int capacidadDeCombustible, int capacidadMaximaDePasajeros, int velocidadMaxima, String tipoDePropulsion) {
        this.capacidadDeCombustible = capacidadDeCombustible;
        setCostoPorKM();
        this.capacidadMaximaDePasajeros = capacidadMaximaDePasajeros;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoDePropulsion = tipoDePropulsion;
        ocupado = false;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getCapacidadDeCombustible() {
        return capacidadDeCombustible;
    }

    public void setCapacidadDeCombustible(int capacidadDeCombustible) {
        this.capacidadDeCombustible = capacidadDeCombustible;
    }

    public int getCostoPorKM() {
        return costoPorKM;
    }

    public void setCostoPorKM() {
        //elije un numero random entre 150 y 300
        costoPorKM = (int) (Math.random() *150 + 150);
    }

    public int getCapacidadMaximaDePasajeros() {
        return capacidadMaximaDePasajeros;
    }

    public void setCapacidadMaximaDePasajeros(int capacidadMaximaDePasajeros) {
        this.capacidadMaximaDePasajeros = capacidadMaximaDePasajeros;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public String getTipoDePropulsion() {
        return tipoDePropulsion;
    }

    public void setTipoDePropulsion(String tipoDePropulsion) {
        this.tipoDePropulsion = tipoDePropulsion;
    }

    @Override
    public String toString() {
        return  "capacidadDeCombustible: " + capacidadDeCombustible +
                "\ncostoPorKM: " + costoPorKM +
                "\ncapacidadMaximaDePasajeros: " + capacidadMaximaDePasajeros +
                "\nvelocidadMaxima: " + velocidadMaxima +
                "\ntipoDePropulsion: " + tipoDePropulsion + "\n";
    }
}
