package com.asclepio.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.asclepio.control.AppControl;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;

public class PStock extends JPanel{
	
	static final int ALTO = 600;
	static final int ANCHO = 950;
	public static final String BTN_VOLVER = "Volver";
	public static final String BTN_SALIR = "Salir";
	public static final String BTN_BUSQUEDA = "Busqueda";
	
	private static final String ID_PRODUCTO = "idProducto";
	private static final String NOMBRE = "Nombre";
	private static final String TIPO = "Tipo";
	private static final String PRECIO = "Precio";
	private static final String STOCK = "Stock";
	private static final String REPONER = "Reponer";
	
	private DefaultTableModel tModel;
	private static JTextField txtBusqueda;
	private JTable tblStock;
	private JScrollPane scrollPane;
	private JButton btnsSalir;
	private JButton btnVolver;
	private JButton btnBuscar;
	
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
		txtBusqueda.setBounds(226, 109, 459, 35);
		add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JLabel lblBusqueda = new JLabel("Busqueda:");
		lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBusqueda.setBounds(106, 109, 110, 35);
		add(lblBusqueda);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 185, 872, 377);
		add(scrollPane);
		
		tblStock = new JTable();
		scrollPane.setViewportView(tblStock);
		
		btnsSalir = new JButton(BTN_SALIR);
		btnsSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnsSalir.setBounds(855, 10, 85, 21);
		add(btnsSalir);
		
		btnVolver = new JButton(BTN_VOLVER);
		btnVolver.setBounds(10, 11, 85, 21);
		add(btnVolver);
		
		btnBuscar = new JButton(BTN_BUSQUEDA);
		btnBuscar.setBounds(432, 154, 85, 21);
		add(btnBuscar);
	
		
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

	public void hacerVisible(boolean b) {
		
		scrollPane.setVisible(b);
	}
	
	public void setControlador(AppControl control) {
		btnBuscar.addActionListener(control);
		btnVolver.addActionListener(control);
		btnsSalir.addActionListener(control);
	
		
	}

	public static  String obtenerTexto() {
		
		
		String medicamento = txtBusqueda.getText();
		
		
		return medicamento;
	}
	
	
}
