package proyecto.RedSocial.proyecto.model.DAO;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.Query;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserDAO extends ADAO {
	// Las consultas MariaDB de este DAO
	private final static String SELECTBYUSERPOST = "SELECT u.id,nombre,password,avatar FROM user u JOIN post p WHERE p.id_usuario=?1 AND u.id=?2 LIMIT 1";
	private final static String SELECTALL = "FROM user";
	// Fin de las consultas

	public UserDAO() {
		emf = Persistence.createEntityManagerFactory("mariadb");
		manager = emf.createEntityManager();
	}
	
	public void save(User user) {
		manager.persist(user);
		manager.close();
	}

	public void saveLike(User user) {
		this.save(user);
	}

	public void insertFollow(User id, User id_usuario) {
    	id = manager.find(User.class, id.getId());
    	id_usuario = manager.find(User.class, id_usuario.getId());
    	manager.getTransaction().begin();
    	id.getFollowed().size();
    	id_usuario.getFollower().size();
    	id.addFollowed(id_usuario);
        manager.persist(id);
        manager.getTransaction().commit();
        manager.close();
	} 

	public Collection<User> getById(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		u.add(uaux);
		return u;
	}
	
	public Collection<User> getByIdUser(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		u.add(uaux);
		return u;
	}

	public Collection<User> getByName(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.merge(user);
		u.add(uaux);
		return u;
	}

	public Collection<User> getByFollow(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		try {
		u = uaux.getFollower();
		}
		catch (NullPointerException e) {}
		return u;
	}

	public Collection<User> getByFollowed(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		try {
		u = uaux.getFollowed();
		}
		catch (NullPointerException e) {}
		return u;
	}

	public Collection<User> getByLike(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		for (User like: uaux.getUserLikes()) {
			if (!user.getNombre().toString().equals(uaux.getId()+"")) {
				u.add(like);
			}
		}
		u = uaux.getUserLikes();
		return u;
	}

	public Collection<User> getByLikePost(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		u = uaux.getUserLikes();
		return u;
	}

	public Collection<User> getByFollowById(User user) {
		Collection<User> u = new ArrayList<User>();
		User uaux = manager.find(User.class, user.getId());
		u.add(uaux);
		return u;
	}
	
	public Collection<User> getByUserPost(User user) {
		Collection<User> u = null;
		Query query = manager.createNativeQuery(SELECTBYUSERPOST);
		query.setParameter(1, user.getId());
		query.setParameter(2, user.getNombre());
		return query.getResultList();
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
		manager.merge(user);
		manager.remove(user);
		manager.getTransaction().commit();
	}

	public void deleteLike(User user) {
		List<User> u = new ArrayList<User>();
		manager.merge(user);
		for (User like: user.getUserLikes()) {
			if (like.getId() != user.getId()) {
				u.add(like);
			}
			else {
				manager.remove(like);
			}
		}
		user.setUserLikes(u);
	}
	
	public void deleteFollow(User id, User id_usuario) {
    	id = manager.find(User.class, id.getId());
    	id_usuario = manager.find(User.class, id_usuario.getId());
    	manager.getTransaction().begin();
    	id.getFollowed().size();
    	id_usuario.getFollower().size();
    	id.deleteFollowed(id_usuario);
        manager.persist(id);
        manager.getTransaction().commit();
        manager.close();
    	
	}

}
