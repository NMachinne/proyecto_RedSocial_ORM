package proyecto.RedSocial.proyecto;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
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
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;
import proyecto.RedSocial.proyecto.model.DAO.CommentDAO;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;

public class CommentPostController extends AController {

	@FXML
	private TextArea textComment;

	CommentDAO cd = new CommentDAO();
	PostDAO pd = new PostDAO();
	UserDAO ud = new UserDAO();

	@FXML
	void sendComment(ActionEvent event) {

		//cd.save(new Comment(Timestamp.valueOf(LocalDateTime.now().withNano(0)), textComment.getText(),
		//		new User(login_user.getId(), "", "", null), new Post(post.getId(), null, "", null)));
		pd.save(new Post(0,Timestamp.valueOf(LocalDateTime.now().withNano(0)), textComment.getText(), null, login_user));
		refresh = true;
		// user = login_user;
		textComment.getScene().getWindow().hide();
	}
}