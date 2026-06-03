package Modelo;

public abstract class ApuestaBase {

    private final int    monto;
    private final String etiqueta;

    public ApuestaBase(int monto, String etiqueta) {
        this.monto    = monto;
        this.etiqueta = etiqueta;
    }

    public abstract boolean acierta(int numero, String color);

    public int getMonto() {
        return monto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta + " $" + monto;
    }
}