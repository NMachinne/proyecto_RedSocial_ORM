package proyecto.RedSocial.proyecto.Interfaces;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Like;
import proyecto.RedSocial.proyecto.model.Entity.User;

public interface IPost {
	public int getId();

	public void setId(int id);

	public Timestamp getFecha();

	public void setFecha(Timestamp fecha);

	public String getTxt();

	public void setTxt(String txt);

	public Blob getMultimedia();

	public void setMultimedia(Blob multimedia);

	public User getLikes();

	public void setLikes(User likes);

}