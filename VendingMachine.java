package vendingCo;

import java.time.LocalDate;
import java.util.ArrayList;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Esta clase tiene asociado un identificador que caracteriza a la m�quina y un estado
 * que depende de si dicha m�quina est� operativa o no.
 * Se incluye en la clase la instancia de productos totales, que tiene como objetivo guardar los productos que vende
 * cada m�quina
 * Para el funcionamiento de esta clase es necesario disponer de una tarjeta monedero.

 * @author vicsand
 * @author alegavi
 *
 */
public class VendingMachine {
	private int id;
	private boolean estado;
	TarjetaMonedero tarjeta;
	private ArrayList<Product> productosTotales;
	

/**
 * Construye una instancia de una m�quina de vending, caracterizada por su identificador y su estado,
 * y junto con ella se crea la lista de los productos que maneja la m�quina reci�n descrita.
 * 
 * @param identificador unico para cada maquina
 * @param estado operativa o no
 * @throws IllegalArgumentException si el identificador o el estado de la m�quina es nulo. 
 */
public VendingMachine(int ident, Boolean esta) {
	if (ident == 0) {
		throw new IllegalArgumentException ("Llamada incorrecta: el identificador de una m�quina no puede ser nulo");
	}else id = ident;
	if (esta == null) {
		throw new IllegalArgumentException ("Llamada incorrecta: el estado de una m�quina no puede ser nulo");
	}else estado = esta;
	
	productosTotales = new ArrayList<Product>();			
}
	
/**
 * A�ade un nuevo producto a la m�quina con su nombre, precio, fecha de consumici�n preferente, UPC y cantidad.
 * @param nombre �nico a cada producto	
 * @param precio mayor que 0
 * @param fecha de caducidad del producto
 * @param UPC identificador unico del producto 
 * @param cantidad debe ser mayor que 0
 */
public void a�adirProducto(String nombre, double precio, LocalDate fecha, String UPC, int cantidad ) {
	Product nuevo = new Product(nombre,precio,fecha,UPC,cantidad);
	productosTotales.add(nuevo);
}
	
/**
 * Dada la posici�n de un producto lo busca entre los productos gestionados y devuelve el producto.
 * @param posicion en la m�quina
 * @throws IllegalArgumentException si la posici�n es negativa
 * @return producto 
 */
public Product getProductoPorPosicion(int posicion){
	if (posicion < 0) {
		throw new IllegalArgumentException ("La posicion de un producto no puede ser negativa");
	}else {
	Product productoDeseado = null;
		if(productosTotales.get(posicion).getCantidad() != 0) {
			productoDeseado = productosTotales.get(posicion);
		}
		return productoDeseado;
	}
}

/**
 * Dado el nombre de un producto devuelve sobre todos los productos gestionados el producto deseado.
 * @param nombre del producto
 * @throws IllegalArgumentException si el nombre de ese producto no existe
 * @return producto deseado
 */
public Product getProductoPorNombre(String nombre){
	Product productoDeseado = null;
	for(int i = 0; i < productosTotales.size(); i++) {
		if(productosTotales.get(i).getNombre() == nombre) {
			productoDeseado = productosTotales.get(i);
		}else {
			throw new IllegalArgumentException ("El nombre de ese producto no est�");
		}
	}
		return productoDeseado;
}

/**
 * Dado el upc de un producto devuelve sobre todos los productos gestionados el producto deseado.
 * @param upc 
 * @return producto deseado
 */
public Product getProductoPorUpc(String upc) {
	Product productoDeseado = null;
	for (int i = 0; i < productosTotales.size(); i++) {
		if (productosTotales.get(i).getUPC() == upc ) {
			productoDeseado = productosTotales.get(i);			
		}
	}
	return productoDeseado;
}

/**
 * Devuelve un valor booleano si una de las lineas de la m�quina est� vac�a, es decir, que no tiene productos.
 * @return valor booleano lineaVacia
 */
public boolean lineaVacia() {
	boolean lineaVacia = false; 
	for(int i = 0; i < productosTotales.size(); i++){
		if(productosTotales.get(i).getCantidad() == 0) {
				 return lineaVacia = true;
					}
		}
	return lineaVacia;
}

/**
 * Devuelve el precio del producto de la posici�n indicada.
 * @param posicion en la m�quina
 * @return precio del producto deseado
 * @throws IllegalStateException si la linea seleccionada para obtener el precio est� vac�a
 */
public double precioPorPosicion(int posicion) {
	double precio = 0; 
	if(productosTotales.get(posicion).getCantidad() != 0) {
		precio = productosTotales.get(posicion).getPrecio();
	}else {
		throw new IllegalStateException ("La linea seleccionada est� vac�a");
	}
	return precio; 
}

/**
 * Retira un producto de la m�quina como operaci�n de compra dada su posici�n, una tarjeta y la credencial requerida.
 * @param posicion en la m�quina
 * @param tarjeta valida del cliente, debe tener saldo
 * @param clave credencial de la tarjeta
 * @throws IllegalStateException si la tarjeta no tiene suficiente saldo
 * @throws IllegalStateException si la linea de dodne se quiere comprar el producto est� vac�a
 */
public void comprarProducto(int posicion, TarjetaMonedero tarjeta, String clave) {
	double precio = precioPorPosicion(posicion);							
	int cantidadNueva = (productosTotales.get(posicion).getCantidad() - 1);
	if(tarjeta.getSaldoActual() >= precio) {							
		tarjeta.descontarDelSaldo(clave, precio);
	}else {
		throw new IllegalStateException ("La tarjeta no tiene saldo suficiente para la operaci�n");
	}
	productosTotales.get(posicion).setCantidad(cantidadNueva);
	
}

/**
 * Reabastecimiento de un producto dado su upc.
 * @param UPC
 * @param cantidad a reabastecer
 * @throws IllegalArgumentException si se pretende reabastecer con una cantidad nula o negativa
 */
public void reabastecer(String upc, int cantidad) {
	if (cantidad <= 0) {
		throw new IllegalArgumentException ("No se puede reabastecer con una cantidad nula o negativa");
	}else {
	Product producto = getProductoPorUpc(upc);
	int cantidadNueva = producto.getCantidad() + cantidad;
	producto.setCantidad(cantidadNueva);
	}
}

/**
 * Consulta el identificador de la m�quina que llama al m�todo.
 * @return identificador
 */
public int getId() {
	return id;
}

/**
 * Actualiza el valor del identificador de la m�quina que llama al m�todo.
 * @throws IllegalArgumentException si el identificador es nulo
 * @param identificador
 */
public void setId(int id) {
	if (id == 0) {
		throw new IllegalArgumentException ("El identificador de una m�quina no puede ser nulo");
	}else {this.id = id;}
}

/**
 * Consulta el valor del estado de la m�quina que llama al m�todo.
 * @return estado
 */
public boolean getEstado() {
	return estado;
}

/**
 * Actualiza el estado de la m�quina que llama al m�todo.
 * @param estado
 * @throws IllegalArgumentException si el estado es nulo
 */
public void setEstado(Boolean estado) {
	if (estado == null) {
		throw new IllegalArgumentException ("El estado de una m�quina no puede ser nulo");
	}else {this.estado = estado;}
}

/**
 * Consulta los productos de una m�quina de vending.
 * @return productos
 */
public ArrayList<Product> getProductos(){
	return productosTotales;
}

	


}
