package my.vaadin.practica3;

import java.util.ArrayList;
import java.util.List;

public class ListaReglas {
	
	// Variables
	private List<Regla> reglas;
	
	// Constructor
	public ListaReglas() {
		super();
		reglas = new ArrayList<>();
	}

	// Métodos de Reglas
	public void addReglas(Regla r) {
		reglas.add(r);
	}
	
	public void deleteReglas(Regla r) {
		reglas.remove(r);
	}
	
	public List<Regla> getReglas() {
		return reglas;
	}
}
