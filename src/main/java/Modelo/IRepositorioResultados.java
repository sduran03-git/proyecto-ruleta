package Modelo;

import java.util.List;

public interface IRepositorioResultados {

	void agregar(Resultado resultado);

	List<Resultado> obtenerTodos();
}