package proyecto.RedSocial.proyecto.model.Entity;


import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import proyecto.RedSocial.proyecto.Interfaces.IPost;

@Entity(name = "post")
@Table(name = "POST")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="fecha")
	protected Timestamp fecha;
	
	@Column(name="texto")
	protected String txt;
	
	@JoinColumn(name = "id_usuario")
	@Lob
	@Column(name="multimedia",columnDefinition = "Blob")
	protected Blob multimedia;
	
	@ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	protected User user;
	
	//@ManyToMany(mappedBy = "like")
    //private List<Post> postsLikes;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
	protected List<Comment> comments;
	//List<Post> likesPost;

	public Post(int id, Timestamp fecha, String txt, Blob multimedia, User idUsuario) {
		this.id = id;
		this.fecha = fecha;
		this.txt = txt;
		this.multimedia = multimedia;
		this.user = idUsuario;
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
		return user;
	}

	public void setLikes(User idUsuario) {
		this.user = idUsuario;
	}
	
	public User getIdUsuario() {
		return user;
	}

	public void setIdUsuario(User idUsuario) {
		this.user = idUsuario;
	}

	public List<Post> getPostsLikes() {
		return postsLikes;
	}

	public void setPostsLikes(List<Post> postsLikes) {
		if (postsLikes == null) return;
		for (Post pl: postsLikes) {
			this.addPostLikes(pl);
		};
	}
	public boolean addPostLikes(Post pl) {
		boolean result = false;
		if(this.postsLikes == null) {
			this.postsLikes = new ArrayList<Post>();
			this.postsLikes.add(pl);
			result = true;
		} else {
			this.postsLikes.add(pl);
			result = true;
		}
		return result;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		if (comments == null) return;
		for (Comment c: comments) {
			this.addUserComments(c);
		};
	}
	
	public boolean addUserComments(Comment c) {
		boolean result = false;
		if (this.comments == null) {
			this.comments = new ArrayList<Comment>();
			this.comments.add(c);
			result = true;
		} else {
			this.comments.add(c);
			result = true;
		}
		return result;
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
				+ user + ", userLikes=" + postsLikes + ", comments=" + comments + "]";
	}

	

}
