import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame      = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave   = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistro = new JButton("Registrarse");

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("jugador1", "abcd", "Juan Pérez"));
        USUARIOS.add(new Usuario("jugador2", "pass", "María López"));
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
        btnIngresar.addActionListener(e -> login());
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String nombre = validarCredenciales(u, p);
        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "¡Bienvenido " + nombre + "!");
            abrirSaludo(nombre);
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre();
            }
        }
        return "";
    }

    private void abrirSaludo(String nombre) {
        frame.dispose();
        VentanaSaludo saludo = new VentanaSaludo(nombre);
        saludo.mostrarVentana();
    }

    private void abrirRegistro() {
        frame.dispose();
        VentanaRegistro registro = new VentanaRegistro();
        registro.mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}