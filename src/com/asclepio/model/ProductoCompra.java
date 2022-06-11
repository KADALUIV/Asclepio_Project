package com.asclepio.model;

public class ProductoCompra {
	
	private Producto producto;
	private int cantidad;
	
	public ProductoCompra(Producto producto, int cantidad) {
		this.producto= producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public String toString() {
		return "ProductoCompra [producto " + this.producto.toString() + ", cantidad=" + cantidad + "]";
	}
}
