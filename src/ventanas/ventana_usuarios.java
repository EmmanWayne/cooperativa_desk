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
import java.sql.PreparedStatement;
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

public class ventana_usuarios extends JFrame {

	public JPanel contentPane;
	public static JTextField txtId;
	public static JLabel lblNombreEmpresa;
	public static String nombre = null;
	public static String frase = null;
	public static String ipServidor = null;
	public static String ruta_logo = null;

	public static String usuario;
	public static String contrasena;
	public static String identidad;
	public static String nombreCompleto;
	public static String nombreRol;
	public static String id_usuario;
	public JTextField txtIdentidad;
	public JTextField txtUsuario;
	public JTextField txtContrasena;
	public JTextField txtNombre;

	public ventana_usuarios() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 475);
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
		panel.setBounds(10, 11, 327, 414);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Id:");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblUsuario.setBounds(10, 68, 57, 25);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Identidad:");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblContrasea.setBounds(10, 120, 181, 25);
		panel.add(lblContrasea);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtId.setBounds(10, 90, 307, 25);
		panel.add(txtId);
		txtId.setText("1");
		txtId.setColumns(10);
		InputMap map4 = txtId.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtId.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtId.getText().length() == 15)
					ke.consume();

				if (txtId.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtId.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		lblNombreEmpresa = new JLabel("Cooperativa SAN PIO");
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEmpresa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNombreEmpresa.setBounds(14, 0, 303, 36);
		panel.add(lblNombreEmpresa);

		JLabel lblLoginSistemaAdministrativo = new JLabel("Registro de Usuarios");
		lblLoginSistemaAdministrativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistemaAdministrativo.setBounds(10, 32, 307, 36);
		panel.add(lblLoginSistemaAdministrativo);
		lblLoginSistemaAdministrativo.setBackground(new Color(0, 128, 128));
		lblLoginSistemaAdministrativo.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarUsuario();
			}
		});
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegistrar.setBackground(new Color(60, 179, 113));
		btnRegistrar.setBounds(71, 327, 181, 25);
		panel.add(btnRegistrar);

		JLabel lblUsuario_1 = new JLabel("Usuario:");
		lblUsuario_1.setForeground(Color.BLACK);
		lblUsuario_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblUsuario_1.setBounds(10, 171, 181, 25);
		panel.add(lblUsuario_1);

		JLabel lblContrasea_1 = new JLabel("Contrase\u00F1a:");
		lblContrasea_1.setForeground(Color.BLACK);
		lblContrasea_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblContrasea_1.setBounds(10, 222, 181, 25);
		panel.add(lblContrasea_1);

		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setForeground(Color.BLACK);
		lblNombreCompleto.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNombreCompleto.setBounds(10, 270, 181, 25);
		panel.add(lblNombreCompleto);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_login login = new ventana_login();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				dispose();
			}
		});
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(71, 363, 181, 25);
		panel.add(btnAtras);

		txtIdentidad = new JTextField();
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(10, 140, 307, 25);
		panel.add(txtIdentidad);
		InputMap map11 = txtId.getInputMap(JComponent.WHEN_FOCUSED);
		map11.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtIdentidad.getText().length() == 15)
					ke.consume();

				if (txtIdentidad.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtIdentidad.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(10, 192, 307, 25);
		panel.add(txtUsuario);
		InputMap map111 = txtId.getInputMap(JComponent.WHEN_FOCUSED);
		map111.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtUsuario.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtUsuario.getText().length() == 20)
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

		txtContrasena = new JTextField();
		txtContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasena.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(10, 243, 307, 25);
		panel.add(txtContrasena);
		InputMap map1112 = txtId.getInputMap(JComponent.WHEN_FOCUSED);
		map1112.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContrasena.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtContrasena.getText().length() == 20)
					ke.consume();

				if (txtContrasena.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContrasena.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 291, 307, 25);
		panel.add(txtNombre);
		InputMap map11112 = txtId.getInputMap(JComponent.WHEN_FOCUSED);
		map11112.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtNombre.getText().length() == 50)
					ke.consume();

				if (txtNombre.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtNombre.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

	}

	public void comprobarUsuarioRegistrado() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"SELECT * FROM dbo.usuarios WHERE identidad='" + txtIdentidad.getText().toString() + "'");
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

	public void obtenerUltimoId() {
		String ultimoValor = null;
		int valor;
		String id = null;
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.usuarios ORDER BY id DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id");
				valor = Integer.parseInt(ultimoValor);
				valor = valor + 1;
				id = String.valueOf(valor);
				txtId.setText(id);
			}else {
				txtId.setText("1");
			}
			
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardarUsuario() {
		comprobarUsuarioRegistrado();
		if (txtUsuario.getText().equals("") && txtContrasena.getText().equals("") && txtIdentidad.getText().equals("")
				&& txtNombre.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Ingrese los datos solicitados, para guardar el usuario! \nNo esta permitido dejar espacios vacios.");
		} else {
			if (identidad == null) {
				Usuarios clase = new Usuarios();
				Consultas_usuarios consulta = new Consultas_usuarios();
				clase.setId(Integer.parseInt(txtId.getText().toString()));
				clase.setIdentidad(txtIdentidad.getText().toString());
				clase.setUsuario(txtUsuario.getText().toString());
				clase.setContrasena(txtContrasena.getText().toString());
				clase.setNombre_completo(txtNombre.getText().toString());

				if (consulta.insertar(clase)) {
					JOptionPane.showMessageDialog(null, "Credenciales creadas!");
					Limpiar();
				} else {
					JOptionPane.showMessageDialog(null, "Credenciales no creadas!");
					Limpiar();

				}
			} else {
				JOptionPane.showMessageDialog(null, "Atencion! ha intentado registrar\nUn usuario con la misma identidad.\nDatos del usuario existente:\nIdentidad: " 
			+ identidad + "\nNombre: "
						+ nombreCompleto + "\nUsuario: " + usuario + "\nContraseña: " + contrasena);
				Limpiar();
			}

		}

	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void Limpiar() {

		txtId.setText("");
		txtUsuario.setText("");
		txtContrasena.setText("");
		txtIdentidad.setText("");
		txtNombre.setText("");
		obtenerUltimoId();

	}
}
