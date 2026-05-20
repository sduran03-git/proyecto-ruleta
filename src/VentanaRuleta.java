import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final JFrame frame       = new JFrame("Ruleta - Casino Black Cat");
    private final JComboBox<String> cmbTipo     = new JComboBox<>();
    private final JComboBox<String> cmbColor    = new JComboBox<>();
    private final JComboBox<String> cmbParidad  = new JComboBox<>();
    private final JSpinner spinMonto            = new JSpinner(new SpinnerNumberModel(100, 1, 10000, 50));
    private final JButton btnGirar              = new JButton("Girar");
    private final JLabel lblSaldo               = new JLabel();
    private final JTextArea txtResultados       = new JTextArea();
    private final Ruleta ruleta;

    public VentanaRuleta(Ruleta ruleta) {
        this.ruleta = ruleta;
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        actualizarSaldo();
    }

    private void configurarVentana() {
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private void configurarComponentes() {
        JPanel panelApuesta = crearPanelApuesta();
        JPanel panelResultado = crearPanelResultado();
        frame.add(panelApuesta, BorderLayout.NORTH);
        frame.add(panelResultado, BorderLayout.CENTER);
    }

    private JPanel crearPanelApuesta() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        cmbTipo.addItem("Color");
        cmbTipo.addItem("Paridad");
        cmbColor.addItem("Rojo");
        cmbColor.addItem("Negro");
        cmbParidad.addItem("Par");
        cmbParidad.addItem("Impar");
        panel.add(new JLabel("Tipo de apuesta:"));
        panel.add(cmbTipo);
        panel.add(new JLabel("Seleccione color:"));
        panel.add(cmbColor);
        panel.add(new JLabel("Seleccione paridad:"));
        panel.add(cmbParidad);
        panel.add(new JLabel("Monto:"));
        panel.add(crearPanelMonto());
        return panel;
    }

    private JPanel crearPanelMonto() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSaldo.setText("Saldo: $" + ruleta.getSaldo());
        panel.add(spinMonto);
        panel.add(btnGirar);
        panel.add(lblSaldo);
        return panel;
    }

    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new BorderLayout());
        txtResultados.setEditable(false);
        panel.add(new JScrollPane(txtResultados), BorderLayout.CENTER);
        return panel;
    }

    private void configurarEventos() {
        btnGirar.addActionListener(e -> jugar());
    }

    private void jugar() {
        if (saldoInsuficiente()) return;
        char tipo  = obtenerTipoApuesta();
        int monto  = (int) spinMonto.getValue();
        if (monto > ruleta.getSaldo()) {
            JOptionPane.showMessageDialog(frame,
                    "No tienes suficiente saldo para esa apuesta.");
            return;
        }
        int numero = ruleta.girar();
        boolean acierto = ruleta.evaluarResultado(numero, tipo);
        ruleta.registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
        actualizarSaldo();
    }

    private boolean saldoInsuficiente() {
        if (ruleta.getSaldo() <= 0) {
            JOptionPane.showMessageDialog(frame,
                    "¡Te quedaste sin saldo! Vuelve al menú.");
            btnGirar.setEnabled(false);
            return true;
        }
        return false;
    }
    private char obtenerTipoApuesta() {
        if (cmbTipo.getSelectedItem().equals("Color")) {
            return cmbColor.getSelectedItem().equals("Rojo") ? 'R' : 'N';
        } else {
            return cmbParidad.getSelectedItem().equals("Par") ? 'P' : 'I';
        }
    }

    private void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        String color  = ruleta.esRojo(numero) ? "Rojo" : (numero == 0 ? "Verde" : "Negro");
        String estado = acierto ? "GANASTE" : "PERDISTE";
        txtResultados.append(
                "Número " + numero + " (" + color + ") | " +
                        "Apuesta=" + tipo + " | " +
                        "Monto=$" + monto + " | " +
                        estado + " | " +
                        "Saldo=" + ruleta.getSaldo() + "\n"
        );
    }

    private void actualizarSaldo() {
        lblSaldo.setText("Saldo: $" + ruleta.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
