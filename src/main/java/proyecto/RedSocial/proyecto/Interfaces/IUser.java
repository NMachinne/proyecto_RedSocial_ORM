package proyecto.RedSocial.proyecto.Interfaces;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

import java.sql.Blob;

import proyecto.RedSocial.proyecto.model.Entity.Comment;

public interface IUser {
	public int getId();

	public void setId(int id);

	public String getNombre();

	public void setNombre(String nombre);

	public String getPassword();

	public void setPassword(String password);

	public Blob getAvatar();

	public void setAvatar(Blob avatar);
}
