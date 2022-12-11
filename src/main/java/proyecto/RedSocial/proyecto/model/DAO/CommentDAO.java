package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import proyecto.RedSocial.proyecto.model.Entity.Comment;

public class CommentDAO extends ADAO<Comment>{
	//Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO comment (id_usuario,id_publicacion,fecha,texto) VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE comment SET fecha=?,texto=? WHERE id=?";
	private final static String DELETE = "DELETE FROM comment WHERE id=?";
	private final static String SELECTBYID = "SELECT id_usuario,id_publicacion,fecha,texto FROM comment WHERE id_publicacion=?";
	private final static String SELECTALL = "SELECT id_usuario,id_publicacion,fecha,texto FROM comment";
	// Fin de las consultas
	
	public void save(Comment comment) {
		try {
			super.create(comment, INSERT, new Integer[]{0, 1, 2, 3});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<Comment> getById(Comment comment) {
		try {
			Collection<Comment> c = super.read(comment, SELECTBYID, new Integer[]{1});
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Comment> getAll() {
		try {
			Collection<Comment> c = super.read(new Comment(), SELECTALL, null);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Comment comment) {
		try {
			super.update(comment, UPDATE, new Integer[]{0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Comment comment) {
		try {
			super.delete(comment, DELETE, new Integer[]{0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
