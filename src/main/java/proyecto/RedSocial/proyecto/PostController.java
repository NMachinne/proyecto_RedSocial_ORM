package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyecto.RedSocial.proyecto.model.DAO.CommentDAO;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class PostController extends AController implements Initializable, Runnable {

	@FXML
	private Text likes;

	@FXML
	private ImageView post_imagen;

	@FXML
	private CheckBox post_like;

	@FXML
	private TextArea post_texto;

	@FXML
	private TextArea comment_texto;

	private static int action;

	private static PostController p;

	/**
	 * Muestra el escribePost.fxml
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void banadir(ActionEvent event) throws IOException {
		App.setRoot("EscribePost");
	}

	/**
	 * Muestra el commentPost.fxml
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void userComment(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("commentPost.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage1 = new Stage();
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle("Comment");
		stage1.setScene(new Scene(root1));
		stage1.show();
		action = 2;
		Thread t = new Thread(p);
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Muestra el search.fxml
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void bbuscar(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage1 = new Stage();
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle("Buscador");
		stage1.setScene(new Scene(root1));
		stage1.show();
		action = 1;
		Thread t = new Thread(p);
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Añade o Elimina el like al post
	 * 
	 * @param event Objeto ActionEvent
	 */
	@FXML
	void blike(ActionEvent event) {
		Timestamp locdate = Timestamp.valueOf(LocalDateTime.now().withNano(0));
		boolean isLike = false;
		try {
			if (new UserDAO().getByLike(new User(login_user.getId(), post.getId() + "", "", null)).toArray()[0] != null)
				;
			isLike = true;
		} catch (Exception e) {
			isLike = false;
		}
		if (isLike) {
			new UserDAO().deleteLike(new User(login_user.getId(), post.getId()+"", null,null));
		} else {
			new UserDAO().saveLike(new User(login_user.getId(), post.getId()+"", locdate.toString(),null));
		}
	}

	/**
	 * Muestra el perfil del usuario
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void busuario(ActionEvent event) throws IOException {
		AController.user = AController.login_user;
		AController.post = null;
		App.setRoot("user");
	}

	/**
	 * Inicializa el PostController
	 */
	
	public void initialize(URL location, ResourceBundle resources) {
		post_texto.setText("Cargando Post...");
		comment_texto.setText("Cargando Comentarios...");
		action = 0;
		post = null;
		p = this;

		Platform.runLater(new Runnable() {
			
			public void run() {
				Thread t = new Thread(p);
				t.setDaemon(true);
				t.start();
			}
		});
	}

	/**
	 * Metodo run que inicia el Objeto Thread
	 */
	
	public void run() {
		if (action == 0) {
			while (true) {
				try {
					if (post != null) {
						Thread.sleep(1000);
						try {
							post_imagen.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode( post.getMultimedia().getBytes(0, (int) post.getMultimedia().length())))));
						} catch (Exception e) {
						}
						post_texto.setText("Fecha: " + post.getFecha() + "\nUsuario: " + user.getNombre() + "\n\n"
								+ post.getTxt());
						Collection<Comment> comment2 = null;
						comment2 = new CommentDAO()
								.getById(new Comment(null, "", new User(), new Post(post.getId(), null, "", null)));
						if (comment2.size() >= 1) {
							String comments = "";
							for (Comment c : comment2) {

								User user_comment = (User) new UserDAO()
										.getById(new User(c.getUser().getId(), null, null, null)).toArray()[0];
								comments += "Fecha: " + c.getFecha() + "\nUsuario: " + user_comment.getNombre()
										+ "\n\n-" + c.getTxt() + "\n\n";
							}
							comment_texto.setText(comments);
						} else {
							comment_texto.setText("Sin Comentarios");
						}
						boolean isLike = false;
						try {
							if (new UserDAO().getByLike(new User(login_user.getId(), post.getId() + "", "", null))
									.toArray()[0] != null)
								;
							isLike = true;
						} catch (Exception e) {
							isLike = false;
						}
						post_like.setSelected(isLike);
						while (true) {
							try {
								likes.setText(new UserDAO().getByLikePost(new User(post.getId(), "", "", null)).size()
										+ " Likes");
								Thread.sleep(1000);
							} catch (Exception e) {
								return;
							}
						}
					} else {
						Thread.sleep(1000);
						Collection<User> follow = new UserDAO().getByFollow(login_user);
						int l = 0;
						User user2 = null;
						Post post2 = null;
						for (User f : follow) {
							Collection<Post> k = new PostDAO().getByIdUser(new Post(0, null, f.getNombre(), null));
							if (k.size() > 0) {
								post2 = (Post) new PostDAO().getByIdUser(new Post(0, null, f.getNombre(), null))
										.toArray()[0];
								int iaux = Integer.parseInt(f.getNombre());
								user2 = (User) new UserDAO().getByUserPost(new User(iaux, iaux + "", "", null))
										.toArray()[0];
							}
							if (post2 != null && l < post2.getId()) {
								l = post2.getId();
							}
						}
						try {
						for (Post post3 : new PostDAO().getAll()) {
							if (post3.getMultimedia() != null) {
								post = post3;
							}
						}
						}
						catch (Exception e) {}
						user = login_user;
						if (post != null) {
							Thread.sleep(1000);
							try {
								post_imagen.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode( post.getMultimedia().getBytes(0, (int) post.getMultimedia().length())))));
							} catch (Exception e) {
							}
							post_texto.setText("Fecha: " + post.getFecha() + "\nUsuario: " + user.getNombre() + "\n\n"
									+ post.getTxt());
							Collection<Post> comment2 = null;
							comment2 = new PostDAO().getAll();
							//comment2 = new CommentDAO().getById(new Comment(null, "", new User(), new Post(post.getId(), null, "", null)));
							if (comment2.size() >= 2) {
								String comments = "";
								int co = 0;
								for (Post c : comment2) {
									if (co >= 1 && c.getMultimedia() == null) {
									comments += "Fecha: " + c.getFecha() + "\n"+
											 "\n" + c.getTxt() + "\n\n";
									}
									co++;
								}
								comment_texto.setText(comments);
							} else {
								comment_texto.setText("Sin Comentarios");
							}
							boolean isLike = false;
							int li = 0;
							try {
								if (new UserDAO().getByLike(new User(login_user.getId(), post.getId() + "", "", null))
										.toArray()[0] != null)
									;
								isLike = true;
								li = 1;
							} catch (Exception e) {
								isLike = false;
							}
							post_like.setSelected(isLike);
							while (true) {
								try {
									if (post_like.isSelected()) {
										li = 1;
									}
									else {
										li = 0;
									}
									likes.setText(li
											+ " Likes");
									Thread.sleep(1000);
								} catch (Exception e) {
									return;
								}
							}
						} else {
							post_texto.setText("Sin Post");
							comment_texto.setText("Sin Comentarios");
							return;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (action == 1) {
			user = null;
			while (true) {
				try {

					if (user != null) {
						try {
							AController.post = null;
							App.setRoot("user");
							return;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (action == 2) {
			while (true) {
				try {
					if (refresh) {
						try {
							refresh = false;
							App.setRoot("post");
							return;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
