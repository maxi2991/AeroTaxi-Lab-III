package Clases;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
        for(Avion actual : aviones) {
            if(!actual.getFechas().contains(fecha)) {
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
        int index = buscarVuelo(usuario,fecha,origen,destino);
        if(index != -1) {
            //agarro el objeto en el lugar index de la lista de vuelos y seteo disponible como false
            vuelos.get(index).setDisponible(false);
        } else {
            System.out.println("no existe tal vuelo");
        }
    }


    public int buscarVuelo(Usuario usuario, String fecha, String origen, String destino) {
        for(Vuelo vuelo:vuelos) {
            if(vuelo.getCliente().equals(usuario) && vuelo.getFecha().equals(fecha) && vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino)) {
                return vuelos.indexOf(vuelo);
            }
        }
        return -1;
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

    public static void escribirUsuarios(File file, LinkedList<Usuario> users) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,users);
        }catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void escribirVuelos(File file, ArrayList<Vuelo> vuelos) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,vuelos);
        }catch(IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void escribirAviones(File file, ArrayList<Avion> aviones) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,aviones);
        }catch(IOException e ) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static LinkedList<Usuario> leerUsusarios(File file) {
        ObjectMapper mapper = new ObjectMapper();
        LinkedList<Usuario>users = new LinkedList<>();
        try{
            users = mapper.readValue(file, new TypeReference<>() {});
        }catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return users;
    }

    public static ArrayList<Vuelo> leerVuelos(File file) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        try{
            vuelos = mapper.readValue(file, new TypeReference<>() {});
        }catch(IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return vuelos;
    }

    public static ArrayList<Avion> leerAviones(File file) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Avion> aviones = new ArrayList<>();
        try{
            aviones = mapper.readValue(file, new TypeReference<>() {});
        }catch(IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return aviones;
    }
}
 