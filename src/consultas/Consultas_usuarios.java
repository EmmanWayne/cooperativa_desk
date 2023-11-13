package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Usuarios;
import conexion.conexion;

public class Consultas_usuarios extends conexion {
	public static String identidad = null;
	public static String nombre_completo = null;
	public static String usuario = null;
	public static String contraseña = null;

	public boolean insertar(Usuarios usuarios) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO dbo.usuarios (id, identidad, usuario, contrasena, nombre_completo) VALUES(?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, usuarios.getId());
			ps.setString(2, usuarios.getIdentidad());
			ps.setString(3, usuarios.getUsuario());
			ps.setString(4, usuarios.getContrasena());
			ps.setString(5, usuarios.getNombre_completo());
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

	public boolean actualizar(Usuarios usuarios) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.usuarios SET identidad=?, usuario=?, contrasena=?, nombre_completo=? WHERE id=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuarios.getIdentidad());
			ps.setString(2, usuarios.getUsuario());
			ps.setString(3, usuarios.getContrasena());
			ps.setString(4, usuarios.getNombre_completo());
			ps.setInt(5, usuarios.getId());
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

	public boolean buscarUsuario(Usuarios usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT * FROM dbo.usuarios WHERE usuario=? and contrasena =?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getContrasena());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
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
