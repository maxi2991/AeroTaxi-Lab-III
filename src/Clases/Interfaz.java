package Clases;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interfaz {
    Sistema system = new Sistema();
    Scanner tecla = new Scanner(System.in);

    public void mainMenu() {

        int valor;
        boolean continuar = true;

        do {

            System.out.println("ingresar como:");

            System.out.println("0-Administrador");
            System.out.println("1-Cliente");

            System.out.println("9-Salir");

            try {
                valor = tecla.nextInt();
                switch (valor) {
                    case 0:
                        System.out.println("ingrese su contraseña");

                        String pass = tecla.nextLine();

                        try {
                            Verificador.validarPass(pass, "administrador1");
                            System.out.println("bienvenido!");
                            //menuAdmin();
                        } catch (CustomException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 1:
                        userMenu();
                        break;

                    case 9:
                        System.out.println("saliendo del programa");
                        continuar = false;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar un numero entero");
                tecla.next();
            }
        } while (continuar);


    }

    public void userMenu() {
        int valor;
        boolean continuar = true;

        do {
            System.out.println("0-Registrarse");
            System.out.println("1-Acceder");

            System.out.println("9-Volver");

            try {

                valor = tecla.nextInt();
                switch (valor) {
                    case 0:
                        menuRegistro();
                        break;
                    case 1:

                        break;
                    case 9:
                        System.out.println("volviendo al menu principal");
                        continuar = false;
                    default:
                        System.out.println("Opcion incorrecta");

                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar un numero entero");
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
        }

    }

    public void adminMenu() {
        int eleccion;
        boolean continuar = true;

        System.out.println("Menu Admin");// poner menu admin
        do {
            try {
                System.out.println("0-Mostrar Clientes");
                System.out.println("1-Mostrar Aviones");
                System.out.println("2-Mostrar Vuelos");
                System.out.println("3-Baja Cliente");
                System.out.println("4-Baja Avion");
                System.out.println("5-Alta Avion");
                System.out.println("6-Baja Vuelo");

                System.out.println("9-Volver al menu principal");
                System.out.println("ingrese un numero");
                eleccion = tecla.nextInt();


                switch (eleccion) {
                    case 0:
                        system.mostrarClientes();
                        break;
                    case 1:
                        system.mostrarTodosLosAviones();
                        break;
                    case 2:
                        system.mostrarVuelos();
                        break;
                    case 3:
                        //menuBajaCliente()
                        break;
                    case 4:
                        //menuBajaAvion()
                        break;
                    case 5:
                        //menuAltaAvion()
                        break;
                    case 6:
                        //menuBajaVuelo()
                        break;
                    case 9:
                        System.out.println("saliendo del menu");
                        continuar = false;
                    default:
                        System.out.println("opcion incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("ingrese un numero entero, por favor");
            }

        } while (continuar);


    }



}
