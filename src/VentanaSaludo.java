import javax.swing.*;
import java.awt.*;

public class VentanaSaludo {

    private final JFrame frame = new JFrame("Casino Black Cat");
    private final JLabel lblSaludo = new JLabel();
    private final JButton btnJugar = new JButton("Jugar Ruleta");
    private final JButton btnSalir = new JButton("Salir");

    public VentanaSaludo(String nombreUsuario) {
        configurarVentana();
        configurarComponentes(nombreUsuario);
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
    }

    private void configurarComponentes(String nombreUsuario) {
        lblSaludo.setText("¡Bienvenido, " + nombreUsuario + "!");
        lblSaludo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblSaludo);
        frame.add(btnJugar);
        frame.add(btnSalir);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirRuleta());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirRuleta() {
        JOptionPane.showMessageDialog(frame, "Abriendo Ruleta...");
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}