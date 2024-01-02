package com.onlinestore.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
@Entity
@DiscriminatorValue("UNO")
public class ClientePremium extends Cliente{
	@Column(name = "descuento")
	private double descuento;
	@Column(name = "cuotaAnual")
	private int cuotaAnual = 10;

	@Column(name = "vip")
	private int vip  = 1;

	/**
	 * Constructor de la clase heredada de cliente
	 * @param correoElectronico recibe un String
	 * @param nombre recibe un String
	 * @param direccion recibe un String
	 */
	public ClientePremium(String correoElectronico, String nombre, String direccion, double descuento) {
		super(correoElectronico, nombre, direccion);
		this.descuento = descuento;


	}

	/**
	 * Método vacío necesario para Hibernate
	 */
	public ClientePremium() {

	}

	/**
	 * Getter para vip
	 * @return valor de vip
	 */
	public int getVip() {
		return vip;
	}

	/**
	 * Método para el valor de vip
	 * @return valor de vip
	 */
	@Override
	protected int setVip() {
		return this.vip;
	}

	/**
	 * Setter para vip
	 * @param vip valor de vip
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
		return "Premium";
	}

	/**
	 * Implementación de la clase abstracta calcAnual
	 * @return devuelve un tipo double
	 */
	@Override
	public double calcAnual() {
		// Implementación del cálculo de la cuota anual para un cliente premium.
		double cuota = 30.0;
		return cuota;
	}

	public double getDescuento() {
		return descuento;
	}

	public int getCuotaAnual() {
		return cuotaAnual;
	}

	/**
	 * Implementación de la clase abstracta descuentoEnv
	 * @return devuelve un tipo double
	 */
	@Override
	public double descuentoEnv() {
		// Implementación del descuento de gastos de envío para un cliente premium.
		double descuentoE = 0.2;
		return descuentoE;
	}

	/**
	 * Método que muestra los datos del cliente Premium
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString() + "CLIENTE PREMIUM " + "y su descuento es " + this.descuento+"\n";
	}
}
