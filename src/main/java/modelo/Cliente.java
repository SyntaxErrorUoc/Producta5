package com.onlinestore.onlinestore_producto5.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

/**
 * @author SyntaxError
 * @version 2.0.1
 */

@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoVip",discriminatorType = DiscriminatorType.STRING)
public abstract class Cliente {
	@Id
	@Column(name = "mail")
	private String correoElectronico;
	@Column(name ="nombre")
	private String nombre;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "apellidos")
	private String apellidos;


	/**
	 * Esta parte sirve para poder modificar el cambio TipoVip y que siempre quede actualizado.
	 */
	@Formula("(CASE WHEN vip = 1 THEN 'UNO' ELSE 'CERO' END)")
	private String TipoVip;
	public abstract int getVip();
	protected abstract int setVip();

	/**
	 * Constructor de la clase con 3 prámetros
	 * @param correoElectronico recibe un tipo String
	 * @param nombre recibe un tipo String
	 * @param direccion recibe un tipo String
	 */
	public Cliente(String correoElectronico, String nombre, String direccion) {
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.direccion = direccion;

	}

	/**
	 * Constructor de la clase vacío
	 */
	public Cliente() {
	}

	/**
	 * Getter de TipoVip
	 * @return valor en TipoVip
	 */
	public String getTipoVip() {
		return TipoVip;
	}

	/**
	 * Setter para TipoVip
	 * @param tipoVip valor para TipoVip
	 */
	public void setTipoVip(String tipoVip) {
		TipoVip = tipoVip;
	}

	/**
	 * Getter para Apellidos del cliente.
	 * @return Apellidos del cliente.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Setter para apellidos del cliente
	 * @param apellidos Corresponde a los apellidos del cliente.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Getter de Correo Electronico
	 * @return devuelve un tipo String
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Getter de Nombre
	 * @return devuelve un tipo String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter de Direccion
	 * @return devuelve un tipo String
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Setter de Correo electrónico
	 * @param correoElectronico recibe un tipo Strind
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Setter de nombre
	 * @param nombre recibe un tipo String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Setter de direccion
	 * @param direccion recibe un tipo String
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo abstracto de tipoCliente
	 * @return devuelve un String
	 */
	public abstract String tipoCliente();

	/**
	 * Metodo abstracto de Calculo Anual
	 * @return devuelve un double
	 */
	public abstract double calcAnual();

	/**
	 * Metodo abstracto de descuento envio
	 * @return devuelve un tipo double
	 */
	public abstract double descuentoEnv();

	public void mapVipToString(int vip) {
		System.out.println(vip);
		if (vip == 1) { setTipoVip("UNO"); }else{ setTipoVip("CERO");};

	}

	/**
	 * Método para mostrar en pantalla los datos del cliente.
	 * @return string con la información
	 */
	@Override
	public String toString() {
		return "\n\n-----------\n"+"Cliente :" + nombre +"\n-----------"+ "\nCorreo electronico : "+correoElectronico+"\nDireccion fiscal: " +  direccion + "\n";
	}



}