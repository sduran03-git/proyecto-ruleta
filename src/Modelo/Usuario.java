package Modelo;

public class Usuario {

    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre   = nombre;
    }

    public Usuario() {
        this("invitado", "0000", "Invitado");
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return;
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) return;
        this.password = password;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    @Override
    public String toString() {
        return "Usuario [username=" + username + ", nombre=" + nombre + "]";
    }
}
