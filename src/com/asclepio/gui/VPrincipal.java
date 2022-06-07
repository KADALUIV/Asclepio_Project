package com.asclepio.gui;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import com.asclepio.control.AppControl;
import java.awt.Color;
import java.awt.Font;

public class VPrincipal extends JFrame {
	private static final int ANCHO = 950;
	private static final int ALTO = 600;
	public static final String ITEM_MENU_LOGOUT = "Cerrar Sesión";
	public static final String ITEM_MENU_EXIT = "Salir";
	public static final String BTN_SEE_STOCK = "Consultar Stock";
	public static final String BTN_REGISTRAR_C = "Registrar Compra";
	public static final String BTN_HISTORIAL_C = "Historial de Compra";
	private JMenuBar menuBar;
	private JMenu jMenuClose;
	private JMenuItem itemMenuLogOut;
	private JMenuItem itemMenuExit;
	private JButton btnSeeStock;
	private JButton btnRegistrarC;
	private JButton btnHistorialC;
	public VPrincipal() {
		initComponents();
		menuBar();
		
		
	}
	
	private void menuBar() {
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.GRAY);
		setJMenuBar(menuBar);
		
		jMenuClose = new JMenu("Log Out");
		jMenuClose.setForeground(Color.RED);
		menuBar.add(jMenuClose);
		
		itemMenuLogOut = new JMenuItem(ITEM_MENU_LOGOUT);
		itemMenuLogOut.setForeground(Color.BLACK);
		jMenuClose.add(itemMenuLogOut);
		
		JSeparator separator = new JSeparator();
		jMenuClose.add(separator);
		
		itemMenuExit = new JMenuItem(ITEM_MENU_EXIT);
		itemMenuExit.setForeground(Color.RED);
		jMenuClose.add(itemMenuExit);
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bienvenido");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("¿Qué quieres hacer?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblNewLabel_1.setBounds(276, 160, 318, 50);
		getContentPane().add(lblNewLabel_1);
		
		btnSeeStock = new JButton(BTN_SEE_STOCK);
		btnSeeStock.setBounds(183, 266, 176, 50);
		getContentPane().add(btnSeeStock);
		
		btnRegistrarC = new JButton(BTN_REGISTRAR_C);
		btnRegistrarC.setBounds(520, 266, 176, 50);
		getContentPane().add(btnRegistrarC);
		
		btnHistorialC = new JButton(BTN_HISTORIAL_C);
		btnHistorialC.setBounds(365, 391, 176, 50);
		getContentPane().add(btnHistorialC);
		setSize(ANCHO, ALTO);
		centrarVentana();
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
		
	}

	public void setControlador(AppControl control) {
		// TODO Auto-generated method stub
		
	}
}
