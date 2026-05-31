package Controlador;

import Modelo.ApuestaBase;
import Modelo.Resultado;
import Modelo.Ruleta;

public class RuletaController {

    private final Ruleta            ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta  = ruleta;
        this.session = session;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        Resultado resultado = ruleta.jugar(apuesta);
        session.registrarResultadoEnUsuario(resultado);
        return resultado;
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public boolean saldoSuficiente(int monto) {
        return ruleta.saldoSuficiente(monto);
    }

    public boolean haySaldo() {
        return ruleta.haySaldo();
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}