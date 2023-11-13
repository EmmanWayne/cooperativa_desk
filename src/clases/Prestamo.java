package clases;

public class Prestamo {
	private String Numero_Prestamo;
	private String Identidad;
	private String Fecha_Prestamo;
	private String Cantidad_Prestada;
	private String Numero_cuotas;
	private String Porcentaje_interes;
	private String cuotas_pagadas;
	
	public String getCuotas_pagadas() {
		return cuotas_pagadas;
	}
	public void setCuotas_pagadas(String cuotas_pagadas) {
		this.cuotas_pagadas = cuotas_pagadas;
	}
	public String getNumero_Prestamo() {
		return Numero_Prestamo;
	}
	public void setNumero_Prestamo(String numero_Prestamo) {
		Numero_Prestamo = numero_Prestamo;
	}
	public String getIdentidad() {
		return Identidad;
	}
	public void setIdentidad(String identidad) {
		Identidad = identidad;
	}
	public String getFecha_Prestamo() {
		return Fecha_Prestamo;
	}
	public void setFecha_Prestamo(String fecha_Prestamo) {
		Fecha_Prestamo = fecha_Prestamo;
	}
	public String getCantidad_Prestada() {
		return Cantidad_Prestada;
	}
	public void setCantidad_Prestada(String cantidad_Prestada) {
		Cantidad_Prestada = cantidad_Prestada;
	}
	public String getNumero_cuotas() {
		return Numero_cuotas;
	}
	public void setNumero_cuotas(String numero_cuotas) {
		Numero_cuotas = numero_cuotas;
	}
	public String getPorcentaje_interes() {
		return Porcentaje_interes;
	}
	public void setPorcentaje_interes(String porcentaje_interes) {
		Porcentaje_interes = porcentaje_interes;
	}
}
