package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

	private static final String ARCHIVO = "historial.csv";

	@Override
	public void agregar(Resultado resultado) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(ARCHIVO, true))) {
			writer.write(serializar(resultado));
			writer.newLine();
		} catch (IOException e) {
			System.err.println("Error al guardar resultado: " + e.getMessage());
		}
	}

	@Override
	public List<Resultado> obtenerTodos() {
		List<Resultado> lista = new ArrayList<>();
		File archivo = new File(ARCHIVO);
		if (!archivo.exists()) return lista;
		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				Resultado r = deserializar(linea);
				if (r != null) lista.add(r);
			}
		} catch (IOException e) {
			System.err.println("Error al leer historial: " + e.getMessage());
		}
		return lista;
	}

	private String serializar(Resultado r) {
		return r.getNumero()           + ","
				+ r.getEtiquetaApuesta()  + ","
				+ r.getMonto()            + ","
				+ r.isAcierto()           + ","
				+ r.getSaldoTrasApuesta();
	}

	private Resultado deserializar(String linea) {
		String[] partes = linea.split(",");
		if (partes.length != 5) return null;
		int        numero  = Integer.parseInt(partes[0]);
		String     etiqueta = partes[1];
		int        monto   = Integer.parseInt(partes[2]);
		boolean    acierto = Boolean.parseBoolean(partes[3]);
		int        saldo   = Integer.parseInt(partes[4]);
		ApuestaBase apuesta = reconstruirApuesta(etiqueta, monto);
		if (apuesta == null) return null;
		return new Resultado(numero, apuesta, acierto, saldo);
	}

	private ApuestaBase reconstruirApuesta(String etiqueta, int monto) {
		switch (etiqueta) {
			case "ROJO":  return new ApuestaRojo(monto);
			case "NEGRO": return new ApuestaNegro(monto);
			case "PAR":   return new ApuestaPar(monto);
			case "IMPAR": return new ApuestaImpar(monto);
			default:      return null;
		}
	}
}