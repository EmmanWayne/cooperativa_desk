package clases;

public class Retiros {
	private String Id_retiro;
	private String Identidad;
	private String cantidad_retirar;
	private String fecha;
	
	public String getId_retiro() {
		return Id_retiro;
	}
	public void setId_retiro(String id_retiro) {
		Id_retiro = id_retiro;
	}
	public String getIdentidad() {
		return Identidad;
	}
	public void setIdentidad(String identidad) {
		Identidad = identidad;
	}
	public String getCantidad_retirar() {
		return cantidad_retirar;
	}
	public void setCantidad_retirar(String cantidad_retirar) {
		this.cantidad_retirar = cantidad_retirar;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
