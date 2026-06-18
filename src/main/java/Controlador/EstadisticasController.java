package Controlador;

import Modelo.Estadisticas;
import Modelo.IRepositorioResultados;

public class EstadisticasController {

    private final IRepositorioResultados repositorio;

    public EstadisticasController(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    private Estadisticas construirEstadisticas() {
        return new Estadisticas(repositorio);
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

    public String getTipoMasJugado() {
        return construirEstadisticas().getTipoMasJugado();
    }
}