package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Aval;
import clases.Beneficiario;
import conexion.conexion;
import consultas.consultas_generales.Opciones;

public class AvalConsultas extends conexion{
	//Atributos
		PreparedStatement ps = null;
		String sql = "";
		public boolean modificar(Aval aval,Opciones opcion) {
			//Conexion
			Connection con = getConexion();
			//Operacion
			if (opcion == Opciones.INSERTAR) {
				sql = "EXEC dbo.Pa_Aval 'ADD',1,?,?,?,?,?,?,?,?,?,?,?";			
			} else if (opcion == Opciones.EDITAR){
				sql = "EXEC dbo.Pa_Aval 'UPD',1,?,?,?,?,?,?,?,?,?,?,?";
			}else {
				System.err.println("Opcion no valida");
				return false;
			}
			//Ejecutar operacion
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1,aval.getId_aval());
				ps.setString(2,aval.getIdentidad_aval());
				ps.setString(3,aval.getNombre_aval());
				ps.setString(4,aval.getTelefono());
				ps.setString(5,aval.getDireccion());
				ps.setBoolean(6,aval.getAfiliado());
				ps.setBoolean(7,aval.getTrabaja());
				ps.setString(8,aval.getLugar_trabajo());
				ps.setString(9,aval.getAfinidad());
				ps.setString(10,aval.getIngreso_mensual());
				ps.setString(11,aval.getIdentidad());
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
			sql = "EXEC dbo.Pa_Aval 'DEL',1,?";
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
