package proyecto.RedSocial.proyecto;

import java.sql.Blob;

import proyecto.RedSocial.proyecto.model.Conection.MariaDBDatabase;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Follow;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class Exe {
	
	public static void main(String[] args) {
		User user = new User();
		
		boolean no = false;
		try {
			if (new UserDAO().getAll() != null) {
				no = true;
			}
		} catch (Exception e) {
			
		}

		if (!no) {
			MariaDBDatabase.create();
		}
		App.main(args);
		
		
	}
	 

}
