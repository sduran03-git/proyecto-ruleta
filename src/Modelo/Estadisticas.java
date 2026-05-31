package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

	private final List<Resultado> historial;

	public Estadisticas(List<Resultado> historial) {
		this.historial = historial;
	}

	public int getTotalJugadas() {
		return historial.size();
	}

	public int getVictorias() {
		int victorias = 0;
		for (Resultado r : historial) {
			if (r.isAcierto()) victorias++;
		}
		return victorias;
	}

	public double getPorcentajeVictorias() {
		if (historial.isEmpty()) return 0.0;
		return (getVictorias() * 100.0) / getTotalJugadas();
	}

	public int getRachaMaxima() {
		int rachaActual = 0;
		int rachaMax    = 0;
		for (Resultado r : historial) {
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
		if (historial.isEmpty()) return "Sin jugadas";
		Map<String, Integer> conteo = new HashMap<>();
		for (Resultado r : historial) {
			String etiqueta = r.getEtiquetaApuesta();
			conteo.put(etiqueta, conteo.getOrDefault(etiqueta, 0) + 1);
		}
		return conteo.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.get().getKey();
	}
}