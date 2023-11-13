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

import clases.Aval;
import clases.Beneficiario;
import clases.DataCombo;
import clases.FuncionesGlobales;
import clases.Registro;
import clases.Validaciones;
import conexion.conexion;
import consultas.consultas_generales.*;
import consultas.AvalConsultas;
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

public class ventana_aval extends JFrame {
	//Conexion
	private consultas_generales con = new consultas_generales();
	private AvalConsultas consulta = new AvalConsultas();
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
	private JTable table;
	private JTextField txtFiltrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnIngresar;
	private JTextField txtDireccion;
	private JTextField txtLugar;
	private JTextField txtAfinidad;
	private JTextField txtIngreso;
	private JTextField txtID;
	private JComboBox<String> cbxAfiliado;
	private JComboBox<String> cbxTrabaja;

	public ventana_aval(String identidadRegistro,String nombreRegistro) {
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
		
		JLabel lblRegistro = new JLabel("Avales de:");
		lblRegistro.setForeground(new Color(17, 16, 38));
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblRegistro.setBounds(10, 10, 171, 27);
		panel.add(lblRegistro);
		
		JLabel lblPrimerNombre = new JLabel("Nombre:");
		lblPrimerNombre.setForeground(new Color(17, 16, 38));
		lblPrimerNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerNombre.setBounds(10, 85, 86, 27);
		panel.add(lblPrimerNombre);
		
		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setForeground(new Color(17, 16, 38));
		lblIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblIdentidad.setBounds(10, 48, 86, 27);
		panel.add(lblIdentidad);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(95, 49, 241, 28);
		panel.add(txtIdentidad);
		validar.entero(txtIdentidad);
		
		JLabel lblPrimerSegundo_1_1_1 = new JLabel("Tel\u00E9fono:");
		lblPrimerSegundo_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1.setBounds(10, 122, 86, 27);
		panel.add(lblPrimerSegundo_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1 = new JLabel("Afiliado:");
		lblPrimerSegundo_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1.setBounds(10, 196, 86, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(95, 86, 241, 28);
		panel.add(txtNombre);
		validar.letras(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(95, 124, 241, 28);
		panel.add(txtTelefono);
		validar.entero(txtTelefono);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 294, 873, 330);
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
				modificarAval(Opciones.EDITAR);
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnActualizar.setBackground(new Color(150, 152, 214));
		btnActualizar.setBounds(751, 105, 133, 39);
		panel.add(btnActualizar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_2 = new JLabel("Filtrar:");
		lblPrimerSegundo_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_2.setBounds(10, 257, 60, 27);
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
		txtFiltrar.setBounds(80, 256, 274, 28);
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
		btnEliminar.setBounds(751, 50, 133, 39);
		panel.add(btnEliminar);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarAval(Opciones.INSERTAR);
			}
		});
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnIngresar.setBackground(new Color(77, 76, 172));
		btnIngresar.setBounds(751, 162, 133, 39);
		panel.add(btnIngresar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGlobal.imprimirReporte(table,"Reporte avales de "+nomRegistro);
			}
		});
		btnReporte.setForeground(new Color(94, 129, 244));
		btnReporte.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnReporte.setBackground(new Color(238, 242, 253));
		btnReporte.setBounds(364, 257, 133, 27);
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
		btnLimpiar.setBounds(575, 257, 133, 27);
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
		btnAtras.setBounds(750, 257, 133, 27);
		panel.add(btnAtras);
		
		JLabel lblPrimerSegundo_1_1_1_1 = new JLabel("Direcci\u00F3n:");
		lblPrimerSegundo_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1.setBounds(10, 159, 86, 27);
		panel.add(lblPrimerSegundo_1_1_1_1);
		
		txtDireccion = new JTextField();
		txtDireccion.setText("");
		txtDireccion.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(95, 161, 241, 28);
		panel.add(txtDireccion);
		
		lbRegistro = new JLabel(identidadRegistro+" - "+nombreRegistro);
		lbRegistro.setForeground(new Color(17, 16, 38));
		lbRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lbRegistro.setBounds(116, 10, 699, 27);
		panel.add(lbRegistro);
		
		JLabel lblTrabaja = new JLabel("Trabaja:");
		lblTrabaja.setForeground(new Color(17, 16, 38));
		lblTrabaja.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTrabaja.setBounds(346, 49, 126, 27);
		panel.add(lblTrabaja);
		
		JLabel lblTrabajolugar = new JLabel("Trabajo/Lugar:");
		lblTrabajolugar.setForeground(new Color(17, 16, 38));
		lblTrabajolugar.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTrabajolugar.setBounds(346, 86, 126, 27);
		panel.add(lblTrabajolugar);
		
		txtLugar = new JTextField();
		txtLugar.setText("");
		txtLugar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtLugar.setColumns(10);
		txtLugar.setBounds(482, 85, 246, 28);
		panel.add(txtLugar);
		
		JLabel lblPrimerSegundo_1_1_1_2 = new JLabel("Afinidad/Afiliado:");
		lblPrimerSegundo_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_2.setBounds(346, 123, 126, 27);
		panel.add(lblPrimerSegundo_1_1_1_2);
		
		txtAfinidad = new JTextField();
		txtAfinidad.setText("");
		txtAfinidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtAfinidad.setColumns(10);
		txtAfinidad.setBounds(482, 122, 246, 28);
		panel.add(txtAfinidad);
		validar.letras(txtAfinidad);
		
		JLabel lblPrimerSegundo_1_1_1_1_2 = new JLabel("Ingreso Mensual:");
		lblPrimerSegundo_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_2.setBounds(346, 160, 126, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_2);
		
		txtIngreso = new JTextField();
		txtIngreso.setText("");
		txtIngreso.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIngreso.setColumns(10);
		txtIngreso.setBounds(482, 159, 246, 28);
		panel.add(txtIngreso);
		validar.decimal(txtIngreso);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1 = new JLabel("ID:");
		lblPrimerSegundo_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1.setBounds(346, 197, 126, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1);
		
		txtID = new JTextField();
		txtID.setDisabledTextColor(Color.BLACK);
		txtID.setEnabled(false);
		txtID.setText("");
		txtID.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBounds(482, 196, 246, 28);
		panel.add(txtID);
		validar.entero(txtID);
		
		cbxAfiliado = new JComboBox<String>();
		cbxAfiliado.setFont(new Font("Open Sans", Font.PLAIN, 16));
		cbxAfiliado.setModel(new DefaultComboBoxModel<String>(new String[] {"NO", "SI"}));
		cbxAfiliado.setBounds(95, 199, 241, 27);
		panel.add(cbxAfiliado);
		
		cbxTrabaja = new JComboBox<String>();
		cbxTrabaja.setFont(new Font("Open Sans", Font.PLAIN, 16));
		cbxTrabaja.setModel(new DefaultComboBoxModel<String>(new String[] {"SI", "NO"}));
		cbxTrabaja.setBounds(482, 48, 246, 27);
		panel.add(cbxTrabaja);
		
		idRegistro = identidadRegistro;
		nomRegistro = nombreRegistro;
		limpiar();
	}
	
	/**Limpia cada uno de los campos del formulario*/
	private void limpiar() {
		txtIdentidad.setText("");
		txtIdentidad.setFocusable(true);
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxAfiliado.setSelectedIndex(0);
		cbxTrabaja.setSelectedIndex(0);
		txtLugar.setText("");
		txtAfinidad.setText("");
		txtIngreso.setText("");
		txtID.setText(String.valueOf(con.max("SELECT MAX(ID_AVAL) FROM dbo.AVAL")));
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnIngresar.setEnabled(true);
		con.cargarJTable(table, "EXEC dbo.Pa_Aval 'LIS',1,'','','','','','','','','','','"+ idRegistro +"'");
		txtFiltrar.setText("");
	}
	
	/**Ingresa o modifica el aval segun la opcion*/
	private void modificarAval(Opciones opcion) {
		if(
			txtIdentidad.getText().isEmpty()||
			txtNombre.getText().isEmpty()||
			txtTelefono.getText().isEmpty() ||
			txtDireccion.getText().isEmpty()||
			txtAfinidad.getText().isEmpty()||
			txtIngreso.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(null, "Por favor llene los campos");
		}else {
			Aval aval = new Aval();
			aval.setId_aval(txtID.getText());
			aval.setIdentidad_aval(txtIdentidad.getText());
			aval.setNombre_aval(txtNombre.getText());
			aval.setTelefono(txtTelefono.getText());
			aval.setDireccion(txtDireccion.getText());
			aval.setAfiliado(cbxAfiliado.getSelectedItem()=="SI"?true:false);
			aval.setTrabaja(cbxTrabaja.getSelectedItem()=="SI"?true:false);
			aval.setLugar_trabajo(txtLugar.getText());
			aval.setAfinidad(txtAfinidad.getText());
			aval.setIngreso_mensual(txtIngreso.getText());
			aval.setIdentidad(idRegistro);
			boolean condicionExistencia = opcion==Opciones.INSERTAR?false:true;
			if (con.comprobarExistencia("SELECT * FROM dbo.AVAL WHERE ID_AVAL = '"+ txtID.getText() +"'")==condicionExistencia) {
				if (consulta.modificar(aval, opcion)) {
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
			txtID.setText(fGlobal.setValueRow(table, fila, "ID"));
			txtIdentidad.setText(fGlobal.setValueRow(table, fila, "Identidad"));
			txtNombre.setText(fGlobal.setValueRow(table, fila, "Nombre Completo"));
			txtTelefono.setText(fGlobal.setValueRow(table, fila, "Telefono")); 
			txtDireccion.setText(fGlobal.setValueRow(table, fila, "Direccion"));
			cbxAfiliado.setSelectedItem(fGlobal.setValueRow(table, fila, "Afiliado"));
			cbxTrabaja.setSelectedItem(fGlobal.setValueRow(table, fila, "Trabaja"));
			txtLugar.setText(fGlobal.setValueRow(table, fila, "Lugar Trabajo"));
			txtAfinidad.setText(fGlobal.setValueRow(table, fila, "Afinidad"));
			txtIngreso.setText(fGlobal.setValueRow(table, fila, "Ingreso Mensual"));
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
			String id = txtID.getText();
			String nombreCompleto = txtNombre.getText();
			if (con.comprobarExistencia("SELECT * FROM dbo.AVAL WHERE ID_AVAL = '"+ id +"'")) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar a "+txtIdentidad.getText()+" - "+nombreCompleto+"?", "Salir del sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (consulta.eliminar(id)) {
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
