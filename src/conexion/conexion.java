package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {

	private final String user = "iccoar";
	private final String password = "informatica";
	private final String url = "jdbc:sqlserver://localhost;databaseName=Cooperativa;";
	private Connection con = null;

	public Connection getConexion() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (SQLException e) {
			System.err.println(e);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;
	}

	public void desconectar() {
		con = null;
	}

}

