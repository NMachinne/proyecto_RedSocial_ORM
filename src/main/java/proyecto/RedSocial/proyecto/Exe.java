package proyecto.RedSocial.proyecto;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

import proyecto.RedSocial.proyecto.model.Conection.MariaDBDatabase;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Post;
//import proyecto.RedSocial.proyecto.model.Entity.Follow;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class Exe {

	public static void main(String[] args) {
		// User user = new User(28,"paco123","",null);
		// User ser = new User(30,"ana1234","",null);
/*
		boolean no = false;
		try {
			if (new UserDAO().getAll() != null) {
				no = true;
			}
		} catch (Exception e) {

		}

		if (!no) {
			// MariaDBDatabase.create();
		}*/
		App.main(args);

		// new UserDAO().saveLike(user, new
		// Post(41,Timestamp.valueOf(LocalDateTime.now().withNano(0)),"",null));
		// new UserDAO().insertFollow(user,ser);

		/*
		 * Collection<User> follow = new UserDAO().getByFollow(user); for (User f :
		 * follow) { System.out.println(f); }
		 */

	}

}
