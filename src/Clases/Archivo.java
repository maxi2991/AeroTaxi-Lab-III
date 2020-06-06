package Clases;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Archivo {

    /* esto escribe una lista de usuarios en el archivo*/
    public static void escribirUsuarios(File file, List<Usuario> users) {
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

    /*esto lee del archivo una lista de ususarios y la devuelve en otra*/
    public static List<Usuario> leerUsusarios(File file) {
        ObjectMapper mapper = new ObjectMapper();
        List<Usuario>users = new LinkedList<>();
        try{
            users = mapper.readValue(file, new TypeReference<LinkedList<Usuario>>() {});
        }catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return users;
    }

    public static List<Vuelo> leerVuelos(File file) {
        ObjectMapper mapper = new ObjectMapper();
        List<Vuelo> vuelos = new ArrayList<>();
        try{
            vuelos = mapper.readValue(file, new TypeReference<ArrayList<Vuelo>>() {});
        }catch(IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return vuelos;
    }

    public static List<Avion> leerAviones(File file) {
        ObjectMapper mapper = new ObjectMapper();
        List<Avion> aviones = new ArrayList<>();
        try{
            aviones = mapper.readValue(file, new TypeReference<ArrayList<Avion>>() {});
        }catch(IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return aviones;
    }

}
