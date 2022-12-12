package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
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
	private final static String SELECTBYLIKE = "FROM like WHERE id_usuario=?1 AND id_publicacion=?2";
	private final static String SELECTBYLIKEPOST = "FROM like WHERE id_publicacion=?1";
	private final static String SELECTBYFOLLOWBYID = "FROM follow WHERE id=?1 AND id_usuario=?2";
	private final static String SELECTBYUSERPOST = " SELECT u.id,nombre,password,avatar FROM user u JOIN post p WHERE p.id_usuario=?1 AND u.id=?2 ";
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
		query.setParameter(1, user.getNombre());
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

	public Collection<User> getByFollow(User follow) {
		
		Collection<User> u = new ArrayList<User>();
		Query query = manager.createQuery(SELECTBYFOLLOWER);
		query.setParameter(1, follow.getId());
		Collection<Follow> fw = null;
		fw = query.getResultList();
		for (Follow follow2 : fw) {
			User uaux = (User) follow2.getIdUsuario().toArray()[0];
			u.add(new User(follow2.getId(),uaux.getId() +"" , "", null));
		}
		return u;
	}

	public Collection<User> getByFollowed(User follow) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYFOLLOWED);
		query.setParameter(1, follow.getNombre());
		u = query.getResultList();
		return u;
	}

	public Collection<User> getByLike(User like) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYLIKE);
		query.setParameter(1, like.getId());
		query.setParameter(2, like.getNombre());
		return u = query.getResultList();
	}

	public Collection<User> getByLikePost(User like) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYLIKEPOST);
		query.setParameter(1, like.getNombre());
		return u = query.getResultList();
	}

	public Collection<User> getByFollowById(User follow) {
		Collection<User> u = null;
		Query query = manager.createQuery(SELECTBYFOLLOWBYID);
		query.setParameter(1, follow.getId());
		query.setParameter(2, follow.getNombre());
		return u = query.getResultList();
	}
	
	public Collection<User> getByUserPost(User user) {
		Collection<User> u = null;
		
		Query query = manager.createNativeQuery(SELECTBYUSERPOST);
		query.setParameter(1,user.getNombre());
		query.setParameter(2, user.getId());
		return u = query.getResultList();
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
