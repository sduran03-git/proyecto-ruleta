package Modelo;

public class ApuestaPar extends ApuestaBase {

    private static final String ETIQUETA = "PAR";

    public ApuestaPar(int monto) {
        super(monto, ETIQUETA);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && numero % 2 == 0;
    }
}