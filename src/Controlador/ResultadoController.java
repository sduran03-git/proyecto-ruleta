package Controlador;

import Modelo.Resultado;
import java.util.List;

public class ResultadoController {

    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public String formatearResultado(Resultado r) {
        String estado  = r.isAcierto() ? "GANASTE" : "PERDISTE";
        String tipo    = r.getTipo().toString();
        return "Número: " + r.getNumero()
                + " | Apuesta: " + tipo
                + " | Monto: $" + r.getMonto()
                + " | " + estado
                + " | Saldo: $" + r.getSaldoTrasApuesta();
    }

    public List<Resultado> getHistorial() {
        return session.getHistorialUsuario();
    }

    public int getTotalJugadas() {
        return getHistorial().size();
    }

    public int getTotalAciertos() {
        int aciertos = 0;
        for (Resultado r : getHistorial()) {
            if (r.isAcierto()) aciertos++;
        }
        return aciertos;
    }

    public int getGananciaNeta() {
        int neta = 0;
        for (Resultado r : getHistorial()) {
            neta += r.isAcierto() ? r.getMonto() : -r.getMonto();
        }
        return neta;
    }
}