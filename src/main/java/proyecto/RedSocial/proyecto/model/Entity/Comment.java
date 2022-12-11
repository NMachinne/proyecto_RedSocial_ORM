package proyecto.RedSocial.proyecto.model.Entity;



import java.io.Serializable;
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
	protected String fecha;
	@Column(name="TEXTO")
	protected String txt;
	@ManyToOne(fetch = FetchType.LAZY)
	protected User user;
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
	@Column(name="ID_PUBLICACION")
	protected List<Post> post;

	public Comment(String fecha, String txt, User  user, List<Post> post) {

		this.fecha = fecha;
		this.txt = txt;
		this.user = user;
		this.post = post;
	}
	
	public Comment() {
		this("","",null,null);

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

	public User  getUser() {
		return user;
	}

	public void setUser(User  user) {
		this.user = user;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [fecha=" + fecha + ", txt=" + txt + ", user=" + user + ", post=" + post + "]";
	}

}
