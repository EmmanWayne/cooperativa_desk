package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Prestamo;
import clases.Registro;
import conexion.conexion;
import consultas.consultas_generales.Opciones;

public class PrestamoConsulta extends conexion{
	//Atributos
	PreparedStatement ps = null;
	String sql = "";
	public boolean modificar(Prestamo prestamo,Opciones opcion) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		if (opcion == Opciones.INSERTAR) {
			sql = "EXEC dbo.Pa_Prestamos 'ADD',1,?,?,?,?,?,?,?";			
		} else if (opcion == Opciones.EDITAR){
			sql = "EXEC dbo.Pa_Prestamos 'UPD',1,?,?,?,?,?,?,?";
		}else {
			System.err.println("Opcion no valida");
			return false;
		}
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,prestamo.getNumero_Prestamo());
			ps.setString(2,prestamo.getIdentidad());
			ps.setString(3,prestamo.getFecha_Prestamo());
			ps.setString(4,prestamo.getCantidad_Prestada());
			ps.setString(5,prestamo.getNumero_cuotas());
			ps.setString(6,prestamo.getPorcentaje_interes());
			ps.setString(7,prestamo.getCuotas_pagadas());
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
	
	public boolean eliminar(String numeroPrestamo) {
		//Conexion
		Connection con = getConexion();
		//Operacion
		sql = "EXEC dbo.Pa_Prestamos 'DEL',1,?";
		//Ejecutar operacion
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,numeroPrestamo);
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
