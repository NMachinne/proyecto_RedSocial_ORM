package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;

public class UserController extends AController implements Initializable, Runnable {

	// variables para el perfil del usuario con el archivo user.fxml

	@FXML
	private Button followuser;

	@FXML
	private Button editPerfil;
	@FXML
	private ToggleButton delPost;
	@FXML
	private Text getNameUser;

	@FXML
	private ImageView imgUser;
	@FXML
	private ImageView imgAdd;

	@FXML
	private Text nFollowed;

	@FXML
	private Text namefollowed;

	@FXML
	private Text namefollower;
	@FXML
	private Text nFollower;

	@FXML
	private Text nPost;
	@FXML
	private Text idnameFollowed;

	@FXML
	private Text idnameFollower;

	@FXML
	private GridPane postGrid;
	@FXML
	private ScrollPane userPosts;

	private static int action;

	private static UserController u;
	PostDAO pd = new PostDAO();
	UserDAO ud = new UserDAO();

	/**
	 * permite seguir al usuario que se muestra
	 * 
	 * @param event
	 */
	@FXML
	void follow(ActionEvent event) {
		boolean follow = false;
		try {
			if (new UserDAO().getByFollowById(new User(login_user.getId(), user.getId() + "", "", null))
					.toArray()[0] != null)
				;
			follow = true;
		} catch (Exception e) {
			follow = false;
		}
		if (follow) {
			ud.deleteFollow(new User(login_user.getId(), user.getId() + "", "", null));
		} else {
			ud.insertFollow(new User(login_user.getId(), user.getId() + "", "", null));
		}

	}

	/**
	 * accede a la seccion de seguidos
	 * 
	 * @param event
	 */
	@FXML
	void openFollowed(MouseEvent event) {

		try {
			nameFollow = idnameFollowed.getText();
			App.setRoot("follow-ed");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * accede a la seccion de seguidores
	 * 
	 * @param event
	 */
	@FXML
	void openFollower(MouseEvent event) {

		try {
			nameFollow = idnameFollower.getText();
			App.setRoot("follow-ed");
		} catch (IOException e) {

		}

	}

	/**
	 * vuelve al menu principal del usuario
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void backtoMenu(MouseEvent event) throws IOException {
		try {
			User paux = (User) new UserDAO().getByUserPost(new User(user.getId(), user.getId() + "", "", null))
					.toArray()[0];
			user = paux;
			// user = null;post = null;

		} catch (Exception e) {

		}
		App.setRoot("post");
	}

	/**
	 * permite volver al perfil del usuario
	 * 
	 * @param event
	 */
	@FXML
	void backtoPerfil(MouseEvent event) {
		try {
			App.setRoot("user");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edita el perfil del propio usuario
	 * 
	 * @param event
	 */
	@FXML
	void perfilEdit(ActionEvent event) {

		if (!delPost.isVisible()) {
			delPost.setVisible(true);
			imgAdd.setVisible(true);
			delPost.setDisable(false);
			imgAdd.setDisable(false);
		} else {
			delPost.setVisible(false);
			imgAdd.setVisible(false);
			delPost.setDisable(true);
			imgAdd.setDisable(true);
		}

	}

	/**
	 * Seleciona un objeto dentro
	 * 
	 * @param event
	 */
	@FXML
	void selectedPostDeleted(ActionEvent event) {
		isPostDel = delPost.isSelected();
		action = 2;
		Thread t = new Thread(u);
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Cambia la imagen del Avatar del usuario
	 * 
	 * @param event
	 */
	@FXML
	void imageChange(MouseEvent event) {
		User u = new User();
		UserDAO ud = new UserDAO();
		EscribePostController epc = new EscribePostController();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Se permite png, jpg y bmp", "*.png", "*.jpg", "*.bmp"));
		File f = fc.showOpenDialog(null);

		String imagen = null;
		File fl = f.getAbsoluteFile();
		imagen = epc.encodeFileToBase64(fl);
		byte[] byteData = imagen.getBytes();

		Blob docInBlob = null;
		try {
			docInBlob = new SerialBlob(byteData);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ud.update(new User(login_user.getId(), login_user.getNombre(), login_user.getPassword(), docInBlob));
		
		
		u.setAvatar(docInBlob);

		user = (User) ud.getById(login_user).toArray()[0];
		login_user = (User) ud.getById(login_user).toArray()[0];

		try {
			App.setRoot("user");
		} catch (IOException e) {
		}

	}

	/**
	 * carga una coleccion de post de un usuario con su contenido
	 */
	public void loadUserPost() {
		Collection<Post> posts = new PostDAO().getAll();
		//Collection<Post> posts = new PostDAO().getAllByIdUser(new Post(user.getId(), null, "", null));
		int columns = 0;
		int rows = 1;
		try {
			for (int i = 0; i < posts.size(); i++) {
				FXMLLoader fxmloader = new FXMLLoader();
				fxmloader.setLocation(getClass().getResource("userPost.fxml"));
				AnchorPane apane = fxmloader.load();
				UserPostController upc = fxmloader.getController();
				upc.setData((Post) posts.toArray()[i]);
				if (columns == 2) {
					columns = 0;
					++rows;
				}
				postGrid.add(apane, columns++, rows);

				GridPane.setMargin(apane, new Insets(3));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		if (login_user.getId() == user.getId()) {
			editPerfil.toFront();
			editPerfil.setVisible(true);
		} else {
			followuser.toFront();
			followuser.setVisible(true);
		}
		action = 0;
		u = this;
		try {
			getNameUser.setText(user.getNombre());
} catch (Exception e) {}	
		try {
			nPost.setText(pd.getAllByIdUser(new Post(user.getId(), null, "", null)).size() + "");
		} catch (Exception e) {}	
			try {
			nFollowed.setText(ud.getByFollow(user).size() + "");
			} catch (Exception e) {}	
			try {
			nFollower.setText(ud.getByFollowed(user).size() + "");
			} catch (Exception e) {}	
			try {
			pd.getByIdUser(new Post(0,null ,user.getId() + "", null));
			} catch (Exception e) {}	
			try {
			loadUserPost();
		} catch (Exception e) {
			
		}	
		
		Platform.runLater(new Runnable() {
			
			public void run() {
				Thread t = new Thread(u);
				t.setDaemon(true);
				t.start();
			}
		});
	}

	
	public void run() {
		User uvar = user;
		if (action == 0) {
			try {
				while (true) {
					if (user != null) {
						try {
							Thread.sleep(1000);
							try {
								nPost.setText(pd.getAllByIdUser(new Post(uvar.getId(), null, "", null)).size() + "");
								nFollowed.setText(ud.getByFollow(uvar).size() + "");
								nFollower.setText(ud.getByFollowed(uvar).size() + "");
								imgUser.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode( post.getMultimedia().getBytes(0, (int) post.getMultimedia().length())))));
							} catch (Exception e) {

							}
							boolean follow = false;
							try {
								if (new UserDAO()
										.getByFollowById(new User(login_user.getId(), user.getId() + "", "", null))
										.toArray()[0] != null);
								follow = true;
							} catch (Exception e) {
								follow = false;
							}
							if (follow) {
								//followuser.setText("SIGUIENDO");
							} else {
								//followuser.setText("SEGUIR");
							}
							try {
								if (post != null) {
									try {
										App.setRoot("post");
										return;
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							Thread.sleep(1000);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action == 1) {
			while (true) {
				try {
					if (post != null) {
						try {
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
		} else if (action == 2) {
			while (true) {
				try {
					if (refreshDelete) {
						try {
							refreshDelete = false;
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
		}
	}
}
