package vendingCo;


import java.time.LocalDate;
import org.junit.Test;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Clase de prueba de las excepciones lanzadas por los métodos definidos en la clase VendingMachine.
 * 
 * @author vicsand
 * @author alegavi
 *
 */


public class VendingMachineTestExcepciones {
	
	VendingMachine v = new VendingMachine(67,true);
	
	
	
	@Test (expected = IllegalStateException.class)
		@SuppressWarnings("unused")
	public void testPrecioPorPosicion() {
		String nombre = "Patatas";
		double precio = 1.66;
		LocalDate fecha = LocalDate.of(2022, 3, 23);
		String upc = "617629617337";
		int cantidad = 0;
		int posicion = 0;
		v.añadirProducto(nombre, precio, fecha, upc, cantidad);
		v.precioPorPosicion(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
		@SuppressWarnings("unused")
	public void testVendingMachineIdNull() {
		VendingMachine maquina = new VendingMachine(0, true);
	} 
	
	@Test (expected = IllegalArgumentException.class)
		@SuppressWarnings("unused")
	public void testVendingMachineEstadoNull() {
		VendingMachine maquina = new VendingMachine(45, null);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testComprarProductoVacio() {
		String nombre = "Patatas";
		double precio = 1.66;
		LocalDate fecha = LocalDate.of(2022, 3, 23);
		String upc = "617629617337";
		int cantidad = 0;
		int posicion = 0;
		v.añadirProducto(nombre, precio, fecha, upc, cantidad);
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",20.0);
		String clave = "6Z1y00Nm31aA-571";
		v.comprarProducto(posicion, visa, clave);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetIDNulo() {
		int id = 0;
		v.setId(id);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetEstadoNulo() {
		Boolean estado = null;
		v.setEstado(estado);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReabastecerNulo() {
		int cantidad = 0;
		String upc = "617629617337";
		v.reabastecer(upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReabastecerNegativo() {
		int cantidad = -4;
		String upc = "617629617337";
		v.reabastecer(upc, cantidad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetProductoPorNombreNoExiste() {
		VendingMachine v = new VendingMachine(67,true);
		String nombre = "Patatas";
		String upc = "617629617337";
		double precio = 1.6 ;
		LocalDate fecha = LocalDate.of(2022, 3, 23);
		int cantidad = 5;
		v.añadirProducto(nombre, precio, fecha, upc, cantidad);
		@SuppressWarnings("unused")
		Product productoPorNombre = v.getProductoPorNombre("Refresco");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetProductoPorPosicionNegativa() {
		String upc = "617629617337";
		int cantidad = 5;
		double precio = 1.6 ;
		v.añadirProducto("Patatas",precio,LocalDate.of(2022, 3, 23),upc,cantidad);
		@SuppressWarnings("unused")
		Product producto = v.getProductoPorPosicion(-1);
	}
	
}
