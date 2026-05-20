import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {

    private final JFrame frame         = new JFrame("Registro - Casino Black Cat");
    private final JLabel lblUsuario    = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave      = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JLabel lblNombre     = new JLabel("Nombre completo:");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnVolver    = new JButton("Volver al Login");

    public VentanaRegistro() {
        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
    }

    private void configurarComponentes() {
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(btnRegistrar);
        frame.add(btnVolver);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void registrar() {
        String u = txtUsuario.getText().trim();
        String p = new String(txtClave.getPassword()).trim();
        String n = txtNombre.getText().trim();
        if (camposVacios(u, p, n)) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        VentanaLogin.USUARIOS.add(new Usuario(u, p, n));
        JOptionPane.showMessageDialog(frame, "Usuario registrado con éxito.");
        volverLogin();
    }

    private boolean camposVacios(String u, String p, String n) {
        return u.isEmpty() || p.isEmpty() || n.isEmpty();
    }

    private void volverLogin() {
        frame.dispose();
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
