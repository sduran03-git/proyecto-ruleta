package Modelo;

public class Resultado {

    private final int        numero;
    private final ApuestaBase apuesta;
    private final boolean    acierto;
    private final int        saldoTrasApuesta;

    public Resultado(int numero, ApuestaBase apuesta,
                     boolean acierto, int saldoTrasApuesta) {
        this.numero           = numero;
        this.apuesta          = apuesta;
        this.acierto          = acierto;
        this.saldoTrasApuesta = saldoTrasApuesta;
    }

    public int        getNumero()           { return numero; }
    public ApuestaBase getApuesta()         { return apuesta; }
    public int        getMonto()            { return apuesta.getMonto(); }
    public String     getEtiquetaApuesta()  { return apuesta.getEtiqueta(); }
    public boolean    isAcierto()           { return acierto; }
    public int        getSaldoTrasApuesta() { return saldoTrasApuesta; }

    @Override
    public String toString() {
        String estado = acierto ? "GANASTE" : "PERDISTE";
        return "Número: "   + numero
                + " | Apuesta: " + apuesta.getEtiqueta()
                + " | Monto: $"  + apuesta.getMonto()
                + " | "          + estado
                + " | Saldo: $"  + saldoTrasApuesta;
    }
}
