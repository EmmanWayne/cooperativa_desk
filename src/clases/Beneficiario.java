package clases;

public class Beneficiario {
	private String id_beneficiario;
	private String Nombre_Beneficiario;
	private String Telefono;
	private String Direccion;
	private String Parentesco;
	private String Identidad;
	
	public String getId_beneficiario() {
		return id_beneficiario;
	}
	public void setId_beneficiario(String id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}
	public String getNombre_Beneficiario() {
		return Nombre_Beneficiario;
	}
	public void setNombre_Beneficiario(String nombre_Beneficiario) {
		Nombre_Beneficiario = nombre_Beneficiario;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getParentesco() {
		return Parentesco;
	}
	public void setParentesco(String parentesco) {
		Parentesco = parentesco;
	}
	public String getIdentidad() {
		return Identidad;
	}
	public void setIdentidad(String identidad) {
		Identidad = identidad;
	}
}
