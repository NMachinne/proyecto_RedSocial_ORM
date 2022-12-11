package proyecto.RedSocial.proyecto.model.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.ILike;

@Entity
@Table(name = "LIKE")
public class Like implements ILike,Serializable{
	private static final long serialVersionUID = 1L;
	@Id	
	protected int id;
	@OneToOne(mappedBy = "like", cascade = CascadeType.ALL)
	@JoinColumn(name="ID_PUBLICACION")
	protected int idPublicacion;
	@Column(name="FECHA")
	protected String fecha;
	
	public Like(int id, int idPublicacion, String fecha) {
		super();
		this.id = id;
		this.idPublicacion = idPublicacion;
		this.fecha = fecha;
	}
	
	public Like() {
		this(-1,-1,null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	
	
}