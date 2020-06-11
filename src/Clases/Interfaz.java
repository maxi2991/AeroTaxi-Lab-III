package Clases;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import javax.xml.validation.Validator;
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
                        tecla.nextLine();
                        String pass = tecla.nextLine();

                        try {
                            Verificador.validarPass(pass, "administrador1");
                            System.out.println("bienvenido!");
                            adminMenu();
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
                        break;
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
                        accesoMenu();
                        break;
                    case 9:
                        System.out.println("volviendo al menu principal");
                        continuar = false;
                        break;
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
            tecla.nextLine();
        }

    }

    public void accesoMenu() {
        int dni;
        int index;
        String pass;
        try {
            System.out.println("Login:");
            System.out.println("Ingrese Dni ");
            dni = tecla.nextInt();
            Verificador.chequearDni(dni);
            index = system.buscarCliente(dni);
            System.out.println("Ingrese contraseña ");
            tecla.nextLine();
            pass = tecla.nextLine();
            Verificador.validarPass(pass, system.getClientes().get(index).getPassword());
            System.out.println("Acceso exitoso!");
            userOptions(index);
        } catch (CustomException ex) {
            System.out.println("No se puede acceder debiado a : " + ex.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar valores enteros para DNI");
            tecla.nextLine();

        }
    }


    public void userOptions(int indexUsuario) {
        int eleccion;
        boolean continuar = true;

        System.out.println("Menu Opciones de Cliente "+system.getClientes().get(indexUsuario).getNombre());
        do {
            try {
                System.out.println("0-Realizar reserva");
                System.out.println("1-Cancelar Vuelo");
                System.out.println("2-Mostrar Historial de Vuelos"); // hay que realizar dicha funcion
                System.out.println("9-Volver al menu de acceso");
                eleccion = tecla.nextInt();


                switch (eleccion) {
                    case 0:
                        //Menu Reserva
                        break;
                    case 1:
                        //Menu Cancelar vuelo
                        break;
                    case 2:
                        //Metodo para mostrar historial
                        break;
                    case 9:
                        System.out.println("saliendo del menu");
                        continuar = false;
                        break;
                    default:
                        System.out.println("opcion incorrecta");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ingrese un numero entero, por favor");
                tecla.nextLine();
            }

        } while (continuar);

    }

    public void cancelarMenu(int indexUsuario){



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
                        menuBajaCliente();
                        break;
                    case 4:
                        menuBajaAvion();
                        break;
                    case 5:
                        //menuAltaAvion();
                        break;
                    case 6:
                        //menuBajaVuelo();
                        break;
                    case 9:
                        System.out.println("saliendo del menu");
                        continuar = false;
                        break;
                    default:
                        System.out.println("opcion incorrecta");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ingrese un numero entero, por favor");
                tecla.nextLine();
            }

        } while (continuar);


    }

    public void mostrarCiudades() {
        System.out.println("0-Buenos Aires");
        System.out.println("1-Cordoba");
        System.out.println("2-Santiago de Chile");
        System.out.println("3-Montevideo");
    }

    public void menuBajaVuelo() {
        System.out.println("MENU BAJA VUELO");
        System.out.println("por favor, ingrese el indice del vuelo a eliminar");
    }

    public void menuAltaAvion() {
        //TODO: ver el codigo del constructor del avion porque hay que cambiar cosas
    }

    public void menuBajaAvion() {
        System.out.println("por favor, ingrese el indice del avion a eliminar");
        try {
            int eleccion = tecla.nextInt();
            system.bajaAvion(eleccion);
            System.out.println("avion eliminado exitosamente!");
        }catch (InputMismatchException e) {
            System.out.println("por favor, ingrese un numero entero");
        }






    }

    public void menuBajaCliente() {
        int dniUsuario;

        System.out.println("MENU BAJA CLIENTE");
        System.out.println("por favor, ingrese el DNI del usuario que quiere eliminar");
        system.mostrarClientes();

        try {
            dniUsuario = tecla.nextInt();
            Verificador.chequearDni(dniUsuario);
            system.bajaCliente(dniUsuario);
            System.out.println("cliente borrado exitosamente");
        } catch (CustomException e) {
            System.out.println("por favor, ingrese un dni correcto");
        }



    }

}
