package Clases;
import java.io.*;
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

    public Sistema(){}

    public void mostrarClientes() {
        for (Usuario actual : clientes) {
            System.out.println(actual);
        }
    }

    //si el avion no contiene la fecha en su lista de fechas ocupadas, lo muestro
    public void mostrarAvionesDisponivlesPorFecha(String fecha) {
        int i=0;
        for (Avion actual : aviones) {
            if (!actual.getFechas().contains(fecha)) {
                System.out.println(actual+" "+i);
            }
            i++;
        }
    }

    public void mostrarTodosLosAviones() {
        int i = 0;
        for(Avion avion:aviones) {
            System.out.println(i + ". " + avion);
            i++;
        }
    }

    public void mostrarVuelos() {
        for (Vuelo actual : vuelos) {
            System.out.println(actual);
        }
    }

    public void mostrarVuelosParaFecha(String fecha) {
        for(Vuelo vuelo:vuelos) {
            if(vuelo.getFecha().equals(fecha)) {
                System.out.println(vuelo);
            }
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

    public void altaCliente(String nombre, String apellido, int edad, String pass, int dni) {
        Usuario nuevo = new Usuario(nombre, apellido, dni, edad);
        nuevo.setPassword(pass);
        clientes.add(nuevo);
    }

    public void bajaCliente(int dni) {
        int indice = buscarCliente(dni);
        if (indice != -1)
            clientes.remove(indice);
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

    public void altaAvion(int decicion, int cantidadDeCombustible, int cantidadMaximaDePasajeros, int velocidadMaxima, Propulsores tipoDePropulsor) {
        switch (decicion) {
            case 0:
                Gold gold = new Gold(cantidadDeCombustible,cantidadMaximaDePasajeros,velocidadMaxima,tipoDePropulsor);
                aviones.add(gold);
                break;
            case 1:
                Silver silver = new Silver(cantidadDeCombustible,cantidadMaximaDePasajeros,velocidadMaxima,tipoDePropulsor);
                aviones.add(silver);
                break;
            case 2:
                Bronze bronze = new Bronze(cantidadDeCombustible,cantidadMaximaDePasajeros,velocidadMaxima,tipoDePropulsor);
                aviones.add(bronze);
                break;
            default:
                System.out.println("error en la eleccion de avion");
                break;
        }
    }

    public void bajaAvion(int eleccion) throws IndexOutOfBoundsException {
        aviones.remove(eleccion);
        throw new IndexOutOfBoundsException("indice incorrecto");
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

            if ( actual.getTarifa() == 6000) {
                clientes.get(indexUsuario).setMejorCategoria("Gold");
            } else {
                if (actual.getTarifa() == 4000 && clientes.get(indexUsuario).getMejorCategoria().equals("Bronze")) {
                    clientes.get(indexUsuario).setMejorCategoria("Silver");
                }
            }

        }
    }

}
 