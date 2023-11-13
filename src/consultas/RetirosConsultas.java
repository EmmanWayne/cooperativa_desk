package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.AhorroRetirable;
import clases.Retiros;
import conexion.conexion;
import consultas.consultas_generales.Opciones;

public class RetirosConsultas extends conexion{
	//Atributos
	PreparedStatement ps = null;
	String sql = "";
	public boolean modificar(Retiros rt,Opciones opcion) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		if (opcion == Opciones.INSERTAR) {
			sql = "EXEC dbo.Pa_Retiros 'ADD',1,?,?,?,?";			
		} else if (opcion == Opciones.EDITAR){
			sql = "EXEC dbo.Pa_Retiros 'UPD',1,?,?,?,?";
		}else {
			System.err.println("Opcion no valida");
			return false;
		}
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,rt.getId_retiro());
			ps.setString(2,rt.getIdentidad());
			ps.setString(3,rt.getCantidad_retirar());
			ps.setString(4,rt.getFecha());
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
	
	public boolean eliminar(String id) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		sql = "EXEC dbo.Pa_Retiros 'DEL',1,?";
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,id);
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
