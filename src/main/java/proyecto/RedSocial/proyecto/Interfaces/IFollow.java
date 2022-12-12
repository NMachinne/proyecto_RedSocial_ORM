package proyecto.RedSocial.proyecto.Interfaces;

import java.util.List;

import proyecto.RedSocial.proyecto.model.Entity.User;

public interface IFollow {
	public int getId();

	public void setId(int id);

	public List<User> getIdUsuario();

	public void setIdUsuario(List<User> idUsuario);

}
