package proyecto.RedSocial.proyecto.model.Entity;

import java.io.Serializable;
import java.sql.Blob;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import proyecto.RedSocial.proyecto.Interfaces.IUser;

@Entity(name = "user")
@Table(name = "USER")
public class User implements IUser, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@Column(name = "nombre")
	// @UniqueConstraint(name="NOMBRE")
	protected String nombre;

	@Column(name = "password")
	protected String password;

	@Lob
	@Column(name = "avatar", columnDefinition = "Blob")
	protected Blob avatar;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	List<Post> postUser;
	// @ManyToOne(cascade = CascadeType.ALL)

	@JoinTable(name = "FOLLOW", joinColumns = @JoinColumn(name = "id_follower"), inverseJoinColumns = @JoinColumn(name = "id_followed"))
	@ManyToMany
	private List<User> follower;

	@ManyToMany(mappedBy = "follower")
	private List<User> followed;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "LIKES", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_post") })
	protected List<User> like;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> post;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> postComments;

	public User() {
		this(0, "", "", null);
	}

	public User(int id, String nombre, String password, Blob avatar) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUserLikes() {
		return like;
	}

	public void setUserLikes(List<User> userLikes) {
		if (userLikes == null)
			return;
		for (User like : userLikes) {
			this.addUserLikes(like);
		}
		;
	}

	public boolean addUserLikes(User like) {
		boolean result = false;
		if (this.like == null) {
			this.like = new ArrayList<User>();
			this.like.add(like);
			result = true;
		} else {
			this.like.add(like);
			result = true;
		}
		return result;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> posts) {
		if (posts == null)
			return;
		for (Post p : posts) {
			this.addPosts(p);
		}
		;
	}

	public boolean addPosts(Post p) {
		boolean result = false;
		if (this.post == null) {
			this.post = new ArrayList<Post>();
			this.post.add(p);
			result = true;
		} else {
			this.post.add(p);
			result = true;
		}
		return result;
	}

	public List<Comment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<Comment> postComments) {
		if (postComments == null)
			return;
		for (Comment pc : postComments) {
			this.addPostsComments(pc);
		}
		;
	}

	public boolean addPostsComments(Comment c) {
		boolean result = false;
		if (this.postComments == null) {
			this.postComments = new ArrayList<Comment>();
			this.postComments.add(c);
			result = true;
		} else {
			this.postComments.add(c);
			result = true;
		}
		return result;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Blob getAvatar() {
		return avatar;
	}

	public void setAvatar(Blob avatar) {
		this.avatar = avatar;
	}

	public List<Post> getPostUser() {
		return postUser;
	}

	public void setPostUser(List<Post> postUser) {
		this.postUser = postUser;
	}

	public List<User> getFollowed() {
		return followed;
	}

	public void setFollowed(List<User> followed) {
		if (followed == null)
			return;
		for (User u : followed) {
			this.addFollowed(u);
		}
		;
	}

	public List<User> getFollower() {
		return follower;
	}

	public void setFollower(List<User> follower) {
		if (follower == null)
			return;
		for (User u : follower) {
			this.addFollowers(u);
		}
		;
	}

	public boolean addFollowers(User u) {
		boolean result = false;
		if (this.follower == null) {
			this.follower = new ArrayList<User>();
			this.follower.add(u);
			result = true;
		} else {
			this.follower.add(u);
			result = true;
		}
		return result;
	}

	public boolean addFollowed(User u) {
		boolean result = false;
		if (this.followed == null) {
			this.followed = new ArrayList<User>();
			this.followed.add(u);
			result = true;
		} else {
			this.followed.add(u);
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", password=" + password + ", avatar=" + avatar + ", postUser="
				+ postUser + ", follower=" + follower + ", followed=" + followed + ", like=" + like + ", post=" + post
				+ ", postComments=" + postComments + "]";
	}

	

}
