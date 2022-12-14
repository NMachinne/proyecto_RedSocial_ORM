package proyecto.RedSocial.proyecto.model.DAO;


import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Persistence;
import proyecto.RedSocial.proyecto.model.Entity.Comment;

public class CommentDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTALL = "FROM comment";
	// Fin de las consultas
	public CommentDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}

	public void save(Comment comment) {
		manager.persist(comment);
	}

	public Collection<Comment> getById(Comment comment) {
		Collection<Comment> c = new ArrayList<Comment>();
		Comment aux = manager.find(Comment.class, comment.getUser());
		try {
		c.add(aux);
		}
		catch (Exception e) {}
		manager.close();
		emf.close();
		return c;
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
