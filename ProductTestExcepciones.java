package vendingCo;

import java.time.LocalDate;

import org.junit.Test;

/**
 * Clase de prueba de las excepciones lanzadas por los métodos definidos en la clase Product.
 * 
 * @author vicsand
 * @author alegavi
 *
 */


public class ProductTestExcepciones { 
	
	String nombre = "Patatas";
	LocalDate fecha = LocalDate.of(2022, 3, 23);
	double precio = 1.66;
	String upc = "617629617337";
	int cantidad = 3;
	Product producto = new Product (nombre, precio, fecha, upc, cantidad);
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductNombreNull() {
		@SuppressWarnings("unused")
		Product test = new Product(null, precio, fecha, upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductNombreNum() {
		@SuppressWarnings("unused")
		Product test = new Product("12345", precio, fecha, upc, cantidad);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUPCletras(){
		producto.setUPC("1236abcde");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductFechaNull() {
		@SuppressWarnings("unused")
		Product test = new Product(nombre, precio, null, upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductfechaPrevia() {	
		LocalDate fecha1 = LocalDate.of(2021, 11, 7);
		@SuppressWarnings("unused")
		Product test = new Product(nombre, precio, fecha1, upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductPrecio0() {
		@SuppressWarnings("unused")
		Product test = new Product(nombre, 0, fecha, upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductPrecioNegativo() {
		@SuppressWarnings("unused")
		Product test = new Product(nombre, -5, fecha, upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testProductUpcNull() {
		@SuppressWarnings("unused")
		Product test = new Product(nombre, precio, fecha, null, cantidad);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testProductCantidadNull() {
		@SuppressWarnings("unused")
		Product test = new Product(nombre, precio, fecha, upc, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUPCIncorrecto() {
		producto.setUPC("12365474241");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNombreNumerico() {
		producto.setNombre("1234345");	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUPCIncorrectoMasDeLongitud12() {
		producto.setUPC("12365474245454651");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetCantidadNegativa() {
		producto.setCantidad(-4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetFechaPrevia() {
		LocalDate fecha1 = LocalDate.of(2021, 11, 7);
		producto.setFecha(fecha1);
	}
	
	
}
	


