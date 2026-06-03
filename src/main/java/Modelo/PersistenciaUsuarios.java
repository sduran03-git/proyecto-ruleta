package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaUsuarios {

    private static final String ARCHIVO = "usuarios.txt";

    public void guardar(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios) {
                writer.write(u.getUsername() + "," + u.getPassword() + "," + u.getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public List<Usuario> cargar() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return usuarios;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Usuario u = parsearLinea(linea);
                if (u != null) usuarios.add(u);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    private Usuario parsearLinea(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 3) return null;
        return new Usuario(partes[0], partes[1], partes[2]);
    }
}