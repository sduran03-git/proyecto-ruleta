package Modelo;

public class ApuestaImpar extends ApuestaBase {

    private static final String ETIQUETA = "IMPAR";

    public ApuestaImpar(int monto) {
        super(monto, ETIQUETA);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero % 2 != 0;
    }
}