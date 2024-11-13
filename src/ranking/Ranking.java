package ranking;

import categoria.Categoria;

public class Ranking {
	private int puntaje;
	private String comentario;
	private Categoria categoria;
	
	public Ranking(int puntaje, String comentario, Categoria categoria){
		this.puntaje = puntaje;
		this.comentario = comentario;
		this.categoria = categoria;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
}
