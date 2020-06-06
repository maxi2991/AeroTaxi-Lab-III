package Clases;


public class Vuelo {
    private Avion transporte;
    private String fecha;
    private String origen;
    private String destino;
    private Usuario cliente;
    private float costoVuelo;
    private boolean disponible; // es para marcar si el vuelo fue cancelado o no en el arraylist de sistema

    public Vuelo() {

    }

    public Vuelo(Avion transporte, Usuario cliente, String fecha, String origen, String destino) {
        this.transporte = transporte;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.disponible = true;
        this.cliente = cliente;
        definirCosto();
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

    public int getTarifa() {
        return transporte.getTarifa();
    }


    public float calcularCosto( int distancia) {

        if (cliente != null) {
            return (float) (distancia * transporte.getCostoPorKM()) + ((cliente.getAcompañantes() + 1) * 3500) + getTarifa();
        } else {
            return 0;
        }
    }

    public void definirCosto() { // Defino la distancia segun origen y destino para calcular el costo del vuelo
        float costo = 0;
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Cordoba")) || (this.getOrigen().equals("Cordoba") && this.getDestino().equals("BuenosAires"))) {

            this.costoVuelo = calcularCosto(Distancia.BSASCORDOBA.getDistancia());
        }
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Santiago")) || (this.getOrigen().equals("Santiago") && this.getDestino().equals("BuenosAires"))) {

            this.costoVuelo = calcularCosto(Distancia.BSASSANTIAGO.getDistancia());
        }
        if ((this.getOrigen().equals("BuenosAires") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("BuenosAires"))) {

            this.costoVuelo = calcularCosto(Distancia.BSASMONTEVIDEO.getDistancia());
        }
        if ((this.getOrigen().equals("Cordoba") && this.getDestino().equals("Santiago")) || (this.getOrigen().equals("Santiago") && this.getDestino().equals("Cordoba"))) {

            this.costoVuelo = calcularCosto(Distancia.CORDOBASANTIAGO.getDistancia());
        }
        if ((this.getOrigen().equals("Cordoba") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("Cordoba"))) {

            this.costoVuelo = calcularCosto(Distancia.CORDOBAMONTEVIDEO.getDistancia());
        }
        if ((this.getOrigen().equals("Santiago") && this.getDestino().equals("Montevideo")) || (this.getOrigen().equals("Montevideo") && this.getDestino().equals("Santiago"))) {

            this.costoVuelo = calcularCosto(Distancia.MONTEVIDEOSANTIAGO.getDistancia());
        }


    }


    @Override
    public String toString() {
        if (this.disponible) {
            return this.transporte.toString() + " Salida: " + this.getFecha() + " Origen: " + this.getOrigen() + " Destino: " + this.getDestino();
        } else {
            return this.transporte.toString() + " Salida: " + this.getFecha() + " Origen: " + this.getOrigen() + " Destino: " + this.getDestino() + " Vuelo cancelado";
        }
    }


}
