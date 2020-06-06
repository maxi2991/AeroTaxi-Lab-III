package Clases;

import java.util.ArrayList;

public class Sistema {

    private ArrayList<Usuario> clientes = new ArrayList<>();
    private ArrayList<Avion> aviones = new ArrayList<>();
    private ArrayList<Vuelo> vuelos = new ArrayList<>();

    public void mostrarClientes() {
        for (Usuario actual : clientes) {
            System.out.println(actual);
        }
    }

    //comentos esta funcion para que me deje pushear el projecto
    /*public void mostrarAvionesDisp() {
        for (Avion actual : aviones) {
            if (!actual.isOcupado())
                System.out.println(actual);
        }
    }*/

    public void mostrarVuelos() {

        for (Vuelo actual : vuelos) {
            System.out.println(actual);
        }
    }

    public void cancelarVuelo(String fecha, String origen, String destino) {
        Vuelo nuevo = buscarVuelo(fecha, origen, destino);
        if (nuevo != null) {
            nuevo.setDisponible(false);
        }
    }

    public Vuelo buscarVuelo(String fecha, String origen, String destino) {
        Vuelo encontrado = null;
        for (Vuelo actual : vuelos) {
            if (actual.getFecha().equals(fecha) && actual.getOrigen().equals(origen) && actual.getDestino().equals(destino))
                encontrado = actual;
        }
        return encontrado;
    }

    public Usuario buscarCliente(int dni) {
        Usuario encontrado = null;
        for (Usuario actual : clientes) {
            if (actual.getDni() == dni)
                encontrado = actual;
        }
        return encontrado;
    }

    public void actualizarCostoTotal(Usuario actual, float costoVuelo, int operacion) { // actualiza el costo total segun situacion , nuevo vuelo +, reserva cancelada -
        switch (operacion) {
            case 0:
                actual.setCostoTotal(actual.getCostoTotal() + costoVuelo);
                break;
            case 1:
                actual.setCostoTotal(actual.getCostoTotal() - costoVuelo);
                break;

        }
    }
}
