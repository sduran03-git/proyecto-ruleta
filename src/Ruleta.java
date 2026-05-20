import java.util.Random;

public class Ruleta {

    private static final int MAX_HISTORIAL = 100;
    private static final int[] NUMEROS_ROJOS =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    private int[] historialNumeros  = new int[MAX_HISTORIAL];
    private int[] historialApuestas = new int[MAX_HISTORIAL];
    private boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;
    private int saldo;
    private final Random rng = new Random();

    public Ruleta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int girar() {
        return rng.nextInt(37);
    }

    public boolean evaluarResultado(int numero, char tipo) {
        if (tipo == 'R') {
            return esRojo(numero);
        } else if (tipo == 'N') {
            return !esRojo(numero) && numero != 0;
        } else if (tipo == 'P') {
            return numero != 0 && numero % 2 == 0;
        } else if (tipo == 'I') {
            return numero % 2 != 0;
        } else {
            return false;
        }
    }

    public boolean esRojo(int numero) {
        for (int rojo : NUMEROS_ROJOS) {
            if (numero == rojo) return true;
        }
        return false;
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize >= MAX_HISTORIAL) return;
        historialNumeros [historialSize] = numero;
        historialApuestas[historialSize] = apuesta;
        historialAciertos[historialSize] = acierto;
        historialSize++;
        actualizarSaldo(apuesta, acierto);
    }

    private void actualizarSaldo(int apuesta, boolean acierto) {
        if (acierto) {
            saldo += apuesta;
        } else {
            saldo -= apuesta;
        }
    }

    public int getSaldo() {
        return saldo;
    }

    public int getHistorialSize() {
        return historialSize;
    }

    public int getTotalApostado() {
        int total = 0;
        for (int i = 0; i < historialSize; i++) total += historialApuestas[i];
        return total;
    }

    public int getTotalAciertos() {
        int aciertos = 0;
        for (int i = 0; i < historialSize; i++) if (historialAciertos[i]) aciertos++;
        return aciertos;
    }

    public int getGananciaNeta() {
        int neta = 0;
        for (int i = 0; i < historialSize; i++) {
            neta += historialAciertos[i] ? historialApuestas[i] : -historialApuestas[i];
        }
        return neta;
    }
}