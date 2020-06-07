package Clases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


public class Sistema {

    private LinkedList<Usuario> clientes = new LinkedList<>();
    private ArrayList<Avion> aviones = new ArrayList<>();
    private ArrayList<Vuelo> vuelos = new ArrayList<>();
    private File archivoClientes = new File("Archivo de Clientes.json");
    private File archivoAviones = new File("Archivo de Aviones.json");
    private File archivoVuelos = new File("Archivo de Vuelos.json");

    public void mostrarClientes() {
        for (Usuario actual : clientes) {
            System.out.println(actual);
        }
    }

    //si el avion no contiene la fecha en su lista de fechas ocupadas, lo muestro
    public void mostrarAvionesDisponivlesPorFecha(String fecha) {
        for (Avion actual : aviones) {
            if (!actual.getFechas().contains(fecha)) {
                System.out.println(actual);
            }
        }
    }

    public void mostrarVuelos() {
        for (Vuelo actual : vuelos) {
            System.out.println(actual);
        }
    }

    public void cancelarVuelo(Usuario usuario, String fecha, String origen, String destino) {
        //asigno el resultado de buscarVuelos a index
        int index = buscarVuelo(usuario, fecha, origen, destino);
        if (index != -1) {
            //agarro el objeto en el lugar index de la lista de vuelos y seteo disponible como false
            vuelos.get(index).setDisponible(false);
        } else {
            System.out.println("No existe este vuelo");
        }
    }


    public int buscarVuelo(Usuario usuario, String fecha, String origen, String destino) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCliente().equals(usuario) && vuelo.getFecha().equals(fecha) && vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino)) {
                return vuelos.indexOf(vuelo);
            }
        }
        return -1;
    }

    public int buscarCliente(int dni) {

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDni() == dni)
                return i;
        }
        return -1;
    }

    public void actualizarCostoTotal(int index, float costoVuelo, int operacion) { // actualiza el costo total segun situacion , nuevo vuelo +, reserva cancelada -
        switch (operacion) {
            case 0:
                clientes.get(index).setCostoTotal(clientes.get(index).getCostoTotal() + costoVuelo);
                break;
            case 1:
                clientes.get(index).setCostoTotal(clientes.get(index).getCostoTotal() - costoVuelo);
                break;

        }
    }

    public void altaVuelo(int indexUsuario, String fecha, String origen, String destino, Avion transporte) {
        if (clientes.get(indexUsuario).getAcompanantes() + 1 <= transporte.getCapacidadMaximaDePasajeros()) {
            Vuelo nuevo = new Vuelo(transporte, clientes.get(indexUsuario), fecha, origen, destino);
            vuelos.add(nuevo);
            actualizarCostoTotal(indexUsuario, nuevo.getCostoVuelo(), 0);
            actualizarMejorAvion(nuevo.getTransporte(), indexUsuario);
        } else {
            System.out.println("No es posible reservar el vuelo, usted ha superado la capacidad de pasajeros");
        }

    }

    public void bajaVuelo(Usuario cliente, int dia, int mes, int ano, String origen, String destino) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaAcancelar = LocalDate.of(ano, mes, dia);
        if (fechaActual.isBefore(fechaAcancelar)) {
            String fecha = dia + "-" + mes + "-" + ano;
            cancelarVuelo(cliente, fecha, origen, destino);

        } else {
            System.out.println("No se puede cancelar un vuelo con menos de 24hs de anticipación");
        }

    }

    public void actualizarMejorAvion(Avion actual, int indexUsuario) {
        if (clientes.get(indexUsuario).getMejorCategoria().equals("")) {
            switch (actual.getTarifa()) {
                case 3000:
                    clientes.get(indexUsuario).setMejorCategoria("Bronze");
                    break;
                case 4000:
                    clientes.get(indexUsuario).setMejorCategoria("Silver");
                    break;
                case 6000:
                    clientes.get(indexUsuario).setMejorCategoria("Gold");
                    break;
            }
        } else {

            if (actual.getTarifa() == 6000) {
                clientes.get(indexUsuario).setMejorCategoria("Gold");
            } else {
                if (actual.getTarifa() == 4000 && clientes.get(indexUsuario).getMejorCategoria().equals("Bronze")) {
                    clientes.get(indexUsuario).setMejorCategoria("Silver");
                }
            }

        }
    }

    public static void escribirUsuarios(File file, LinkedList<Usuario> users) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void escribirVuelos(File file, ArrayList<Vuelo> vuelos) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, vuelos);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void escribirAviones(File file, ArrayList<Avion> aviones) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, aviones);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static LinkedList<Usuario> leerUsusarios(File file) {
        ObjectMapper mapper = new ObjectMapper();
        LinkedList<Usuario> users = new LinkedList<>();
        try {
            users = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return users;
    }

    public static ArrayList<Vuelo> leerVuelos(File file) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        try {
            vuelos = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return vuelos;
    }

    public static ArrayList<Avion> leerAviones(File file) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Avion> aviones = new ArrayList<>();
        try {
            aviones = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return aviones;
    }
}
 