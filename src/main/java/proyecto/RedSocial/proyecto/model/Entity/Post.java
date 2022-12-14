package proyecto.RedSocial.proyecto.model.Entity;


import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.IPost;

@Entity(name = "post")
@Table(name = "post")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	protected int id;
	@Column(name="fecha")
	protected Timestamp fecha;
	@Column(name="texto")
	protected String txt;
	@Lob
	@Column(name="multimedia",columnDefinition = "Blob")
	protected Blob multimedia;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	protected User idUsuario;
	
	//List<Post> likesPost;

	public Post(int id, Timestamp fecha, String txt, Blob multimedia, User idUsuario) {
		this.id = id;
		this.fecha = fecha;
		this.txt = txt;
		this.multimedia = multimedia;
		this.idUsuario = idUsuario;
	}

	public Post() {
		this(0,null,"",null,null);
	}
	
	public Post(int id, Timestamp fecha, String txt, Blob multimedia) {
		this.id = id;
		this.fecha = fecha;
		this.txt = txt;
		this.multimedia = multimedia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Blob getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Blob multimedia) {
		this.multimedia = multimedia;
	}

	public User getLikes() {
		return idUsuario;
	}

	public void setLikes(User idUsuario) {
		this.idUsuario = idUsuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", fecha=" + fecha + ", txt=" + txt + ", multimedia=" + multimedia + ", idUsuario="
				+ idUsuario + "]";
	}

}
