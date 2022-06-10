package com.asclepio.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.asclepio.control.AppControl;
import com.asclepio.model.Producto;

//import asclepio.view.PCompra;

public class PCompra extends JPanel {
	
	public static final int ALTO = 650;
	public static final int ANCHO = 800;

	public static final String BTN_COMPRAR = "COMPRAR";
	public static final String BTN_ELIMINAR = "ELIMINAR";
	public static final String BTN_VOLVER = "VOLVER";
	public static final String BTN_BUSQ = "B�SQUEDA";
	public static final String BTN_CARRITO = "CARRITO";

	private JScrollPane scrpCarrito;
	private JTable tbCarrito;
	private DefaultTableModel tModel;
	private JButton btnComprar;
	private JButton btnEliminar;
	private JTextField txtPago;
	private JButton btnVolver;
	private JTextField txtBusq;
	private JList<Producto> list;
	private DefaultListModel<Producto> dlm;
	private JButton btnBusq;
	private JButton btnCarrito;
	private JSpinner spinner;
	private JScrollPane scrpLista;
	
	private ArrayList<Producto> datosLista;
	
	public PCompra() {
		setBackground(Color.WHITE);
		init();
	}

	private void init() {
		setLayout(null);

		JLabel lblCarrito = new JLabel("CARRITO");
		lblCarrito.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrito.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCarrito.setBounds(404, 79, 335, 35);
		add(lblCarrito);

		scrpCarrito = new JScrollPane();
		scrpCarrito.setBounds(401, 125, 335, 381);
		add(scrpCarrito);

		tbCarrito = new JTable();
		tbCarrito.setBackground(Color.WHITE);
		tbCarrito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrpCarrito.setViewportView(tbCarrito);

		// LOS BOTONES EST�N EN LA MISMA POSICI�N, PARA QUE CUANDO
		// UN PRODUCTO SEA SELECCIONADO, EL BOT�N COMPRAR SE CAMBIAR� AL
		// DE ELIMINAR (PONIENDO VISIBLE UNO E INVISIBLE OTRO)
		btnComprar = new JButton(BTN_COMPRAR);
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComprar.setBounds(472, 552, 229, 48);
		add(btnComprar);

		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminar.setBounds(472, 552, 229, 48);
		add(btnEliminar);
		btnEliminar.setVisible(false);// 

		txtPago = new JTextField();
		txtPago.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPago.setBounds(632, 506, 104, 35);
		add(txtPago);
		txtPago.setColumns(10);
		txtPago.setEditable(false);

		btnVolver = new JButton(BTN_VOLVER);
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 85, 21);
		add(btnVolver);

		scrpLista = new JScrollPane();
		scrpLista.setBounds(23, 125, 335, 381);
		add(scrpLista);

		list = new JList<Producto>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpLista.setViewportView(list);
		dlm = new DefaultListModel<Producto>();

		JLabel lblBusq = new JLabel("B\u00DASQUEDA");
		lblBusq.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusq.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBusq.setBounds(23, 79, 335, 35);
		add(lblBusq);

		spinner = new JSpinner();
		spinner.setBounds(44, 557, 64, 40);
		add(spinner);

		btnCarrito = new JButton(BTN_CARRITO);
		btnCarrito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCarrito.setBounds(148, 553, 114, 48);
		add(btnCarrito);
		

		txtBusq = new JTextField();
		txtBusq.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBusq.setBounds(256, 33, 287, 35);
		add(txtBusq);
		txtBusq.setColumns(10);
		txtBusq.setToolTipText("B�squeda");

		btnBusq = new JButton(BTN_BUSQ);
		btnBusq.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBusq.setBounds(587, 11, 60, 60);
		add(btnBusq);
		

		centrarVentana();
		configurarTabla();

	}
	


	public String getTxtBusq() {
		return txtBusq.getText();
	}

	private void configurarTabla() {
		tModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tModel = (DefaultTableModel) tbCarrito.getModel();

		tModel.addColumn("CANTIDAD");
		tModel.addColumn("PRODUCTO");
		tModel.addColumn("PRECIO");

		tbCarrito.getColumn("CANTIDAD").setPreferredWidth(50);
		tbCarrito.getColumn("PRODUCTO").setPreferredWidth(150);
		tbCarrito.getColumn("PRECIO").setPreferredWidth(50);
		
	
	}
	
	public void showList(ArrayList<Producto> lista) {
		datosLista = lista;
		list.setModel(dlm);
		
		dlm.addAll(lista);
	}

	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = this.getPreferredSize();
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}

	public void hacerVisible() {
		setVisible(true);
	}
	
	
	public JList<Producto> getList(){
		return list;
	}
	
	public String getSelectedRow() {
		Producto item = list.getSelectedValue();
		String id = item.getIdProducto();
		System.out.println(list.getSelectedValue());
		return id;
		
	}

	public void setControlador(AppControl control) {
		btnBusq.addActionListener(control);
		btnCarrito.addActionListener(control);
		btnComprar.addActionListener(control);
		btnEliminar.addActionListener(control);
		btnVolver.addActionListener(control);
		
	}
	
}
