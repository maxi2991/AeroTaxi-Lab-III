package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

// Aca van todos los metodoas de comprobacion y validacion utilizados en el menu
public class Verificador {
    public Verificador() {

    }

    // para passwords
    public static boolean validarPass(String pass, String repass) throws CustomException {
        if (!pass.equals(repass))
            throw new CustomException("Los passwords no coinciden");
        if (pass.length() < 8)
            throw new CustomException("El password debe tener al menos 8 caracteres");
        if (!sololetrasYnumeros(pass))
            throw new CustomException("Solo se permiten caracteres de letras o numeros ");
        if (!unNumero(pass))
            throw new CustomException("Debe contener al menos un caracter numerico ");
        if (!unaLetra(pass))
            throw new CustomException("Debe contener al menos un caracter alfabetico ");


        return true;
    }


    public static boolean sololetrasYnumeros(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            if (!Character.isDigit(pass.charAt(i)) && !Character.isLetter(pass.charAt(i)))
                return false;
        }

        return true;
    }

    public static boolean unNumero(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i)))
                return true;
        }
        return false;
    }

    public static boolean unaLetra(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isLetter(pass.charAt(i)))
                return true;
        }
        return false;
    }

    // nombres y apellidos
    public static boolean validarLetra(String nombre) throws CustomException{
        if(!soloLetra(nombre))
            throw new CustomException("Debe ingresar solo caracteres alfabeticos");
        if(nombre.length()<4)
            throw new CustomException("Debe ingresar al menos 4 caracteres");


        return true;

    }

    public static boolean soloLetra(String cadena){
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isLetter(cadena.charAt(i)))
                return false;
        }
        return true;
    }

    // Para Edad
    public static boolean chequearEdad(int edad ) throws CustomException{
        if(edad>65 || edad<21)
            throw new CustomException("Edad no permitida");

        return true;

    }

    // Para Dni
    public static boolean chequearDni(int dni ) throws CustomException{
        if(String.valueOf(dni).length()!=8)
            throw new CustomException("Dni incorrecto");

        return true;

    }
    //para verificar origen y destino
    public static boolean verficarOrigenDestino(int origen, int destino)throws CustomException {
        if(origen == destino) {
            throw new CustomException("el origen y el destino no pueden ser iguales");
        }

        return true;
    }

    public static void verificarAvion(int indexAvion, ArrayList<Avion> aviones, String fecha) throws CustomException {
        if(indexAvion < 0 || indexAvion >= aviones.size()) {
            throw new CustomException("fuera del rango permitido");
        }

        if (!aviones.get(indexAvion).isDisponible()) {
            throw new CustomException("avion no disponible");
        }

        if(aviones.get(indexAvion).getFechas().contains(fecha)) {
            throw new CustomException("avion ocupado para estas fechas");
        }
    }



    //Para fechas

    public static boolean validarFecha(int dia, int mes, int ano) throws CustomException {
        int diasDelmes = diasDelMes(mes, ano);
        LocalDate fechaActual = LocalDate.now();

        if (dia <= 0 || dia > diasDelmes || dia < fechaActual.getDayOfMonth())
            throw new CustomException("Dia incorrecto");
        if (mes <= 0 || mes > 12 || mes< fechaActual.getMonthValue())
            throw new CustomException("Mes incorrecto");


        return true;
    }


    public static int diasDelMes(int mes, int ano) {
        switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10:  // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                if (((ano % 100 == 0) && (ano % 400 == 0)) ||
                        ((ano % 100 != 0) && (ano % 4 == 0)))
                    return 29;  // Año Bisiesto
                else
                    return 28;
            default:
                return 0;
        }
    }
}
