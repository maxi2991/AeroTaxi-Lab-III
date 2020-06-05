package Clases;

public enum Distancia {
    BSASCORDOBA(695), BSASSANTIAGO(1400), BSASMONTEVIDEO(950), CORDOBAMONTEVIDEO(1190), CORDOBASANTIAGO(1050), MONTEVIDEOSANTIAGO(2100);

    private int distancia;

    private Distancia(int distancia) {
        this.distancia = distancia;
    }


    public int getDistancia() {
        return distancia;
    }


}
