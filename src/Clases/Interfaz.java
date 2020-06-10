package Clases;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interfaz {
    Sistema system= new Sistema() ;
    Scanner tecla = new Scanner(System.in);


    public void mainMenu() {

        int valor;
        boolean continuar;

        do {
            try {
                clearScreen();
                System.out.println("0-Administrador");
                System.out.println("1-Cliente");
                continuar = false;
                valor = tecla.nextInt();
                switch (valor) {
                    case 0:
                        System.out.println("Menu Admin");// poner menu admin
                        break;
                    case 1:
                        userMenu();
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        continuar = true;
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar un numero entero");
                tecla.next();
                continuar = true;
            }
        } while (continuar);


    }

    public void userMenu() {
        clearScreen();
        int valor;
        boolean continuar;
        System.out.println("0-Registrarse");
        System.out.println("1-Acceder");
        do {
            try {
                continuar = false;
                valor = tecla.nextInt();
                switch (valor) {
                    case 0:
                        menuRegistro();

                        break;
                    case 1:

                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        continuar = true;
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar un numero entero");
                tecla.next();
                continuar = true;
            }
        } while (continuar);
    }

    public void menuRegistro() {
        String rpass;
        int edad;
        int dni;
        String pass;
        String nombre;
        String apellido;

        try {
            clearScreen();

            System.out.println("Ingrese su nombre: ");
            tecla.nextLine();
            nombre = tecla.nextLine();
            Verificador.validarLetra(nombre);
            System.out.println("Ingrese su apellido: ");
            apellido = tecla.nextLine();
            Verificador.validarLetra(apellido);
            System.out.println("Ingrese su edad: ");
            edad = tecla.nextInt();
            Verificador.chequearEdad(edad);
            System.out.println("Ingrese su dni: ");
            dni = tecla.nextInt();
            Verificador.chequearDni(dni);
            System.out.println("Ingrese su password: ");
            tecla.nextLine();
            pass = tecla.nextLine();
            System.out.println("Repita su password: ");
            rpass = tecla.nextLine();
            Verificador.validarPass(pass, rpass);
            system.altaCliente(nombre, apellido, edad, pass, dni);
            System.out.println("Registracion exitosa");


        } catch (CustomException ex) {
            System.out.println("No se puede registrar el usuario debido a: " + ex.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar numeros para Dni o edad...");
        } finally {

            tecla.nextLine();
            mainMenu();
        }


    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
