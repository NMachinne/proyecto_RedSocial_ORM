package proyecto.RedSocial.proyecto.model.DAO;

import java.util.Collection;
import javax.persistence.Persistence;
import javax.persistence.Query;

import proyecto.RedSocial.proyecto.model.Entity.Post;

public class PostDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYID = "FROM post WHERE id=?1";
	private final static String SELECTBYID_USER = "FROM post WHERE id_usuario=?1 ORDER BY id DESC LIMIT 1";
	private final static String SELECTALL = "FROM post";
	private final static String SELECTALLBYID_USER = "FROM post WHERE id_usuario=?1";
	// Fin de las consultas
	
	public PostDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}

	public void save(Post post) {
		manager.getTransaction().begin();
		manager.persist(post);
		manager.getTransaction().commit();
	}

	public Collection<Post> getById(Post post) {
		Collection<Post> p = null;
		Query query = manager.createQuery(SELECTBYID);
		query.setParameter(1, post.getId());
		p = query.getResultList();
		return p;
	}
	
	public Collection<Post> getAllByIdUser(Post post){
		Collection<Post> p = null;
		Query query = manager.createQuery(SELECTALLBYID_USER);
		query.setParameter(1, post.getId());
		p = query.getResultList();
		return p;
	}
	
	public Collection<Post> getByIdUser(Post post) {
		Collection<Post> p = null;
		Query query = manager.createQuery(SELECTBYID_USER);
		query.setParameter(1, post.getFecha());
		p = query.getResultList();
		return p;
	}

	public Collection<Post> getAll() {
		Collection<Post> p = null;
		p = manager.createQuery(SELECTALL).getResultList();
		return p;
	}

	public void update(Post post) {
		manager.getTransaction().begin();
		manager.merge(post);
		manager.getTransaction().commit();
	}

	public void delete(Post post) {
		manager.getTransaction().begin();
		manager.remove(post);
		manager.getTransaction().commit();
	}
}
