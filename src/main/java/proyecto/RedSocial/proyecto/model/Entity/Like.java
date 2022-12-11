package proyecto.RedSocial.proyecto.model.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.ILike;

@Entity
@Table(name = "LIKES")
public class Like implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "ID_USUARIO")
	protected int id;
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="ID_PUBLICACION")
	@Column(name="ID_PUBLICACION")
	protected int idPublicacion;
	@Column(name="FECHA")
	protected Timestamp fecha;
	
	public Like(int id, int idPublicacion, Timestamp fecha) {
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

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	
	
	
}