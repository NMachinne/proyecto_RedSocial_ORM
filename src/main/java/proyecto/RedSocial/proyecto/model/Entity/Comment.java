package proyecto.RedSocial.proyecto.model.Entity;



import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import proyecto.RedSocial.proyecto.Interfaces.IComment;


@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="FECHA")
	protected Timestamp fecha;
	@Column(name="TEXTO")
	protected String txt;
	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name="ID_USUARIO")
	protected int user;
	//@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
	@Column(name="ID_PUBLICACION")
	//protected List<Post> post;
	protected int post;

	public Comment(Timestamp fecha, String txt, int  user, int post) {

		this.fecha = fecha;
		this.txt = txt;
		this.user = user;
		this.post = post;
	}
	
	public Comment() {
		this(null,"",-1,-1);

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

	public int  getUser() {
		return user;
	}

	public void setUser(int  user) {
		this.user = user;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + post;
		result = prime * result + user;
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
		Comment other = (Comment) obj;
		if (post != other.post)
			return false;
		if (user != other.user)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [fecha=" + fecha + ", txt=" + txt + ", user=" + user + ", post=" + post + "]";
	}

}
