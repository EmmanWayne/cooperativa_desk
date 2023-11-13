package clases;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import conexion.conexion;

public class FuncionesGlobales {
	
	/**Obtiene el valor de de la celda con respecto a la fila y columna*/
	public String setValueRow(JTable tabla,int fila,String Columna) {
		try {
			//Obtiene el indice de la columna independientemente si se mueve
			int indiceColumna = tabla.convertColumnIndexToView(tabla.getColumn(Columna).getModelIndex());
			if(tabla.getValueAt(fila, indiceColumna)!=null) {
				return tabla.getValueAt(fila, indiceColumna).toString();						
			}else {
				return "";
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La Columna: "+Columna+" No fue encontrada" );
			return "";
		}
	}
	
	/**Devuelve null si el texto está vacio si no devuelve el texto original*/
	public String emptyIsNull(String texto) {
		if(texto.isEmpty()) {
			return null;
		}else {
			return texto;
		}
		
	}
	
	/**Filtra una tabla usando un JTextBox*/
	public void filtrarJTable(JTextField textField,JTable tabla) {
		String text = textField.getText();
		DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		tabla.setRowSorter(sorter);
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }	
	}
	
	/**Filtra una tabla usando texto*/
	public void filtrarPorTexto(String text,JTable tabla) {
		DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		tabla.setRowSorter(sorter);
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }	
	}
	
	public void imprimirReporte(JTable tabla,String encabezado) {
		String fecha = new SimpleDateFormat("yyyy-MMMM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		fecha+=new GregorianCalendar().get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
		int totalDatos = tabla.getRowCount();
		if (totalDatos == 0) {
			JOptionPane.showMessageDialog(null, "No hay registros disponibles para imprimir un reporte");
		} else {
			int numeroPaginas = 0;
			numeroPaginas = (int) (totalDatos/46)+1;
			boolean interactive = true;
			boolean showPrintDialog = true;
			String piePagina = "Pagina {0} de " + numeroPaginas + " Fundación Alivio del Sufrimiento "+fecha.substring(0, 4);
			JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;
			try {
			boolean complete = tabla.print(mode, new MessageFormat(encabezado), new MessageFormat(piePagina), showPrintDialog,
			null, interactive);
			if (complete) {
			JOptionPane.showMessageDialog(tabla, "Print complete (Impresión completa)",
			"Print result (Resultado de la impresión)", JOptionPane.INFORMATION_MESSAGE);
			} else {
			JOptionPane.showMessageDialog(tabla, "Print canceled (Impresión cancelada)",
			"Print result (Resultado de la impresión)", JOptionPane.WARNING_MESSAGE);
			}
			} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(tabla, "Print fail (Fallo de impresión): " + pe.getMessage(),
			"Print result (Resultado de la impresión)", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public void salirSistema(JRootPane rootPane) {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
}
