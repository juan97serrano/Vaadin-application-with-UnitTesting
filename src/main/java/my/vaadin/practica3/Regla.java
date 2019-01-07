package my.vaadin.practica3;

public class Regla {

	// Variables
	private Producto p1;
	private Producto p2;
	private Producto resultado;
	
	// Constructor
	public Regla(Producto p1, Producto p2, Producto resultado) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.resultado = resultado;
	}
	
	// Getters y Setters
	public Producto getP1() {
		return p1;
	}
	public void setP1(Producto p1) {
		this.p1 = p1;
	}
	public Producto getP2() {
		return p2;
	}
	public void setP2(Producto p2) {
		this.p2 = p2;
	}
	public Producto getResultado() {
		return resultado;
	}
	public void setResultado(Producto resultado) {
		this.resultado = resultado;
	}
}
