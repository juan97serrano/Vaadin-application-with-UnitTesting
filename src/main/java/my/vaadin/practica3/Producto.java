package my.vaadin.practica3;

import java.util.Date;

public class Producto {

	// Variables
	private String name;
	private String number;
	private String price;
	private Date fecha;
	
	// Constructor
	public Producto(String number, String name, String price) {
		
		this.name = name;
		this.number = number;
		this.price = price;
	
	} 
	
	public Producto(String number, String name, String price,Date fecha) {
	
		this.name = name;
		this.number = number;
		this.price = price;
		this.fecha = fecha;
	}
	
	// Getters y Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}