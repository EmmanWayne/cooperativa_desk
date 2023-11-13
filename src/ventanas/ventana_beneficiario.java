package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import clases.Beneficiario;
import clases.DataCombo;
import clases.FuncionesGlobales;
import clases.Registro;
import clases.Validaciones;
import conexion.conexion;
import consultas.consultas_generales.*;
import consultas.BeneficiarioConsultas;
import consultas.RegistroConsultas;
import consultas.consultas_generales;

import java.util.Calendar;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class ventana_beneficiario extends JFrame {
	//Conexion
	private consultas_generales con = new consultas_generales();
	private BeneficiarioConsultas consulta = new BeneficiarioConsultas();
	private FuncionesGlobales fGlobal = new FuncionesGlobales();
	private Validaciones validar = new Validaciones();
	private String idRegistro = "";
	private String nomRegistro = "";
	//Componentes
	private JPanel contentPane;
	private JLabel lbRegistro;
	private JTextField txtIdentidad;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtParentesco;
	private JTable table;
	private JTextField txtFiltrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnIngresar;
	private JTextField txtDireccion;

	public ventana_beneficiario(String identidadRegistro,String nombreRegistro) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(245, 245, 251));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				fGlobal.salirSistema(rootPane);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(10, 10, 894, 634);
		contentPane.add(panel);
		
		JLabel lblRegistro = new JLabel("Beneficiarios de:");
		lblRegistro.setForeground(new Color(17, 16, 38));
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblRegistro.setBounds(10, 10, 171, 27);
		panel.add(lblRegistro);
		
		JLabel lblPrimerNombre = new JLabel("Nombre:");
		lblPrimerNombre.setForeground(new Color(17, 16, 38));
		lblPrimerNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerNombre.setBounds(10, 89, 142, 27);
		panel.add(lblPrimerNombre);
		
		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setForeground(new Color(17, 16, 38));
		lblIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblIdentidad.setBounds(10, 52, 142, 27);
		panel.add(lblIdentidad);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setDisabledTextColor(Color.BLACK);
		txtIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(162, 51, 277, 28);
		panel.add(txtIdentidad);
		validar.entero(txtIdentidad);
		
		JLabel lblPrimerSegundo_1_1_1 = new JLabel("Tel\u00E9fono:");
		lblPrimerSegundo_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1.setBounds(10, 126, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1 = new JLabel("Parentesco:");
		lblPrimerSegundo_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1.setBounds(10, 200, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(162, 88, 277, 28);
		panel.add(txtNombre);
		validar.letras(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(162, 126, 277, 28);
		panel.add(txtTelefono);
		validar.entero(txtTelefono);
		
		txtParentesco = new JTextField();
		txtParentesco.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtParentesco.setColumns(10);
		txtParentesco.setBounds(162, 201, 277, 28);
		panel.add(txtParentesco);
		validar.letras(txtParentesco);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 873, 328);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getRowRegistro();
			}
		});
		scrollPane.setViewportView(table);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarRegistro(Opciones.EDITAR);
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnActualizar.setBackground(new Color(150, 152, 214));
		btnActualizar.setBounds(482, 107, 133, 39);
		panel.add(btnActualizar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_2 = new JLabel("Filtrar:");
		lblPrimerSegundo_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_2.setBounds(10, 259, 60, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_2);
		
		txtFiltrar = new JTextField();
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fGlobal.filtrarJTable(txtFiltrar, table);
			}
		});
		txtFiltrar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtFiltrar.setColumns(10);
		txtFiltrar.setBounds(80, 258, 274, 28);
		panel.add(txtFiltrar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRegistro();
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnEliminar.setBackground(new Color(255, 128, 139));
		btnEliminar.setBounds(482, 52, 133, 39);
		panel.add(btnEliminar);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarRegistro(Opciones.INSERTAR);
			}
		});
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnIngresar.setBackground(new Color(77, 76, 172));
		btnIngresar.setBounds(482, 164, 133, 39);
		panel.add(btnIngresar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGlobal.imprimirReporte(table,"Reporte beneficiarios de "+nomRegistro);
			}
		});
		btnReporte.setForeground(new Color(94, 129, 244));
		btnReporte.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnReporte.setBackground(new Color(238, 242, 253));
		btnReporte.setBounds(364, 259, 133, 27);
		panel.add(btnReporte);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setForeground(new Color(94, 129, 244));
		btnLimpiar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnLimpiar.setBackground(new Color(238, 242, 253));
		btnLimpiar.setBounds(575, 259, 133, 27);
		panel.add(btnLimpiar);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_registro registro = new ventana_registro();
				registro.setVisible(true);
				dispose();
			}
		});
		btnAtras.setForeground(new Color(94, 129, 244));
		btnAtras.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAtras.setBackground(new Color(238, 242, 253));
		btnAtras.setBounds(750, 259, 133, 27);
		panel.add(btnAtras);
		
		JLabel lblPrimerSegundo_1_1_1_1 = new JLabel("Direcci\u00F3n:");
		lblPrimerSegundo_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1.setBounds(10, 163, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1_1);
		
		txtDireccion = new JTextField();
		txtDireccion.setText("");
		txtDireccion.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(162, 163, 277, 28);
		panel.add(txtDireccion);
		
		lbRegistro = new JLabel(identidadRegistro+" - "+nombreRegistro);
		lbRegistro.setForeground(new Color(17, 16, 38));
		lbRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lbRegistro.setBounds(184, 10, 699, 27);
		panel.add(lbRegistro);
		
		idRegistro = identidadRegistro;
		nomRegistro = nombreRegistro;
		limpiar();
	}
	
	/**Limpia cada uno de los campos del formulario*/
	private void limpiar() {
		txtIdentidad.setText("");
		txtIdentidad.setEnabled(true);
		txtIdentidad.setFocusable(true);
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtParentesco.setText("");
		txtFiltrar.setText("");
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnIngresar.setEnabled(true);
		con.cargarJTable(table, "EXEC dbo.Pa_Beneficiario 'LIS',1,'','','','','','"+ idRegistro +"'");
	}
	
	/**Ingresa o modifica el registro segun la opcion*/
	private void modificarRegistro(Opciones opcion) {
		if(
			txtIdentidad.getText().isEmpty()||
			txtNombre.getText().isEmpty()||
			txtTelefono.getText().isEmpty() ||
			txtDireccion.getText().isEmpty()||
			txtParentesco.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(null, "Por favor llene los campos");
		}else {
			Beneficiario beneficiario = new Beneficiario();
			beneficiario.setId_beneficiario(txtIdentidad.getText());
			beneficiario.setNombre_Beneficiario(txtNombre.getText());
			beneficiario.setTelefono(txtTelefono.getText());
			beneficiario.setDireccion(txtDireccion.getText());
			beneficiario.setParentesco(txtParentesco.getText());
			beneficiario.setIdentidad(idRegistro);
			boolean condicionExistencia = opcion==Opciones.INSERTAR?false:true;
			if (con.comprobarExistencia("SELECT * FROM dbo.Beneficiario WHERE id_beneficiario = '"+ txtIdentidad.getText() +"'")==condicionExistencia) {
				if (consulta.modificar(beneficiario, opcion)) {
					limpiar();
					JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "Error al realizar la acción");
				}
			} else {
				String mensaje = condicionExistencia?"NO":"YA";
				JOptionPane.showMessageDialog(null,mensaje+" existen datos con: "+txtIdentidad.getText());
			}
			
		}
	}
	
	/**Establece los valores de la fila seleccionada a su correspondiente componente*/
	private void getRowRegistro() {
		int fila = table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
		} else {
			txtIdentidad.setText(fGlobal.setValueRow(table, fila, "Identidad"));
			txtNombre.setText(fGlobal.setValueRow(table, fila, "Nombre"));
			txtTelefono.setText(fGlobal.setValueRow(table, fila, "Telefono")); 
			txtDireccion.setText(fGlobal.setValueRow(table, fila, "Direccion"));
			txtParentesco.setText(fGlobal.setValueRow(table, fila, "Parentesco"));
			txtIdentidad.setEnabled(false);
			btnEliminar.setEnabled(true);
			btnActualizar.setEnabled(true);
			btnIngresar.setEnabled(false);
		}
	}
	
	/**Elimina el registro*/
	private void eliminarRegistro() {
		int fila = table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
		} else {
			String identidad = txtIdentidad.getText();
			String nombreCompleto = txtNombre.getText();
			if (con.comprobarExistencia("SELECT * FROM dbo.Beneficiario WHERE id_beneficiario = '"+ txtIdentidad.getText() +"'")) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar a "+identidad+" - "+nombreCompleto+"?", "Salir del sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					consulta.deshabilitar(identidad);
					if (consulta.deshabilitar(identidad)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,"NO existen datos con: "+txtIdentidad.getText());
			}
		}
	}
}
