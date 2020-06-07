package Clases;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;
    private String password;
    private float costoTotal;
    private String mejorCategoria;
    private int acompanantes;

    public Usuario() {
        this.costoTotal = 0;
        this.mejorCategoria = "";
        this.acompanantes = 0;

    }

    public Usuario(String nombre, String apellido, int dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.costoTotal = 0;
        this.mejorCategoria = "";
        this.acompanantes = 0;

    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getMejorCategoria() {
        return mejorCategoria;
    }

    public void setMejorCategoria(String mejorCategoria) {
        this.mejorCategoria = mejorCategoria;
    }

    public int getAcompanantes() {
        return acompanantes;
    }

    public void setAcompanantes(int acompanantes) {
        this.acompanantes = acompanantes;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + edad + " " + dni + " Mejor Categoria: " + mejorCategoria + " Costo acumulado: " + costoTotal;
    }
}
