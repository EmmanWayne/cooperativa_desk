package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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

import clases.AhorroFijo;
import clases.AhorroRetirable;
import clases.DataCombo;
import clases.FuncionesGlobales;
import clases.Globals;
import clases.Registro;
import clases.Retiros;
import clases.Validaciones;
import conexion.conexion;
import consultas.AhorrosFijoConsultas;
import consultas.AhorrosRetirablesConsultas;
import consultas.RegistroConsultas;
import consultas.RetirosConsultas;
import consultas.consultas_generales;
import consultas.consultas_generales.Opciones;

import java.util.Calendar;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JCheckBox;

public class ventana_contabilidad extends JFrame {
	//Conexion
	private consultas_generales con = new consultas_generales();
	private AhorrosFijoConsultas consultaAF = new AhorrosFijoConsultas();
	private AhorrosRetirablesConsultas consultasAR = new AhorrosRetirablesConsultas();
	private RetirosConsultas consultasRT = new RetirosConsultas();
	private FuncionesGlobales fGlobal = new FuncionesGlobales();
	private Validaciones validar = new Validaciones();
	private int idMovimiento = 0;
	private String nombreMovimiento = "";
	private String fechaMovimiento = "";
	private double sar = 0.0;
	
	private JPanel contentPane;
	public static JTextField txtIdentidad;
	public static JTextField txtNombre;
	private JComboBox<String> cbxMovimiento;
	private JTable table;
	private JTextField txtMonto;
	private JTextField txtSAF;
	private JTextField txtSAR;
	private JTextField txtSTA;
	private JTextField txtFiltrar;
	private JComboBox<DataCombo> cbxProyecto;
	private JTextField txtAF;
	private JTextField txtAR;
	public static JButton btnAfiliado;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	public static JButton btnAtras;
	private JTextField txtRT;
	private JCheckBox chkTodos;

	public ventana_contabilidad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 756);
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
		panel.setBounds(10, 10, 1056, 699);
		contentPane.add(panel);
		
		JLabel lblRegistro = new JLabel("Contabilidad");
		lblRegistro.setForeground(new Color(17, 16, 38));
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblRegistro.setBounds(22, 16, 344, 27);
		panel.add(lblRegistro);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1 = new JLabel("Afiliado Identidad:");
		lblPrimerSegundo_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1.setBounds(20, 68, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setDisabledTextColor(Color.BLACK);
		txtIdentidad.setEnabled(false);
		txtIdentidad.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(207, 67, 243, 28);
		txtIdentidad.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  	sumarIndividual();
				  }
				  public void removeUpdate(DocumentEvent e) {
					  sumarIndividual();
				  }
				  public void insertUpdate(DocumentEvent e) {
					  sumarIndividual();
				  }
		});
		panel.add(txtIdentidad);
		validar.entero(txtIdentidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 324, 1036, 328);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getRowRegistro();
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_2 = new JLabel("Filtrar:");
		lblPrimerSegundo_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_2.setBounds(22, 287, 60, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_2);
		
		txtFiltrar = new JTextField();
		txtFiltrar.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				fGlobal.filtrarJTable(txtFiltrar, table);
				sumarTotal();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  fGlobal.filtrarJTable(txtFiltrar, table);
				  sumarTotal();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  fGlobal.filtrarJTable(txtFiltrar, table);
				  sumarTotal();
			  }
		});
		txtFiltrar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtFiltrar.setColumns(10);
		txtFiltrar.setBounds(258, 286, 192, 28);
		panel.add(txtFiltrar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGlobal.imprimirReporte(table,"Reporte Registros");
			}
		});
		btnReporte.setForeground(new Color(94, 129, 244));
		btnReporte.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnReporte.setBackground(new Color(238, 242, 253));
		btnReporte.setBounds(485, 287, 133, 27);
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
		btnLimpiar.setBounds(711, 287, 133, 27);
		panel.add(btnLimpiar);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_principal principal = new ventana_principal();
				principal.setVisible(true);
				dispose();
			}
		});
		btnAtras.setForeground(new Color(94, 129, 244));
		btnAtras.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAtras.setBackground(new Color(238, 242, 253));
		btnAtras.setBounds(913, 287, 133, 27);
		panel.add(btnAtras);
		
		txtNombre = new JTextField();
		txtNombre.setDisabledTextColor(Color.BLACK);
		txtNombre.setEnabled(false);
		txtNombre.setText("");
		txtNombre.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(207, 105, 292, 28);
		panel.add(txtNombre);
		validar.letras(txtNombre);
		
		btnAfiliado = new JButton("...");
		btnAfiliado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Globals.contabilidadRegistro = 1;
				ventana_registro registro = new ventana_registro();
				registro.setVisible(true);
				btnAfiliado.setEnabled(false);
				btnAtras.setEnabled(false);
			}
		});
		btnAfiliado.setForeground(new Color(94, 129, 244));
		btnAfiliado.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnAfiliado.setBackground(new Color(238, 242, 253));
		btnAfiliado.setBounds(460, 67, 39, 28);
		panel.add(btnAfiliado);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1 = new JLabel("Monto:");
		lblPrimerSegundo_1_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1.setBounds(20, 181, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1);
		
		txtMonto = new JTextField();
		txtMonto.setText("");
		txtMonto.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtMonto.setColumns(10);
		txtMonto.setBounds(207, 180, 243, 28);
		panel.add(txtMonto);
		validar.decimal(txtMonto);
		
		cbxMovimiento = new JComboBox<String>();
		cbxMovimiento.setModel(new DefaultComboBoxModel(new String[] {"AHORROS FIJOS", "AHORROS RETIRABLES", "RETIROS"}));
		cbxMovimiento.setFont(new Font("Open Sans", Font.PLAIN, 16));
		cbxMovimiento.setBounds(207, 143, 243, 27);
		panel.add(cbxMovimiento);
		
		JLabel lblPrimerSegundo_1_1_1_1_1 = new JLabel("Movimiento:");
		lblPrimerSegundo_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1.setBounds(20, 143, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1 = new JLabel("SALDO AHORROS FIJOS (SAF)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1.setBounds(766, 34, 267, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1);
		
		txtSAF = new JTextField();
		txtSAF.setDisabledTextColor(Color.BLACK);
		txtSAF.setEnabled(false);
		txtSAF.setText("");
		txtSAF.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtSAF.setColumns(10);
		txtSAF.setBounds(766, 69, 192, 28);
		panel.add(txtSAF);
		validar.decimal(txtSAF);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1_1 = new JLabel("SALDO AHORROS RETIRABLES (SAR)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_1.setBounds(766, 108, 267, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1_1);
		
		txtSAR = new JTextField();
		txtSAR.setDisabledTextColor(Color.BLACK);
		txtSAR.setEnabled(false);
		txtSAR.setText("");
		txtSAR.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtSAR.setColumns(10);
		txtSAR.setBounds(766, 143, 192, 28);
		panel.add(txtSAR);
		validar.decimal(txtSAR);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1_2 = new JLabel("SALDO TOTAL DE AHORROS (STA)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2.setBounds(766, 181, 267, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1_2);
		
		txtSTA = new JTextField();
		txtSTA.setDisabledTextColor(Color.BLACK);
		txtSTA.setEnabled(false);
		txtSTA.setText("");
		txtSTA.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtSTA.setColumns(10);
		txtSTA.setBounds(766, 216, 192, 28);
		panel.add(txtSTA);
		validar.decimal(txtSTA);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMovimiento();
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnEliminar.setEnabled(false);
		btnEliminar.setBackground(new Color(255, 128, 139));
		btnEliminar.setBounds(543, 68, 133, 39);
		panel.add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar(Opciones.EDITAR);
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnActualizar.setEnabled(false);
		btnActualizar.setBackground(new Color(150, 152, 214));
		btnActualizar.setBounds(543, 123, 133, 39);
		panel.add(btnActualizar);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar(Opciones.INSERTAR);
			}
		});
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnIngresar.setEnabled(true);
		btnIngresar.setBackground(new Color(77, 76, 172));
		btnIngresar.setBounds(543, 180, 133, 39);
		panel.add(btnIngresar);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_2 = new JLabel("Afiliado Nombre:");
		lblPrimerSegundo_1_1_1_1_1_1_2.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_2.setBounds(20, 105, 139, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_2);
		
		txtAF = new JTextField();
		txtAF.setDisabledTextColor(Color.BLACK);
		txtAF.setEnabled(false);
		txtAF.setText("");
		txtAF.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtAF.setColumns(10);
		txtAF.setBounds(886, 662, 140, 28);
		panel.add(txtAF);
		validar.decimal(txtAF);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1 = new JLabel("AHORROS FIJOS (AF)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1.setBounds(704, 662, 158, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1 = new JLabel("AHORROS RETIRABLES (AR)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1.setBounds(314, 662, 202, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1);
		
		txtAR = new JTextField();
		txtAR.setDisabledTextColor(Color.BLACK);
		txtAR.setEnabled(false);
		txtAR.setText("");
		txtAR.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtAR.setColumns(10);
		txtAR.setBounds(540, 662, 140, 28);
		panel.add(txtAR);
		validar.decimal(txtAR);
		
		cbxProyecto = new JComboBox<DataCombo>();
		cbxProyecto.setEnabled(false);
		cbxProyecto.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(cbxProyecto.isEnabled()) {
		    		fGlobal.filtrarPorTexto(cbxProyecto.getSelectedItem().toString(),table);
		    		//con.cargarJTable(table, "EXEC dbo.MostrarContabilidad 'LIS',3,'','"+ cbxProyecto.getSelectedItem().toString() +"'");
		    		//sumarTotal();		    		
		    	}else {
		    		//limpiar();
		    		//con.cargarJTable(table, "EXEC dbo.MostrarContabilidad 'LIS',1");
		    		//sumarTotal();
		    	}
		    }
		});
		cbxProyecto.setFont(new Font("Open Sans", Font.PLAIN, 16));
		cbxProyecto.setBounds(77, 287, 171, 27);
		panel.add(cbxProyecto);
		
		JLabel lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1_1 = new JLabel("RETIROS (RT)");
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1_1.setForeground(new Color(17, 16, 38));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1_1.setBounds(24, 662, 102, 27);
		panel.add(lblPrimerSegundo_1_1_1_1_1_1_1_1_2_1_1_1);
		
		txtRT = new JTextField();
		txtRT.setDisabledTextColor(Color.BLACK);
		txtRT.setText("");
		txtRT.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtRT.setEnabled(false);
		txtRT.setColumns(10);
		txtRT.setBounds(150, 662, 140, 28);
		panel.add(txtRT);
		validar.decimal(txtRT);
		
		chkTodos = new JCheckBox("Todos");
		chkTodos.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		    	  if (chkTodos.isSelected()) {
		    		  if (table.getRowCount()>0) {
		    			  cbxProyecto.setEnabled(false);
		    			  fGlobal.filtrarPorTexto("",table);
		    			  sumarTotal();						
		    		  }
					} else {
						cbxProyecto.setEnabled(true);
						DefaultComboBoxModel<DataCombo> modelo = (DefaultComboBoxModel<DataCombo>) cbxProyecto.getModel();
						fGlobal.filtrarPorTexto(modelo.getElementAt(0).toString(),table);
						sumarTotal();
					}
		      }
		    });
		chkTodos.setSelected(true);
		chkTodos.setFont(new Font("Open Sans", Font.PLAIN, 16));
		chkTodos.setBackground(Color.WHITE);
		chkTodos.setBounds(77, 257, 97, 23);
		panel.add(chkTodos);
		
		limpiar();
	}
	
	/**Limpia cada uno de los campos del formulario*/
	private void limpiar() {
		txtIdentidad.setText("");
		txtNombre.setText("");
		cbxMovimiento.setEnabled(true);
		cbxMovimiento.setSelectedIndex(0);
		txtMonto.setText("");
		txtSAF.setText("0.0");
		txtSAR.setText("0.0");
		txtSTA.setText("0.0");
		con.cargarJComboBox(cbxProyecto,"select * from Proyecto");
		chkTodos.setSelected(true);
		txtRT.setText("0.0");
		txtAF.setText("0.0");
		txtAR.setText("0.0");
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnIngresar.setEnabled(true);
		con.cargarJTable(table, "EXEC dbo.MostrarContabilidad 'LIS',1");
		sumarTotal();
		txtFiltrar.setText("");
	}
	
	/**Suma el Ahorro retirable, Ahorro fijo y Retiros*/
	private void sumarTotal() {
		double ar = 0;
        double af = 0;
        double rt = 0;
		for(int i = 0; i < table.getRowCount(); i++){
	        String tipo = fGlobal.setValueRow(table, i, "Tipo");
	        double cantidad = Double.parseDouble(fGlobal.setValueRow(table, i, "Cantidad"));
	        if(tipo.equals("AHORROS FIJOS")) {
	        	af+=cantidad;
	        }else if(tipo.equals("AHORROS RETIRABLES")) {
	        	ar+=cantidad;
	        }else {
	        	rt+=cantidad;
	        }
	    }
		txtRT.setText(String.valueOf(rt));
		txtAF.setText(String.valueOf(af));
		txtAR.setText(String.valueOf(ar));
	}
	
	/**Ingresa o modifica el movimiento segun la opcion*/
	private void modificar(Opciones opcion) {
		if(
			txtIdentidad.getText().isEmpty()||
			txtNombre.getText().isEmpty()||
			cbxMovimiento.getSelectedItem().toString().isEmpty()||
			txtMonto.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(null, "Por favor llene los campos");
		}else {
			String movimiento = cbxMovimiento.getSelectedItem().toString();
			String queryExistencia = "";
			if(opcion == Opciones.INSERTAR) {
				fechaMovimiento = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
			}
			boolean condicionExistencia = opcion==Opciones.INSERTAR?false:true;
			AhorroFijo af = new AhorroFijo();
			AhorroRetirable ar = new AhorroRetirable();
			Retiros rt = new Retiros();
			if(movimiento=="AHORROS FIJOS") {
				if(opcion == Opciones.INSERTAR) {
					idMovimiento = con.max("SELECT MAX(id_ahorro_fijo) FROM dbo.Ahorro_Fijos");
				}
				queryExistencia = "SELECT * FROM dbo.Ahorro_Fijos WHERE id_ahorro_fijo = '"+ idMovimiento +"'"; 
				af.setId_ahorro_fijo(String.valueOf(idMovimiento));
				af.setIdentidad(txtIdentidad.getText());
				af.setCantidad_fija_ahorrar(txtMonto.getText());
				af.setFecha_deposita_fijo(fechaMovimiento);
			}else if(movimiento=="AHORROS RETIRABLES") {
				if(opcion == Opciones.INSERTAR) {
					idMovimiento = con.max("SELECT MAX(id_retirable) FROM dbo.Ahorro_Retirable");					
				}
				queryExistencia = "SELECT * FROM dbo.Ahorro_Retirable WHERE id_retirable = '"+ idMovimiento +"'"; 
				ar.setId_retirable(String.valueOf(idMovimiento));
				ar.setIdentidad(txtIdentidad.getText());
				ar.setCantidad_ahorrar(txtMonto.getText());
				ar.setFecha(fechaMovimiento);
			}else if(movimiento=="RETIROS") {
				if (sar>=Double.parseDouble(txtMonto.getText())) {
					if(opcion == Opciones.INSERTAR) {
						idMovimiento = con.max("SELECT MAX(Id_retiro) FROM dbo.Retiros");
					}
					queryExistencia = "SELECT * FROM dbo.Retiros WHERE Id_retiro = '"+ idMovimiento +"'";
					rt.setId_retiro(String.valueOf(idMovimiento));
					rt.setIdentidad(txtIdentidad.getText());
					rt.setCantidad_retirar(txtMonto.getText());
					rt.setFecha(fechaMovimiento);
				} else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente, el máximo retirable es de "+sar);
					return;
				}
				
			}else {
				System.err.println("movimiento: "+movimiento);
				JOptionPane.showMessageDialog(null, "Movimiento no valido");
			}
			
			if (con.comprobarExistencia(queryExistencia)==condicionExistencia) {
				if(movimiento=="AHORROS FIJOS") {
					if (consultaAF.modificar(af, opcion)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				}else if(movimiento=="AHORROS RETIRABLES") {
					if (consultasAR.modificar(ar, opcion)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				}else if(movimiento=="RETIROS") {
					if (consultasRT.modificar(rt, opcion)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error al realizar la acción");
					}
				}else {
					System.err.println("movimiento: "+movimiento);
					JOptionPane.showMessageDialog(null, "Movimiento no valido");
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
			idMovimiento = Integer.parseInt(fGlobal.setValueRow(table, fila, "ID"));
			nombreMovimiento = fGlobal.setValueRow(table, fila, "Tipo");
			fechaMovimiento = fGlobal.setValueRow(table, fila, "Fecha");
			txtIdentidad.setText(fGlobal.setValueRow(table, fila, "Identidad"));
			txtNombre.setText(fGlobal.setValueRow(table, fila, "Colaborador"));
			cbxMovimiento.setEnabled(false);
			cbxMovimiento.setSelectedItem(fGlobal.setValueRow(table, fila, "Tipo"));
			txtMonto.setText(fGlobal.setValueRow(table, fila, "Cantidad"));
			txtIdentidad.setEnabled(false);
			btnEliminar.setEnabled(true);
			btnActualizar.setEnabled(true);
			btnIngresar.setEnabled(false);
		}
	}
	
	/**Suma el SAF, SAR y STA individual*/
	private void sumarIndividual() {
		ResultSet rs;
		rs = con.consulta("EXEC dbo.MostrarContabilidad 'LIS',2,'"+ txtIdentidad.getText() +"'");
		try {
			while(rs.next()) {
				txtSAF.setText(rs.getString("safTotal"));
				txtSAR.setText(rs.getString("sarTotal"));
				txtSTA.setText(rs.getString("staTotal"));
				sar = Double.parseDouble(rs.getString("sarTotal"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
		}
	}
	
	private void eliminarMovimiento() {
		int fila = table.getSelectedRow();
		if (fila==-1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
		} else {
			String queryExistencia = "";
			if(nombreMovimiento.equals("AHORROS FIJOS")) {
				queryExistencia = "SELECT * FROM dbo.Ahorro_Fijos WHERE id_ahorro_fijo = '"+ idMovimiento +"'";
			}else if(nombreMovimiento.equals("AHORROS RETIRABLES")) {
				queryExistencia = "SELECT * FROM dbo.Ahorro_Retirable WHERE id_retirable = '"+ idMovimiento +"'";
			}else if(nombreMovimiento.equals("RETIROS")) {
				queryExistencia = "SELECT * FROM dbo.Retiros WHERE Id_retiro = '"+ idMovimiento +"'";
			}else {
				System.err.println("movimiento: "+nombreMovimiento);
				JOptionPane.showMessageDialog(null, "Movimiento no valido");
			}
			if (con.comprobarExistencia(queryExistencia)) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el movimiento de "+nombreMovimiento+"?", "Salir del sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(nombreMovimiento.equals("AHORROS FIJOS")) {
						if (consultaAF.eliminar(String.valueOf(idMovimiento))) {
							limpiar();
							JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Error al realizar la acción");
						}
					}else if(nombreMovimiento.equals("AHORROS RETIRABLES")) {
						if (consultasAR.eliminar(String.valueOf(idMovimiento))) {
							limpiar();
							JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Error al realizar la acción");
						}
					}else if(nombreMovimiento.equals("RETIROS")) {
						if (consultasRT.eliminar(String.valueOf(idMovimiento))) {
							limpiar();
							JOptionPane.showMessageDialog(null, "Acción realizada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Error al realizar la acción");
						}
					}else {
						System.err.println("movimiento: "+nombreMovimiento);
						JOptionPane.showMessageDialog(null, "Movimiento no valido");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,"NO existen datos con: "+idMovimiento+" - "+nombreMovimiento);
			}
		}
	}
}
