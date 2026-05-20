package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;

public class ResultadoController {

    private final Ruleta ruleta;

    public ResultadoController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public String formatearResultado(Resultado resultado) {
        String color  = obtenerColor(resultado.getNumero());
        String estado = resultado.isAcierto() ? "GANASTE" : "PERDISTE";
        return "Número " + resultado.getNumero() +
                " (" + color + ")" +
                " | Apuesta=" + resultado.getTipo() +
                " | Monto=$" + resultado.getMonto() +
                " | " + estado +
                " | Saldo=$" + resultado.getSaldoTrasApuesta();
    }

    private String obtenerColor(int numero) {
        if (numero == 0)          return "Verde";
        if (ruleta.esRojo(numero)) return "Rojo";
        return "Negro";
    }

    public String formatearHistorial() {
        if (ruleta.getHistorialSize() == 0) {
            return "Aún no hay rondas jugadas.";
        }
        StringBuilder sb = new StringBuilder("===== HISTORIAL =====\n");
        for (Resultado r : ruleta.getHistorial()) {
            sb.append(formatearResultado(r)).append("\n");
        }
        return sb.toString();
    }

    public String formatearEstadisticas() {
        return "===== ESTADÍSTICAS =====\n" +
                "Rondas jugadas : " + ruleta.getHistorialSize() + "\n" +
                "Total apostado : $" + ruleta.getTotalApostado() + "\n" +
                "Aciertos       : " + ruleta.getTotalAciertos() + "\n" +
                "Ganancia/Pérd. : $" + ruleta.getGananciaNeta() + "\n" +
                "Saldo actual   : $" + ruleta.getSaldo();
    }
}
