package my.vaadin.practica3;

import java.util.ArrayList;

public class Dolares implements Divisa{

	@Override
	public void metodo(ArrayList<Producto> producto) {
		// TODO Auto-generated method stub
		System.out.println("Dolares");
		
		float cambio = (float) 1.2;
		
		for (int i = 0; i < producto.size(); i++){
			
			float cantidad =0;
			
			String resultado = "0";
			
			cantidad = Float.parseFloat(producto.get(i).getPrice());
			 
			resultado = Float.toString(cantidad * cambio);
			
			producto.get(i).setPrice(resultado);
		
		}
	}

}
