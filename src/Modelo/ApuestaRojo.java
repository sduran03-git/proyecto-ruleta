package Modelo;

public class ApuestaRojo extends ApuestaBase {

    private static final String ETIQUETA = "ROJO";

    public ApuestaRojo(int monto) {
        super(monto, ETIQUETA);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equals("ROJO");
    }
}