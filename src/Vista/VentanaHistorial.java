package Vista;

import Controlador.ResultadoController;
import Controlador.SessionController;
import Modelo.Resultado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {

    private final JFrame  frame     = new JFrame("Historial - Casino Black Cat");
    private final JTable  tabla     = new JTable();
    private final JLabel  lblJugadas  = new JLabel();
    private final JLabel  lblAciertos = new JLabel();
    private final JLabel  lblGanancia = new JLabel();
    private final ResultadoController resultadoController;

    public VentanaHistorial(SessionController session,
                            ResultadoController resultadoController) {
        this.resultadoController = resultadoController;
        configurarVentana();
        configurarComponentes();
        cargarHistorial();
    }

    private void configurarVentana() {
        frame.setSize(620, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private void configurarComponentes() {
        frame.add(new JScrollPane(tabla),  BorderLayout.CENTER);
        frame.add(crearPanelResumen(),     BorderLayout.SOUTH);
    }

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Resumen"));
        panel.add(lblJugadas);
        panel.add(lblAciertos);
        panel.add(lblGanancia);
        return panel;
    }

    private void cargarHistorial() {
        List<Resultado> historial = resultadoController.getHistorial();
        tabla.setModel(construirModelo(historial));
        actualizarResumen();
    }

    private DefaultTableModel construirModelo(List<Resultado> historial) {
        String[] columnas = {"#", "Número", "Apuesta", "Monto", "Resultado", "Saldo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        int fila = 1;
        for (Resultado r : historial) {
            modelo.addRow(new Object[]{
                    fila++,
                    r.getNumero(),
                    r.getEtiquetaApuesta(),
                    "$" + r.getMonto(),
                    r.isAcierto() ? "GANASTE" : "PERDISTE",
                    "$" + r.getSaldoTrasApuesta()
            });
        }
        return modelo;
    }

    private void actualizarResumen() {
        lblJugadas.setText("Total jugadas: "  + resultadoController.getTotalJugadas());
        lblAciertos.setText("Total aciertos: " + resultadoController.getTotalAciertos());
        lblGanancia.setText("Ganancia neta: $" + resultadoController.getGananciaNeta());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}