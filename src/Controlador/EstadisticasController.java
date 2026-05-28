package Controlador;

import Modelo.Estadisticas;
import Modelo.TipoApuesta;

public class EstadisticasController {

    private final SessionController session;

    public EstadisticasController(SessionController session) {
        this.session = session;
    }

    private Estadisticas construirEstadisticas() {
        return new Estadisticas(session.getHistorialUsuario());
    }

    public int getTotalJugadas() {
        return construirEstadisticas().getTotalJugadas();
    }

    public int getVictorias() {
        return construirEstadisticas().getVictorias();
    }

    public double getPorcentajeVictorias() {
        return construirEstadisticas().getPorcentajeVictorias();
    }

    public int getRachaMaxima() {
        return construirEstadisticas().getRachaMaxima();
    }

    public TipoApuesta getTipoMasJugado() {
        return construirEstadisticas().getTipoMasJugado();
    }
}