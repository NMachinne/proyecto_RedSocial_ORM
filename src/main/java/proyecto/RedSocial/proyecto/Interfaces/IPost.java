package proyecto.RedSocial.proyecto.Interfaces;

import java.util.List;

import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.User;

public interface IPost {
	public int getId();

	public void setId(int id);

	public String getFecha();

	public void setFecha(String id);

	public String getTxt();

	public void setTxt(String txt);

	public String getMultimedia();

	public void setMultimedia(String multimedia);

	public User getLikes();

	public void setLikes(User likes);

	public Comment getComment();

	public void setComment(Comment comment);
}