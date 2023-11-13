package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import consultas.consultas_generales.*;
import clases.Registro;
import conexion.conexion;

public class RegistroConsultas extends conexion{
	//Atributos
	PreparedStatement ps = null;
	String sql = "";
	public boolean modificar(Registro registro,Opciones opcion) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		if (opcion == Opciones.INSERTAR) {
			sql = "EXEC dbo.Pa_Registros 'ADD',1,?,?,?,?,?,?,?,?,?,?,?";			
		} else if (opcion == Opciones.EDITAR){
			sql = "EXEC dbo.Pa_Registros 'UPD',1,?,?,?,?,?,?,?,?,?,?,?";
		}else {
			System.err.println("Opcion no valida");
			return false;
		}
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,registro.getIdentidad());
			ps.setString(2,registro.getPrimer_Nombre());
			ps.setString(3,registro.getSegundo_Nombre());
			ps.setString(4,registro.getPrimer_Apellido());
			ps.setString(5,registro.getSegundo_Apellido());
			ps.setString(6,registro.getDireccion());
			ps.setString(7,registro.getTelefono());
			ps.setString(8,registro.getEmail());
			ps.setString(9,registro.getNumero_cuenta());
			ps.setString(10,registro.getNombre_Proyecto());
			ps.setString(11,registro.getFecha_Registro());
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
		sql = "EXEC dbo.Pa_Registros 'DEL',1,?";
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
