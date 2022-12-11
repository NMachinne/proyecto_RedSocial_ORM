package proyecto.RedSocial.proyecto;

import proyecto.RedSocial.proyecto.model.Conection.MariaDBDatabase;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;

public class Exe {

	public static void main(String[] args) {
		/*
		 * 	
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
		*/
		System.out.println(new UserDAO().getAll());
	}
	 

}
