

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import my.vaadin.practica3.Almacen;
import my.vaadin.practica3.Producto;

class AlmacenTest {

	@Test
	void EliminarProducto() {
		
		Producto producto = new Producto("5","Agua","2");
		
		Almacen almacen = Almacen.getInstance();
		
		almacen.addProductos(producto);
		
		almacen.Eliminar(producto);
		
		int cantidad = almacen.getProductos().size();
		
		
		assertEquals(cantidad,0);
		
		almacen.getProductos().clear();
	
	}
	
	@Test
	void TotalProductos() {
		
		Producto producto = new Producto("5","Agua","2");
		Producto producto2 = new Producto("5","CocaCola","2");
		
		Almacen almacen = Almacen.getInstance();
		
		almacen.addProductos(producto);
		almacen.addProductos(producto2);

		int cantidad = Math.round(almacen.totalProductos());
	
		
		assertEquals(cantidad,10);
		
		almacen.getProductos().clear();
	
	}
	@Test
	void TotalProductosCero() {
		
	
		Almacen almacen = Almacen.getInstance();

		int cantidad = Math.round(almacen.totalProductos());
	
		assertEquals(cantidad,0);
		
		almacen.getProductos().clear();
	
	}
	
	
	@Test
	void Editarproductos() {
		
		
		Almacen almacen = Almacen.getInstance();
		
		Producto producto = new Producto("5","Agua","2");
		
		almacen.addProductos(producto);
		
		almacen.editProductos(producto, "5","CocaCola","2");
		
		int cantidad = almacen.getProductos().size();
		
		
		assertEquals(cantidad,1);
		
		almacen.getProductos().clear();
		
		
	}
	
	@Test
	void DeleteProdcutos() {
		
		
		Almacen almacen = Almacen.getInstance();
		
		Producto producto = new Producto("5","Agua","2");
		
		almacen.addProductos(producto);
		
		almacen.deleteProductos(producto, "2");
		
		int cantidad = Math.round(almacen.totalProductos());
		
		assertEquals(cantidad,3);
		
		almacen.getProductos().clear();
			
	}
	@Test
	void AniadirProdcutoExistente() {
		
		
		Almacen almacen = Almacen.getInstance();
		
		Producto producto = new Producto("5","Agua","2");
		Producto producto2 = new Producto("10","Agua","2");
		
		almacen.addProductos(producto);
		almacen.addProductos(producto2);
		
		int cantidad = almacen.getProductos().size();
		
		assertEquals(cantidad,1);
		
		almacen.getProductos().clear();
			
	}
	
}
