package vendingCo;

import java.time.*;

/**
 * Esta clase tiene asociado un precio, una fecha de consumo preferente,
 *	un nombre, un código de producto UPC (Universal Product Code) y una cantidad.
 *	Se recuerda que cuando se habla de producto, una instancia de producto no es el ítem sino el tipo de producto.
 *
 * @author vicsand
 * @author alegavi
 *
 */


public class Product {

	private double precio;
	private LocalDate fecha;
	private String nombre;
	private String UPC;
	private int cantidad;
	

	/**
	 * Construye una instancia de producto, caracterizado por su nombre, precio, fecha de consumo preferente
	 * UPC y la cantidad que hay de ese producto.
	 * 
	 * @param nombre único para cada producto
	 * @param precio mayor que 0
	 * @param fecha de caducidad del producto
	 * @param upc único para cada producto
	 * @param cantidad mayor que 0
	 * 
	 * @throws IllegalArgumentException si cualquiera de los valores es nulo, el nombre contiene caracteres numéricos
	 * ,la cantidad del producto es menor que 0 o la fecha es anterior al día de hoy.
	 */
	 
	public Product(String nom, double pre, LocalDate fec,  String upc, int can) {
		if (nom == null) {
			throw new IllegalArgumentException ("Llamada incorrecta: el nombre del producto no puede ser nulo");
		}if(contieneSoloLetras(nom) == false) {
			throw new IllegalArgumentException ("Llamada incorrecta: el nombre del producto no puede contener números");
		}else {nombre = nom;}
		
		if (pre <= 0) {
			throw new IllegalArgumentException ("Llamada incorrecta: el precio de un producto no puede ser nulo");
		}else {precio = pre;}
		
		if (fec == null) {
			throw new IllegalArgumentException ("Llamada incorrecta: la fecha de consumición preferente de un producto no puede ser nula");
		}
		LocalDate hoy = LocalDate.now();
		if(fec.isBefore(hoy) == true) {
			throw new IllegalArgumentException ("Llamada incorrecta: la fecha no puede ser anterior al día de hoy");
		}else {fecha = fec;}
		
		if (upc == null) {
			throw new IllegalArgumentException ("Llamada incorrecta: el UPC de un producto no puede ser nulo");
		}else {UPC = upc;}
		
		if(can < 0) {
			throw new IllegalArgumentException ("Llamada incorrecta: la cantidad de un producto no puede ser menor que 0");
		}else {setCantidad(can);}
	}
	
	/**
	 * Comprueba si una cadena solo tiene letras, no tiene utilidad en el funcionamiento de la máquina,
	 * es usada en métodos para simplificar el código.
	 * @param cadena
	 * @return valor booleano contiene solo letras
	 */
	private boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
	} 
	
	/**
	 * Calcula el dígito de control del upc según el algoritmo dado para los dígitos de entrada.
	 * @param upc los 12 dígitos del codigo universal de producto
	 * @return valor booleano correcto
	 * @throws IllegalArgumentException si el upc tiene un número de dígitos distinto de 12
	 * @throws IllegalArgumentException el upc introducido no es correcto
	 */
	public Boolean upc(String upc) {
		Boolean correcto = true;
		int longitud = upc.length();
		if (!upc.matches("[+-]?\\d*(\\.\\d+)?")){
			correcto = false;
			throw new IllegalArgumentException ("El UPC introducido tiene caracteres no numéricos");
		}if (longitud != 12) {
			correcto = false;
			throw new IllegalArgumentException ("El UPC introducido tiene un número de dígitos distinto a 12");
		}else {
			long control = Long.parseLong(upc) % 10;
			char [] nums = upc.toCharArray();
			long suma_impares = 0;
			long suma_pares = 0;
			long suma = 0;
			long resto = 0;
			for (int i = 0; i < 11; i++) {
				if (i % 2 == 0) {
					//como el array empieza por 0, lo hago al reves
					suma_impares = suma_impares + (long) nums[i];
				}else {
					suma_pares = suma_pares + (long) nums [i];
				}
			}
			suma_impares = suma_impares * 3;
			suma = suma_impares + suma_pares;	
			resto = suma % 10;
			suma = suma + (10 - resto);
			
			if (control == suma/10) {
				correcto = true;
			}
		}
		return correcto;
	}
	
	/**
	 * Devuelve la información de un producto en forma de cadena de caracteres
	 *  @return el producto como String ordenado
	 */
	public String toString(){
		  return "Nombre del producto: "+getNombre()+", precio: "+getPrecio()+" euros, UPC: "+getUPC()+", cantidad del producto:  "+getCantidad()+ ", fecha de caducidad: "+getFecha();
	  }
	
	/**
	 * Consulta el valor del precio del producto que llama al método
	 * @return precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Actualiza el valor del precio del producto que llama al método
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Consulta la fecha de consumo preferente del producto que llama al método
	 * @return fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Actualiza la fecha de consumo preferente del producto que llama al método
	 * @throws IllegalArgumentException si la fecha es anterior al día de hoy
	 * @param fecha
	 */
	public void setFecha(LocalDate fecha) {
		LocalDate hoy = LocalDate.now();
		if(fecha.isBefore(hoy) == false) {
		this.fecha = fecha;
		}else {
			throw new IllegalArgumentException("La fecha no puede ser anterior al día de hoy");
		}
	}

	/**
	 * Consulta el nombre del producto que llama al método
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el nombre del producto que llama al método
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException ("El nombre solo puede contener letras");
		}
		for (int x = 0; x < nombre.length(); x++) {
            char c = nombre.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                throw new IllegalArgumentException ("El nombre solo puede contener letras");
            }
        }
        this.nombre = nombre;
	}

	/**
	 * Consulta el UPC del producto que llama al método
	 * @return UPC
	 */
	public String getUPC() {
		return UPC;
	}

	/**
	 * Actualiza el UPC del producto que llama al método
	 * @param uPC
	 */
	public void setUPC(String uPC) { 
		if(upc(uPC) == true) {
			UPC = uPC;
		}else {throw new IllegalArgumentException("El upc introducido no es correcto");}
	}

	/**
	 * Consulta la cantidad del producto
	 * @return cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Actualiza la cantidad del producto cierta cantidad dada
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		if (cantidad >= 0) {
		this.cantidad = cantidad;
		}else {
			throw new IllegalArgumentException ("La cantidad de un producto no puede ser negativa");
		}
	}

}
