package proyecto.RedSocial.proyecto.model.Entity;

import proyecto.RedSocial.proyecto.Interfaces.IFollow;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FOLLOW")
public class Follow implements  Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	protected int id;
	@Id
	@OneToMany(mappedBy = "follow",cascade = CascadeType.ALL)
	protected User idUsuario;

	public Follow(int id, User idUsuario) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
	}
	
	public Follow() {
		this(-1,null);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<User>  getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(List<User> idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}