package filtros;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FilterBuilder {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Double precioMin;
	private Double precioMax;
	private Integer huespedes;
	private String ciudad;
	private FiltroCompuesto filtro;
	
	
	public FilterBuilder(LocalDate fechaEntrada, LocalDate fechaSalida, Double precioMin, Double precioMax, int huespedes,
			String ciudad) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precioMin = precioMin;
		this.precioMax = precioMax;
		this.huespedes = huespedes;
		this.ciudad = ciudad;
		
		this.filtro = new FiltroCompuesto();
		this.filtro.agregarFiltro(new FiltroCiudad(this.ciudad));
        this.filtro.agregarFiltro(new FiltroFecha(this.fechaEntrada, this.fechaSalida));
	}
	
	public FiltroCompuesto build() {
		if (this.huespedes != null) {
            this.filtro.agregarFiltro(new FiltroHuespedes(this.huespedes));
        };
		if (this.precioMax != null || this.precioMin != null) {
		            this.filtro.agregarFiltro(new FiltroPrecio(this.precioMax, precioMin));
		};	
		return this.filtro;
		};
		
	public List<Inmueble> filtrar(SitioWeb s) {
		return this.build().aplicarFiltro(s);
	}
	
}
