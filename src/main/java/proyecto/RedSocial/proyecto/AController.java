package proyecto.RedSocial.proyecto;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public abstract class AController {

	protected static User login_user;

	protected static Post post;

	protected static User user;
	
	protected static boolean refresh;
	
	protected static boolean refreshDelete;
	
	protected static boolean refreshFollow;
	
	protected static String nameFollow;
	
	protected static boolean isPostDel;
	
}
