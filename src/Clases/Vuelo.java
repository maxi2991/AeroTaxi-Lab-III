package Clases;


import javax.naming.Context;
import java.io.Serializable;

public class Vuelo implements Serializable {
    private Avion transporte;
    private String fecha;
    private Ciudad origen;
    private Ciudad destino;
    private Usuario cliente;
    private float costoVuelo;
    private boolean disponible; // es para marcar si el vuelo fue cancelado o no en el arraylist de sistema
    protected String isA;

    public Vuelo() {
        isA = "Vuelo";
        disponible = true;
    }

    public Vuelo(Avion transporte, Usuario cliente, String fecha, Ciudad origen, Ciudad destino) {
        this.transporte = transporte;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        disponible = true;
        this.cliente = cliente;
        definirCosto();
        isA = "Vuelo";
    }


    public Avion getTransporte() {
        return transporte;
    }

    public void setTransporte(Avion transporte) {
        this.transporte = transporte;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public float getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(float costoVuelo) {
        this.costoVuelo = costoVuelo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }


    public float calcularCosto( int distancia) {

        if (cliente != null) {
            return (float) (distancia * transporte.getCostoPorKM()) + ((cliente.getAcompanantes() + 1) * 3500) + transporte.getTarifa();
        } else {
            return 0;
        }
    }

    public void definirCosto() { // Defino la distancia segun origen y destino para calcular el costo del vuelo
        if ((origen.equals(Ciudad.BUENOS_AIRES) && destino.equals(Ciudad.CORDOBA)) || (origen.equals(Ciudad.CORDOBA) && destino.equals(Ciudad.BUENOS_AIRES))) {

            this.costoVuelo = calcularCosto(Distancia.BSASCORDOBA.getDistancia());
        }
        if ((origen.equals(Ciudad.BUENOS_AIRES) && destino.equals(Ciudad.SANTIAGO)) || (origen.equals(Ciudad.SANTIAGO) && destino.equals(Ciudad.BUENOS_AIRES))) {

            this.costoVuelo = calcularCosto(Distancia.BSASSANTIAGO.getDistancia());
        }
        if ((origen.equals(Ciudad.BUENOS_AIRES) && destino.equals(Ciudad.MONTEVIDEO)) || (origen.equals(Ciudad.MONTEVIDEO) && destino.equals(Ciudad.BUENOS_AIRES))) {

            this.costoVuelo = calcularCosto(Distancia.BSASMONTEVIDEO.getDistancia());
        }
        if ((origen.equals(Ciudad.CORDOBA) && destino.equals(Ciudad.SANTIAGO)) || (origen.equals(Ciudad.SANTIAGO) && destino.equals(Ciudad.CORDOBA))) {

            this.costoVuelo = calcularCosto(Distancia.CORDOBASANTIAGO.getDistancia());
        }
        if ((origen.equals(Ciudad.CORDOBA) && destino.equals(Ciudad.MONTEVIDEO)) || (origen.equals(Ciudad.MONTEVIDEO) && destino.equals(Ciudad.CORDOBA))) {

            this.costoVuelo = calcularCosto(Distancia.CORDOBAMONTEVIDEO.getDistancia());
        }
        if ((origen.equals(Ciudad.SANTIAGO) && destino.equals(Ciudad.MONTEVIDEO)) || (origen.equals(Ciudad.MONTEVIDEO) && destino.equals(Ciudad.SANTIAGO))) {

            this.costoVuelo = calcularCosto(Distancia.MONTEVIDEOSANTIAGO.getDistancia());
        }


    }


    @Override
    public String toString() {
        if (this.disponible) {
                return "Tipo de Avion: " + transporte.isA +
                        "\nID Avion: " + transporte.getId() +
                        "\nSalida: " + this.getFecha() +
                        " Origen: " + this.getOrigen().name() +
                        " Destino: " + this.getDestino().name() +
                        "\n\n";
        } else {
            return "Tipo de Avion: " + transporte.isA +
                    "\nID Avion: " + transporte.getId() +
                    "\nSalida: " + this.getFecha() +
                    " Origen: " + this.getOrigen().name() +
                    " Destino: " + this.getDestino().name() +
                    "\nVUELO CANCELADO\n\n";
        }
    }


}
