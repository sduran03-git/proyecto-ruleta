package Modelo;

public interface IRepositorioResultados {

	/**
	 * 
	 * @param resultado
	 */
	void agregar(Resultado resultado);

	List<Resultado> obtenerTodos();

}