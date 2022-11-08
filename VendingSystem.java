package vendingCo;

import java.util.ArrayList;


public class VendingSystem{

	/**
	 * Esta clase define el sistema de máquinas y los métodos necesarios para garantizar su funcionamiento.
	 * Tiene asociada la lista de máquinas del sistema y una lista de aquellas que tienen una línea vacía.
	 * 
	 * @author vicsand
	 * @author alegavi
	 */
	
	
	private ArrayList<VendingMachine> maquinas; 
	private ArrayList<VendingMachine> maquinasLineaVacia = new ArrayList<VendingMachine>();
	
	/**
	 * Construye un sistema de máquinas de vending, donde se almacenan las máquinas gestionadas.
	 * 
	 */
	public VendingSystem() {
		maquinas = new ArrayList<VendingMachine>();
	}
 
	/**
	 * Añade una nueva máquina al sistema de máquinas que tiene la empresa dado su identificador y su estado.
	 * @param identificador único para cada máquina
	 * @param estado operativa o no
	 */
	public void NuevaMaquina(int id, boolean estado) {
		VendingMachine maq = new VendingMachine(id, estado);
		maquinas.add(maq);	
	}
	
	/**
	 * Modifica el estado de la máquina dado su identificador.
	 * @param identificador de la máquina deseada
	 * @throws IllegalArgumentException si no existe una maquina con ese identificador
	 */
	public void ModificarEstado(int id) {
		for(int i = 0; i < maquinas.size(); i++) {
			if(maquinas.get(i).getId() == id ) {
				maquinas.get(i).setEstado(!maquinas.get(i).getEstado());
			}else {
				throw new IllegalArgumentException ("no existe una maquina con ese identificador");
			}
		}
	}
	
	/**
	 * Elimina una máquina del sistema dado su identificador.
	 * @param identificador de la mñaquina deseada
	 * @throws IllegalArgumentException si no existe una maquina con ese identificador
	 */
	public void EliminarMaquina (int id) {
		for(int i = 0; i< maquinas.size(); i++) {
			if(maquinas.get(i).getId() == id ) {
				maquinas.remove(i);
			}else {
				throw new IllegalArgumentException ("no existe una maquina con ese identificador");
			}
		}
	}
	
	/**
	 * Consulta el número de máquinas operativas del sistema.
	 * @return contador (número de máquinas activas)
	 */
	public int operativas() {
		int contador = 0;
		for(int i = 0; i< maquinas.size(); i++) {
			if(maquinas.get(i).getEstado() == true) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Devuelve la lista de las máquinas que gestiona la empresa, siendo cada máquina caracterizada por su id y su estado.
	 * @return lista de maquinas
	 */
	public ArrayList<VendingMachine> ListaMaquinas() {
		return maquinas;
	}
	
	/**
	 * Comprueba si hay alguna máquina en el sistema que tenga alguna linea vacía.
	 */
	public void comprobarMaquinasLineaVacia(){
		for (int i = 0; i < maquinas.size(); i++) {
			if(maquinas.get(i).lineaVacia() == true) {
				maquinasLineaVacia.add(maquinas.get(i));
			}
		}
	}
		
	/**
	 * Devuelve una lista de las máquinas con alguna linea vacía.
	 * @return maquinasLineaVacia
	 */
	public ArrayList<VendingMachine> maquinasLineasVacias(){
		comprobarMaquinasLineaVacia();
		return maquinasLineaVacia;
	}		
	
	/**
	 * Devuelve una maquina del sistema dado su identificador.
	 * @param identificador de la máquina deseada
	 * @throws IllegalArgumentException si no existe una maquina con ese identificador
	 * @return maquina
	 */
	public VendingMachine getMaquinaId(int id) {
		VendingMachine maquina = null;
		for (int i = 0; i < maquinas.size(); i++) {
			if(maquinas.get(i).getId() == id) {
				maquina = maquinas.get(i);
			}else {
				throw new IllegalArgumentException ("si no existe una maquina con ese identificador");
			}
			
		}
		return maquina;
	}
  
	
}


