package proyecto.RedSocial.proyecto.model.Entity;


import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.IPost;

@Entity
@Table(name = "POST")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	protected int id;
	@Column(name="FECHA")
	protected Timestamp fecha;
	@Column(name="TEXTO")
	protected String txt;
	@Lob
	@Column(name="MULTIMEDIA",columnDefinition = "Blob")
	protected Blob multimedia;
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="ID_USUARIO")
	@Column(name="ID_USUARIO")
	protected int likes;

	public Post(int id, Timestamp fecha, String txt, Blob multimedia, int likes, Comment comment) {
		this.id = id;
		this.fecha = fecha;
		this.txt = txt;
		this.multimedia = multimedia;
		this.likes = likes;
	}

	public Post() {
		this(-1,null,"",null,-1,null);
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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
		return "Post [id=" + id + ", fecha=" + fecha + ", txt=" + txt + ", multimedia=" + multimedia + ", likes="
				+ likes + ", comment=" + "]";
	}

}
