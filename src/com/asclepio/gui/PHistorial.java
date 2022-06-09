package com.asclepio.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.asclepio.control.AppControl;

import com.asclepio.model.ProductoCompra;

import com.asclepio.db.sql.SqlQuery;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PHistorial extends JPanel {
	private static final int ANCHO = 950;
	private static final int ALTO = 600;
	
	private static final String COLUMN_NOMBRE = "NOMBRE";
	private static final String COLUMN_CANTIDAD = "CANTIDAD";
	private static final String COLUMN_PRECIO = "PRECIO";
	
	public static final String BTN_CONSULTAR = "Consultar";

	private JTextField txtFecha;
	private JButton btnConsultar;
	private JTable tblHistorial;
	private JTextField txtPrecioTotal;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	
	public PHistorial() {
		init();
	}

	private void init() {
		setSize(ANCHO, ALTO);
		setLayout(null);
		
		JLabel lblHistorialCompra = new JLabel("Historial Compra");
		lblHistorialCompra.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblHistorialCompra.setBounds(312, 33, 304, 34);
		add(lblHistorialCompra);
		
		JLabel lblFecha = new JLabel("Introduce una fecha:");
		lblFecha.setBounds(55, 107, 134, 13);
		add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(192, 104, 492, 19);
		add(txtFecha);
		txtFecha.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 164, 768, 342);
		add(scrollPane);
		
		tblHistorial = new JTable();
		scrollPane.setViewportView(tblHistorial);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setEditable(false);
		txtPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrecioTotal.setBounds(798, 505, 81, 19);
		add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		
		btnConsultar = new JButton("");
		btnConsultar.setIcon(new ImageIcon(PHistorial.class.getResource("/img/search.png")));
		btnConsultar.setBounds(710, 95, 35, 34);
		add(btnConsultar);
		
		centrarVentana();
		
		configurarTabla();
		
	}
	
	private void configurarTabla() {
		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		dtm.addColumn(COLUMN_NOMBRE);
		dtm.addColumn(COLUMN_PRECIO);
		dtm.addColumn(COLUMN_CANTIDAD);
		
		tblHistorial.setModel(dtm);
		
		tblHistorial.getColumn(COLUMN_NOMBRE).setPreferredWidth(120);
		tblHistorial.getColumn(COLUMN_PRECIO).setPreferredWidth(120);
		tblHistorial.getColumn(COLUMN_CANTIDAD).setPreferredWidth(120);
	
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(950, 600));  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void setControlador(AppControl c) {
		btnConsultar.addActionListener(c);
	}

	public void consultarProductos() {
		
		String fecha = this.getFecha();
		
		SqlQuery productoContract = new SqlQuery();
		ArrayList<ProductoCompra> productos = productoContract.consultarProductos(fecha);
		this.rellenarTabla(productos);	
		
	}

	private String getFecha() {
		return this.txtFecha.getText().trim();
	}

	private void rellenarTabla(ArrayList<ProductoCompra> listaProductos) {
		dtm.setRowCount(0);
		
		double sum = 0;
		for (ProductoCompra productoCompra : listaProductos) {
			double precio = productoCompra.getPrecio();
			int cantidad = productoCompra.getCantidad();
			sum = sum + precio * cantidad;
		}

		txtPrecioTotal.setText(Double.toString(sum) + " €");
		
		if (listaProductos.isEmpty()) {
			mostrarComponentesConsulta(false);
			JOptionPane.showMessageDialog(this, "No se han encontrado datos para la fecha indicada", 
					"Resultado de consulta", 
					JOptionPane.INFORMATION_MESSAGE);	
			
		} else {	
			Object[] fila = new Object[3];
			
			for (ProductoCompra p : listaProductos) {
				fila[0] = p.getNombre();
				fila[1] = p.getPrecio();
				fila[2] = p.getCantidad();
				
				dtm.addRow(fila);
			}
		
			mostrarComponentesConsulta(true);
		}
		
	}

	private void mostrarComponentesConsulta(boolean b) {
		tblHistorial.setVisible(b);
	}

	public void hacerVisible() {
		setVisible(true);
	}
	
}
