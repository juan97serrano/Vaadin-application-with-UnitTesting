

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import my.vaadin.practica3.ListaReglas;
import my.vaadin.practica3.Producto;
import my.vaadin.practica3.Regla;

class ListaReglasTest {

	@Test
	void AniadirRegla() {
		
		ListaReglas lista = new ListaReglas();
		
		Producto producto1 = new Producto("1","pan","2");
		Producto producto2 = new Producto("1","agua","2");
		Producto producto3 = new Producto("1","gazpacho","2");
		
		Regla regla = new Regla(producto1,producto2,producto3);
		
		lista.addReglas(regla);
		
		int cantidad = lista.getReglas().size();
		
		assertEquals(cantidad,1);
		
		lista.getReglas().clear();
		
	}
	
	@Test
	void EliminarRegla() {
		
ListaReglas lista = new ListaReglas();
		
		Producto producto1 = new Producto("1","pan","2");
		Producto producto2 = new Producto("1","agua","2");
		Producto producto3 = new Producto("1","gazpacho","2");
		
		Regla regla = new Regla(producto1,producto2,producto3);
		
		lista.addReglas(regla);
		lista.deleteReglas(regla);
		
		int cantidad = lista.getReglas().size();
		
		assertEquals(cantidad,0);
		
		lista.getReglas().clear();
		
	}
	
	
}
