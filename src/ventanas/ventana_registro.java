package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

import clases.DataCombo;
import clases.FuncionesGlobales;
import clases.Globals;
import clases.Registro;
import clases.Validaciones;
import conexion.conexion;
import consultas.consultas_generales.*;
import consultas.RegistroConsultas;
import consultas.consultas_generales;

import java.util.Calendar;
import java.util.Iterator;
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

public class ventana_registro extends JFrame {
	//Conexion
	private consultas_generales con = new consultas_generales();
	private RegistroConsultas consulta = new RegistroConsultas();
	private FuncionesGlobales fGlobal = new FuncionesGlobales();
	private Validaciones validar = new Validaciones();
	private String fechaRegistro = "";
	
	private JPanel contentPane;
	private JTextField txtIdentidad;
	private JTextField txtSegundoNombre;
	private JTextField txtPrimerNombre;
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido;
	private JTextField txtTelefono;
	private JComboBox<DataCombo> cbxProyecto;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTable table;
	private JTextField txtFiltrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnIngresar;
	private JButton btnBeneficiario;
	private JButton btnAval;
	private JButton btnSeleccionar;
	private JTextField txtNumeroCuenta;

	public ventana_registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 691);
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
		panel.setBounds(10, 10, 1056, 634);
		contentPane.add(panel);
		
		JLabel lblRegistro = new JLabel("Afiliado");
		lblRegistro.setForeground(new Color(17, 16, 38));
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblRegistro.setBounds(10, 10, 344, 27);
		panel.add(lblRegistro);
		
		JLabel lblPrimerNombre = new JLabel("Primer Nombre:");
		lblPrimerNombre.setForeground(new Color(17, 16, 38));
		lblPrimerNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerNombre.setBounds(10, 89, 142, 27);
		panel.add(lblPrimerNombre);
		
		JLabel lblPrimerSegundo = new JLabel("Segundo Nombre:  ");
		lblPrimerSegundo.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo.setBounds(364, 89, 146, 27);
		panel.add(lblPrimerSegundo);
		
		JLabel lblPrimerSegundo_1 = new JLabel("Primer Apellido:");
		lblPrimerSegundo_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1.setBounds(10, 127, 142, 27);
		panel.add(lblPrimerSegundo_1);
		
		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setForeground(new Color(17, 16, 38));
		lblIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblIdentidad.setBounds(10, 52, 142, 27);
		panel.add(lblIdentidad);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setDisabledTextColor(Color.BLACK);
		txtIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(162, 51, 192, 28);
		panel.add(txtIdentidad);
		validar.entero(txtIdentidad);
		
		JLabel lblPrimerSegundo_1_1 = new JLabel("Segundo Apellido:");
		lblPrimerSegundo_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1.setBounds(364, 126, 146, 27);
		panel.add(lblPrimerSegundo_1_1);
		
		JLabel lblPrimerSegundo_1_1_1 = new JLabel("Tel\u00E9fono:");
		lblPrimerSegundo_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1.setBounds(10, 164, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1 = new JLabel("Proyecto:");
		lblPrimerSegundo_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1.setBounds(364, 163, 146, 27);
		panel.add(lblPrimerSegundo_1_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1 = new JLabel("Email:");
		lblPrimerSegundo_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1.setBounds(10, 201, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1 = new JLabel("Direcci\u00F3n:");
		lblPrimerSegundo_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1.setBounds(364, 201, 146, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1);
		
		txtSegundoNombre = new JTextField();
		txtSegundoNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtSegundoNombre.setColumns(10);
		txtSegundoNombre.setBounds(520, 87, 192, 28);
		panel.add(txtSegundoNombre);
		validar.letras(txtSegundoNombre);
		
		txtPrimerNombre = new JTextField();
		txtPrimerNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtPrimerNombre.setColumns(10);
		txtPrimerNombre.setBounds(162, 88, 192, 28);
		panel.add(txtPrimerNombre);
		validar.letras(txtPrimerNombre);
		
		txtPrimerApellido = new JTextField();
		txtPrimerApellido.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtPrimerApellido.setColumns(10);
		txtPrimerApellido.setBounds(162, 126, 192, 28);
		panel.add(txtPrimerApellido);
		validar.letras(txtPrimerApellido);
		
		txtSegundoApellido = new JTextField();
		txtSegundoApellido.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtSegundoApellido.setColumns(10);
		txtSegundoApellido.setBounds(520, 125, 192, 28);
		panel.add(txtSegundoApellido);
		validar.letras(txtSegundoApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(162, 164, 192, 28);
		panel.add(txtTelefono);
		validar.entero(txtTelefono);
		
		cbxProyecto = new JComboBox<DataCombo>();
		cbxProyecto.setFont(new Font("Open Sans", Font.PLAIN, 16));
		cbxProyecto.setBounds(520, 163, 192, 27);
		panel.add(cbxProyecto);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(162, 202, 192, 28);
		panel.add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(520, 200, 192, 28);
		panel.add(txtDireccion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 1036, 328);
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
		btnActualizar.setBounds(741, 107, 133, 39);
		panel.add(btnActualizar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_2 = new JLabel("Filtrar:");
		lblPrimerSegundo_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_2.setBounds(10, 259, 60, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_2);
		
		txtFiltrar = new JTextField();
		txtFiltrar.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				fGlobal.filtrarJTable(txtFiltrar, table);
			  }
			  public void removeUpdate(DocumentEvent e) {
				  fGlobal.filtrarJTable(txtFiltrar, table);
			  }
			  public void insertUpdate(DocumentEvent e) {
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
		btnEliminar.setBounds(741, 52, 133, 39);
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
		btnIngresar.setBounds(741, 164, 133, 39);
		panel.add(btnIngresar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGlobal.imprimirReporte(table,"Reporte Afiliados");
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
				if(Globals.contabilidadRegistro==0&&Globals.prestamoRegistro==0) {
					ventana_principal principal = new ventana_principal();
					principal.setVisible(true);				
				}
				if (Globals.contabilidadRegistro==1) {
					ventana_contabilidad.btnAfiliado.setEnabled(true);
					ventana_contabilidad.btnAtras.setEnabled(true);
					Globals.contabilidadRegistro=0;
				}
				if(Globals.prestamoRegistro==1) {
					ventana_prestamo.btnAfiliado.setEnabled(true);
					ventana_prestamo.btnAtras.setEnabled(true);
					Globals.prestamoRegistro=0;
				}
				dispose();
			}
		});
		btnAtras.setForeground(new Color(94, 129, 244));
		btnAtras.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAtras.setBackground(new Color(238, 242, 253));
		btnAtras.setBounds(913, 259, 133, 27);
		panel.add(btnAtras);
		
		JLabel lblNmeroCuenta = new JLabel("N\u00FAmero Cuenta:");
		lblNmeroCuenta.setForeground(new Color(17, 16, 38));
		lblNmeroCuenta.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblNmeroCuenta.setBounds(364, 54, 146, 27);
		panel.add(lblNmeroCuenta);
		
		txtNumeroCuenta = new JTextField();
		txtNumeroCuenta.setText("");
		txtNumeroCuenta.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNumeroCuenta.setColumns(10);
		txtNumeroCuenta.setBounds(520, 52, 192, 28);
		panel.add(txtNumeroCuenta);
		validar.entero(txtNumeroCuenta);
		
		btnBeneficiario = new JButton("Beneficiario");
		btnBeneficiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtIdentidad.getText();
				String nombre = txtPrimerNombre.getText()+" "+txtPrimerApellido.getText();
				ventana_beneficiario beneficiario = new ventana_beneficiario(id,nombre);
				beneficiario.setVisible(true);
				dispose();
			}
		});
		btnBeneficiario.setForeground(new Color(94, 129, 244));
		btnBeneficiario.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnBeneficiario.setBackground(new Color(238, 242, 253));
		btnBeneficiario.setBounds(899, 51, 133, 40);
		panel.add(btnBeneficiario);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if (fila==-1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					String identidad = fGlobal.setValueRow(table, fila, "Identidad");
					String nombre = fGlobal.setValueRow(table, fila, "Primer_Nombre")+" "+fGlobal.setValueRow(table, fila, "Primer_Apellido");
					if (Globals.contabilidadRegistro==1) {
						ventana_contabilidad.txtIdentidad.setText(identidad);
						ventana_contabilidad.txtNombre.setText(nombre);
						ventana_contabilidad.btnAfiliado.setEnabled(true);
						ventana_contabilidad.btnAtras.setEnabled(true);
						Globals.contabilidadRegistro=0;
					} 
					if (Globals.prestamoRegistro==1){
						ventana_prestamo.txtIdentidad.setText(identidad);
						ventana_prestamo.txtNombre.setText(nombre);
						ventana_prestamo.btnAfiliado.setEnabled(true);
						ventana_prestamo.btnAtras.setEnabled(true);
						Globals.prestamoRegistro=0;
					}
					dispose();
				}
				
			}
		});
		btnSeleccionar.setForeground(new Color(94, 129, 244));
		btnSeleccionar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnSeleccionar.setBackground(new Color(238, 242, 253));
		btnSeleccionar.setBounds(770, 259, 133, 27);
		panel.add(btnSeleccionar);
		
		btnAval = new JButton("Aval");
		btnAval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtIdentidad.getText();
				String nombre = txtPrimerNombre.getText()+" "+txtPrimerApellido.getText();
				ventana_aval aval = new ventana_aval(id,nombre);
				aval.setVisible(true);
				dispose();
			}
		});
		btnAval.setForeground(new Color(94, 129, 244));
		btnAval.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnAval.setEnabled(false);
		btnAval.setBackground(new Color(238, 242, 253));
		btnAval.setBounds(899, 107, 133, 40);
		panel.add(btnAval);
		
		limpiar();
	}
	
	/**Limpia cada uno de los campos del formulario*/
	private void limpiar() {
		txtIdentidad.setText("");
		txtIdentidad.setEnabled(true);
		txtIdentidad.setFocusable(true);
		txtNumeroCuenta.setText("");
		txtPrimerNombre.setText("");
		txtSegundoNombre.setText("");
		txtPrimerApellido.setText("");
		txtSegundoApellido.setText("");
		txtTelefono.setText("");
		con.cargarJComboBox(cbxProyecto,"select * from Proyecto");
		txtEmail.setText("");
		txtDireccion.setText("");
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnIngresar.setEnabled(true);
		btnBeneficiario.setEnabled(false);
		btnAval.setEnabled(false);
		btnSeleccionar.setVisible(false);
		con.cargarJTable(table, "EXEC dbo.Pa_Registros 'LIS',1");
		txtFiltrar.setText("");
	}
	
	/**Ingresa o modifica el registro segun la opcion*/
	private void modificarRegistro(Opciones opcion) {
		if(
			txtIdentidad.getText().isEmpty()||
			txtNumeroCuenta.getText().isEmpty()||
			txtPrimerNombre.getText().isEmpty()||
			txtPrimerApellido.getText().isEmpty()||
			txtDireccion.getText().isEmpty()||
			txtTelefono.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(null, "Por favor llene los campos");
		}else {
			Registro registro = new Registro();
			if(opcion == Opciones.INSERTAR) {
				fechaRegistro = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
			}
			registro.setIdentidad(fGlobal.emptyIsNull(txtIdentidad.getText()));
			registro.setPrimer_Nombre(fGlobal.emptyIsNull(txtPrimerNombre.getText()));
			registro.setSegundo_Nombre(fGlobal.emptyIsNull(txtSegundoNombre.getText()));
			registro.setPrimer_Apellido(fGlobal.emptyIsNull(txtPrimerApellido.getText()));
			registro.setSegundo_Apellido(fGlobal.emptyIsNull(txtSegundoApellido.getText()));
			registro.setDireccion(fGlobal.emptyIsNull(txtDireccion.getText()));
			registro.setTelefono(fGlobal.emptyIsNull(txtTelefono.getText()));
			registro.setEmail(fGlobal.emptyIsNull(txtEmail.getText()));
			registro.setNumero_cuenta(fGlobal.emptyIsNull(txtNumeroCuenta.getText()));
			registro.setNombre_Proyecto(fGlobal.emptyIsNull(cbxProyecto.getItemAt(cbxProyecto.getSelectedIndex()).getId()));
			registro.setFecha_Registro(fGlobal.emptyIsNull(fechaRegistro));
			boolean condicionExistencia = opcion==Opciones.INSERTAR?false:true;
			if (con.comprobarExistencia("SELECT * FROM Registros WHERE identidad = '"+ txtIdentidad.getText() +"'")==condicionExistencia) {
				if (consulta.modificar(registro, opcion)) {
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
			fechaRegistro = fGlobal.setValueRow(table, fila, "Fecha");
			txtIdentidad.setText(fGlobal.setValueRow(table, fila, "Identidad"));
			txtNumeroCuenta.setText(fGlobal.setValueRow(table, fila, "Nro Cuenta"));
			txtPrimerNombre.setText(fGlobal.setValueRow(table, fila, "Primer Nombre"));
			txtSegundoNombre.setText(fGlobal.setValueRow(table, fila, "Segundo Nombre"));
			txtPrimerApellido.setText(fGlobal.setValueRow(table, fila, "Primer Apellido"));
			txtSegundoApellido.setText(fGlobal.setValueRow(table, fila, "Segundo Apellido"));
			txtTelefono.setText(fGlobal.setValueRow(table, fila, "Telefono"));
			DefaultComboBoxModel<DataCombo> modelo = (DefaultComboBoxModel<DataCombo>) cbxProyecto.getModel();
			for (int i = 0; i < modelo.getSize(); i++) {
				if (modelo.getElementAt(i).toString().equals(fGlobal.setValueRow(table, fila, "Proyecto"))) {
					cbxProyecto.setSelectedIndex(i);
		        }
			}
			txtEmail.setText(fGlobal.setValueRow(table, fila, "Email"));
			txtDireccion.setText(fGlobal.setValueRow(table, fila, "Direccion"));
			txtIdentidad.setEnabled(false);
			btnEliminar.setEnabled(true);
			btnActualizar.setEnabled(true);
			btnIngresar.setEnabled(false);
			btnBeneficiario.setEnabled(true);
			btnAval.setEnabled(true);
			if(Globals.contabilidadRegistro==1 || Globals.prestamoRegistro == 1) {
				btnSeleccionar.setVisible(true);
			}
		}
	}
	
	/**Deshabilita el registro*/
	private void eliminarRegistro() {
		int fila = table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
		} else {
			String identidad = txtIdentidad.getText();
			String nombreCompleto = txtPrimerNombre.getText()+" "+txtPrimerApellido.getText();
			if (con.comprobarExistencia("SELECT * FROM Registros WHERE identidad = '"+ txtIdentidad.getText() +"'")) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar a "+identidad+" - "+nombreCompleto+"?", "Salir del sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
