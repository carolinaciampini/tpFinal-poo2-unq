package ranking;

import categoria.Categoria;

public class Ranking {
	private Integer puntaje;
	private String comentario;
	private Categoria categoria;
	
	public Ranking(Integer puntaje, String comentario, Categoria categoria){
		this.puntaje = puntaje;
		this.comentario = comentario;
		this.categoria = categoria;
	}
	
	public Integer getPuntaje() {
		return puntaje;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
}
