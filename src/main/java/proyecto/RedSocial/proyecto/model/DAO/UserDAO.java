package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;


import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYID = "FROM user WHERE id=?1";
	private final static String SELECTBYNAME = "FROM user WHERE nombre=?1";	
	private final static String SELECTBYFOLLOWER = "FROM follow WHERE id=?1";
	private final static String SELECTBYFOLLOWED = "FROM follow WHERE id_usuario=?1";
	private final static String SELECTBYLIKE = "FROM like WHERE id_usuario=?1 AND id_publicacion=?2";
	private final static String SELECTBYLIKEPOST = "FROM like WHERE id_publicacion=?1";
	private final static String SELECTBYFOLLOWBYID = "FROM follow WHERE id=?1 AND id_usuario=?2";
	private final static String SELECTBYUSERPOST = "SELECT u.id,nombre,password,avatar FROM user u JOIN post p WHERE p.id_usuario=?1 AND u.id=?2 LIMIT 1";
	private final static String SELECTALL = "FROM user";
	
	private final static String INSERTLIKE  = "INSERT INTO likes (id_usuario,id_publicacion,fecha) VALUES (?1,?2,?3)";
	private final static String INSERTFOLLOW = "INSERT INTO follow (id,id_usuario) VALUES (?1,?2)";
	private final static String DELETEFOLLOW = "DELETE FROM follow WHERE id=? AND id_usuario=?";
	private final static String DELETELIKE = "DELETE FROM likes WHERE id_usuario=? and id_publicacion=?";
	
	
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

	public void saveLike(User us) {
		
		manager.getTransaction().begin();
		Query query= manager.createNativeQuery(INSERTLIKE);
		query.setParameter(1,us.getId());
		query.setParameter(2,us.getNombre());
		query.setParameter(3,us.getPassword());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	public void insertFollow(User us) {
		manager.getTransaction().begin();
		Query query= manager.createNativeQuery(INSERTFOLLOW);
		query.setParameter(1,us.getId());
		query.setParameter(2,us.getNombre());
		query.executeUpdate();
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
		Query query = manager.createQuery(SELECTBYNAME);
		query.setParameter(1, user.getNombre());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByFollow(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYFOLLOWER);
		query.setParameter(1, user.getId());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByFollowed(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYFOLLOWED);
		query.setParameter(1, user.getId());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByLike(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYLIKE);
		query.setParameter(1, user.getId());
		query.setParameter(2, user.getNombre());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByLikePost(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYLIKEPOST);
		query.setParameter(1, user.getId());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByFollowById(User user) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYFOLLOWBYID);
		query.setParameter(1, user.getId());
		query.setParameter(2, user.getNombre());
		u = query.getResultList();
		return u;
	}
	
	public Collection<User> getByUserPost(User user) {
		Collection<User> u = null;
		Query query = manager.createNativeQuery(SELECTBYUSERPOST);
		query.setParameter(1, user.getId());
		query.setParameter(2, user.getNombre());
		return u;
	}

	public Collection<User> getAll() {
		Collection<User> u = null;
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

	public void deleteLike(User su) {
		manager.getTransaction().begin();
		Query query= manager.createNativeQuery(DELETELIKE);
		query.setParameter(1,su.getId());
		query.setParameter(2,su.getNombre());
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	public void deleteFollow(User su) {
		manager.getTransaction().begin();
		Query query= manager.createNativeQuery(DELETEFOLLOW);
		query.setParameter(1,su.getId());
		query.setParameter(2,su.getNombre());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

}
