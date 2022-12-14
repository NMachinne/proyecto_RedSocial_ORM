package proyecto.RedSocial.proyecto.model.DAO;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import proyecto.RedSocial.proyecto.model.Entity.Post;

public class PostDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYID_USER = "SELECT id,fecha,texto,multimedia FROM post WHERE id_usuario=?1 ORDER BY id DESC LIMIT 1";
	private final static String SELECTALL = "FROM post";
	// Fin de las consultas
	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public PostDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}

	public void save(Post post) {
		manager.getTransaction().begin();
		manager.persist(post);
		manager.getTransaction().commit();
		manager.close();
	}

	public Collection<Post> getById(Post post) {
		Collection<Post> p = new ArrayList<Post>();
		Post aux = manager.find(Post.class, post.getId());
		p.add(aux);
		return p;
	}
	
	public Collection<Post> getAllByIdUser(Post post){
		Collection<Post> p = new ArrayList<Post>();
		Post aux =manager.find( Post.class, post.getId());
		try {
		p = aux.getIdUsuario().getPost();
		}
		catch (Exception e) {}
		return p;
	}
	
	public Collection<Post> getByIdUser(Post post) {
		Collection<Post> p = null;
		Query query = manager.createNativeQuery(SELECTBYID_USER);
		query.setParameter(1, post.getTxt());
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
