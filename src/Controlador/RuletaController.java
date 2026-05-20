package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {

    private final Ruleta ruleta;

    public RuletaController(int saldoInicial) {
        this.ruleta = new Ruleta(saldoInicial);
    }

    public Resultado jugar(TipoApuesta tipo, int monto) {
        int numero = ruleta.girar();
        return ruleta.registrarResultado(numero, tipo, monto);
    }

    public boolean saldoSuficiente(int monto) {
        return ruleta.getSaldo() >= monto;
    }

    public boolean haySaldo() {
        return ruleta.getSaldo() > 0;
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public int getHistorialSize() {
        return ruleta.getHistorialSize();
    }

    public int getTotalApostado() {
        return ruleta.getTotalApostado();
    }

    public int getTotalAciertos() {
        return ruleta.getTotalAciertos();
    }

    public int getGananciaNeta() {
        return ruleta.getGananciaNeta();
    }

    public String getResumenHistorial() {
        return "Rondas jugadas : " + ruleta.getHistorialSize() + "\n" +
                "Total apostado : $" + ruleta.getTotalApostado() + "\n" +
                "Aciertos       : " + ruleta.getTotalAciertos() + "\n" +
                "Ganancia/Pérd. : $" + ruleta.getGananciaNeta() + "\n" +
                "Saldo actual   : $" + ruleta.getSaldo();
    }
}