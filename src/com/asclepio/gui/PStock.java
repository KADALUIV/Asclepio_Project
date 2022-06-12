package com.asclepio.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.asclepio.control.AppControl;
import com.asclepio.model.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PStock extends JPanel{
	
	static final int ALTO = 600;
	static final int ANCHO = 950;
	
	public static final String BTN_BUSQUEDA_PSTOCK = "Busqueda";
	public static final String BTN_REPONER_PSTOCK = "Reponer";
	
	private static final String ID_PRODUCTO = "ID-Producto";
	private static final String NOMBRE = "Nombre";
	private static final String TIPO = "Tipo";
	private static final String PRECIO = "Precio";
	private static final String STOCK = "Stock";
	private static final String REPONER = "Reponer";
	
	private DefaultTableModel tModel;
	private static JTextField txtBusqueda;
	private JTable tblStock;
	private JScrollPane scrollPane;
	private JButton btnVolver;
	private JButton btnBuscar;
	private JSpinner spnCantidad;
	private JButton btnReponer;
	
	public PStock() {
		setForeground(Color.BLACK);
		init();
	}

	private void init() {
		setLayout(null);
		setSize(ANCHO,ALTO);
		
		JLabel lblStock = new JLabel("STOCK");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblStock.setBounds(404, 33, 141, 44);
		add(lblStock);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBusqueda.setBounds(245, 109, 459, 35);
		add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JLabel lblBusqueda = new JLabel("Busqueda:");
		lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBusqueda.setBounds(106, 109, 110, 35);
		add(lblBusqueda);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 185, 872, 328);
		scrollPane.setVisible(false);
		add(scrollPane);
		
		tblStock = new JTable();
		scrollPane.setViewportView(tblStock);
		
		
		btnBuscar = new JButton(BTN_BUSQUEDA_PSTOCK);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.setBounds(428, 154, 93, 21);
		add(btnBuscar);
		
		spnCantidad = new JSpinner();
		spnCantidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spnCantidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnCantidad.setBounds(856, 540, 54, 21);
		add(spnCantidad);
		
		btnReponer = new JButton(BTN_REPONER_PSTOCK);
		btnReponer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReponer.setBounds(735, 540, 99, 21);
		add(btnReponer);
	
		
		configurarTabla();
		
	}
	
	private void configurarTabla() {
		tModel = new DefaultTableModel() { 
			public boolean isCellEditable(int row, int colum) {
				return false; 
			}
		};
		
			tblStock.setModel(tModel);
		
			tModel.addColumn(ID_PRODUCTO);
			tModel.addColumn(NOMBRE);
			tModel.addColumn(TIPO); 
			tModel.addColumn(PRECIO); 
			tModel.addColumn(STOCK); 


			tblStock.getColumn(ID_PRODUCTO).setPreferredWidth(75);
			tblStock.getColumn(NOMBRE).setPreferredWidth(75);
			tblStock.getColumn(TIPO).setPreferredWidth(75);
			tblStock.getColumn(PRECIO).setPreferredWidth(75);
			tblStock.getColumn(STOCK).setPreferredWidth(75);


	}
	
	public void filtrarTabla(ArrayList<Producto> listaProd) {
		
		tModel.getDataVector().clear();
		
		Object[] row  = new Object[5];
		
		for (Producto pro : listaProd) {
			row [0] = pro.getIdProducto();
			row [1] = pro.getNombre();
			row [2] = pro.getTipo();
			row [3] = pro.getPrecio() +  "�";
			row [4] = pro.getStock();
			
			tModel.addRow(row);
			
			
		}
		
		scrollPane.setVisible(true);
	}

	public void hacerVisible(boolean b) {
		
		scrollPane.setVisible(b);
	}
	
	public void setControlador(AppControl control) {
		btnBuscar.addActionListener(control);
		btnReponer.addActionListener(control);
		
	}

	public static  String obtenerTexto() {
		
		String medicamento = txtBusqueda.getText();
		
		return medicamento;
	}
	
	public String productoSeleccionado() {
		
		String repo = null;
		
		if(tblStock.getSelectedRow() >=0) {
			int colum = 0;
			int row = tblStock.getSelectedRow();
			repo = tblStock.getModel().getValueAt(row, colum).toString();
		}
		
		
		return repo;
	}
	
	public int cantidadReponer() {
		
		int cantRe = (int) spnCantidad.getValue();
		
		if(cantRe == 0) {
			mostrarError("La cantidad a reponer debe ser superior a 0");
		}
		
		return cantRe;
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error,
				"Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void reponerVisible() {
		btnReponer.setVisible(productoSeleccionado() != null);
		spnCantidad.setVisible(productoSeleccionado() != null);
	}
	
}
