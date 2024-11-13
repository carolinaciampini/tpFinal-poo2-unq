package excepciones;

public class NoHayPuntajesParaEstaCategoria extends Exception {
	
	public NoHayPuntajesParaEstaCategoria(){
		super ("No hay puntajes para esta categoría");
	}
}
