package proyecto.RedSocial.proyecto.model.Entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.IPost;

@Entity
@Table(name = "POST")
public class Post implements IPost,Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	protected int id;
	@Column(name="FECHA")
	protected String fecha;
	@Column(name="TXT")
	protected String txt;
	@Column(name="MULTIMEDIA")
	protected String multimedia;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_USUARIO")
	protected User likes;
	protected Comment comment;

	public Post(int id, String fecha, String txt, String multimedia, User likes, Comment comment) {
		this.id = id;
		this.fecha = fecha;
		this.txt = txt;
		this.multimedia = multimedia;
		this.likes = likes;
		this.comment = comment;
	}

	public Post() {
		this(-1,"","","",null,null);
	}
	
	public Post(int id, String fecha, String txt, String multimedia) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}

	public User getLikes() {
		return likes;
	}

	public void setLikes(User likes) {
		this.likes = likes;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
				+ likes + ", comment=" + comment + "]";
	}

}
