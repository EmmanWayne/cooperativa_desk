package consultas;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import clases.DataCombo;
import conexion.conexion;

public class consultas_generales {
	conexion conexion = new conexion();
	public enum Opciones{INSERTAR,EDITAR}
	
	public void cargarJTable(JTable tabla,String consulta){
		try {
			Statement estatuto = conexion.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(consulta);
		    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		    conexion.desconectar();
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    DefaultTableModel modelo = new DefaultTableModel(data,columnNames);
		    if(tabla.getRowCount()>0) {
		    	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
		    	for (int i = tabla.getRowCount() - 1; i >= 0; i--) {
			        dm.removeRow(i);
			    }
		    }
		    tabla.setDefaultEditor(Object.class, null);
		    tabla.setModel(modelo);
		    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    TableColumnModel columnModel = tabla.getColumnModel();
		    for (int column = 0; column < tabla.getColumnCount(); column++) {
		        int width = 120;
		        for (int row = 0; row < tabla.getRowCount(); row++) {
		            TableCellRenderer renderer = tabla.getCellRenderer(row, column);
		            Component comp = tabla.prepareRenderer(renderer, row, column);
		            width = Math.max(comp.getPreferredSize().width + 1, width);
		        }
		        if (width > 300) {
		            width = 300;
		        }
		        columnModel.getColumn(column).setPreferredWidth(width);
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
		}
	}
	
	public ResultSet consulta(String consulta) {
		try {
			Statement estatuto = conexion.getConexion().createStatement();
			return estatuto.executeQuery(consulta);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
			return null;
		}
	}
	
	public void cargarJComboBox(JComboBox<DataCombo> combo,String consulta){
		try {
			DefaultComboBoxModel<DataCombo> modelo = (DefaultComboBoxModel<DataCombo>) combo.getModel();
			modelo.removeAllElements();
			Statement estatuto = conexion.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(consulta);
			conexion.desconectar();
		    while(rs.next()){  
		    	combo.addItem(new DataCombo(rs.getString(1), rs.getString(2)));  
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
		}
	}
	
	public boolean comprobarExistencia(String consulta) {
		try {
			Statement estatuto = conexion.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(consulta);
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
			return false;
		}
	}
	
	public int max(String consulta) {
		try {
			Statement estatuto = conexion.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(consulta);
			String id = "";
			if(rs.next()){
				id=rs.getString(1);
	        }
			if(id==null) {
				return 1;
			}else {
				return Integer.parseInt(id)+1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e);
			return 0;
		}
	}

}