package vendingCo;

import java.util.ArrayList;


public class VendingSystem{

	/**
	 * Esta clase define el sistema de m�quinas y los m�todos necesarios para garantizar su funcionamiento.
	 * Tiene asociada la lista de m�quinas del sistema y una lista de aquellas que tienen una l�nea vac�a.
	 * 
	 * @author vicsand
	 * @author alegavi
	 */
	
	
	private ArrayList<VendingMachine> maquinas; 
	private ArrayList<VendingMachine> maquinasLineaVacia = new ArrayList<VendingMachine>();
	
	/**
	 * Construye un sistema de m�quinas de vending, donde se almacenan las m�quinas gestionadas.
	 * 
	 */
	public VendingSystem() {
		maquinas = new ArrayList<VendingMachine>();
	}
 
	/**
	 * A�ade una nueva m�quina al sistema de m�quinas que tiene la empresa dado su identificador y su estado.
	 * @param identificador �nico para cada m�quina
	 * @param estado operativa o no
	 */
	public void NuevaMaquina(int id, boolean estado) {
		VendingMachine maq = new VendingMachine(id, estado);
		maquinas.add(maq);	
	}
	
	/**
	 * Modifica el estado de la m�quina dado su identificador.
	 * @param identificador de la m�quina deseada
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
	 * Elimina una m�quina del sistema dado su identificador.
	 * @param identificador de la m�aquina deseada
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
	 * Consulta el n�mero de m�quinas operativas del sistema.
	 * @return contador (n�mero de m�quinas activas)
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
	 * Devuelve la lista de las m�quinas que gestiona la empresa, siendo cada m�quina caracterizada por su id y su estado.
	 * @return lista de maquinas
	 */
	public ArrayList<VendingMachine> ListaMaquinas() {
		return maquinas;
	}
	
	/**
	 * Comprueba si hay alguna m�quina en el sistema que tenga alguna linea vac�a.
	 */
	public void comprobarMaquinasLineaVacia(){
		for (int i = 0; i < maquinas.size(); i++) {
			if(maquinas.get(i).lineaVacia() == true) {
				maquinasLineaVacia.add(maquinas.get(i));
			}
		}
	}
		
	/**
	 * Devuelve una lista de las m�quinas con alguna linea vac�a.
	 * @return maquinasLineaVacia
	 */
	public ArrayList<VendingMachine> maquinasLineasVacias(){
		comprobarMaquinasLineaVacia();
		return maquinasLineaVacia;
	}		
	
	/**
	 * Devuelve una maquina del sistema dado su identificador.
	 * @param identificador de la m�quina deseada
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


