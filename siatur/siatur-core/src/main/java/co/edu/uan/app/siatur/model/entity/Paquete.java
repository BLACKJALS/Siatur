package co.edu.uan.app.siatur.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Paquete implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paq_id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "paq_version")
	private int version;

	@Column(name = "paq_ciudad", nullable = false)
	private String ciudad;
	
	@Column(name = "paq_hotel", nullable = false)
	private String hotel;
	
	@Column(name = "paq_comidas", nullable = false)
	private String comidas;

	@Column(name = "paq_precio", nullable = false)
	private double precio;
	
	@Column(name = "paq_sitios", nullable = false)
	private String sitios;
	
	@Column(name = "paq_editable", nullable = false)
	private Boolean editable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getComidas() {
		return comidas;
	}

	public void setComidas(String comidas) {
		this.comidas = comidas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getSitios() {
		return sitios;
	}

	public void setSitios(String sitios) {
		this.sitios = sitios;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paquete other = (Paquete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requisitos [Ciudad=" + ciudad + ", Hotel=" + hotel + ", Comidas=" + comidas + ", Precio=" + precio + ", Sitios=" + sitios + ", editable=" + editable + "]";
	}
	
	
}
