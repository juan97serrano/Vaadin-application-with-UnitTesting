

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import my.vaadin.practica3.AlmacenTransacciones;
import my.vaadin.practica3.Transaccion;


class AlmacenTrasaccionesTest {

	@Test
	void AniadirTransaccion() {
		
		
		Date myDate = new Date();
		
		Transaccion transaccion = new Transaccion("123" ,myDate,"Agua");
		
		AlmacenTransacciones AlamcenTrans = AlmacenTransacciones.getInstance();
		
		AlamcenTrans.addTransaccion(transaccion);
		
		int cantidad = AlamcenTrans.getTransaccion().size();
		
		assertEquals(cantidad,1);
			
		AlamcenTrans.getTransaccion().clear();
	}
	

	
	@Test
	
	void CalculoSaldo() {
		
		AlmacenTransacciones AlamcenTrans = AlmacenTransacciones.getInstance();
		
		
		int cantidad = Integer.parseInt(AlamcenTrans.CalculoSaldo("2", "2"));
		
		assertEquals(cantidad,Integer.parseInt("4"));
		
	}
	
	@Test
	
	void balanceEconomico() {
		
		
		Date myDate = new Date();
		
		AlmacenTransacciones AlamcenTrans = AlmacenTransacciones.getInstance();

		Transaccion transaccion2 = new Transaccion("100" ,myDate,"Agua");
		
		Transaccion transaccion3 = new Transaccion("12" ,myDate,"CocaCola");
				
		AlamcenTrans.addTransaccion(transaccion3);
		AlamcenTrans.addTransaccion(transaccion2);	
		
		int  cantidad = Math.round(AlamcenTrans.balanceEconomico());
		
		assertEquals(cantidad,112);
		
		AlamcenTrans.getTransaccion().clear();
		
	}
	 
	@Test
	
	void balanceEconomicoCero() {
		
		
		AlmacenTransacciones AlamcenTrans = AlmacenTransacciones.getInstance();

		int  cantidad = Math.round(AlamcenTrans.balanceEconomico());
		
		assertEquals(cantidad,0);
	
		
	}

}
