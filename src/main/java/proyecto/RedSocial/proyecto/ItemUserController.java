package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyecto.RedSocial.proyecto.model.Entity.User;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;

public class ItemUserController extends AController {

	// variables de seguidores con el archivo itemUser.fxml
	@FXML
	private Button btnUnfollow;

	@FXML
	private ImageView imgfollowuser;

	@FXML
	private Text nameFolllowUser;

	UserDAO ud = new UserDAO();

	/**
	 * permite dejar de seguir a un usuario
	 * 
	 * @param event
	 */
	@FXML
	void unfollowUser(ActionEvent event) {
		User uaux = (User) ud.getByName(new User(0, nameFolllowUser.getText(), "", "")).toArray()[0];
		ud.deleteFollow(new User(login_user.getId(), uaux.getId() + "", "", ""));
		ud.deleteFollow(new User(uaux.getId(), login_user.getId() + "", "", ""));
		refreshFollow = true;
	}

	/**
	 * Prepara y asigna los datos de user y de Acontroller a las variables
	 * 
	 * @param user
	 */
	public void setData(User user) {
		try {
			Image img = new Image(new ByteArrayInputStream(Base64.getDecoder().decode(user.getAvatar())));
			imgfollowuser.setImage(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		nameFolllowUser.setText(user.getNombre());
	}

}
