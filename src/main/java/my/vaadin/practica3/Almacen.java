package my.vaadin.practica3;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.TextField;



public class Almacen {
	// Variables
	private static Almacen singleton;
	private ArrayList<Producto> productos;
	
	private Divisa mostrar;

	// Constructor
	private Almacen() {
		super();
		productos = new ArrayList<>();
	}
	
	public static Almacen getInstance() {
		
		if(singleton == null)
			singleton = new Almacen();
		
		return singleton; 
	}
	
	// Métodos de Almacen
	public void addProductos(Producto p) {
		
		int c=0; 
	  
		for (int i = 0; i < productos.size(); i++) {
			
				
			if (productos.get(i).getName().equals(p.getName())) {
			
					float NuevoNumero  = 0;
					
					c = 1;
					
					NuevoNumero =  Float.parseFloat(productos.get(i).getNumber()) + Float.parseFloat(p.getNumber());								
					productos.get(i).setNumber(Float.toString(NuevoNumero));
		 
				}
			}
		if (c==0) {
			
			productos.add(p);
				
		} 
	
	}
				
	public void deleteProductos(Producto p,String textFieldVenderNumber) {
		 
		int NuevoNumero  = 0;
		int x = productos.indexOf(p);
		NuevoNumero = Integer.parseInt(productos.get(x).getNumber()) -Integer.parseInt(textFieldVenderNumber);								
		productos.get(x).setNumber(Integer.toString(NuevoNumero));
		
	} 
	
	public void Eliminar(Producto p) {
		
		productos.remove(p);
		
	}
	
	public void editProductos(Producto p, String number, String name, String price) {
		
		int x = productos.indexOf(p);
		
		productos.get(x).setName(name);
		productos.get(x).setNumber(number);
		productos.get(x).setPrice(price);
	}
	
	public float totalProductos(){
		int i;
		float cantidad = 0;
		
		if(productos.size()==0) {
			
			cantidad=0;
		}else {
			for(i=0 ; i<productos.size();  i++) {
				cantidad = cantidad + Float.parseFloat(productos.get(i).getNumber());
			}
			
		}
		return cantidad;
	}
	
	public List<Producto> getProductos() {
		
		return productos;
		
	}
	
	// Para hacer el PatrÃ³n strategy
	public void setMostrar(Divisa mostrar) {
			
			this.mostrar = mostrar;
			
	}

	public void Mostrar() {
			
			mostrar.metodo(productos);
			
	}
}
