package my.vaadin.practica3;

import java.util.ArrayList;
import java.util.List;

public class AlmacenTransacciones {
	// Variables
	private static AlmacenTransacciones singleton;
	private ArrayList<Transaccion> transacciones;

	// Constructor
	private AlmacenTransacciones() {
		super();
		transacciones = new ArrayList<>();
	}
	
	public static AlmacenTransacciones getInstance() {
		
		if(singleton == null)
			singleton = new AlmacenTransacciones();
		
		return singleton;
	}
	
	// Métodos de Almacen
	public void addTransaccion(Transaccion p) {
		transacciones.add(p); 
	}
		

	public ArrayList<Transaccion> getTransaccion() {
		return transacciones;
	}
	
	
	public float balanceEconomico(){
		
		int i;
		float cantidad = 0;
		
		if(transacciones.size()==0) {
			
			cantidad=0;
		}else {
			for(i=0 ; i<transacciones.size();  i++) {
				cantidad = cantidad + Float.parseFloat(transacciones.get(i).getSaldo());
			}
			
		}
		return cantidad;
	}
	
	public String CalculoSaldo (String precio,String Cantidad) {

		return Float.toString(Float.parseFloat(precio)*Float.parseFloat(Cantidad));
		
	}
	
}
