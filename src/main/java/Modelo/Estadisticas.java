package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

	private final IRepositorioResultados repositorio;

	public Estadisticas(IRepositorioResultados repositorio) {
		this.repositorio = repositorio;
	}

	private List<Resultado> getHistorial() {
		return repositorio.obtenerTodos();
	}

	public int getTotalJugadas() {
		return getHistorial().size();
	}

	public int getVictorias() {
		int victorias = 0;
		for (Resultado r : getHistorial()) {
			if (r.isAcierto()) victorias++;
		}
		return victorias;
	}

	public double getPorcentajeVictorias() {
		if (getHistorial().isEmpty()) return 0.0;
		return (getVictorias() * 100.0) / getTotalJugadas();
	}

	public int getRachaMaxima() {
		int rachaActual = 0;
		int rachaMax    = 0;
		for (Resultado r : getHistorial()) {
			if (r.isAcierto()) {
				rachaActual++;
				if (rachaActual > rachaMax) rachaMax = rachaActual;
			} else {
				rachaActual = 0;
			}
		}
		return rachaMax;
	}

	public String getTipoMasJugado() {
		if (getHistorial().isEmpty()) return "Sin jugadas";
		Map<String, Integer> conteo = new HashMap<>();
		for (Resultado r : getHistorial()) {
			String etiqueta = r.getEtiquetaApuesta();
			conteo.put(etiqueta, conteo.getOrDefault(etiqueta, 0) + 1);
		}
		return conteo.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.get().getKey();
	}
}