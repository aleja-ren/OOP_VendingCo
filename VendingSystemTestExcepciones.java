package vendingCo;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Clase de prueba de las excepciones lanzadas por los métodos definidos en la clase VendingSystem.
 * 
 * @author vicsand
 * @author alegavi
 *
 */


public class VendingSystemTestExcepciones {

	VendingMachine maquina = new VendingMachine(57,true);
	VendingSystem sistema = new VendingSystem();
	ArrayList<VendingMachine> maquinas;
	
	@Test (expected = IllegalArgumentException.class)
	public void ModificarEstadoNo() {
		int id = 35;
		Boolean estado = true;
		sistema.NuevaMaquina(id, estado);
		@SuppressWarnings("unused")
		VendingMachine maqu = (sistema.getMaquinaId(id));
		sistema.ModificarEstado(45);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void EliminarMaquinaNo() {
		int id = 35;
		Boolean estado = true;
		sistema.NuevaMaquina(id, estado);
		@SuppressWarnings("unused")
		VendingMachine maqu = (sistema.getMaquinaId(id));
		sistema.EliminarMaquina(45);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getMaquinaID() {
		int id = 35;
		Boolean estado = true;
		sistema.NuevaMaquina(id, estado);
		@SuppressWarnings("unused")
		VendingMachine maqu = (sistema.getMaquinaId(id));
		sistema.getMaquinaId(45);
	
	}
	
	
}
