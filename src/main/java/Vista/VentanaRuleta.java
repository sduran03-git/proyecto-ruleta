package Vista;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.ApuestaBase;
import Modelo.ApuestaImpar;
import Modelo.ApuestaNegro;
import Modelo.ApuestaPar;
import Modelo.ApuestaRojo;
import Modelo.Resultado;
import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final JFrame            frame         = new JFrame("Ruleta - Casino Black Cat");
    private final JComboBox<String> cmbTipo       = new JComboBox<>();
    private final JComboBox<String> cmbColor      = new JComboBox<>();
    private final JComboBox<String> cmbParidad    = new JComboBox<>();
    private final JSpinner          spinMonto     = new JSpinner(
            new SpinnerNumberModel(100, 1, 10000, 50));
    private final JButton           btnGirar      = new JButton("Girar");
    private final JLabel            lblSaldo      = new JLabel();
    private final JTextArea         txtResultados = new JTextArea();
    private final RuletaController   ruletaController;
    private final ResultadoController resultadoController;

    public VentanaRuleta(RuletaController ruletaController, SessionController session) {
        this.ruletaController    = ruletaController;
        this.resultadoController = new ResultadoController(session);
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        refrescarSaldo();
    }

    private void configurarVentana() {
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private void configurarComponentes() {
        frame.add(crearPanelApuesta(),   BorderLayout.NORTH);
        frame.add(crearPanelResultado(), BorderLayout.CENTER);
    }

    private JPanel crearPanelApuesta() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        cmbTipo.addItem("Color");     cmbTipo.addItem("Paridad");
        cmbColor.addItem("Rojo");     cmbColor.addItem("Negro");
        cmbParidad.addItem("Par");    cmbParidad.addItem("Impar");
        panel.add(new JLabel("Tipo de apuesta:"));   panel.add(cmbTipo);
        panel.add(new JLabel("Seleccione color:"));  panel.add(cmbColor);
        panel.add(new JLabel("Seleccione paridad:")); panel.add(cmbParidad);
        panel.add(new JLabel("Monto:"));             panel.add(crearPanelMonto());
        return panel;
    }

    private JPanel crearPanelMonto() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(spinMonto); panel.add(btnGirar); panel.add(lblSaldo);
        return panel;
    }

    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new BorderLayout());
        txtResultados.setEditable(false);
        panel.add(new JScrollPane(txtResultados), BorderLayout.CENTER);
        return panel;
    }

    private void configurarEventos() {
        btnGirar.addActionListener(e -> intentarJugar());
    }

    private void intentarJugar() {
        if (saldoInsuficiente()) return;
        int monto = (int) spinMonto.getValue();
        if (!ruletaController.saldoSuficiente(monto)) {
            JOptionPane.showMessageDialog(frame,
                    "No tienes suficiente saldo para esa apuesta.");
            return;
        }
        ApuestaBase apuesta   = obtenerApuesta(monto);
        Resultado   resultado = ruletaController.jugar(apuesta);
        mostrarResultado(resultado);
        refrescarSaldo();
    }

    private ApuestaBase obtenerApuesta(int monto) {
        if (cmbTipo.getSelectedItem().equals("Color")) {
            return cmbColor.getSelectedItem().equals("Rojo") ?
                    new ApuestaRojo(monto) : new ApuestaNegro(monto);
        } else {
            return cmbParidad.getSelectedItem().equals("Par") ?
                    new ApuestaPar(monto) : new ApuestaImpar(monto);
        }
    }

    private boolean saldoInsuficiente() {
        if (!ruletaController.haySaldo()) {
            JOptionPane.showMessageDialog(frame,
                    "¡Te quedaste sin saldo! Ve al perfil para recargar.");
            btnGirar.setEnabled(false);
            return true;
        }
        return false;
    }

    private void mostrarResultado(Resultado resultado) {
        txtResultados.append(
                resultadoController.formatearResultado(resultado) + "\n");
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());
    }

    public void alCerrar(Runnable callback) {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                callback.run();
            }
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}