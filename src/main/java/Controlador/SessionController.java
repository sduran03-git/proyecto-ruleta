package Controlador;

import Modelo.PersistenciaUsuarios;
import Modelo.Resultado;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private Usuario usuarioActual;
    private final List<Usuario>          usuarios     = new ArrayList<>();
    private final PersistenciaUsuarios   persistencia = new PersistenciaUsuarios();

    public SessionController() {
        usuarios.addAll(persistencia.cargar());
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("admin", "1234", "Administrador"));
            persistencia.guardar(usuarios);
        }
    }

    public boolean iniciarSesion(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.validarCredenciales(username, password)) {
                usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public boolean registrarUsuario(String username, String password, String nombre) {
        if (existeUsername(username)) return false;
        usuarios.add(new Usuario(username, password, nombre));
        persistencia.guardar(usuarios);
        return true;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return usuarioActual != null ? usuarioActual.getNombre() : "";
    }

    public String getUsernameUsuario() {
        return usuarioActual != null ? usuarioActual.getUsername() : "";
    }

    public void setNombreUsuario(String nombre) {
        if (usuarioActual != null) {
            usuarioActual.setNombre(nombre);
            persistencia.guardar(usuarios);
        }
    }

    public void registrarResultadoEnUsuario(Resultado resultado) {
        if (usuarioActual != null) usuarioActual.agregarResultado(resultado);
    }

    public List<Resultado> getHistorialUsuario() {
        if (usuarioActual == null) return new ArrayList<>();
        return usuarioActual.getHistorial();
    }

    private boolean existeUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }
}