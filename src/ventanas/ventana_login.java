package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clases.Usuarios;
import conexion.conexion;
import consultas.Consultas_usuarios;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventana_login extends JFrame {

	public JPanel contentPane;
	public static JTextField txtUsuario;
	public static JPasswordField txtContraseña;
	public JLabel lblAlerta;
	public JButton btnIngresar;
	public static JLabel lblNombreEmpresa;
	public static JLabel lblFotoEmpresa;
	public static String nombre = null;
	public static String frase = null;
	public static String ipServidor = null;
	public static String ruta_logo = null;
	public static JRadioButton rdbtnPass;
	public static JLabel lblestadocontraseña;

	public static String usuario;
	public static String contrasena;
	public static String identidad;
	public static String nombreCompleto;
	public static String nombreRol;
	public static String id_usuario;
	
	public ventana_login() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/pio.png"));
		final ImageIcon icono1 = new ImageIcon(getClass().getResource("/recursos/ver.png"));
		final ImageIcon icono2 = new ImageIcon(getClass().getResource("/recursos/ocultar.png"));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(586, 426, 98, 34);
		contentPane.add(btnSalir);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 327, 465);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblUsuario.setBounds(74, 255, 181, 25);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblContrasea.setBounds(74, 301, 181, 20);
		panel.add(lblContrasea);

		lblestadocontraseña = new JLabel("");
		lblestadocontraseña.setBackground(Color.WHITE);
		lblestadocontraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblestadocontraseña.setBounds(277, 319, 21, 20);
		panel.add(lblestadocontraseña);
		final ImageIcon iconoocultar = new ImageIcon(icono2.getImage().getScaledInstance(lblestadocontraseña.getWidth(),
				lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
		lblestadocontraseña.setIcon(iconoocultar);

		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtUsuario.setBounds(74, 275, 181, 25);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		InputMap map4 = txtUsuario.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtUsuario.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtUsuario.getText().length() == 15)
					ke.consume();

				if (txtUsuario.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtUsuario.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnIngresar.setBackground(new Color(60, 179, 113));
		btnIngresar.setBounds(74, 355, 181, 25);
		panel.add(btnIngresar);

		txtContraseña = new JPasswordField();
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtContraseña.setBounds(74, 319, 181, 25);
		panel.add(txtContraseña);
		InputMap map5 = txtContraseña.getInputMap(JComponent.WHEN_FOCUSED);
		map5.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContraseña.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContraseña.getText().length() == 15)
					e.consume();

				if (txtContraseña.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContraseña.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		lblAlerta = new JLabel("");
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAlerta.setBounds(10, 427, 307, 25);
		panel.add(lblAlerta);

		lblNombreEmpresa = new JLabel("Cooperativa SAN PIO");
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEmpresa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNombreEmpresa.setBounds(10, 32, 303, 33);
		panel.add(lblNombreEmpresa);

		rdbtnPass = new JRadioButton("");
		rdbtnPass.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPass.setBackground(Color.WHITE);
		rdbtnPass.setBounds(256, 319, 21, 20);
		panel.add(rdbtnPass);
		rdbtnPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnPass.isSelected()) {
					txtContraseña.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(icono1.getImage().getScaledInstance(
							lblestadocontraseña.getWidth(), lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontraseña.setIcon(iconover);
				} else {
					txtContraseña.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(icono2.getImage().getScaledInstance(
							lblestadocontraseña.getWidth(), lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontraseña.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(74, 64, 181, 180);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblFotoEmpresa = new JLabel("");
		lblFotoEmpresa.setBounds(10, 11, 161, 158);
		panel_1.add(lblFotoEmpresa);
		final ImageIcon icono4 = new ImageIcon(logo.getImage().getScaledInstance(lblFotoEmpresa.getWidth(),
				lblFotoEmpresa.getHeight(), Image.SCALE_DEFAULT));
		lblFotoEmpresa.setIcon(icono4);

		JLabel lblLoginSistemaAdministrativo = new JLabel("Sistema Administrativo");
		lblLoginSistemaAdministrativo.setBounds(10, 11, 307, 25);
		panel.add(lblLoginSistemaAdministrativo);
		lblLoginSistemaAdministrativo.setBackground(new Color(0, 128, 128));
		lblLoginSistemaAdministrativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistemaAdministrativo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_usuarios user = new ventana_usuarios();
				user.setLocationRelativeTo(null);
				user.setVisible(true);
				user.obtenerUltimoId();
				dispose();
			}
		});
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegistrar.setBackground(new Color(0, 139, 139));
		btnRegistrar.setBounds(74, 391, 181, 25);
		panel.add(btnRegistrar);

	}

	public void consultarDatosInicioSesion() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM dbo.usuarios WHERE usuario='" + txtUsuario.getText().toString() + "'");
			if (rs.next()) {
				usuario = (rs.getString("usuario"));
				contrasena = (rs.getString("contrasena"));
				identidad = (rs.getString("identidad"));
				nombreCompleto = (rs.getString("nombre_completo"));
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}
	

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	
	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		ventana_principal principal = new ventana_principal();
		String user = String.valueOf(txtUsuario.getText().toString());
		String pass = String.valueOf(txtContraseña.getText().toString());
		if (user.equals("") && pass.equals("")) {
			lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
			lblAlerta.setForeground(Color.RED);
		} else {
			if (user.equals("")) {
				lblAlerta.setText("El campo de (Usuario) esta vacio.");
				lblAlerta.setForeground(Color.RED);
			} else {
				if (pass.equals("")) {
					lblAlerta.setText("El campo de (Contraseña) esta vacio.");
					lblAlerta.setForeground(Color.RED);
				} else {
					Consultas_usuarios consulta = new Consultas_usuarios();
					Usuarios clase = new Usuarios();
					clase.setUsuario(txtUsuario.getText().toString());
					clase.setContrasena(txtContraseña.getText().toString());
					if (consulta.buscarUsuario(clase)) {
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						consultarDatosInicioSesion();
						principal.lblusuario.setText(nombreCompleto);
						dispose();
					} else {
						lblAlerta.setText("El usuario y contraseña son incorrectas");
						lblAlerta.setForeground(Color.RED);
					}
				}
			}
		}

	}
}
