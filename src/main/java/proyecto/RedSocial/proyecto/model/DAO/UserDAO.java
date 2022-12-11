package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import proyecto.RedSocial.proyecto.model.Entity.Follow;
import proyecto.RedSocial.proyecto.model.Entity.Like;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYID = "FROM user WHERE id=?1";
	private final static String SELECTBYNAME = "FROM user WHERE nombre=?1";	
	private final static String SELECTBYFOLLOWER = "FROM follow WHERE id=?1";
	private final static String SELECTBYFOLLOWED = "FROM follow WHERE id_usuario=?1";
	private final static String SELECTBYLIKE = "FROM like WHERE id_usuario=?1 AND id_publicacion=?1";
	private final static String SELECTBYLIKEPOST = "FROM like WHERE id_publicacion=?1";
	private final static String SELECTBYFOLLOWBYID = "FROM follow WHERE id=?1 AND id_usuario=?1";
	private final static String SELECTBYUSERPOST = "FROM user u JOIN post p WHERE p.id_usuario=?1 AND u.id=?1 LIMIT 1";
	private final static String SELECTALL = "FROM user";
	// Fin de las consultas

	public UserDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}
	
	public void save(User user) {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
	}

	public void saveLike(Like like) {
		manager.getTransaction().begin();
		manager.persist(like);
		manager.getTransaction().commit();
	}

	public void insertFollow(Follow follow) {
		manager.getTransaction().begin();
		manager.persist(follow);
		manager.getTransaction().commit();
	}

	public Collection<User> getById(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYID);
		query.setParameter(1, user.getId());
		u = query.getResultList();
		return u;
	}
	
	public Collection<User> getByIdUser(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYID);
		query.setParameter(1, user.getId());
		u = query.getResultList();
		return u;
	}


	public Collection<User> getByName(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYNAME).getResultList();
		return u;
	}

	public Collection<User> getByFollow(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYFOLLOWER).getResultList();
		return u;
	}

	public Collection<User> getByFollowed(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYFOLLOWED).getResultList();
		return u;
	}

	public Collection<User> getByLike(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYLIKE).getResultList();
		return u;
	}

	public Collection<User> getByLikePost(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYLIKEPOST).getResultList();
		return u;
	}

	public Collection<User> getByFollowById(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYFOLLOWBYID).getResultList();
		return u;
	}
	
	public Collection<User> getByUserPost(User user) {
		Collection<User> u = null;
		u = manager.createQuery(SELECTBYUSERPOST).getResultList();
		return u;
	}

	public List<User> getAll() {
		List<User> u = null;
		u = manager.createQuery(SELECTALL).getResultList();
		return u;
	}
	 

	public void update(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
	}

	public void delete(User user) {
		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();
	}

	public void deleteLike(Like like) {
		manager.getTransaction().begin();
		manager.remove(like);
		manager.getTransaction().commit();
	}
	
	public void deleteFollow(Follow follow) {
		manager.getTransaction().begin();
		manager.remove(follow);
		manager.getTransaction().commit();
	}

}
