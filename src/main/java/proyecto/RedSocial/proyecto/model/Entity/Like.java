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

@Entity(name = "likes")
@Table(name = "likes")
public class Like implements ILike,Serializable{
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "id_usuario")
	protected int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_publicacion")
	protected Post idPublicacion;
	@Column(name="fecha")
	protected Timestamp fecha;
	
	public Like(int id, Post idPublicacion, Timestamp fecha) {
		super();
		this.id = id;
		this.idPublicacion = idPublicacion;
		this.fecha = fecha;
	}
	
	public Like() {
		this(-1,null,null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Post getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Post idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	
	
	
}