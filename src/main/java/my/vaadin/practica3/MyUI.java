package my.vaadin.practica3;

import java.util.Date;

import javax.servlet.annotation.WebServlet;

import org.atmosphere.config.service.Message;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.HasValue;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;


import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	 
	// Variables
	private Producto selectedProducto;
	private Transaccion selectedProductoTrans;
	private ListaReglas ListaRegla;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	

    	Grid<Producto> grid = new Grid<Producto>();
    	Grid<Transaccion> grid2 = new Grid<Transaccion>();
    	Grid<AlmacenTransacciones> grid3 = new Grid<AlmacenTransacciones>();
    	
     	Label labelTotal1 = new Label();
    	Label labelTotal2 = new Label();
    	Label BalanceEconomico1 = new Label();
    	Label BalanceEconomico2 = new Label();
    	
    	Date myDate = new Date();

    	HorizontalLayout horizontalLayout = new HorizontalLayout(); 
    	
    	//Para contar el número total de unidades
    	
    	FormLayout formLayout2 = new FormLayout();
    	
    	labelTotal2.setValue(Float.toString(Almacen.getInstance().totalProductos()));
    	
    	labelTotal1.setValue("Numero total de unidades:");
   	
    	
    	formLayout2.addComponents(
        			labelTotal1,labelTotal2
        );
        		
    	
    	FormLayout formLayout5 = new FormLayout();
    	
    	BalanceEconomico1.setValue(Float.toString(Almacen.getInstance().totalProductos()));
    	
    	BalanceEconomico2.setValue("Balance Económico (Beneficio de la producción):");
   	
    	
    	formLayout5.addComponents(
    			BalanceEconomico2,BalanceEconomico1
        );
    
    	// Ventana emegerte
    	
    	Window subWindow = new Window("Detalles del producto");
    	
        VerticalLayout subContent = new VerticalLayout();
        VerticalLayout vertical = new VerticalLayout ();
        VerticalLayout vertical2 = new VerticalLayout ();
        VerticalLayout vertical3 = new VerticalLayout ();
        
        //Label labelNumber = new Label();
        //Label labelName = new Label();
        //Label labelPrice = new Label();
        Button buttonDelete = new Button("Vender unidades");
        Button buttonEdit = new Button("Editar producto");
        Button buttonEliminar = new Button("Eliminar producto");
              
        TextField textFieldVenderNumber = new TextField("Numero que deseas vender");
    	TextField textFieldEditNumber = new TextField("Numero");
    	TextField textFieldEditName = new TextField("Nombre");
    	TextField textFieldEditPrice = new TextField("Precio/Producto");
    	
    	
    	//EDITAR 
    	
        buttonEdit.addClickListener(e -> {
        	Almacen.getInstance().editProductos(selectedProducto, textFieldEditNumber.getValue(),
        			textFieldEditName.getValue(), textFieldEditPrice.getValue());
        	grid.setItems(Almacen.getInstance().getProductos());
        	
        	removeWindow(subWindow);
        		
        	textFieldEditNumber.clear();
        	textFieldEditName.clear();
        	textFieldEditPrice.clear();
  
        });
                
        //ELIMINAR
        
        buttonEliminar.addClickListener(e -> {
        	
        	Almacen.getInstance().Eliminar(selectedProducto);
        	grid.setItems(Almacen.getInstance().getProductos());
        	
        	//Para tener actualizado el número total de unidades
    		labelTotal2.setValue(Float.toString(Almacen.getInstance().totalProductos()));
        	
        	removeWindow(subWindow);
        		
        	textFieldEditNumber.clear();
        	textFieldEditName.clear();
        	textFieldEditPrice.clear();
        	        
        });
        
        // VENDER
        buttonDelete.addClickListener(e -> {
        	
        	Almacen.getInstance().deleteProductos(selectedProducto,textFieldVenderNumber.getValue());
        	grid.setItems(Almacen.getInstance().getProductos());
        	
        	
        	// lo añadimos a la tabala de transacciones
    		String Valor = AlmacenTransacciones.getInstance().CalculoSaldo(selectedProducto.getPrice(), textFieldVenderNumber.getValue());
    		
    		Transaccion T = new Transaccion(Valor,myDate,selectedProducto.getName());
   
        	AlmacenTransacciones.getInstance().addTransaccion(T);
        	
        	//PAra tener actualizado el número total de unidades
    		labelTotal2.setValue(Float.toString(Almacen.getInstance().totalProductos()));
    		
    		//Para tener actualizado el balance económico
    		BalanceEconomico1.setValue(Float.toString(AlmacenTransacciones.getInstance().balanceEconomico()));
        	
        	grid2.setItems(AlmacenTransacciones.getInstance().getTransaccion());
        	removeWindow(subWindow);	
        	
        });   
        
        //subContent.addComponents(labelNumber, labelName, labelPrice, buttonDelete, 
        	//	textFieldEditNumber, textFieldEditName, textFieldEditPrice, buttonEdit);
        subContent.addComponents(textFieldVenderNumber,buttonDelete,buttonEliminar, textFieldEditNumber, textFieldEditName, textFieldEditPrice, buttonEdit);
    
        subWindow.center();
        subWindow.setContent(subContent);
        
    	//TABLA DE PRODCUTOS
        
    	grid.addColumn(Producto::getNumber).setCaption("Numero");
    	grid.addColumn(Producto::getName).setCaption("Nombre");
    	grid.addColumn(Producto::getPrice).setCaption("Precio/Producto");
    	grid.setSelectionMode(SelectionMode.SINGLE);
    	
    	grid.addItemClickListener(event -> {
    		
    		selectedProducto = event.getItem();
    		
        	// Notification.show("Value: " + event.getItem());
        	//labelNumber.setValue(selectedProducto.getNumber());
        	//labelName.setValue(selectedProducto.getName());
        	//labelPrice.setValue(selectedProducto.getPrice());
        	
        	
        	removeWindow(subWindow);
        	addWindow(subWindow);
        	
    	});
    	
    	// TABALA DE TRANSACCIONES
    		
    	grid2.addColumn(Transaccion::getConcepto).setCaption("Concepto");
    	grid2.addColumn(Transaccion::getSaldo).setCaption("Valor");
    	grid2.addColumn(Transaccion::getFecha).setCaption("Fecha");
    	
    	grid2.setSelectionMode(SelectionMode.SINGLE);
    	
        vertical.addComponents(grid,grid2);

    	// AÑADIR SOBRE TABLA PRODUCTOS
    	FormLayout formLayout = new FormLayout();
    	
    	TextField textFieldNumber = new TextField("Numero");
    	TextField textFieldName = new TextField("Nombre");
    	TextField textFieldPrice = new TextField("Precio/Producto");
    	Button buttonAddProducto = new Button("Comprar");
    	
    	
    	buttonAddProducto.addClickListener(e -> {
    		
    		Producto p = new Producto(
    				textFieldNumber.getValue(),
    				textFieldName.getValue(),
    				textFieldPrice.getValue(),myDate
    				); 
    		    		
    		String Valor = AlmacenTransacciones.getInstance().CalculoSaldo(textFieldPrice.getValue(), textFieldNumber.getValue());
    		
    		Transaccion T = new Transaccion("-"+Valor,myDate,textFieldName.getValue());
    		   	
        	Almacen.getInstance().addProductos(p);
    		
    		//PAra tener actualizado el número total de unidades
    		labelTotal2.setValue(Float.toString(Almacen.getInstance().totalProductos()));
   	
    		AlmacenTransacciones.getInstance().addTransaccion(T);
    		
    		//Para tener actualizado el balance económico
    		BalanceEconomico1.setValue(Float.toString(AlmacenTransacciones.getInstance().balanceEconomico()));
    		
    		textFieldNumber.clear();
    		textFieldName.clear();
    		textFieldPrice.clear();
    		
    		grid.setItems(Almacen.getInstance().getProductos());
    		grid2.setItems(AlmacenTransacciones.getInstance().getTransaccion());
  
    	});
    	
    	   
    	//TABLA DE BALANCE ECONÓMICO      
    	grid3.addColumn(AlmacenTransacciones::balanceEconomico).setCaption("Balance Económico");
    	
    	//labelTotal.setValue(Float.toString(Almacen.getInstance().totalProductos()));
    
    	formLayout.addComponents(
    			textFieldNumber, 
    			textFieldName, 
    			textFieldPrice, 
    			buttonAddProducto
    	);
    	
    	// AÑADIR SOBRE TABLA TRANSACCIONES
    	 
    	FormLayout formLayout3 = new FormLayout();
    	
    	TextField textFieldConcepto = new TextField("Concepto");
    	TextField textFieldNumber2 = new TextField("Valor(+Venta,-Compra)");
    	
    	Button buttonAddProducto2 = new Button("Añadir Transaccion");
    			
    	buttonAddProducto2.addClickListener(e -> {
    		
 
    		Transaccion T = new Transaccion(
    				textFieldNumber2.getValue(),
    				myDate,textFieldConcepto.getValue()
    	
    				);
    		
  
    		textFieldNumber2.clear();
    		textFieldConcepto.clear();
    		AlmacenTransacciones.getInstance().addTransaccion(T);
    		
    		//Para tener actualizado el balance económico
    		BalanceEconomico1.setValue(Float.toString(AlmacenTransacciones.getInstance().balanceEconomico()));
    		
    		grid2.setItems(AlmacenTransacciones.getInstance().getTransaccion());
        		
    	});

    	formLayout3.addComponents(
    			textFieldConcepto,
    			textFieldNumber2, 
    		
    			buttonAddProducto2
    	);
    	
    	
    	
    	//Button Euro = new Button("Euro");
    	//Button Dolar = new Button("Dolares");
    	
    	
    	 
    	RadioButtonGroup<String> group = new RadioButtonGroup<>();
    	
    	
    	group.setItems("Euros", "Dolares");
    	group.setValue("Euros");
    	
    	group.addValueChangeListener(e -> {
    		 
    		if(e.getValue()=="Euros"){
    			
    			Almacen.getInstance().setMostrar(new Dolares());				
        		Almacen.getInstance().Mostrar();
    			
        		
        		grid.setItems(Almacen.getInstance().getProductos());
        		grid2.setItems(AlmacenTransacciones.getInstance().getTransaccion());
    			
    			
    		}
    		if(e.getValue()=="Dolares") {
    			
    			Almacen.getInstance().setMostrar(new Euros());				
    			Almacen.getInstance().Mostrar();

    			grid.setItems(Almacen.getInstance().getProductos());
    			grid2.setItems(AlmacenTransacciones.getInstance().getTransaccion());
    		}
    		
    	});
    	
   
    	vertical2.addComponents(group,formLayout,formLayout3,formLayout2,formLayout5); 
    	
    	
    	
    	// PARTE NUEVA QUE HAY QUE JUNTAR CON LO DEMAS
    	// TAMBIEN LAS CLASES ListaReglas y Reglas son nuevas y hay que juntarlas
    	// Form 3 REGLAS
    	FormLayout formLayout4 = new FormLayout();

    	// CAJAS DE SELECCION
    	ComboBox<Producto> select1 = new ComboBox<>("Selecciona producto 1", 
    			Almacen.getInstance().getProductos());
    	ComboBox<Producto> select2 = new ComboBox<>("Selecciona producto 2", 
    			Almacen.getInstance().getProductos());
    	ComboBox<Producto> select3 = new ComboBox<>("Selecciona resultado", 
    			Almacen.getInstance().getProductos());
    	
    	// ESTO PONE UN TEXTO POR DEEFECTO EN LA CAJA DE SELECCIÓN
    	select1.setPlaceholder(" ... ");
    	select2.setPlaceholder(" ... ");
    	select3.setPlaceholder(" ... ");

    	// Use the name property for item captions
    	select1.setItemCaptionGenerator(Producto::getName);
    	select2.setItemCaptionGenerator(Producto::getName);
    	select3.setItemCaptionGenerator(Producto::getName);
    	
    	// SI SABEMOS EN QUE VARIABLE RECOGE LA OPCION SELECCIONADA PODRIAMOS CREAR LA REGLA
    	// ASI
    	/*Regla r = new Regla(
    				?.getvalue(),
    				?.getvalue(),
    				?.getvalue()
    				);*/
    	
    	formLayout4.addComponents(
    			select1, 
    			select2, 
    			select3
    	);
    	
    	/*Label labelRegla1 = new Label();
    	
    	//labelRegla1.setValue(select1.getPrimaryStyleName());
    	
    	Label labelRegla2 = new Label();
    	
    	//labelRegla2.setValue(select1.getValue().getName());
    	
    	Label labelRegla3 = new Label();
    	
    	labelRegla3.setValue("+");
    	
    	Label labelReglaResultado = new Label();
    	
    	labelReglaResultado.setValue("---------------");
    	
    	select1.addAttachListener(e -> {
    		
    		
    		labelRegla1.setValue(select1.getValue().getName());
    		
    	}); */	
	    	    	    	
    	Label labelDivisa = new Label();
    
    	labelDivisa.setValue("Selecciona Divisa");
    			   	
    	vertical3.addComponents(labelDivisa,group,formLayout4 );

 
    	horizontalLayout.addComponents(vertical,vertical2,vertical3);

    	
    	setContent(horizontalLayout);
    	

    	//setContent(horizontalLayout2);
    	
    	//ESTO NO FUNCIONA
    	//TwinColSelect<String> select = new TwinColSelect<>("Select Targets");

    	// Put some items in the select
    	//select.setItems("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");

    	// Few items, so we can set rows to match item count
    	//select.setRows(select.size());

    	// Preselect a few items
    	//select.select("Venus", "Earth", "Mars");

    	// Handle value changes
    	//select.addSelectionListener(event -> layout.addComponent(new Label("Selected: " + event.getNewSelection())));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
