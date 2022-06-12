package com.asclepio.gui;



import javax.swing.*;

import com.asclepio.control.AppControl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VPrincipal extends JFrame{
	private static final int ANCHO = 950;
	private static final int ALTO = 850;
	public static final String JMENU_SALIR = "Log Out";
	public static final String ITEM_MENU_LOGOUT = "Cerrar Sesión";
	public static final String ITEM_MENU_EXIT = "Salir";
	public static final String BTN_SEE_STOCK = "Consultar Stock";
	public static final String BTN_REGISTRAR_C = "Registrar Compra";
	public static final String BTN_HISTORIAL_C = "Historial de Compra";
	private JMenu jMenu;
	private JMenuItem itemMenuCerrarSesion;
	private JMenuItem itemMenuSalir;
	private JScrollPane scrollContainer;
	private JButton btnSeeStock;
	private JButton btnHistorialC;
	private JButton btnResgistrarC;
	private JLabel lblNewLabel;
	
	
	public VPrincipal() {
		
		
		initComponents();
		menuBar();
		
		
		
	}
	
	private void menuBar() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		jMenu = new JMenu(JMENU_SALIR);
		jMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(jMenu);
		
		itemMenuCerrarSesion = new JMenuItem(ITEM_MENU_LOGOUT);
		jMenu.add(itemMenuCerrarSesion);
		
		JSeparator separator = new JSeparator();
		jMenu.add(separator);
		
		itemMenuSalir = new JMenuItem(ITEM_MENU_EXIT);
		itemMenuSalir.setForeground(Color.RED);
		jMenu.add(itemMenuSalir);
		
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrollContainer = new JScrollPane();
		scrollContainer.setVisible(false);
		scrollContainer.setBounds(0, 0, 950, 659);
		getContentPane().add(scrollContainer);
		
		btnHistorialC = new JButton(BTN_HISTORIAL_C);
		btnHistorialC.setBounds(361, 713, 206, 53);
		getContentPane().add(btnHistorialC);
		
		btnSeeStock = new JButton(BTN_SEE_STOCK);
		btnSeeStock.setBounds(143, 666, 206, 53);
		getContentPane().add(btnSeeStock);
		
		btnResgistrarC = new JButton(BTN_REGISTRAR_C);
		btnResgistrarC.setBounds(579, 666, 206, 53);
		getContentPane().add(btnResgistrarC);
		
		lblNewLabel = new JLabel("¿Qué necesitas hacer?");
		lblNewLabel.setBounds(0, 0, 946, 466);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		setSize(ANCHO,ALTO);
		centrarVentana();
	}
	
	

	
	private void centrarVentana() {
		
		setPreferredSize(new Dimension(ANCHO, ALTO));  
	       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		
		Dimension ventana = this.getPreferredSize();               
		       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
		
		

	}
	
	public void showVPrincipal() {
		
		setVisible(true);
	}

	public void hacerVisible(boolean b) {
		
		scrollContainer.setVisible(b);
	}

	public void setControlador(AppControl control) {
		
		itemMenuCerrarSesion.addActionListener(control);
		itemMenuSalir.addActionListener(control);
		btnSeeStock.addActionListener(control);
		btnResgistrarC.addActionListener(control);
		btnResgistrarC.addActionListener(control);
		btnHistorialC.addActionListener(control);
			
		
	}

	public void uploadPanel(JPanel panel) {
		
		scrollContainer.setViewportView(panel);
		
	}
	
	
	

	
	
}
