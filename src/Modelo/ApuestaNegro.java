package Modelo;

public class ApuestaNegro extends ApuestaBase {

    private static final String ETIQUETA = "NEGRO";

    public ApuestaNegro(int monto) {
        super(monto, ETIQUETA);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equals("NEGRO");
    }
}