package proyecto.RedSocial.proyecto.Interfaces;

import java.sql.Timestamp;

import proyecto.RedSocial.proyecto.model.Entity.Post;

public interface ILike {
	public int getId();

	public void setId(int id);

	public Post getIdPublicacion();

	public void setIdPublicacion(Post idPublicacion);

	public Timestamp getFecha();

	public void setFecha(Timestamp fecha);

}
