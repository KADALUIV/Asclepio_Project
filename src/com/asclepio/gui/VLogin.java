package com.asclepio.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import com.asclepio.control.AppControl;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VLogin extends JFrame {
	private static final int ANCHO = 950;
	private static final int ALTO = 600;
	
	private JTextField txtUsuario;
	private JPasswordField txtPwd;
	
	public VLogin() {
		initComponents();
	}

	private void initComponents() {
		setTitle("User Login");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ASCLEPIO");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(177, 19, 103, 25);
		getContentPane().add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(150, 68, 157, 35);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(150, 127, 157, 35);
		getContentPane().add(txtPwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(150, 174, 144, 29);
		getContentPane().add(btnLogin);
		
		setSize(ANCHO, ALTO);
		centrarVentana();
	}

	private void centrarVentana() {
		
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
				
	}
	
	//TODO setControl and resquestData
	
	
	
	
	
	
	public void hacerVisible() {
		
		setVisible(true);
	}
	
	
	public void cleanData() {
		
		txtUsuario.setText("");
		txtPwd.setText("");
	}

	public void setControlador(AppControl control) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
