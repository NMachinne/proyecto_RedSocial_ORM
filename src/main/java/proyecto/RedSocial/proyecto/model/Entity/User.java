package proyecto.RedSocial.proyecto.model.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import proyecto.RedSocial.proyecto.Interfaces.IUser;

@Entity
@Table(name = "USER",uniqueConstraints=
@UniqueConstraint(columnNames={"NOMBRE"})
)
public class User implements IUser,Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	protected int id;
	@Column(name="NOMBRE")
	protected String nombre;
	@Column(name="PASSWORD")
	protected String password;
	@Column(name="AVATAR")
	protected String avatar;

	
	public User() {
		this(-1,"","","");
	}
	
	public User(int id, String nombre, String password, String avatar) {
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
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
