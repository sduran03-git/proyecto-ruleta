package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {

    private final Ruleta           ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta  = ruleta;
        this.session = session;
    }

    public Resultado jugar(TipoApuesta tipo, int monto) {
        int numero        = ruleta.girar();
        Resultado resultado = ruleta.registrarResultado(numero, tipo, monto);
        session.registrarResultadoEnUsuario(resultado);
        return resultado;
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public boolean saldoSuficiente(int monto) {
        return ruleta.getSaldo() >= monto;
    }

    public boolean haySaldo() {
        return ruleta.getSaldo() > 0;
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}