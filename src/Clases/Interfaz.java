package Clases;

import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Interfaz {

    Sistema system = new Sistema();
    Scanner tecla = new Scanner(System.in);

    /*public Interfaz() {
        system = new Sistema();
        tecla = new Scanner(System.in);
    }*/

    public void start() {
        mainMenu();
    }

    private void mainMenu() {

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
                        System.out.println("guardando los datos");
                        system.guardarAviones();
                        system.guardarVuelos();
                        system.guardarClientes();
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

    private void userMenu() {
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
                tecla.nextLine();
            }
        } while (continuar);
    }

    private void menuRegistro() {
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
            if (system.buscarCliente(dni) == -1) {
                system.altaCliente(nombre, apellido, edad, pass, dni);
                System.out.println("Registracion exitosa");
            } else {
                System.out.println("Este cliente ya existe en el sistema");
            }

        } catch (CustomException ex) {
            System.out.println("No se puede registrar el usuario debido a: " + ex.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar numeros para Dni o edad...");
            tecla.nextLine();
        }

    }

    private void accesoMenu() {
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

    private void userOptions(int indexUsuario) {
        int eleccion;
        boolean continuar = true;


        do {
            try {
                System.out.println("Menu Opciones de Cliente " + system.getClientes().get(indexUsuario).getNombre());
                System.out.println("0-Realizar reserva");
                System.out.println("1-Cancelar Vuelo");
                System.out.println("2-Mostrar Historial de Vuelos");
                System.out.println("3-Mostrar Vuelos de Fecha elegida");
                System.out.println("9-Volver al menu de acceso");
                eleccion = tecla.nextInt();


                switch (eleccion) {
                    case 0:
                        reservaMenu(indexUsuario);
                        break;
                    case 1:
                        cancelarMenu(indexUsuario);
                        break;
                    case 2:
                        system.mostrarVuelosUsuario(system.getClientes().get(indexUsuario));
                        break;

                    case 3:
                        menuFecha(indexUsuario);
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

    private void reservaMenu(int indexUsuario) {
        String fecha;
        int dia;
        int mes;
        int origen;
        int destino;
        int indexAvion;


        try {
            System.out.println("Ingrese dia de su reserva");
            dia = tecla.nextInt();
            System.out.println("Ingrese mes de su reserva");
            mes = tecla.nextInt();
            System.out.println("ingrese cantidad de acompañantes");
            system.getClientes().get(indexUsuario).setAcompanantes(tecla.nextInt());
            Verificador.validarFecha(dia, mes, LocalDate.now().getYear());
            mostrarCiudades();
            System.out.println("Ingrese Ciudad de origen ");
            origen = tecla.nextInt();
            System.out.println("Ingrese Ciudad de destino");
            destino = tecla.nextInt();
            Verificador.verficarOrigenDestino(origen, destino);
            fecha = dia + "-" + mes + "-" + LocalDate.now().getYear();
            if (!system.mostrarAvionesDisponiblesPorFecha(fecha)) {
                System.out.println("Ingrese el numero de avion ");
                indexAvion = tecla.nextInt();
                Verificador.verificarAvion(indexAvion, system.getAviones(), fecha);

                if (confirmarVuelo(system.getAviones().get(indexAvion), system.getClientes().get(indexUsuario), Ciudad.devolverCiudad(origen), Ciudad.devolverCiudad(destino))) {
                    system.altaVuelo(indexUsuario, fecha, Ciudad.devolverCiudad(origen), Ciudad.devolverCiudad(destino), system.getAviones().get(indexAvion));
                    System.out.println("Reserva Exitosa!");
                } else {
                    System.out.println("Vuelo rechazado exitosamente!");
                }
            } else {
                System.out.println("no hay aviones disponibles para esta fecha");
            }

        } catch (CustomException ex) {
            System.out.println("No se pudo reservar el vuelo debido a : " + ex.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error en la eleccion " + e.getMessage());
        } catch (InputMismatchException exc) {
            System.out.println("Debe ingresar un numero entero");
            tecla.nextLine();
        }
    }

    private void cancelarMenu(int indexUsuario) {
        int dia;
        int mes;
        int origen;
        int destino;
        try {
            System.out.println("Ingrese dia del vuelo");
            dia = tecla.nextInt();
            System.out.println("Ingrese mes del vuelo");
            mes = tecla.nextInt();
            Verificador.validarFecha(dia, mes, LocalDate.now().getYear());
            mostrarCiudades();
            System.out.println("Ingrese Ciudad de origen");
            origen = tecla.nextInt();
            System.out.println("Ingrese Ciudad de destino");
            destino = tecla.nextInt();
            Verificador.verficarOrigenDestino(origen, destino);
            system.bajaVuelo(system.getClientes().get(indexUsuario), dia, mes, LocalDate.now().getYear(), Ciudad.devolverCiudad(origen), Ciudad.devolverCiudad(destino));
            System.out.println("Vuelo cancelado Exitosamente!");
        } catch (CustomException ex) {
            System.out.println("No se pudo cancelar el vuelo debido a : " + ex.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error en la eleccion " + e.getMessage());
        } catch (InputMismatchException exc) {
            System.out.println("Debe ingresar un numero entero");
            tecla.nextLine();
        }


    }

    private void menuFecha(int indexUsuario) {
        int dia;
        int mes;
        String fecha;
        try {
            System.out.println("Ingrese dia del vuelo");
            dia = tecla.nextInt();
            System.out.println("Ingrese mes del vuelo");
            mes = tecla.nextInt();
            fecha = dia + "-" + mes + "-" + LocalDate.now().getYear();
            if (!system.mostrarVuelosParaFecha(fecha, system.getClientes().get(indexUsuario)))
                System.out.println("No existen vuelos suyos para esta fecha");
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un numero entero");
            tecla.nextLine();
        }

    }

    private void adminMenu() {
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
                System.out.println("6-Alta Avion Random");
                System.out.println("7-Baja Vuelo");
                System.out.println("8-Mostrar Vuelos de Fecha");

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
                        menuAltaAvion();
                        break;
                    case 6:
                        System.out.println("GENERANDO AVION RANDOM...");
                        system.altaAvionRandom();
                        break;
                    case 7:
                        menuBajaVuelo();
                        break;
                    case 8:
                        menuVuelosFecha();
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

    private void mostrarCiudades() {
        System.out.println("0-Buenos Aires");
        System.out.println("1-Cordoba");
        System.out.println("2-Santiago de Chile");
        System.out.println("3-Montevideo");
    }

    private void menuBajaVuelo() {
        System.out.println("MENU BAJA VUELO");

        try {
            System.out.println("por favor, ingrese el DNI del usuario al que le va a borrar el vuelo");
            system.mostrarClientes();
            int dniUser = tecla.nextInt();
            Verificador.chequearDni(dniUser);


            System.out.println("ingrese el dia");
            int dia = tecla.nextInt();

            System.out.println("ingrese el mes en numeros");
            int mes = tecla.nextInt();

            int anio = LocalDate.now().getYear();
            System.out.println("elija un origen");
            mostrarCiudades();
            Ciudad origen = Ciudad.devolverCiudad(tecla.nextInt());
            System.out.println("elija un destino");
            Ciudad destino = Ciudad.devolverCiudad(tecla.nextInt());
            Verificador.verficarOrigenDestino(origen.ordinal(), destino.ordinal());

            system.bajaVuelo(system.getClientes().get(system.buscarCliente(dniUser)), dia, mes, anio, origen, destino);
            System.out.println("vuelo eliminado con exito!");

        } catch (CustomException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            tecla.nextInt();
        }


    }

    private void menuVuelosFecha() {
        int dia;
        int mes;
        int anio;
        String fecha;

        try {
            System.out.println("ingrese el dia");
            dia = tecla.nextInt();

            System.out.println("ingrese el mes en numeros");
            mes = tecla.nextInt();

            anio = LocalDate.now().getYear();
            fecha = dia + "-" + mes + "-" + anio;

            if (!system.mostrarTodosLosVuelosEnFecha(fecha))
                System.out.println("No existen vuelos para esta fecha");

        } catch (InputMismatchException e) {
            tecla.nextInt();
        }

    }

    private void menuAltaAvion() {
        System.out.println("MENU ALTA AVION");


        try {
            System.out.println("Seleccione el tipo de taxi a crear");
            System.out.println("0-Gold");
            System.out.println("1-Silver");
            System.out.println("2-Bronze");
            int decicion = tecla.nextInt();

            System.out.println("ingrese la cantidad de conbustible que el avion va a poder llevar");
            int cantCombustible = tecla.nextInt();

            System.out.println("ingrese la cantidad de pasajeros que puede llevar el taxi");
            int cantPasajeros = tecla.nextInt();

            System.out.println("indique la velocidad maxima del taxi");
            int velocidadMAX = tecla.nextInt();

            System.out.println("ingrese el tipo de propulsion del taxi");
            System.out.println("0-A Reaccion");
            System.out.println("1-A Helice");
            System.out.println("2-A Piston");
            Propulsores propulsor = Propulsores.seleccionarPropulsor(tecla.nextInt());

            system.altaAvion(decicion, cantCombustible, cantPasajeros, velocidadMAX, propulsor);
            System.out.println("Taxi creado con exito!");

        } catch (InputMismatchException e) {
            System.out.println("ingrese los datos requeridos");
            tecla.nextLine();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuBajaAvion() {
        System.out.println("por favor, ingrese el indice del avion a eliminar");
        system.mostrarTodosLosAviones();
        try {
            int eleccion = tecla.nextInt();
            system.bajaAvion(eleccion);
            System.out.println("avion eliminado exitosamente!");
        } catch (InputMismatchException e) {
            System.out.println("por favor, ingrese un numero entero");
            tecla.nextLine();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }


    }

    private void menuBajaCliente() {
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

    public boolean confirmarVuelo(Avion transporte, Usuario actual, Ciudad origen, Ciudad destino) {
        Vuelo preVuelo = new Vuelo(transporte, actual, "", origen, destino);
        System.out.println("El costo del Vuelo es:  " + preVuelo.getCostoVuelo());
        System.out.println("¿Confirma el vuelo? ");
        System.out.println("0-SI\n1-NO");
        if (tecla.nextInt() == 0) {
            return true;
        } else {
            return false;
        }

    }

}
