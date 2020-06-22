package Clases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sistema {


    private File archivoClientes = new File("Clientes.json");
    private File archivoAviones = new File("Aviones.json");
    private File archivoVuelos = new File("Vuelos.json");
    private ArrayList<Usuario> clientes;
    private ArrayList<Avion> aviones;
    private ArrayList<Vuelo> vuelos;

    private static GsonBuilder gb = new GsonBuilder();

    static {
        gb.registerTypeAdapter(ArrayList.class, new CustomDeserializer<Vuelo>());
        gb.registerTypeAdapter(ArrayList.class, new CustomSerializer<Vuelo>());
        gb.registerTypeAdapter(ArrayList.class, new CustomDeserializer());
        gb.registerTypeAdapter(ArrayList.class, new CustomSerializer());
    }

    Gson gson = gb.setPrettyPrinting().create();

    public Sistema() {
        aviones = cargarAviones();
        clientes = cargarClientes();
        vuelos = cargarVuelos();
    }

    public void guardarClientes() {
        try {
            String json = gson.toJson(clientes);

            FileWriter file = new FileWriter(archivoClientes);
            file.write(json);

            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> cargarClientes() {
        ArrayList<Usuario> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoClientes));

            list = gson.fromJson(reader, list.getClass());

            reader.close();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return list;
    }

    public void guardarVuelos() {
        try {
            String json = gson.toJson(vuelos);

            FileWriter file = new FileWriter(archivoVuelos);
            file.write(json);

            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public ArrayList<Vuelo> cargarVuelos() {
        ArrayList<Vuelo> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoVuelos));

            list = gson.fromJson(reader, list.getClass());

            reader.close();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return list;
    }

    public void guardarAviones() {
        try {
            String json = gson.toJson(aviones);

            FileWriter file = new FileWriter(archivoAviones);
            file.write(json);

            file.flush();
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Avion> cargarAviones() {
        ArrayList<Avion> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoAviones));

            list = gson.fromJson(reader, list.getClass());

            reader.close();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Usuario> getClientes() {
        return clientes;
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    public void mostrarClientes() {
        for (Usuario actual : clientes) {
            System.out.println(actual);
        }
    }

    //si el avion no contiene la fecha en su lista de fechas ocupadas, lo muestro
    public boolean mostrarAvionesDisponiblesPorFecha(String fecha) {
        boolean vacio = true;
        int i = 0;
        for (Avion actual : aviones) {
            if (!actual.getFechas().contains(fecha) && actual.isDisponible()) {
                System.out.println(actual + "numero de Avion: " + i + "\n\n");
                vacio = false;
            }
            i++;
        }
        return vacio;
    }

    public void mostrarTodosLosAviones() {
        int i = 0;
        for (Avion avion : aviones) {
            System.out.println(avion + "numero de Avion: " + i + "\n\n");
            i++;
        }
    }

    public void mostrarVuelos() {
        for (Vuelo actual : vuelos) {
            System.out.println(actual);
        }
    }

    public boolean mostrarTodosLosVuelosEnFecha(String fecha) {
        boolean encontrado = false;
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFecha().equals(fecha)) {
                System.out.println(vuelo);
                encontrado = true;
            }
        }
        return encontrado;
    }

    public boolean mostrarVuelosParaFecha(String fecha, Usuario actual) {
        boolean encontrado = false;
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFecha().equals(fecha) && vuelo.getCliente().getDni() == actual.getDni()) {
                System.out.println(vuelo);
                encontrado = true;
            }
        }
        return encontrado;
    }

    public void mostrarVuelosUsuario(Usuario actual) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCliente().getDni() == actual.getDni()) {
                System.out.println(vuelo);
            }
        }
    }

    public void cancelarVuelo(Usuario usuario, String fecha, Ciudad origen, Ciudad destino) throws CustomException {
        //asigno el resultado de buscarVuelos a index
        int index = buscarVuelo(usuario, fecha, origen, destino);
        int indexUsuario = buscarCliente(usuario.getDni());
        if (index != -1) {
            //agarro el objeto en el lugar index de la lista de vuelos y seteo disponible como false
            vuelos.get(index).getTransporte().quitarFecha(fecha);
            vuelos.get(index).setDisponible(false);
            actualizarCostoTotal(indexUsuario, vuelos.get(index).getCostoVuelo(), 1);
        } else {
            throw new CustomException("No existe este vuelo");
        }
    }

    public void altaCliente(String nombre, String apellido, int edad, String pass, int dni) {
        Usuario nuevo = new Usuario(nombre, apellido, dni, edad);
        nuevo.setPassword(pass);
        clientes.add(nuevo);
    }

    public void bajaCliente(int dni) {
        ArrayList<Vuelo> aux = new ArrayList<>();
        int index = buscarCliente(dni);
        if (index != -1) {

            for (Vuelo vuelo : vuelos) {
                //busco el  vuelo y la fecha del avion del cliente y los borro
                if (vuelo.getCliente().getDni() == clientes.get(index).getDni()) {
                    vuelo.getTransporte().quitarFecha(vuelo.getFecha());
                    aux.add(vuelo);
                    System.out.println("prueba");
                }
            }
            vuelos.removeAll(aux);
            clientes.remove(index);
        }

    }

    public int buscarVuelo(Usuario usuario, String fecha, Ciudad origen, Ciudad destino) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCliente().getDni() == usuario.getDni() && vuelo.getFecha().equals(fecha) && vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino)) {
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

    public void altaVuelo(int indexUsuario, String fecha, Ciudad origen, Ciudad destino, Avion transporte) throws CustomException {
        if (clientes.get(indexUsuario).getAcompanantes() + 1 <= transporte.getCapacidadMaximaDePasajeros()) {
            Vuelo nuevo = new Vuelo(transporte, clientes.get(indexUsuario), fecha, origen, destino);
            vuelos.add(nuevo);
            transporte.agregarFechas(fecha);
            actualizarCostoTotal(indexUsuario, nuevo.getCostoVuelo(), 0);
            actualizarMejorAvion(nuevo.getTransporte(), indexUsuario);
        } else {
            throw new CustomException("no es posible reservar el vuelo, ustes ha superado la capacidad de pasajeros");
        }

    }

    public void bajaVuelo(Usuario cliente, int dia, int mes, int ano, Ciudad origen, Ciudad destino) throws CustomException {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaAcancelar = LocalDate.of(ano, mes, dia);
        if (fechaActual.isBefore(fechaAcancelar)) {
            String fecha = dia + "-" + mes + "-" + ano;
            cancelarVuelo(cliente, fecha, origen, destino);
        } else {
            throw new CustomException("No se puede cancelar un vuelo con menos de 24hs de anticipación");
        }

    }

    public void altaAvionRandom() {
        int rand = (int) (Math.random() * 2.8f);

        switch (rand) {
            case 0:
                aviones.add(new Gold());
                break;
            case 1:
                aviones.add(new Silver());
                break;
            case 2:
                aviones.add(new Bronze());
                break;
        }
    }

    public void altaAvion(int decicion, int cantidadDeCombustible, int cantidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsor) throws CustomException {
        switch (decicion) {
            case 0:
                Gold gold = new Gold(cantidadDeCombustible, cantidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsor);
                aviones.add(gold);
                break;
            case 1:
                Silver silver = new Silver(cantidadDeCombustible, cantidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsor);
                aviones.add(silver);
                break;
            case 2:
                Bronze bronze = new Bronze(cantidadDeCombustible, cantidadMaximaDePasajeros, velocidadMaxima, tipoDePropulsor);
                aviones.add(bronze);
                break;
            default:
                throw new CustomException("error en la eleccion de avion");
        }
    }

    public void bajaAvion(int eleccion) throws CustomException {
        if (eleccion >= 0 && eleccion < aviones.size()) {
            aviones.get(eleccion).setDisponible(false);
        } else {
            throw new CustomException("error, indice incorrecto");
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

}
 