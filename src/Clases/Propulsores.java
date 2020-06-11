package Clases;


public enum Propulsores {
    REACCION,
    HELICE,
    PISTONES;

    public static Propulsores randomPropulsor() {
        //es 2.8 porque asi aumenta la posibilidad de que toque pistones, sino no sale
        return Propulsores.values()[(int)(Math.random() * 2.8f)];
    }

    public static Propulsores seleccionarPropulsor(int index) throws IndexOutOfBoundsException{
        if(index >= 0 && index < Propulsores.values().length) {
            return Propulsores.values()[index];
        } else {
            throw new IndexOutOfBoundsException("indice incorrecto");
        }
    }

}
