package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
@Entity
@DiscriminatorValue("CERO")
public class ClienteEstandar extends Cliente {


	/**
	 * Constructor de la clase heredada de cliente
	 * @param correoElectronico recibe un String
	 * @param nombre recibe un String
	 * @param direccion recibe un String
	 */
	@Column(name = "descuento")
	private double descuento = 0.0;
	@Column(name = "cuotaAnual")
	private int cuotaAnual = 0;
	@Column(name = "vip")
	private int vip  = 0;


	/**
	 * Método para crear un cliente estandar
	 * @param correoElectronico identificador único de la tabla
	 * @param nombre nombre del cliente
	 * @param direccion dirección del cliente
	 */
	public ClienteEstandar(String correoElectronico, String nombre, String direccion) {
		super(correoElectronico, nombre, direccion);


	}

	/**
	 * Constructor vacío necesario para Hibernate.
	 */
	public ClienteEstandar() {

	}

	/**
	 * Getter para vip
	 * @return valor en vip
	 */
	public int getVip() {
		return vip;
	}

	/**
	 * Método para el valor de vip
	 * @return valor en vip
	 */
	@Override
	protected int setVip() {
		return this.vip;
	}

	/**
	 * Setter para vip
	 * @param vip valor en vip
	 */
	public void setVip(int vip) {
		this.vip = vip;
	}



	/**
	 * Implementación de la clase abstracta tipoCliente
	 * @return devuelve un tipo String
	 */

	@Override
	public String tipoCliente() {
		
		return "Estandar";
	}

	/**
	 * Implementación de la clase abstracta calcAnual
	 * @return devuelve un tipo double
	 */
	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 0.0;
		return cuota;
	}
	/**
	 * Implementación de la clase abstracta descuentoEnv
	 * @return devuelve un tipo double
	 */
	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.0;
		return descuentoE;
	}

	/**
	 * Método que muestra por pantalla los datos del cliente estantar.
	 * @return cadena con el resultado.
	 */
	@Override
	public String toString() {
		return super.toString() + "CLIENTE STANDARD\n";
	}

	public int getCuotaAnual() {
		return cuotaAnual;
	}
}
