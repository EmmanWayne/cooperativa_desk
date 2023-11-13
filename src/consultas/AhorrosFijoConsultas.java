package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.AhorroFijo;
import clases.Beneficiario;
import conexion.conexion;
import consultas.consultas_generales.Opciones;

public class AhorrosFijoConsultas extends conexion {
	//Atributos
		PreparedStatement ps = null;
		String sql = "";
		public boolean modificar(AhorroFijo ahorroFijo,Opciones opcion) {
			//Conexion
			Connection con = getConexion();
			//Operacion
			if (opcion == Opciones.INSERTAR) {
				sql = "EXEC dbo.Pa_AhorroFijo 'ADD',1,?,?,?,?";			
			} else if (opcion == Opciones.EDITAR){
				sql = "EXEC dbo.Pa_AhorroFijo 'UPD',1,?,?,?,?";
			}else {
				System.err.println("Opcion no valida");
				return false;
			}
			//Ejecutar operacion
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1,ahorroFijo.getId_ahorro_fijo());
				ps.setString(2,ahorroFijo.getIdentidad());
				ps.setString(3,ahorroFijo.getCantidad_fija_ahorrar());
				ps.setString(4,ahorroFijo.getFecha_deposita_fijo());
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
			sql = "EXEC dbo.Pa_AhorroFijo 'DEL',1,?";
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
