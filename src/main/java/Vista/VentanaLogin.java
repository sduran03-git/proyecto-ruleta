package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin {

    private final JFrame frame         = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario    = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave      = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar  = new JButton("Ingresar");
    private final JButton btnRegistro  = new JButton("Registrarse");
    private final SessionController session;

    public VentanaLogin(SessionController session) {
        this.session = session;
        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));
    }

    private void configurarComponentes() {
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistro);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> intentarLogin());
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    private void intentarLogin() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        if (session.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(frame,
                    "¡Bienvenido " + session.getNombreUsuario() + "!");
            abrirMenu();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Credenciales incorrectas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirMenu() {
        frame.dispose();
        VentanaMenu menu = new VentanaMenu(session);
        menu.mostrarVentana();
    }

    private void abrirRegistro() {
        frame.dispose();
        VentanaRegistro registro = new VentanaRegistro(session);
        registro.mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}