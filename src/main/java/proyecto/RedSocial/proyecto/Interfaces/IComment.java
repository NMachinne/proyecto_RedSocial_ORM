package proyecto.RedSocial.proyecto.Interfaces;


import java.sql.Timestamp;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public interface IComment {

	public Timestamp getFecha();

	public void setFecha(Timestamp fecha);

	public String getTxt();

	public void setTxt(String txt);

	public Post getPost();

	public void setPost(Post post);

	public User getUser();

	public void setUser(User user);
}
