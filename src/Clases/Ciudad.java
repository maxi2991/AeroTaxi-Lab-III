package Clases;

public enum Ciudad {
    BUENOS_AIRES, CORDOBA, SANTIAGO, MONTEVIDEO;

    public static Ciudad devolverCiudad(int eleccion) {
        if (eleccion >= 0 && eleccion < Ciudad.values().length) {
            return Ciudad.values()[eleccion];
        } else {
            throw new IndexOutOfBoundsException("indice incorrecto");
        }
    }
}
