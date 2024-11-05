package excepciones;

public class PropietarioNoRegistradoExcepcion extends Exception{
	
		public PropietarioNoRegistradoExcepcion() {
	        super("El propietario no está registrado en el sitio.");
	    }
}
