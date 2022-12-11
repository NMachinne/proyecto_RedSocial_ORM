package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import proyecto.RedSocial.proyecto.model.DAO.CommentDAO;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserPostController extends AController{

	@FXML
	private Text comments;

	@FXML
	private ImageView iconComment;

	@FXML
	private ImageView iconLike;
	@FXML
	private AnchorPane postUserId;
	@FXML
	private ImageView imagePost;

	@FXML
	private ImageView imgfollowuser;

	@FXML
	private Text likes;

	@FXML
	private Pane panePost;
	@FXML
	private Text nameFolllowUser;

	@FXML
	private TextArea textComment;

	UserDAO us = new UserDAO();
	CommentDAO cd = new CommentDAO();
	PostDAO pd = new PostDAO();

	/**
	 * Muestra el post seleccionado
	 * @param event
	 */
	@FXML
	void showUserPost(MouseEvent event) {
		String r = null;
		try {
			r = event.getPickResult().getIntersectedNode().toString().split("=")[1];
			r = r.split("]")[0];		
		} catch (Exception e) {
			r = null;
		}
		int num = Integer.parseInt(r);
		if (isPostDel) {
			Post paux = (Post) pd.getById(new Post(num,"","","")).toArray()[0];
			Alert al = new Alert(Alert.AlertType.CONFIRMATION);
			al.setHeaderText("are you SURE you want to DELETE the post? \n" +" with date time  "+ paux.getFecha());
			al.setTitle("DELETE POST");
			Optional<ButtonType> result = al.showAndWait();
			if (result.isEmpty()) {	
			} else if (result.get() == ButtonType.OK) {
				pd.delete(new Post(num, "", "", ""));
				refreshDelete = true;
			} else if (result.get() == ButtonType.CANCEL) {
				
			} 
		} else {
			post = (Post) pd.getById(new Post(num,"","","")).toArray()[0];
		}
		
		
	}

	/**
	 * Prepara y asigna los datos de post y de Acontroller a las variables
	 * @param post
	 */
	public void setData(Post post) {
		try {
			imagePost.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode(post.getMultimedia()))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		textComment.setText(post.getFecha() + "\n\n" + post.getTxt());
		likes.setText(us.getByLikePost(new User(post.getId(), "", "", "")).size() + "");
		comments.setText(cd.getById(new Comment("", "", new User(), new Post(post.getId(), "", "", ""))).size() + "");
		panePost.setId(post.getId() + "");

	}

}
