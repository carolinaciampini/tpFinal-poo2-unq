package excepciones;

public class EstadoInvalidoParaRankear extends Exception{
	public EstadoInvalidoParaRankear() {
        super("Se puede rankear solo si se hizo el checkout en la reserva");
    }
}
