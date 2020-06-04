package Clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private Avion transporte;
    private SimpleDateFormat fecha = new SimpleDateFormat("dd-mm-yyyy");
    private String origen;
    private String destino;
    private List<Usuario> pasajeros = new ArrayList<>();

    public Vuelo() {

    }

    public Vuelo(Avion transporte, SimpleDateFormat fecha, String origen, String destino) {
        this.transporte = transporte;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;

    }

    public Avion getTransporte() {
        return transporte;
    }

    public void setTransporte(Avion transporte) {
        this.transporte = transporte;
    }

    public SimpleDateFormat getFecha() {
        return fecha;
    }

    public void setFecha(SimpleDateFormat fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void agregarPasajero(Usuario nuevo) {
        int indice = this.pasajeros.indexOf(nuevo);
        if (indice == -1) {
            pasajeros.add(nuevo);
        }
    }

    public void eliminarPasajero(Usuario nuevo) {
        int indice = this.pasajeros.indexOf(nuevo);
        if (indice != -1) {
            pasajeros.remove(indice);
        }
    }


    public int getTarifa() {
        int tarifa = 0;
        if (this.transporte instanceof Gold) {
            tarifa = 6000;
        } else {
            if (this.transporte instanceof Silver) {
                tarifa = 4000;

            } else {
                tarifa = 3000;
            }
        }
        return tarifa;
    }


    public int contarPasajeros() {// Esto es por si algun pasajero cancela el vuelo , la celda de este objeto sera nula y no debe ser contada como un pasajero efectivo
        int validos = 0;
        for (int i = 0; i < pasajeros.size(); i++) {
            if (pasajeros.get(i) != null) {
                validos++;
                validos += pasajeros.get(i).getAcompañantes(); //Agrego la cantidad de acompañantes del pasajero
            }
        }

        return validos;
    }

    public float calcularCosto(Usuario actual, int distancia) {
        int indice = this.pasajeros.indexOf(actual);
        if (indice >= 0) {
            return (float) (distancia * this.transporte.getCostoPorKM()) + ((this.pasajeros.get(indice).getAcompañantes() + 1) * 3500) + this.getTarifa();
        } else {
            return 0;
        }
    }

    public float definirCosto(Usuario actual) { // Defino la distancia segun origen y destino para calcular el costo del vuelo
        float costo = 0;
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Cordoba")) || (this.getOrigen().equals("Cordoba") && this.getDestino().equals("BuenosAires"))) {

            costo = calcularCosto(actual, Distancia.BSASCORDOBA.getDistancia());
        }
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Santiago")) || (this.getOrigen().equals("Santiago") && this.getDestino().equals("BuenosAires"))) {

            costo = calcularCosto(actual, Distancia.BSASSANTIAGO.getDistancia());
        }
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("BuenosAires"))) {

            costo = calcularCosto(actual, Distancia.BSASMONTEVIDEO.getDistancia());
        }
        if ((this.getOrigen().equals("Cordoba") && this.getDestino().equals("Santiago")) || (this.getOrigen().equals("Santiago") && this.getDestino().equals("Cordoba"))) {

            costo = calcularCosto(actual, Distancia.CORDOBASANTIAGO.getDistancia());
        }
        if ((this.getOrigen().equals("Cordoba") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("Cordoba"))) {

            costo = calcularCosto(actual, Distancia.CORDOBAMONTEVIDEO.getDistancia());
        }
        if ((this.getOrigen().equals("Santiago") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("Santiago"))) {

            costo = calcularCosto(actual, Distancia.MONTEVIDEOSANTIAGO.getDistancia());
        }

        return costo;

    }

    public void mostrarPasajeros() {
        for (Usuario actual : this.pasajeros) {
            if (actual != null) {
                System.out.println(actual.toString());
            }
        }
    }

    @Override
    public String toString() {
        return this.transporte.toString() + " Salida: " + this.getFecha() + " Origen: " + this.getOrigen() + " Destino: " + this.getDestino();
    }


}
