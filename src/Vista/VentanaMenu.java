package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame frame          = new JFrame("Casino Black Cat - Menú");
    private final JButton btnJugar      = new JButton("Jugar");
    private final JButton btnHistorial  = new JButton("Historial");
    private final JButton btnPerfil     = new JButton("Perfil");
    private final JButton btnSalir      = new JButton("Salir");
    private final JTextArea txtInfo     = new JTextArea();
    private final JLabel lblUsuario     = new JLabel();
    private final JLabel lblSaldo       = new JLabel();
    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaMenu(SessionController session) {
        this.session          = session;
        this.ruletaController = new RuletaController(1000);
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        refrescarInfo();
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
        frame.add(panelInfo,    BorderLayout.CENTER);
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(btnJugar);
        panel.add(btnHistorial);
        panel.add(btnPerfil);
        panel.add(btnSalir);
        panel.add(lblUsuario);
        panel.add(lblSaldo);
        return panel;
    }

    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new BorderLayout());
        txtInfo.setEditable(false);
        txtInfo.setText("Bienvenido/a al menú principal.\n" +
                "A la izquierda tienes:\n" +
                "· Jugar: abre la ventana de juego.\n" +
                "· Historial: muestra tus estadísticas.\n" +
                "· Perfil: ver y editar tu perfil.\n" +
                "· Salir: cierra sesión y vuelve al login.");
        panel.add(new JScrollPane(txtInfo), BorderLayout.CENTER);
        return panel;
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnPerfil.addActionListener(e -> abrirPerfil());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void abrirJuego() {
        VentanaRuleta ventanaRuleta = new VentanaRuleta(ruletaController);
        ventanaRuleta.mostrarVentana();
    }

    private void mostrarHistorial() {
        txtInfo.setText(ruletaController.getResumenHistorial());
    }

    private void abrirPerfil() {
        VentanaPerfil perfil = new VentanaPerfil(session, ruletaController);
        perfil.mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        VentanaLogin login = new VentanaLogin(session);
        login.mostrarVentana();
    }

    private void refrescarInfo() {
        lblUsuario.setText(session.getNombreUsuario());
        lblSaldo.setText("$" + ruletaController.getSaldo());
    }

    public void refrescarSaldo() {
        lblSaldo.setText("$" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}