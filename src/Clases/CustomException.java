package Clases;
// Excepcion custom para enviar los mensajes de error
public class CustomException extends Exception {
    public static final long serialVersionUID=700L;

    public CustomException(String mensaje){
        super(mensaje);
    }
}
