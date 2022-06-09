package com.asclepio.model;

public class ProductoCompra {
	
	private String nombre;
	private int cantidad;
	private double precio;
	
	public ProductoCompra(String nombre, double precio, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}
}
