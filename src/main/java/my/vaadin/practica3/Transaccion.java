package my.vaadin.practica3;

import java.util.Date;

public class Transaccion {

	// Variables
	private String Saldo;
	private Date fecha;
	private String concepto;
	
	// Constructor

	public Transaccion(String Saldo ,Date fecha,String concepto) {
	
		this.Saldo = Saldo;
		this.fecha = fecha;
		this.concepto = concepto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getSaldo() {
		return Saldo; 
	}

	public void setSaldo(String saldo) {
		Saldo = saldo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	


	
}