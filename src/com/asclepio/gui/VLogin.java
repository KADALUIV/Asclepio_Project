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
	public static final String BTN_LOGIN = "Login";
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
		
		JLabel lblidUsuario = new JLabel("ID Usuario:");
		lblidUsuario.setBounds(63, 77, 77, 16);
		getContentPane().add(lblidUsuario);
		
		JLabel lblPwd = new JLabel("Password:");
		lblPwd.setBounds(61, 136, 63, 16);
		getContentPane().add(lblPwd);
		
		setSize(ANCHO, ALTO);
		centrarVentana();
	}

	private void centrarVentana() {
		
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
				
	}
	
	
	
	
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
		
		try {
			int id = Integer.parseInt(txtUsuario.getText().trim());
			
			if (id <= 0) {
				setError("El Id usuario debe ser un número entero positivo");
			}else {
				
				String pwd = txtPwd.getText().trim();
				
				String error = Usuario.validarPwd(pwd);
				
				if (!error.isBlank()) {
					setError(error);
				}else {
					user = new Usuario(id, pwd);
				}
				
			}
		
		}catch (NumberFormatException e) {
			setError("El Id usuario es numérico");
		}
		
		
		
		
		return user;
	}

	public void setError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error en datos", JOptionPane.ERROR_MESSAGE);
		
	}
}
