package Vista;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Ruleta;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final JFrame  frame           = new JFrame("Menú - Casino Black Cat");
    private final JLabel  lblUsuario      = new JLabel();
    private final JLabel  lblSaldo        = new JLabel();
    private final JButton btnJugar        = new JButton("Jugar a la Ruleta");
    private final JButton btnHistorial    = new JButton("Ver historial");
    private final JButton btnEstadisticas = new JButton("Estadísticas");
    private final JButton btnPerfil       = new JButton("Ver perfil");
    private final JButton btnSalir        = new JButton("Cerrar sesión");
    private final SessionController   session;
    private final RuletaController    ruletaController;
    private final ResultadoController resultadoController;

    public VentanaMenu(SessionController session) {
        this.session             = session;
        this.ruletaController    = new RuletaController(new Ruleta(1000), session);
        this.resultadoController = new ResultadoController(session);
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        refrescarInfo();
    }

    private void configurarVentana() {
        frame.setSize(300, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1, 10, 10));
    }

    private void configurarComponentes() {
        frame.add(lblUsuario);
        frame.add(lblSaldo);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnEstadisticas);
        frame.add(btnPerfil);
        frame.add(btnSalir);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(_        -> abrirRuleta());
        btnHistorial.addActionListener(_    -> abrirHistorial());
        btnEstadisticas.addActionListener(_ -> abrirEstadisticas());
        btnPerfil.addActionListener(_       -> abrirPerfil());
        btnSalir.addActionListener(_        -> cerrarSesion());
    }

    private void abrirRuleta() {
        VentanaRuleta ventana = new VentanaRuleta(ruletaController, session);
        ventana.mostrarVentana();
        ventana.alCerrar(this::refrescarSaldo);
    }

    private void abrirHistorial() {
        new VentanaHistorial(session, resultadoController).mostrarVentana();
    }

    private void abrirEstadisticas() {
        new VentanaEstadisticas(session).mostrarVentana();
    }

    private void abrirPerfil() {
        VentanaPerfil ventana = new VentanaPerfil(session, ruletaController);
        ventana.mostrarVentana();
        ventana.alCerrar(this::refrescarSaldo);
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }

    private void refrescarInfo() {
        lblUsuario.setText("Usuario: " + session.getNombreUsuario());
        lblSaldo.setText("Saldo: $"   + ruletaController.getSaldo());
    }

    public void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}