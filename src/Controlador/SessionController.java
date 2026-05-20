package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private Usuario usuarioActual;
    private final List<Usuario> usuarios = new ArrayList<>();

    public SessionController() {
        cargarUsuariosPorDefecto();
    }

    private void cargarUsuariosPorDefecto() {
        usuarios.add(new Usuario("admin",    "1234", "Administrador"));
        usuarios.add(new Usuario("play erwan", "abcd", "Seba"));
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

    public void registrarUsuario(String username, String password, String nombre) {
        if (username == null || username.isBlank()) return;
        if (password == null || password.isBlank()) return;
        if (nombre   == null || nombre.isBlank())   return;
        usuarios.add(new Usuario(username, password, nombre));
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public String getUsernameUsuario() {
        return hayUsuario() ? usuarioActual.getUsername() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setNombreUsuario(String nombre) {
        if (hayUsuario()) usuarioActual.setNombre(nombre);
    }
}