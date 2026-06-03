package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaPerfil {

    private final JFrame frame           = new JFrame("Perfil - Casino Black Cat");
    private final JLabel lblUsernameLabel = new JLabel("Usuario:");
    private final JLabel lblUsername      = new JLabel();
    private final JLabel lblNombreLabel   = new JLabel("Nombre:");
    private final JTextField txtNombre    = new JTextField();
    private final JLabel lblSaldoLabel    = new JLabel("Saldo actual:");
    private final JLabel lblSaldo         = new JLabel();
    private final JLabel lblRecargaLabel  = new JLabel("Monto recarga:");
    private final JSpinner spinRecarga    = new JSpinner(
            new SpinnerNumberModel(100, 1, 10000, 50));
    private final JButton btnGuardar      = new JButton("Guardar nombre");
    private final JButton btnRecargar     = new JButton("Recargar saldo");
    private final JButton btnVolver       = new JButton("Volver al menú");
    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaPerfil(SessionController session, RuletaController ruletaController) {
        this.session          = session;
        this.ruletaController = ruletaController;
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        cargarDatos();
    }

    private void configurarVentana() {
        frame.setSize(350, 320);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
    }

    private void configurarComponentes() {
        frame.add(lblUsernameLabel);
        frame.add(lblUsername);
        frame.add(lblNombreLabel);
        frame.add(txtNombre);
        frame.add(lblSaldoLabel);
        frame.add(lblSaldo);
        frame.add(lblRecargaLabel);
        frame.add(spinRecarga);
        frame.add(btnGuardar);
        frame.add(btnRecargar);
        frame.add(new JLabel());
        frame.add(btnVolver);
    }

    private void configurarEventos() {
        btnGuardar.addActionListener(e -> guardarNombre());
        btnRecargar.addActionListener(e -> recargarSaldo());
        btnVolver.addActionListener(e -> frame.dispose());
    }

    private void cargarDatos() {
        lblUsername.setText(session.getUsernameUsuario());
        txtNombre.setText(session.getNombreUsuario());
        refrescarSaldo();
    }

    private void guardarNombre() {
        String nuevoNombre = txtNombre.getText().trim();
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "El nombre no puede estar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        session.setNombreUsuario(nuevoNombre);
        JOptionPane.showMessageDialog(frame, "Nombre actualizado con éxito.");
    }

    private void recargarSaldo() {
        int monto = (int) spinRecarga.getValue();
        ruletaController.depositar(monto);
        refrescarSaldo();
        JOptionPane.showMessageDialog(frame,
                "Saldo recargado. Nuevo saldo: $" + ruletaController.getSaldo());
    }

    private void refrescarSaldo() {
        lblSaldo.setText("$" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void alCerrar(Runnable callback) {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                callback.run();
            }
        });
    }
}
