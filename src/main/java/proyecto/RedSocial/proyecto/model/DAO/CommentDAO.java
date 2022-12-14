package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.Persistence;
import javax.persistence.Query;

import proyecto.RedSocial.proyecto.model.Entity.Comment;


public class CommentDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYID = "FROM comment WHERE id_publicacion=?";
	private final static String SELECTALL = "FROM comment";

	// Fin de las consultas
	public CommentDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}

	public void save(Comment comment) {
		manager.getTransaction().begin();
		manager.persist(comment);
		manager.getTransaction().commit();
	}

	public Collection<Comment> getById(Comment comment) {
		Collection<Comment> u = null;
		Query query = manager.createQuery(SELECTBYID);
		query.setParameter(1, comment.getPost().getId());
		u = query.getResultList();
		return u;
	}


	public Collection<Comment> getAll() {
		Collection<Comment> u = null;
		u = manager.createQuery(SELECTALL).getResultList();
		return u;
	}

	public void update(Comment comment) {
		manager.getTransaction().begin();
		manager.merge(comment);
		manager.getTransaction().commit();
	}

	public void delete(Comment comment) {
		manager.getTransaction().begin();
		manager.remove(comment);
		manager.getTransaction().commit();
	}

}
