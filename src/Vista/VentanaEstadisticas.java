package Vista;

import Controlador.EstadisticasController;
import Controlador.SessionController;
import Modelo.TipoApuesta;
import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas {

    private final JFrame  frame              = new JFrame("Estadísticas - Casino Black Cat");
    private final JLabel  lblTotalJugadas    = new JLabel();
    private final JLabel  lblVictorias       = new JLabel();
    private final JLabel  lblPorcentaje      = new JLabel();
    private final JLabel  lblRachaMaxima     = new JLabel();
    private final JLabel  lblTipoMasJugado   = new JLabel();
    private final JButton btnCerrar          = new JButton("Cerrar");
    private final EstadisticasController estadisticasController;

    public VentanaEstadisticas(SessionController session) {
        this.estadisticasController = new EstadisticasController(session);
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        cargarEstadisticas();
    }

    private void configurarVentana() {
        frame.setSize(350, 280);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 10, 10));
    }

    private void configurarComponentes() {
        frame.add(lblTotalJugadas);
        frame.add(lblVictorias);
        frame.add(lblPorcentaje);
        frame.add(lblRachaMaxima);
        frame.add(lblTipoMasJugado);
        frame.add(btnCerrar);
    }

    private void configurarEventos() {
        btnCerrar.addActionListener(_ -> frame.dispose());
    }

    private void cargarEstadisticas() {
        lblTotalJugadas.setText("Total jugadas:       "
                + estadisticasController.getTotalJugadas());
        lblVictorias.setText("Victorias:           "
                + estadisticasController.getVictorias());
        lblPorcentaje.setText("Porcentaje victorias: "
                + String.format("%.1f", estadisticasController.getPorcentajeVictorias()) + "%");
        lblRachaMaxima.setText("Racha máxima:        "
                + estadisticasController.getRachaMaxima());
        cargarTipoMasJugado();
    }

    private void cargarTipoMasJugado() {
        TipoApuesta tipo = estadisticasController.getTipoMasJugado();
        String texto     = (tipo != null) ? tipo.toString() : "Sin jugadas aún";
        lblTipoMasJugado.setText("Tipo más jugado:     " + texto);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}