package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.DataCombo;
import clases.FuncionesGlobales;
import clases.Globals;
import clases.Prestamo;
import clases.Registro;
import clases.Validaciones;
import consultas.PrestamoConsulta;
import consultas.RegistroConsultas;
import consultas.consultas_generales;
import consultas.consultas_generales.Opciones;

public class ventana_prestamo extends JFrame {
	//Conexion
	private consultas_generales con = new consultas_generales();
	private PrestamoConsulta consulta = new PrestamoConsulta();
	private FuncionesGlobales fGlobal = new FuncionesGlobales();
	private Validaciones validar = new Validaciones();
	private String fechaRegistro = "";
	
	private JPanel contentPane;
	private JTextField txtNroPrestamo;
	private JTextField txtMonto;
	private JTable table;
	private JTextField txtFiltrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnIngresar;
	private JButton btnSeleccionar;
	public static JButton btnAfiliado;
	public static JButton btnAtras;
	public static JTextField txtNombre;
	public static JTextField txtIdentidad;
	private JTextField txtNroCuotas;
	private JTextField txtInteres;
	private JTextField txtCuotasPagadas;
	private JLabel lblPrimerSegundo_1_1_1_3;

	public ventana_prestamo() {
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
		
		JLabel lblRegistro = new JLabel("Prestamos");
		lblRegistro.setForeground(new Color(17, 16, 38));
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblRegistro.setBounds(10, 10, 864, 27);
		panel.add(lblRegistro);
		
		JLabel lblPrimerSegundo_1 = new JLabel("Nro Prestamo:");
		lblPrimerSegundo_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1.setBounds(10, 126, 142, 27);
		panel.add(lblPrimerSegundo_1);
		
		JLabel lblPrimerSegundo_1_1 = new JLabel("N\u00FAmero de Cuotas:");
		lblPrimerSegundo_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1.setBounds(413, 126, 156, 27);
		panel.add(lblPrimerSegundo_1_1);
		
		JLabel lblPrimerSegundo_1_1_1 = new JLabel("Monto:");
		lblPrimerSegundo_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1.setBounds(10, 163, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1 = new JLabel("Inter\u00E9s:");
		lblPrimerSegundo_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1.setBounds(413, 164, 156, 27);
		panel.add(lblPrimerSegundo_1_1_1_1);
		
		txtNroPrestamo = new JTextField();
		txtNroPrestamo.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNroPrestamo.setColumns(10);
		txtNroPrestamo.setBounds(159, 125, 244, 28);
		panel.add(txtNroPrestamo);
		validar.entero(txtNroPrestamo);
		
		txtMonto = new JTextField();
		txtMonto.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtMonto.setColumns(10);
		txtMonto.setBounds(159, 163, 244, 28);
		panel.add(txtMonto);
		validar.decimal(txtMonto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 306, 1036, 318);
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
				modificarPrestamo(Opciones.EDITAR);
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnActualizar.setBackground(new Color(150, 152, 214));
		btnActualizar.setBounds(870, 102, 133, 39);
		panel.add(btnActualizar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_2 = new JLabel("Filtrar:");
		lblPrimerSegundo_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_2.setBounds(10, 269, 60, 27);
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
		txtFiltrar.setBounds(80, 268, 274, 28);
		panel.add(txtFiltrar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPrestamo();
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnEliminar.setBackground(new Color(255, 128, 139));
		btnEliminar.setBounds(870, 47, 133, 39);
		panel.add(btnEliminar);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPrestamo(Opciones.INSERTAR);
			}
		});
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnIngresar.setBackground(new Color(77, 76, 172));
		btnIngresar.setBounds(870, 159, 133, 39);
		panel.add(btnIngresar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGlobal.imprimirReporte(table,"Reporte Registros");
			}
		});
		btnReporte.setForeground(new Color(94, 129, 244));
		btnReporte.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnReporte.setBackground(new Color(238, 242, 253));
		btnReporte.setBounds(364, 269, 133, 27);
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
		btnLimpiar.setBounds(575, 269, 133, 27);
		panel.add(btnLimpiar);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Globals.contabilidadRegistro==0) {
					ventana_principal principal = new ventana_principal();
					principal.setVisible(true);
					dispose();					
				}
				if (Globals.contabilidadRegistro==1) {
					ventana_contabilidad.btnAfiliado.setEnabled(true);
					Globals.contabilidadRegistro=0;
					dispose();
				}
			}
		});
		btnAtras.setForeground(new Color(94, 129, 244));
		btnAtras.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAtras.setBackground(new Color(238, 242, 253));
		btnAtras.setBounds(913, 269, 133, 27);
		panel.add(btnAtras);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if (fila==-1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					String identidad = fGlobal.setValueRow(table, fila, "Identidad");
					String nombre = fGlobal.setValueRow(table, fila, "Primer_Nombre")+" "+fGlobal.setValueRow(table, fila, "Primer_Apellido");
					ventana_contabilidad.txtIdentidad.setText(identidad);
					ventana_contabilidad.txtNombre.setText(nombre);
					ventana_contabilidad.btnAfiliado.setEnabled(true);
					Globals.contabilidadRegistro=0;
					dispose();
				}
				
			}
		});
		btnSeleccionar.setForeground(new Color(94, 129, 244));
		btnSeleccionar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnSeleccionar.setBackground(new Color(238, 242, 253));
		btnSeleccionar.setBounds(770, 269, 133, 27);
		panel.add(btnSeleccionar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1 = new JLabel("Afiliado Identidad:");
		lblPrimerSegundo_1_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1.setBounds(10, 48, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_2 = new JLabel("Afiliado Nombre:");
		lblPrimerSegundo_1_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_2.setBounds(10, 86, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_2);
		
		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNombre.setEnabled(false);
		txtNombre.setDisabledTextColor(Color.BLACK);
		txtNombre.setColumns(10);
		txtNombre.setBounds(159, 85, 244, 28);
		panel.add(txtNombre);
		validar.letras(txtNombre);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setText("");
		txtIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIdentidad.setEnabled(false);
		txtIdentidad.setDisabledTextColor(Color.BLACK);
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(159, 47, 192, 28);
		panel.add(txtIdentidad);
		validar.entero(txtIdentidad);
		
		btnAfiliado = new JButton("...");
		btnAfiliado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Globals.prestamoRegistro = 1;
				ventana_registro registro = new ventana_registro();
				registro.setVisible(true);
				btnAfiliado.setEnabled(false);
				btnAtras.setEnabled(false);
			}
		});
		btnAfiliado.setForeground(new Color(94, 129, 244));
		btnAfiliado.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAfiliado.setBackground(new Color(238, 242, 253));
		btnAfiliado.setBounds(364, 47, 39, 28);
		panel.add(btnAfiliado);
		
		txtNroCuotas = new JTextField();
		txtNroCuotas.setText("");
		txtNroCuotas.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNroCuotas.setColumns(10);
		txtNroCuotas.setBounds(579, 125, 244, 28);
		panel.add(txtNroCuotas);
		validar.entero(txtNroCuotas);
		
		txtInteres = new JTextField();
		txtInteres.setText("");
		txtInteres.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtInteres.setColumns(10);
		txtInteres.setBounds(579, 162, 215, 28);
		panel.add(txtInteres);
		validar.decimal(txtInteres);
		
		txtCuotasPagadas = new JTextField();
		txtCuotasPagadas.setText("");
		txtCuotasPagadas.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtCuotasPagadas.setColumns(10);
		txtCuotasPagadas.setBounds(159, 200, 244, 28);
		panel.add(txtCuotasPagadas);
		validar.entero(txtCuotasPagadas);
		
		JLabel lblPrimerSegundo_1_1_1_2 = new JLabel("Cuotas Pagadas:");
		lblPrimerSegundo_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_2.setBounds(10, 200, 142, 27);
		panel.add(lblPrimerSegundo_1_1_1_2);
		
		lblPrimerSegundo_1_1_1_3 = new JLabel("%");
		lblPrimerSegundo_1_1_1_3.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_3.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_3.setBounds(802, 163, 31, 27);
		panel.add(lblPrimerSegundo_1_1_1_3);
		
		limpiar();
	}
	
	/**Limpia cada uno de los campos del formulario*/
	private void limpiar() {
		txtIdentidad.setText("");
		txtNombre.setText("");
		txtNroPrestamo.setText("");
		txtMonto.setText("");
		txtNroCuotas.setText("");
		txtInteres.setText("");
		txtCuotasPagadas.setText("0");
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnIngresar.setEnabled(true);
		btnSeleccionar.setVisible(false);
		con.cargarJTable(table, "EXEC dbo.Pa_Prestamos 'LIS',1");
		txtFiltrar.setText("");
	}
	
	/**Ingresa o modifica el prestamo segun la opcion*/
	private void modificarPrestamo(Opciones opcion) {
		if(
			txtIdentidad.getText().isEmpty()||
			txtNroPrestamo.getText().isEmpty()||
			txtMonto.getText().isEmpty()||
			txtNroCuotas.getText().isEmpty()||
			txtCuotasPagadas.getText().isEmpty()||
			txtInteres.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(null, "Por favor llene los campos");
		}else {
			if(Integer.parseInt(txtNroCuotas.getText())>=Integer.parseInt(txtCuotasPagadas.getText())) {
				Prestamo prestamo = new Prestamo();
				if(opcion == Opciones.INSERTAR) {
					fechaRegistro = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
				}
				System.out.println(fechaRegistro);
				prestamo.setNumero_Prestamo(fGlobal.emptyIsNull(txtNroPrestamo.getText()));
				prestamo.setIdentidad(fGlobal.emptyIsNull(txtIdentidad.getText()));
				prestamo.setFecha_Prestamo(fechaRegistro);
				prestamo.setCantidad_Prestada(fGlobal.emptyIsNull(txtMonto.getText()));
				prestamo.setNumero_cuotas(fGlobal.emptyIsNull(txtNroCuotas.getText()));
				prestamo.setPorcentaje_interes(fGlobal.emptyIsNull(txtInteres.getText()));
				prestamo.setCuotas_pagadas(fGlobal.emptyIsNull(txtCuotasPagadas.getText()));
				boolean condicionExistencia = opcion==Opciones.INSERTAR?false:true;
				if (con.comprobarExistencia("SELECT * FROM dbo.Prestamos WHERE Numero_Prestamo = '"+ txtNroPrestamo.getText() +"'")==condicionExistencia) {
					if (consulta.modificar(prestamo, opcion)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				} else {
					String mensaje = condicionExistencia?"NO":"YA";
					JOptionPane.showMessageDialog(null,mensaje+" existen datos con el prestamo: "+txtNroPrestamo.getText());
				}
			}else {
				JOptionPane.showMessageDialog(null,"Las cuotas pagadas no pueden ser mayor que el número de cuotas");
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
			txtNombre.setText(fGlobal.setValueRow(table, fila, "Nombre"));
			txtNroPrestamo.setText(fGlobal.setValueRow(table, fila, "Numero"));
			txtMonto.setText(fGlobal.setValueRow(table, fila, "Monto"));
			txtNroCuotas.setText(fGlobal.setValueRow(table, fila, "Nro Cuotas"));
			txtInteres.setText(fGlobal.setValueRow(table, fila, "Interes"));
			txtCuotasPagadas.setText(fGlobal.setValueRow(table, fila, "Cuotas Pagadas"));
			btnEliminar.setEnabled(true);
			btnActualizar.setEnabled(true);
			btnIngresar.setEnabled(false);
		}
	}
	
	/**Elimina permanentemente el prestamo*/
	private void eliminarPrestamo() {
		int fila = table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
		} else {
			String nroPrestamo = txtNroPrestamo.getText();
			String nombreCompleto = txtNombre.getText();
			if (con.comprobarExistencia("SELECT * FROM Registros WHERE identidad = '"+ txtIdentidad.getText() +"'")) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar a el prestamo "+nroPrestamo+" de "+nombreCompleto+"?", "Salir del sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (consulta.eliminar(nroPrestamo)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,"NO existen datos con el prestamo: "+nroPrestamo);
			}
		}
	}
}
