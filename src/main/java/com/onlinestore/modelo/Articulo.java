package com.onlinestore.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Duration;

/**
 * @author SyntaxError
 * @version 2.0.1
 */
@Entity
@Table(name = "articulo")
public class Articulo {
	@Id
	@Column(name = "CP")
	private String codigo;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "Precio")
    private double precio;
	@Column(name = "TiempoPreparacion")
	private Duration tiempoPreparacion;



	/**
	 * Constructor de la clase con 4 prámetros
	 * @param codigo tipo String
	 * @param descripcion tipo String
	 * @param precio tipo double
	 * @param tiempoPreparacion tipo Duration
	 */
	// Constructor
	public Articulo(String codigo, String descripcion, double precio, Duration tiempoPreparacion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public Articulo() {

	}
	/**
     * Getter de Tiempo de preparación
     *
     * @return Devuelve un tipo Duration
     */
	public Duration getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	/**
	 * Setter de Tiempo de preparación
	 * @param tiempoPreparacion de tipo Duration
	 */
	public void setTiempoPreparacion(Duration tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	/**
	 * Getter de Código
	 * @return devuelve un tipo String
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Getter de Descripcion
	 * @return  devuelve un tipo String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Getter de Precio
	 * @return devuelve un double
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Setter de código
	 * @param codigo recibe un tipo String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Setter de descripcion
	 * @param descripcion recibe un tipo String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Setter de precio
	 * @param precio recibe un tipo double
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Convierte un valor de Duration a String para manejarlo mejor en la impresión en pantalla.
	 * @param duration duración en HH:MM:SS
	 * @return String formateado.
	 */
	public String convertirDurationToString(Duration duration){
		// Obtener una representación en formato ISO-8601
		String isoString = duration.toString();

		// Formatear la cadena según tus necesidades
		String formattedDuration = String.format(
				"%02d:%02d:%02d",
				duration.toHoursPart(),
				duration.toMinutesPart(),
				duration.toSecondsPart()
		);
		return formattedDuration;
	}

	/**
	 * Método para mostrar por pantalla la información del Artículo.
	 * @return
	 */
	@Override
	public String toString() {
		return "\n----------------"+"\nArticulo \n"+"--------------\n"+
				"codigo del producto :" + codigo + "\ndescripcion del producto :"
				+ descripcion + "\nPrecio del producto:" + precio
				+ "\nTiempo : "+ convertirDurationToString(tiempoPreparacion) + "\n";
	}




}
