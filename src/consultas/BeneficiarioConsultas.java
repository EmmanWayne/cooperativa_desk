package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import consultas.consultas_generales.*;
import clases.Beneficiario;
import conexion.conexion;

public class BeneficiarioConsultas extends conexion{
	//Atributos
	PreparedStatement ps = null;
	String sql = "";
	public boolean modificar(Beneficiario beneficiario,Opciones opcion) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		if (opcion == Opciones.INSERTAR) {
			sql = "EXEC dbo.Pa_Beneficiario 'ADD',1,?,?,?,?,?,?";			
		} else if (opcion == Opciones.EDITAR){
			sql = "EXEC dbo.Pa_Beneficiario 'UPD',1,?,?,?,?,?,?";
		}else {
			System.err.println("Opcion no valida");
			return false;
		}
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,beneficiario.getId_beneficiario());
			ps.setString(2,beneficiario.getNombre_Beneficiario());
			ps.setString(3,beneficiario.getTelefono());
			ps.setString(4,beneficiario.getDireccion());
			ps.setString(5,beneficiario.getParentesco());
			ps.setString(6,beneficiario.getIdentidad());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public boolean deshabilitar(String identidad) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		sql = "EXEC dbo.Pa_Beneficiario 'DEL',1,?";
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,identidad);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
