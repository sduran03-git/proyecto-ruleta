package Modelo;

public class Resultado {

    private final int numero;
    private final TipoApuesta tipo;
    private final int monto;
    private final boolean acierto;
    private final int saldoTrasApuesta;

    public Resultado(int numero, TipoApuesta tipo, int monto,
                     boolean acierto, int saldoTrasApuesta) {
        this.numero           = numero;
        this.tipo             = tipo;
        this.monto            = monto;
        this.acierto          = acierto;
        this.saldoTrasApuesta = saldoTrasApuesta;
    }

    public int getNumero() {
        return numero;
    }

    public TipoApuesta getTipo() {
        return tipo;
    }

    public int getMonto() {
        return monto;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public int getSaldoTrasApuesta() {
        return saldoTrasApuesta;
    }

    @Override
    public String toString() {
        String estado = acierto ? "GANASTE" : "PERDISTE";
        return "Número " + numero +
                " | Apuesta=" + tipo +
                " | Monto=$" + monto +
                " | " + estado +
                " | Saldo=$" + saldoTrasApuesta;
    }
}
