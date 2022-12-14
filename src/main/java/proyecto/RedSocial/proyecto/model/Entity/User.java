package proyecto.RedSocial.proyecto.model.Entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "user")
public class User implements IUser, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
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
	
	//@OneToMany
	//@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    //@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    //@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	/*private List<User> following;
    @ManyToMany(mappedBy = "following", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> followed;
	//List<User> misFollower;*/
  
    
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "follow", joinColumns = @JoinColumn(name = "id_follower"), inverseJoinColumns = @JoinColumn(name = "id_followed"))
    private List<User> followed;
    @ManyToMany(mappedBy = "followed", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<User> follower;
    
	public User() {
		this(-1, "", "", null);
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
		return "User [id=" + id + ", nombre=" + nombre + ", password=" + password + ", avatar=" + avatar + "]";
	}

}
