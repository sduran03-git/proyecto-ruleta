import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("Casino Black Cat - Menú");
    private final JButton btnJugar     = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir     = new JButton("Salir");
    private final JTextArea txtInfo    = new JTextArea();
    private final JLabel lblUsuario    = new JLabel();
    private final String nombreUsuario;
    private final Ruleta ruleta;

    public VentanaMenu(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.ruleta = new Ruleta(1000);
        configurarVentana();
        configurarComponentes();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private void configurarComponentes() {
        JPanel panelBotones = crearPanelBotones();
        JPanel panelInfo    = crearPanelInfo();
        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(panelInfo, BorderLayout.CENTER);
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        lblUsuario.setText(nombreUsuario);
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(btnJugar);
        panel.add(btnHistorial);
        panel.add(btnSalir);
        panel.add(new JLabel());
        panel.add(lblUsuario);
        return panel;
    }

    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new BorderLayout());
        txtInfo.setEditable(false);
        txtInfo.setText("Bienvenido/a al menú principal.\n" +
                "A la izquierda tienes:\n" +
                "· Jugar: abre la ventana de juego.\n" +
                "· Historial: muestra tus estadísticas.\n" +
                "· Salir: cierra sesión y vuelve al login.");
        panel.add(new JScrollPane(txtInfo), BorderLayout.CENTER);
        return panel;
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void abrirJuego() {
        VentanaRuleta ventanaRuleta = new VentanaRuleta(ruleta);
        ventanaRuleta.mostrarVentana();
    }

    private void mostrarHistorial() {
        txtInfo.setText(
                "===== HISTORIAL =====\n" +
                        "Rondas jugadas : " + ruleta.getHistorialSize() + "\n" +
                        "Total apostado : $" + ruleta.getTotalApostado() + "\n" +
                        "Aciertos       : " + ruleta.getTotalAciertos() + "\n" +
                        "Ganancia/Pérd. : $" + ruleta.getGananciaNeta() + "\n" +
                        "Saldo actual   : $" + ruleta.getSaldo()
        );
    }

    private void cerrarSesion() {
        frame.dispose();
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}