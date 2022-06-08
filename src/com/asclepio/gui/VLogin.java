package com.asclepio.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import com.asclepio.control.AppControl;
import com.asclepio.model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VLogin extends JFrame {
	private static final String BTN_LOGIN = "Login";
	private static final int ANCHO = 450;
	private static final int ALTO = 300;
	
	private JTextField txtUsuario;
	private JPasswordField txtPwd;
	private JButton btnLogin;
	
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
		
		btnLogin = new JButton(BTN_LOGIN);
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
	
	
	
	
	
	
	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void hacerVisible() {
		
		setVisible(true);
	}
	
	
	public void cleanData() {
		
		txtUsuario.setText("");
		txtPwd.setText("");
	}

	public void setControlador(AppControl control) {
		btnLogin.addActionListener(control);
		
	}

	public Usuario comprobarDatos() {
		Usuario user = null;
		
		String nameUser = txtUsuario.getText().trim();
		
		if (nameUser.isBlank()) {
			setError("Debe introducir el nombre de usuario");
		}else {
			String pwd = txtPwd.getText().trim();
			
			String error = Usuario.validarPwd(nameUser , pwd);
			
			if (!error.isBlank()) {
				setError(error);
			}else {
				user = new Usuario(nameUser, pwd);
			}
		}
		
		
		return user;
		
		
	}

	private void setError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error en datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	
	
	
}
