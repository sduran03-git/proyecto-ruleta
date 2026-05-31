package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {

    private static final int[] NUMEROS_ROJOS =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    private int saldo;
    private final Random         rng      = new Random();
    private final List<Resultado> historial = new ArrayList<>();

    public Ruleta(int saldoInicial) { this.saldo = saldoInicial; }
    public Ruleta()                 { this(0); }

    public int girar() {
        return rng.nextInt(37);
    }

    public String colorDe(int numero) {
        if (numero == 0) return "VERDE";
        for (int rojo : NUMEROS_ROJOS) {
            if (numero == rojo) return "ROJO";
        }
        return "NEGRO";
    }

    public Resultado jugar(ApuestaBase apuesta) {
        int     numero  = girar();
        String  color   = colorDe(numero);
        boolean acierto = apuesta.acierta(numero, color);
        actualizarSaldo(apuesta.getMonto(), acierto);
        Resultado resultado = new Resultado(numero, apuesta, acierto, saldo);
        historial.add(resultado);
        return resultado;
    }

    private void actualizarSaldo(int monto, boolean acierto) {
        if (acierto) saldo += monto;
        else         saldo -= monto;
    }

    public void depositar(int monto) {
        if (monto <= 0) return;
        saldo += monto;
    }

    public boolean saldoSuficiente(int monto) {
        return saldo >= monto;
    }

    public boolean haySaldo() {
        return saldo > 0;
    }

    public int              getSaldo()        { return saldo; }
    public int              getHistorialSize() { return historial.size(); }
    public List<Resultado>  getHistorial()    { return historial; }

    public int getTotalApostado() {
        int total = 0;
        for (Resultado r : historial) total += r.getMonto();
        return total;
    }

    public int getTotalAciertos() {
        int aciertos = 0;
        for (Resultado r : historial) if (r.isAcierto()) aciertos++;
        return aciertos;
    }

    public int getGananciaNeta() {
        int neta = 0;
        for (Resultado r : historial) {
            neta += r.isAcierto() ? r.getMonto() : -r.getMonto();
        }
        return neta;
    }
}