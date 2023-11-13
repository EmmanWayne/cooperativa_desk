package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import clases.AhorroRetirable;
import conexion.conexion;
import consultas.consultas_generales.Opciones;

public class AhorrosRetirablesConsultas extends conexion{
	//Atributos
	PreparedStatement ps = null;
	String sql = "";
	public boolean modificar(AhorroRetirable ahorroRetirable,Opciones opcion) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		if (opcion == Opciones.INSERTAR) {
			sql = "EXEC dbo.Pa_AhorroRetirable 'ADD',1,?,?,?,?";			
		} else if (opcion == Opciones.EDITAR){
			sql = "EXEC dbo.Pa_AhorroRetirable 'UPD',1,?,?,?,?";
		}else {
			System.err.println("Opcion no valida");
			return false;
		}
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,ahorroRetirable.getId_retirable());
			ps.setString(2,ahorroRetirable.getIdentidad());
			ps.setString(3,ahorroRetirable.getCantidad_ahorrar());
			ps.setString(4,ahorroRetirable.getFecha());
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
		sql = "EXEC dbo.Pa_AhorroRetirable 'DEL',1,?";
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
